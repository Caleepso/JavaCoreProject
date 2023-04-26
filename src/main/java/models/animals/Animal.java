package models.animals;
import models.entities.Cell;
import models.entities.Island;
import models.entities.Entity;
import services.UtilsService;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

public abstract class Animal extends Entity {
    protected final static String menu = "src/main/java/config/eatingMap.json";
    protected final static String animalAttrsFile = "src/main/java/config/animalAttrs.json";
    protected static int count = 0;
    protected boolean isCoupled = false;
    protected int id;
    protected Map<String, Integer> eatingMap;
    protected boolean huntedToday = false;
    protected int starvingDays = 0;
    protected double foodVolume;
    protected int moveSpeed;
    public int getMoveSpeed() {
        return moveSpeed;
    }
    public void setStarvingDays(int starvingDays) {
        this.starvingDays = starvingDays;
    }
    public int getStarvingDays() {
        return starvingDays;
    }
    public void setCoupled(boolean coupled) {
        isCoupled = coupled;
    }
    public boolean isCoupled() {
        return isCoupled;
    }
    public void setHuntedToday(boolean huntedToday) {this.huntedToday = huntedToday;}
    public boolean isHuntedToday() {return huntedToday;}


    public Animal(int xPoint, int yPoint, String type) {
        super(xPoint, yPoint);
        Animal.count++;
        this.id = Animal.count;
        this.type = type;
        this.eatingMap = UtilsService.readJson(menu, this.type);
        this.weight = Double.parseDouble(UtilsService.getJsonAttr(animalAttrsFile, type,"weight"));
        this.foodVolume = Double.parseDouble(UtilsService.getJsonAttr(animalAttrsFile, type,"foodVolume"));
        this.moveSpeed = Integer.parseInt(UtilsService.getJsonAttr(animalAttrsFile, type,"moveSpeed"));
    }

    public Entity move(int moveS, Island island){
        int xNew = this.xPoint;
        int yNew = this.yPoint;
        Cell curCell = island.getField()[xPoint][yPoint];
        Cell moveToCell;
        int move = UtilsService.getRandomInt(0, 4);
        // 0-направо, 1-вниз, 2-налево, 3-вверх
        ArrayList<String> possiblePos = UtilsService.getIslandArray(island);

        for (int i = 0; i < moveS; i++) {
            if (move == 0)  yNew += 1;
            else if (move == 1) xNew += 1;
            else if (move == 2) yNew -=1;
            else xNew -=1;

            String checkStr = Integer.toString(xNew) + Integer.toString(yNew);
            List<String> matches = possiblePos.stream().filter(it -> it.equals(checkStr)).collect(Collectors.toList());
            if (matches.size()<1) {
                break;
            }
            else {
                this.setxPoint(xNew);
                this.setyPoint(yNew);
                moveToCell = island.getField()[xNew][yNew];
                moveToCell.getBioSphere().get(this.getType()).add(this);

                return this;
            }
        }
        return null;
    }

    public Entity multiply(Cell myCell) {
        Entity newBorn = null;
        String entityType = this.getClass().getSimpleName();
        int entityCount = myCell.entityCount(entityType);
        ArrayList<Entity> entities = myCell.getBioSphere().get(entityType);
        // проверить, что в клетке не максимальное количество вида
        if (entityCount < Cell.maxBioSphere.get(entityType)) {
            // проверить наличие потенциальной пары на этой итерации
            if (!isCoupled && entityCount > 1) {
                for (Entity entity: entities) {
                    if (entity instanceof Animal) {
                        Animal curAnimal = (Animal) entity;
                        if (this.id != curAnimal.id && !curAnimal.isCoupled()){
                            curAnimal.setCoupled(true);
                            this.setCoupled(true);
                            newBorn = haveBaby(myCell);
                            break;
                        }
                    }
                }
            }
        }
        return newBorn;
    }

    public void eat (Cell mycell){
       // Set<String> cellAnimals = mycell.bioSphere.keySet();
        Map<String, ArrayList<Entity>> cellAnimals = mycell.getBioSphere().entrySet().stream()
                .filter(a->a.getValue().size()>0).collect(Collectors.toMap(e-> e.getKey(), e-> e.getValue()));

        Set<String> myMenu = this.eatingMap.keySet();
        Set<String> filteredAnimals = myMenu.stream()
                .flatMap(n -> cellAnimals.keySet().stream().filter(p -> n.equals(p)))
                .collect(Collectors.toCollection(HashSet::new));
        if (filteredAnimals.size() > 0) {
            HashMap<String, Integer> todaysMenu = new HashMap<String, Integer>();
            for (String s: filteredAnimals) {
                todaysMenu.put(s,this.eatingMap.get(s));
            }
            Map<String, Integer> sortedMenu = todaysMenu.entrySet().stream()
                    .sorted(Comparator.comparingInt(e -> -e.getValue()))
                    .collect(Collectors.toMap(
                            Map.Entry::getKey,
                            Map.Entry::getValue,
                            (a, b) -> { throw new AssertionError(); },
                            LinkedHashMap::new
                    ));
            int destiny;
            double foodEaten = 0;
            String eatable;
            for (int i = 0; i < 3; i++) { // пытаемся кушать 3 раза в день
                if (!sortedMenu.isEmpty()) {
                    eatable = sortedMenu.keySet().stream().findFirst().get();
                    destiny = ThreadLocalRandom.current().nextInt(0, 2); // повезло поесть или не повезло
                    if (destiny == 1) {
                        foodEaten += successHunting(eatable, mycell);
                        sortedMenu.remove(eatable);
                    }
                }
            }
            if (foodEaten < this.foodVolume) this.starvingDays++;
            else this.setStarvingDays(0);
        }
        else this.starvingDays++;
    }

    public double successHunting (String prey, Cell cell) {
        ArrayList<Entity> entityList = cell.getBioSphere().get(prey);
        double weight = 0;
        if (!entityList.isEmpty()) {
            weight = entityList.get(0).getWeight();
            entityList.remove(0);
        }
        return weight;
    }

    public abstract Entity haveBaby(Cell myCell);
}
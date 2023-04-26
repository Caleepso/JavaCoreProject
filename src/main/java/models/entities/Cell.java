package models.entities;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import models.animals.Animal;
import models.animals.AnimalFactory;
import services.UtilsService;

public class Cell {
    public final static Map<String, Integer> maxBioSphere =
            UtilsService.readJson("src/main/java/config/biomax.json",null);
    private HashMap<String, ArrayList<Entity>> bioSphere = new HashMap<String, ArrayList<Entity>>();
    private int rowNum;
    private int colNum;

    public Cell(int rowNum, int colNum) {
        this.rowNum = rowNum;
        this.colNum = colNum;
    }
    public Map<String, Integer> generateLife(){
        Map<String, Integer> generatedCellAnimals = new HashMap<>();
        System.out.println(" ➔ Генерация жизни в клетке " + rowNum +"-"+ colNum);
        for (Map.Entry<String, Integer> entry : maxBioSphere.entrySet()) {
            String key = entry.getKey();
            Integer value = entry.getValue();
            int entityAmount = UtilsService.getRandomInt(0,value);
            ArrayList<Entity> entityArray = new ArrayList<>();
            if (key != "Plant") {
                AnimalFactory factory = new AnimalFactory();
                for (int i = 0; i < entityAmount; i++) {
                    entityArray.add(factory.createAnimal(key, rowNum, colNum));
                }
            } else {
                for (int i = 0; i < entityAmount; i++) {
                    entityArray.add(new Plant(rowNum, colNum));
                }
            }
            bioSphere.put(key, entityArray);
            generatedCellAnimals.put(key, entityArray.size());
        }
        return generatedCellAnimals;
    }
    public Map<String, Integer> proceedLife(Island island){
        Map<String, Integer> cellNewBorns = new HashMap<>();
        System.out.println(" ➔ Продолжение жизни для клетки " + rowNum +"-"+ colNum);
        for (Map.Entry<String, ArrayList<Entity>> entry : bioSphere.entrySet()) {
            String key = entry.getKey();
            ArrayList<Entity> array = entry.getValue();
            ArrayList<Entity> newBorns = new ArrayList<>();
            Iterator it = array.iterator();
            while(it.hasNext()){
                Entity entity = (Entity)it.next();
                Entity newBorn = null;
                if (entity instanceof Animal) {
                    Animal animal = (Animal) entity;
                    // кушаем
                    if (!animal.isHuntedToday()) {
                        animal.eat(this);
                        animal.setHuntedToday(true);
                    }
                    // множимся
                    newBorn = animal.multiply(this);
                    // двигаемся
                    Entity animalToRemove = animal.move(animal.getMoveSpeed(), island);
                    if (animalToRemove!=null) it.remove();
                }
                if (newBorn != null) newBorns.add(newBorn);
            }
            // после того, как прошлись по всем животным в массиве, добавляем новорожденных в стаю
            if (!newBorns.isEmpty()) {
                array.addAll(newBorns);
            }
            if(!key.equals("Plant")) {
                cellNewBorns.put(key, newBorns.size());
            }
        }
        return cellNewBorns;
    }

    public Map<String, Integer> killStarving() {
        Map<String, Integer> deaths = new HashMap<>();
        int countDeaths = 0;
        for (var entry: this.bioSphere.entrySet()) {
            ArrayList<Entity> animalArray = entry.getValue();
            String enityType = entry.getKey();
            if (!enityType.equals("Plant")) {
                Iterator<Entity> iter = animalArray.iterator();
                countDeaths = 0;
                while (iter.hasNext()) {
                    Entity animal = iter.next();
                    Animal an = (Animal) animal;
                    if (an.getStarvingDays() > 3) {
                        iter.remove();
                        countDeaths++;
                    }
                }
                deaths.put(enityType, countDeaths);
            }
        }
        return deaths;
    }

    public int getRowNum() {return rowNum;}
    public int getColNum() {return colNum;}
    public HashMap<String, ArrayList<Entity>> getBioSphere() {return bioSphere;}
    public int entityCount(String animalType) {
        ArrayList<Entity> array = bioSphere.get(animalType);
        if (!array.isEmpty()) return array.size();
        else return 0;
    }
    @Override
    public String toString() {
        return "Cell{" +
                "bioSphere=" + bioSphere +
                '}';
    }
}

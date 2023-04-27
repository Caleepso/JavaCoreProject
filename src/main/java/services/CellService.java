package services;

import models.animals.Animal;
import models.animals.AnimalFactory;
import models.entities.Cell;
import models.entities.Entity;
import models.entities.Island;
import models.entities.Plant;

import java.util.*;

public class CellService {
    private final Cell cell;

    public CellService(Cell cell) {
        this.cell = cell;
    }


    public Map<String, Integer> generateLife(){
        Map<String, Integer> generatedCellAnimals = new HashMap<>();
        System.out.println(" ➔ Генерация жизни в клетке " + cell.getRowNum() +"-"+ cell.getColNum());
        for (Map.Entry<String, Integer> entry : Cell.maxBioSphere.entrySet()) {
            String key = entry.getKey();
            Integer value = entry.getValue();
            int entityAmount = Utils.getRandomInt(0,value);
            ArrayList<Entity> entityArray = new ArrayList<>();
            if (!key.equals("Plant")) {
                AnimalFactory factory = new AnimalFactory();
                for (int i = 0; i < entityAmount; i++) {
                    entityArray.add(factory.createAnimal(key, cell.getRowNum(), cell.getColNum()));
                }
            } else {
                for (int i = 0; i < entityAmount; i++) {
                    entityArray.add(new Plant(cell.getRowNum(), cell.getColNum()));
                }
            }
            cell.getBioSphere().put(key, entityArray);
            generatedCellAnimals.put(key, entityArray.size());
        }
        return generatedCellAnimals;
    }
    public Map<String, Integer> proceedLife(Island island){
        Map<String, Integer> cellNewBorns = new HashMap<>();
        System.out.println(" ➔ Продолжение жизни для клетки " + cell.getRowNum() +"-"+ cell.getColNum());
        for (Map.Entry<String, ArrayList<Entity>> entry : cell.getBioSphere().entrySet()) {
            String key = entry.getKey();
            ArrayList<Entity> array = entry.getValue();
            ArrayList<Entity> newBorns = new ArrayList<>();
            Iterator<Entity> it = array.iterator();
            while(it.hasNext()){
                Entity entity = it.next();
                Entity newBorn = null;
                if (entity instanceof Animal) {
                    Animal animal = (Animal) entity;
                    // кушаем
                    if (!animal.isHuntedToday()) {
                        animal.eat(cell);
                        animal.setHuntedToday(true);
                    }
                    // множимся
                    newBorn = animal.multiply(cell);
                    // двигаемся
                    Entity animalToRemove = animal.move(animal.getMoveSpeed(), island);
                    if (animalToRemove!=null) {
                       it.remove();
                    }
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
        int countDeaths;
        for (var entry: cell.getBioSphere().entrySet()) {
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
}

package services;

import models.animals.Animal;
import models.entities.Cell;
import models.entities.Entity;
import models.entities.Island;
import models.entities.Plant;
import java.util.HashMap;
import java.util.Map;

public class IslandService {
    private static Island island;


    public static void progress(Island island)  {
        IslandService.island = island;
        startNewDay(island.getDays());
    }

    private static void startNewDay(int days) {
        while (true) {
            Map<String, Integer> generatedCellAnimals ;
            Map<String, Integer> generatedIslandAnimals = new HashMap<>();
            Map<String, Integer> cellNewBorns;
            Map<String, Integer> islandNewBorns = new HashMap<>();
            Map<String, Integer> cellDeaths;
            Map<String, Integer> islandDeaths = new HashMap<>();
            Map<String, Integer> islandAnimals ;
            refreshIsland();
            if (days == 0) {
                for (Cell[] rowCell : island.getField()) {
                    for (Cell cell : rowCell) {
                        generatedCellAnimals = cell.generateLife();
                        generatedIslandAnimals = UtilsService.mergeMaps(generatedCellAnimals, generatedIslandAnimals);
                    }
                }
                getStats(generatedIslandAnimals, null, null, null);
            }
            islandAnimals = UtilsService.getAnimalCount(island);
            for (Cell[] rowCell : island.getField()) {
                for (Cell cell : rowCell) {
                    cellNewBorns = cell.proceedLife(island);
                    islandNewBorns = UtilsService.mergeMaps(cellNewBorns, islandNewBorns);
                    cellDeaths = cell.killStarving();
                    islandDeaths = UtilsService.mergeMaps(cellDeaths, islandDeaths);
                }
            }
            getStats(null, islandNewBorns, islandDeaths, islandAnimals);
            island.setDays(++days);
            UtilsService.proceed();
        }
    }

    private static void refreshIsland(){
        for (Cell[] rowCell : island.getField()) {
            for (Cell cell : rowCell) {
                // рефрешим животных: признак спаренности и проведенной охоты
                for (var entry : cell.getBioSphere().entrySet()) {
                    for (Entity entity: entry.getValue()){
                        if (entity instanceof Animal) {
                            Animal animal = (Animal) entity;
                            animal.setCoupled(false);
                            animal.setHuntedToday(false);
                        }
                    }
                }
                // добавляем в каждую клетку рандомное количество растений до максимума (кроме первого дня)
                if (island.getDays() != 0) {
                    int entityAmount = UtilsService.getRandomInt(0, Cell.maxBioSphere.get("Plant"));
                    int delta = entityAmount - cell.getBioSphere().get("Plant").size();
                    for (int i = 0; i < delta; i++) {
                        cell.getBioSphere().get("Plant").add(new Plant(cell.getRowNum(), cell.getColNum()));
                    }
                }
            }
        }
    }

    public static void getStats(Map<String, Integer> generatedIslandAnimals,
                                Map<String, Integer> islandNewBorns,
                                Map<String, Integer> islandDeaths,
                                Map<String, Integer> islandAnimals){

        if (generatedIslandAnimals != null) {
            System.out.println(" ➔ Сгенерировано существ: ");
            System.out.println(generatedIslandAnimals);
        }
        if (islandAnimals != null) {
            System.out.println("---------------------------------------");
            System.out.println("Состояние острова по итогам " + (island.getDays()+1) + " итерации:");
            System.out.println("---------------------------------------");
            System.out.println(" ➔ Количество существ на начало итерации: ");
            System.out.println(islandAnimals);
        }
        if (islandNewBorns != null) {
            System.out.println(" ➔ Родилось существ: ");
            System.out.println(islandNewBorns);
        }
        if (islandDeaths != null) {
            System.out.println(" ➔ Погибло от голода существ: ");
            System.out.println(islandDeaths);
        }
    }
}

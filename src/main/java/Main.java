import models.entities.Island;
import services.IslandService;

public class Main {
    public static void main(String[] args) {
        Island island = Island.initialize();
        IslandService islandService = new IslandService(island);
        islandService.startNewDay(island.getDays());
    }
}

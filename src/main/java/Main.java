import models.entities.Island;
import services.IslandService;

import java.lang.reflect.InvocationTargetException;

public class Main {
    public static void main(String[] args) throws InvocationTargetException, NoSuchMethodException, IllegalAccessException, ClassNotFoundException {
        Island island = Island.initialize();
        IslandService.progress(island);
    }
}
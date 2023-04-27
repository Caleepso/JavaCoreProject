package models.entities;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import services.Utils;

public class Cell {
    public final static Map<String, Integer> maxBioSphere =
            Utils.readJson("src/main/java/config/biomax.json",null);
    private final HashMap<String, ArrayList<Entity>> bioSphere = new HashMap<>();
    private final int rowNum;
    private final int colNum;

    public Cell(int rowNum, int colNum) {
        this.rowNum = rowNum;
        this.colNum = colNum;
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

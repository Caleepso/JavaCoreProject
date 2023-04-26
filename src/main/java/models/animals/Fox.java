package models.animals;

import models.entities.Cell;
import models.entities.Entity;
import services.UtilsService;

import java.util.Map;

public class Fox extends Animal{

    public Fox(int xPoint, int yPoint, String type) {
        super(xPoint, yPoint, type);
    }

    @Override
    public String toString() {
        return "Fox{" +
                "id=" + id +
                " isCoupled=" + isCoupled +
                " starvingDays=" + starvingDays +
                '}';
    }

    @Override
    public Entity haveBaby(Cell myCell){
        return new Fox(myCell.getRowNum(), myCell.getColNum(), "Fox");
    }

}

package models.animals;

import models.entities.Cell;
import models.entities.Entity;
import services.UtilsService;

public class Buffalo extends Animal {

    public Buffalo(int xPoint, int yPoint, String type) {
        super(xPoint, yPoint, type);
    }

    @Override
    public String toString() {
        return "Buffalo{" +
                "id=" + id +
                " isCoupled=" + isCoupled +
                " starvingDays=" + starvingDays +
                '}';
    }

    @Override
    public Entity haveBaby(Cell myCell){
        return new Buffalo(myCell.getRowNum(), myCell.getColNum(), "Buffalo");
    }

}

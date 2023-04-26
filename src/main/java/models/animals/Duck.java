package models.animals;

import models.entities.Cell;
import models.entities.Entity;
import services.UtilsService;

public class Duck extends Animal {

    public Duck(int xPoint, int yPoint, String type) {
        super(xPoint, yPoint, type);
    }

    @Override
    public String toString() {
        return "Duck{" +
                "id=" + id +
                " isCoupled=" + isCoupled +
                " starvingDays=" + starvingDays +
                '}';
    }

    @Override
    public Entity haveBaby(Cell myCell){
        return new Duck(myCell.getRowNum(), myCell.getColNum(), "Duck");
    }

}
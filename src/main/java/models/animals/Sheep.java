package models.animals;

import models.entities.Cell;
import models.entities.Entity;
import services.UtilsService;

public class Sheep extends Animal {

    public Sheep(int xPoint, int yPoint, String type) {
        super(xPoint, yPoint, type);
    }

    @Override
    public String toString() {
        return "Sheep{" +
                "id=" + id +
                " isCoupled=" + isCoupled +
                " starvingDays=" + starvingDays +
                '}';
    }

    @Override
    public Entity haveBaby(Cell myCell){
        return new Sheep(myCell.getRowNum(), myCell.getColNum(), "Sheep");
    }

}

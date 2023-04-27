package models.animals;

import models.entities.Cell;
import models.entities.Entity;

public class Sheep extends Animal {

    public Sheep(int xPoint, int yPoint, String type) {
        super(xPoint, yPoint, type);
    }

    @Override
    public String toString() {
        return "Sheep{" +
                "id=" + this.getId() +
                " isCoupled=" + this.isCoupled() +
                " starvingDays=" + this.getStarvingDays() +
                '}';
    }

    @Override
    public Entity haveBaby(Cell myCell){
        return new Sheep(myCell.getRowNum(), myCell.getColNum(), "Sheep");
    }

}

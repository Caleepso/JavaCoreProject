package models.animals;

import models.entities.Cell;
import models.entities.Entity;

public class Horse extends Animal{

    public Horse(int xPoint, int yPoint, String type) {
        super(xPoint, yPoint, type);
    }

    @Override
    public String toString() {
        return "Horse{" +
                "id=" + this.getId() +
                " isCoupled=" + this.isCoupled() +
                " starvingDays=" + this.getStarvingDays() +
                '}';
    }

    @Override
    public Entity haveBaby(Cell myCell){
        return new Horse(myCell.getRowNum(), myCell.getColNum(), "Horse");
    }

}

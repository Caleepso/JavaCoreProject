package models.animals;

import models.entities.Cell;
import models.entities.Entity;

public class Eagle extends Animal {

    public Eagle(int xPoint, int yPoint, String type) {
        super(xPoint, yPoint, type);
    }

    @Override
    public String toString() {
        return "Eagle{" +
                "id=" + this.getId() +
                " isCoupled=" + this.isCoupled() +
                " starvingDays=" + this.getStarvingDays() +
                '}';
    }

    @Override
    public Entity haveBaby(Cell myCell){
        return new Eagle(myCell.getRowNum(), myCell.getColNum(), "Eagle");
    }

}

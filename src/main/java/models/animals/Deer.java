package models.animals;

import models.entities.Cell;
import models.entities.Entity;

public class Deer extends Animal {

    public Deer(int xPoint, int yPoint, String type) {
        super(xPoint, yPoint, type);
    }

    @Override
    public String toString() {
        return "Deer{" +
                "id=" + this.getId() +
                " isCoupled=" + this.isCoupled() +
                " starvingDays=" + this.getStarvingDays() +
                '}';
    }

    @Override
    public Entity haveBaby(Cell myCell){
        return new Deer(myCell.getRowNum(), myCell.getColNum(), "Deer");
    }

}

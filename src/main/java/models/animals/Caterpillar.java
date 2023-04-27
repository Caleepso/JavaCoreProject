package models.animals;

import models.entities.Cell;
import models.entities.Entity;

public class Caterpillar extends Animal {

    public Caterpillar(int xPoint, int yPoint, String type) {
        super(xPoint, yPoint, type);
    }

    @Override
    public String toString() {
        return "Caterpillar{" +
                "id=" + this.getId() +
                " isCoupled=" + this.isCoupled() +
                " starvingDays=" + this.getStarvingDays() +
                '}';
    }

    @Override
    public Entity haveBaby(Cell myCell){
        return new Caterpillar(myCell.getRowNum(), myCell.getColNum(), "Caterpillar");
    }

}

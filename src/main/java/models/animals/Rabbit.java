package models.animals;

import models.entities.Cell;
import models.entities.Entity;

public class Rabbit extends Animal{

    public Rabbit(int xPoint, int yPoint, String type) {
        super(xPoint, yPoint, type);
    }

    @Override
    public String toString() {
        return "Rabbit{" +
                "id=" + this.getId() +
                " isCoupled=" + this.isCoupled() +
                " starvingDays=" + this.getStarvingDays() +
                '}';
    }

    @Override
    public Entity haveBaby(Cell myCell){
        return new Rabbit(myCell.getRowNum(), myCell.getColNum(), "Rabbit");
    }

}

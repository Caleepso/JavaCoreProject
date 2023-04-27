package models.animals;

import models.entities.Cell;
import models.entities.Entity;

public class Fox extends Animal{

    public Fox(int xPoint, int yPoint, String type) {
        super(xPoint, yPoint, type);
    }

    @Override
    public String toString() {
        return "Fox{" +
                "id=" + this.getId() +
                " isCoupled=" + this.isCoupled() +
                " starvingDays=" + this.getStarvingDays() +
                '}';
    }

    @Override
    public Entity haveBaby(Cell myCell){
        return new Fox(myCell.getRowNum(), myCell.getColNum(), "Fox");
    }

}

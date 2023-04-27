package models.animals;

import models.entities.Cell;
import models.entities.Entity;

public class Mouse extends Animal {

    public Mouse(int xPoint, int yPoint, String type) {
        super(xPoint, yPoint, type);
    }

    @Override
    public String toString() {
        return "Mouse{" +
                "id=" + this.getId() +
                " isCoupled=" + this.isCoupled() +
                " starvingDays=" + this.getStarvingDays() +
                '}';
    }

    @Override
    public Entity haveBaby(Cell myCell){
        return new Mouse(myCell.getRowNum(), myCell.getColNum(), "Mouse");
    }

}

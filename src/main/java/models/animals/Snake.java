package models.animals;

import models.entities.Cell;
import models.entities.Entity;

public class Snake extends Animal{

    public Snake(int xPoint, int yPoint, String type) {
        super(xPoint, yPoint, type);
    }

    @Override
    public String toString() {
        return "Snake{" +
                "id=" + this.getId() +
                " isCoupled=" + this.isCoupled() +
                " starvingDays=" + this.getStarvingDays() +
                '}';
    }

    @Override
    public Entity haveBaby(Cell myCell){
        return new Snake(myCell.getRowNum(), myCell.getColNum(), "Snake");
    }

}

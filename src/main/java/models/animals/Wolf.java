package models.animals;
import models.entities.Entity;
import models.entities.Cell;


public class Wolf extends Animal {
    public Wolf(int xPoint, int yPoint, String type) {
        super(xPoint, yPoint, type);
    }


    @Override
    public String toString() {
        return "Wolf{" +
                "id=" + this.getId() +
                " isCoupled=" + this.isCoupled() +
                " starvingDays=" + this.getStarvingDays() +
                '}';
    }

    @Override
    public Entity haveBaby(Cell myCell){
        return new Wolf(myCell.getRowNum(), myCell.getColNum(), "Wolf");
    }

}

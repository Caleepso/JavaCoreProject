package models.animals;

import models.entities.Cell;
import models.entities.Entity;
import services.UtilsService;

public class Goat extends Animal {

    public Goat(int xPoint, int yPoint, String type) {
        super(xPoint, yPoint, type);
    }

    @Override
    public String toString() {
        return "Goat{" +
                "id=" + id +
                " isCoupled=" + isCoupled +
                " starvingDays=" + starvingDays +
                '}';
    }

    @Override
    public Entity haveBaby(Cell myCell){
        return new Goat(myCell.getRowNum(), myCell.getColNum(), "Goat");
    }

}

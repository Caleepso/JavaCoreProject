package models.animals;

import models.entities.Cell;
import models.entities.Entity;
import services.UtilsService;

public class Caterpillar extends Animal {

    public Caterpillar(int xPoint, int yPoint, String type) {
        super(xPoint, yPoint, type);
    }

    @Override
    public String toString() {
        return "Caterpillar{" +
                "id=" + id +
                " isCoupled=" + isCoupled +
                " starvingDays=" + starvingDays +
                '}';
    }

    @Override
    public Entity haveBaby(Cell myCell){
        return new Caterpillar(myCell.getRowNum(), myCell.getColNum(), "Caterpillar");
    }

}

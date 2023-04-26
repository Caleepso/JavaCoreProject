package models.animals;

import models.entities.Cell;
import models.entities.Entity;
import services.UtilsService;

public class Bear extends Animal {


    public Bear(int xPoint, int yPoint, String type) {
        super(xPoint, yPoint, type);
    }

    @Override
    public String toString() {
        return "Bear{" +
                "id=" + id +
                " isCoupled=" + isCoupled +
                " starvingDays=" + starvingDays +
                '}';
    }

    @Override
    public Entity haveBaby(Cell myCell){
        return new Bear(myCell.getRowNum(), myCell.getColNum(), "Bear");
    }

}

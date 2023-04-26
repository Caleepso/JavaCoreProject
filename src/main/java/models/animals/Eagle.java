package models.animals;

import models.entities.Cell;
import models.entities.Entity;
import services.UtilsService;

public class Eagle extends Animal {

    public Eagle(int xPoint, int yPoint, String type) {
        super(xPoint, yPoint, type);
    }

    @Override
    public String toString() {
        return "Eagle{" +
                "id=" + id +
                " isCoupled=" + isCoupled +
                " starvingDays=" + starvingDays +
                '}';
    }

    @Override
    public Entity haveBaby(Cell myCell){
        return new Eagle(myCell.getRowNum(), myCell.getColNum(), "Eagle");
    }

}

package models.animals;

import models.entities.Cell;
import models.entities.Entity;
import services.UtilsService;

public class Mouse extends Animal {

    public Mouse(int xPoint, int yPoint, String type) {
        super(xPoint, yPoint, type);
    }

    @Override
    public String toString() {
        return "Mouse{" +
                "id=" + id +
                " isCoupled=" + isCoupled +
                " starvingDays=" + starvingDays +
                '}';
    }

    @Override
    public Entity haveBaby(Cell myCell){
        return new Mouse(myCell.getRowNum(), myCell.getColNum(), "Mouse");
    }

}

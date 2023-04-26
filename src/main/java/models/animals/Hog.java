package models.animals;

import models.entities.Cell;
import models.entities.Entity;
import services.UtilsService;

public class Hog extends Animal {

    public Hog(int xPoint, int yPoint, String type) {
        super(xPoint, yPoint, type);
    }

    @Override
    public String toString() {
        return "Hog{" +
                "id=" + id +
                " isCoupled=" + isCoupled +
                " starvingDays=" + starvingDays +
                '}';
    }

    @Override
    public Entity haveBaby(Cell myCell){
        return new Hog(myCell.getRowNum(), myCell.getColNum(), "Hog");
    }

}

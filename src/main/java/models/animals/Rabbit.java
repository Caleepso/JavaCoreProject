package models.animals;

import models.entities.Cell;
import models.entities.Entity;
import services.UtilsService;

import java.util.Map;

public class Rabbit extends Animal{

    public Rabbit(int xPoint, int yPoint, String type) {
        super(xPoint, yPoint, type);
    }

    @Override
    public String toString() {
        return "Rabbit{" +
                "id=" + id +
                " isCoupled=" + isCoupled +
                " starvingDays=" + starvingDays +
                '}';
    }

    @Override
    public Entity haveBaby(Cell myCell){
        return new Rabbit(myCell.getRowNum(), myCell.getColNum(), "Rabbit");
    }

}

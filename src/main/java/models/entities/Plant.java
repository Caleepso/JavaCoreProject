package models.entities;

public class Plant extends Entity{

    public Plant(int xPoint, int yPoint) {
        super(xPoint, yPoint);
        this.type = "Plant";
        this.weight = 1.0;
    }
}

package models.entities;

public abstract class Entity {
    protected int xPoint;
    protected int yPoint;
    protected double weight;
    protected String type;

    public Entity(int xPoint, int yPoint) {
        this.xPoint = xPoint;
        this.yPoint = yPoint;
    }

    public String getType() {return type;}
    public void setxPoint(int xPoint) {
        this.xPoint = xPoint;
    }
    public void setyPoint(int yPoint) {
        this.yPoint = yPoint;
    }
    public int getxPoint() {
        return xPoint;
    }
    public int getyPoint() {
        return yPoint;
    }
    public double getWeight() {
        return weight;
    }

}

package models.animals;


public class AnimalFactory {
    Animal animal = null;
    public Animal createAnimal(String type, int x, int y) {
        switch (type) {
            case "Wolf":
                animal = new Wolf(x, y, "Wolf");
                break;
            case "Snake":
                animal = new Snake(x, y, "Snake");
                break;
            case "Fox":
                animal = new Fox(x, y, "Fox");
                break;
            case "Bear":
                animal = new Bear(x, y, "Bear");
                break;
            case "Eagle":
                animal = new Eagle(x, y, "Eagle");
                break;
            case "Horse":
                animal = new Horse(x, y, "Horse");
                break;
            case "Deer":
                animal = new Deer(x, y, "Deer");
                break;
            case "Rabbit":
                animal = new Rabbit(x, y, "Rabbit");
                break;
            case "Mouse":
                animal = new Mouse(x, y, "Mouse");
                break;
            case "Goat":
                animal = new Goat(x, y, "Goat");
                break;
            case "Sheep":
                animal = new Sheep(x, y, "Sheep");
                break;
            case "Hog":
                animal = new Hog(x, y, "Hog");
                break;
            case "Buffalo":
                animal = new Buffalo(x, y, "Buffalo");
                break;
            case "Duck":
                animal = new Duck(x, y, "Duck");
                break;
            case "Caterpillar":
                animal = new Caterpillar(x, y, "Caterpillar");
                break;
        }
        return animal;
    }
}

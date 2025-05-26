import java.sql.*;
public class Weapon extends Discovery {
    //Inherits from Discovery, and adds its own unique fields
    private String type;
    private String material;
    private int weight;

    public Weapon(int id, String location, int finderId, Date date, int age, Integer museumId, String type, String material, int weight) {
        super(id, location, finderId, date, age, museumId);
        this.type = type;
        this.material = material;
        this.weight = weight;
    }

    // Getters
    public String getType() {
        return type;
    }

    public String getMaterial() {
        return material;
    }

    public int getWeight() {
        return weight;
    }

    @Override
    public String toString() {
        return String.format("%s fra år %d som veier %d gram.", this.getType(), this.getAge(), this.getWeight());
    }
}



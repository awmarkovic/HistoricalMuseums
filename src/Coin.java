import java.sql.*;

public class Coin extends Discovery {
    //Inherits from Discovery, and adds its own unique fields
    private int diameter;
    private String metal;

    public Coin(int id, String location, int finderId, Date date, int age, Integer museumId, int diameter, String metal) {
        super(id, location, finderId, date, age, museumId);
        this.diameter = diameter;
        this.metal = metal;
    }

    // Getters
    public int getDiameter(){
        return diameter;
    }

    public String getMetal(){
        return metal;
    }

    @Override
    public String toString() {
        return String.format("%s-mynt fra år %d med diameter på %d mm.", this.getMetal(), this.getAge(), this.getDiameter());
    }
}

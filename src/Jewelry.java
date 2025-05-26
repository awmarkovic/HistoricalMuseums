import java.sql.*;


public class Jewelry extends Discovery {
    //Inherits from Discovery, and adds its own unique fields
    private String type;
    private int worth;
    private String imageName;

    public Jewelry(int id, String location, int finderId, Date date, int age, Integer museumId, String type, int worth, String imageName) {
        super(id, location, finderId, date, age, museumId);
        this.type = type;
        this.worth = worth;
        this.imageName = imageName;
    }

    // Getters
    public String getType() {
        return type;
    }

    public int getWorth() {
        return worth;
    }

    public String getImageName() {
        return imageName;
    }

    @Override
    public String toString() {
        return String.format("%s fra år %d med verdi av %d norske kroner.", this.getType(), this.getAge(), this.getWorth());
    }
}

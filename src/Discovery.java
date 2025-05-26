import java.sql.Date;

abstract class Discovery {
    // A lot of the same fields are repetitive for Coin, Weapon and Jewelry.
    // Therefore I made one class that they inherit from
    private int id;
    private String location;
    private int finderId;
    private Date date;
    private int age;
    private Integer museumId;

    public Discovery(int id, String location, int finderId, Date date, int age, Integer museumId) {
        this.id = id;
        this.location = location;
        this.finderId = finderId;
        this.date = date;
        this.age = age;
        this.museumId = museumId;
    }

    // Getters
    public int getId() {
        return id;
    }

    public String getLocation() {
        return location;
    }

    public int getFinderId() {
        return finderId;
    }

    public Date getDate() {
        return this.date;
    }

    public int getAge() {
        return age;
    }

    public Integer getMuseumId() {
        return museumId;
    }

}

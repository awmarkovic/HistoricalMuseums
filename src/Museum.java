class Museum {
    private int museumId;
    private String museumName;
    private String location;

    public Museum(int id, String museumName, String location) {
        this.museumId = id;
        this.museumName = museumName;
        this.location = location;
    }

    // Getters
    public int getId() {
        return museumId;
    }

    public String getMuseumName() {
        return museumName;
    }

    public String getLocation() {
        return location;
    }

}
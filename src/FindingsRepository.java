import java.sql.*;
import java.util.ArrayList;

public class FindingsRepository {
    // This class handles everything that goes into our database. It handles SQL querys and gives us
    // necessary information to use in our interactive menu.
    private Database database;

    public FindingsRepository() {
        database = new Database();
        database.connect();
    }
    public void insertPersonIntoDB (Person person) {
        String sql = "INSERT INTO Person (Id, Navn, Tlf, E_post) VALUES (?, ?, ?, ?)" +
                "ON DUPLICATE KEY UPDATE Navn = VALUES(Navn), Tlf = VALUES(Tlf), E_post = VALUES(E_post)";

        Connection connection = database.getConnection();

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, person.getId());
            stmt.setString(2, person.getName());
            stmt.setString(3, person.getPhoneNumber());
            stmt.setString(4, person.getEmail());
            stmt.executeUpdate();
        } catch(Exception e) {
            System.err.println("ERROR! Did not successfully insert into database");
            e.printStackTrace();
        }
    }

    public void insertMuseumIntoDB (Museum museum) {
        String sql = "INSERT INTO Museum (Id, Navn, Sted) VALUES (?, ?, ?)" +
                "ON DUPLICATE KEY UPDATE Navn = VALUES(Navn), Sted = VALUES(Sted)";

        Connection connection = database.getConnection();

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, museum.getId());
            stmt.setString(2, museum.getMuseumName());
            stmt.setString(3, museum.getLocation());
            stmt.executeUpdate();
        } catch(Exception e) {
            System.err.println("ERROR! Did not successfully insert into database");
            e.printStackTrace();
        }
    }
    public void insertJewelryIntoDB(Jewelry jewelry) {
        String sql = "INSERT INTO Smykke (Id, Funnsted, Finner_id, Funntidspunkt, Antatt_årstall, Museum_id, Type, Verdiestimat, filnavn) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)" +
                "ON DUPLICATE KEY UPDATE Funnsted = VALUES(Funnsted), Finner_id = VALUES(Finner_id), Funntidspunkt = VALUES(Funntidspunkt), Antatt_årstall = VALUES(Antatt_årstall), Museum_id = VALUES(Museum_id), Type = VALUES(Type), Verdiestimat = VALUES(Verdiestimat), filnavn = VALUES(filnavn)";

        Connection connection = database.getConnection();

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, jewelry.getId());
            stmt.setString(2, jewelry.getLocation());
            stmt.setInt(3, jewelry.getFinderId());
            stmt.setDate(4, jewelry.getDate());
            stmt.setInt(5, jewelry.getAge());
            if (jewelry.getMuseumId() == null) {
                stmt.setNull(6, Types.INTEGER);
            } else {
                stmt.setInt(6, jewelry.getMuseumId());
            }
            stmt.setString(7, jewelry.getType());
            stmt.setInt(8, jewelry.getWorth());
            stmt.setString(9, jewelry.getImageName());

            stmt.executeUpdate();
        } catch(Exception e) {
            System.err.println("ERROR! Did not successfully insert into database");
            e.printStackTrace();
        }
    }
    public void insertCoinIntoDB(Coin coin) {
        String sql = "INSERT INTO Mynt (Id, Funnsted, Finner_id, Funntidspunkt, Antatt_årstall, Museum_id, Diameter, Metall) VALUES (?, ?, ?, ?, ?, ?, ?, ?)" +
                "ON DUPLICATE KEY UPDATE Funnsted = VALUES(Funnsted), Finner_id = VALUES(Finner_id), Funntidspunkt = VALUES(Funntidspunkt), Antatt_årstall = VALUES(Antatt_årstall), Museum_id = VALUES(Museum_id), Diameter = VALUES(Diameter), Metall = VALUES(Metall)";

        Connection connection = database.getConnection();

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, coin.getId());
            stmt.setString(2, coin.getLocation());
            stmt.setInt(3, coin.getFinderId());
            stmt.setDate(4, coin.getDate());
            stmt.setInt(5, coin.getAge());
            if (coin.getMuseumId() == null) {
                stmt.setNull(6, Types.INTEGER);
            } else {
                stmt.setInt(6, coin.getMuseumId());
            }
            stmt.setInt(7, coin.getDiameter());
            stmt.setString(8, coin.getMetal());

            stmt.executeUpdate();
        } catch(Exception e) {
            System.err.println("ERROR! Did not successfully insert into database");
            e.printStackTrace();
        }
    }

    public void insertWeaponIntoDB (Weapon weapon) {
        String sql = "INSERT INTO Vaapen (Id, Funnsted, Finner_id, Funntidspunkt, Antatt_årstall, Museum_id, Type, Materiale, Vekt) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)" +
                "ON DUPLICATE KEY UPDATE Funnsted = VALUES(Funnsted), Finner_id = VALUES(Finner_id), Funntidspunkt = VALUES(Funntidspunkt), Antatt_årstall = VALUES(Antatt_årstall), Museum_id = VALUES(Museum_id), Type = VALUES(Type), Materiale = VALUES(Materiale), Vekt = VALUES(Vekt)";

        Connection connection = database.getConnection();

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, weapon.getId());
            stmt.setString(2, weapon.getLocation());
            stmt.setInt(3, weapon.getFinderId());
            stmt.setDate(4, weapon.getDate());
            stmt.setInt(5, weapon.getAge());
            if (weapon.getMuseumId() == null) {
                stmt.setNull(6, Types.INTEGER);
            } else {
                stmt.setInt(6, weapon.getMuseumId());
            }
            stmt.setString(7, weapon.getType());
            stmt.setString(8, weapon.getMaterial());
            stmt.setInt(9, weapon.getWeight());

            stmt.executeUpdate();
        } catch(Exception e) {
            System.err.println("ERROR! Did not successfully insert into database");
            e.printStackTrace();
        }
    }

    public ArrayList<Discovery> getDiscoveries() {
        String sqlCoin = "SELECT * FROM Mynt";
        String sqlWeapon = "SELECT * FROM Vaapen";
        String sqlJewelry = "SELECT * FROM Smykke";
        Connection connection = database.getConnection();
        ArrayList<Discovery> discoveries = new ArrayList<>();

        try (Statement stmt = connection.createStatement()) {
            ResultSet resultSet = stmt.executeQuery(sqlCoin);
            while (resultSet.next()) {
                discoveries.add(getCoinFromResultSet(resultSet));
            }
        } catch(Exception e) {
            System.err.println("ERROR! Did not successfully select from database");
            e.printStackTrace();
        }
        try (Statement stmt = connection.createStatement()) {
            ResultSet resultSet = stmt.executeQuery(sqlWeapon);
            while (resultSet.next()) {
                int id = resultSet.getInt("Id");
                String location = resultSet.getString("Funnsted");
                int finderId = resultSet.getInt("Finner_id");
                Date date = resultSet.getDate("Funntidspunkt");
                int age = resultSet.getInt("Antatt_årstall");
                Integer museumId = (Integer) resultSet.getObject("Museum_id");
                String type = resultSet.getString("Type");
                String material = resultSet.getString("Materiale");
                int weight = resultSet.getInt("Vekt");
                discoveries.add(new Weapon(id, location, finderId, date, age, museumId, type, material, weight));
            }
        } catch(Exception e) {
            System.err.println("ERROR! Did not successfully select from database");
            e.printStackTrace();
        }
        try (Statement stmt = connection.createStatement()) {
            ResultSet resultSet = stmt.executeQuery(sqlJewelry);
            while (resultSet.next()) {
                int id = resultSet.getInt("Id");
                String location = resultSet.getString("Funnsted");
                int finderId = resultSet.getInt("Finner_id");
                Date date = resultSet.getDate("Funntidspunkt");
                int age = resultSet.getInt("Antatt_årstall");
                Integer museumId = (Integer) resultSet.getObject("Museum_id");
                String type = resultSet.getString("Type");
                int worth = resultSet.getInt("Verdiestimat");
                String imageName = resultSet.getString("filnavn");
                discoveries.add(new Jewelry(id, location, finderId, date, age, museumId, type, worth, imageName));
            }
        } catch(Exception e) {
            System.err.println("ERROR! Did not successfully select from database");
            e.printStackTrace();
        }
        return discoveries;
    }

    public ArrayList<Discovery> getDiscoveriesFromYear(int fromAge) {
        String sqlCoin = "SELECT * FROM Mynt WHERE Antatt_årstall < ?";
        String sqlWeapon = "SELECT * FROM Vaapen WHERE Antatt_årstall < ?";
        String sqlJewelry = "SELECT * FROM Smykke WHERE Antatt_årstall < ?";
        Connection connection = database.getConnection();
        ArrayList<Discovery> discoveries = new ArrayList<>();

        try (PreparedStatement stmt = connection.prepareStatement(sqlCoin)) {
            stmt.setInt(1, fromAge);
            ResultSet resultSet = stmt.executeQuery();

            while (resultSet.next()) {
                discoveries.add(getCoinFromResultSet(resultSet));
            }
        } catch(Exception e) {
            System.err.println("ERROR! Did not successfully select from database");
            e.printStackTrace();
        }
        try (PreparedStatement stmt = connection.prepareStatement(sqlWeapon)) {
            stmt.setInt(1, fromAge);
            ResultSet resultSet = stmt.executeQuery();

            while (resultSet.next()) {
                discoveries.add(getWeaponFromResultSet(resultSet));
            }
        } catch(Exception e) {
            System.err.println("ERROR! Did not successfully select from database");
            e.printStackTrace();
        }
        try (PreparedStatement stmt = connection.prepareStatement(sqlJewelry)) {
            stmt.setInt(1, fromAge);
            ResultSet resultSet = stmt.executeQuery();

            while (resultSet.next()) {
                discoveries.add(getJewelryFromResultSet(resultSet));

            }
        } catch(Exception e) {
            System.err.println("ERROR! Did not successfully select from database");
            e.printStackTrace();
        }
        return discoveries;
    }

    public int getNumberOfDiscoveries() {
        String sqlCoin = "SELECT COUNT(*) AS 'count' FROM Mynt";
        String sqlWeapon = "SELECT COUNT(*) AS 'count' FROM Vaapen";
        String sqlJewelry = "SELECT COUNT(*) AS 'count' FROM Smykke";
        Connection connection = database.getConnection();
        int sum = 0;

        try (Statement stmt = connection.createStatement()) {
            ResultSet resultSet = stmt.executeQuery(sqlCoin);
            while (resultSet.next()) {
                int count = resultSet.getInt("count");
                sum += count;
            }
        } catch(Exception e) {
            System.err.println("ERROR! Did not successfully select from database");
            e.printStackTrace();
        }
        try (Statement stmt = connection.createStatement()) {
            ResultSet resultSet = stmt.executeQuery(sqlWeapon);
            while (resultSet.next()) {
                int count = resultSet.getInt("count");
                sum += count;
            }
        } catch(Exception e) {
            System.err.println("ERROR! Did not successfully select from database");
            e.printStackTrace();
        }
        try (Statement stmt = connection.createStatement()) {
            ResultSet resultSet = stmt.executeQuery(sqlJewelry);
            while (resultSet.next()) {
                int count = resultSet.getInt("count");
                sum += count;
            }
        } catch(Exception e) {
            System.err.println("ERROR! Did not successfully select from database");
            e.printStackTrace();
        }
        return sum;
    }

    private Coin getCoinFromResultSet(ResultSet resultSet) throws SQLException {
        int id = resultSet.getInt("Id");
        String location = resultSet.getString("Funnsted");
        int finderId = resultSet.getInt("Finner_id");
        Date date = resultSet.getDate("Funntidspunkt");
        int age = resultSet.getInt("Antatt_årstall");
        Integer museumId = (Integer) resultSet.getObject("Museum_id");
        int diameter = resultSet.getInt("Diameter");
        String metal = resultSet.getString("Metall");
        return new Coin(id, location, finderId, date, age, museumId, diameter, metal);
    }

    private Jewelry getJewelryFromResultSet (ResultSet resultSet) throws SQLException {
        int id = resultSet.getInt("Id");
        String location = resultSet.getString("Funnsted");
        int finderId = resultSet.getInt("Finner_id");
        Date date = resultSet.getDate("Funntidspunkt");
        int age = resultSet.getInt("Antatt_årstall");
        Integer museumId = (Integer) resultSet.getObject("Museum_id");
        String type = resultSet.getString("Type");
        int worth = resultSet.getInt("Verdiestimat");
        String imageName = resultSet.getString("filnavn");
        return new Jewelry(id, location, finderId, date, age, museumId, type, worth, imageName);
    }

    private Weapon getWeaponFromResultSet (ResultSet resultSet) throws SQLException {
        int id = resultSet.getInt("Id");
        String location = resultSet.getString("Funnsted");
        int finderId = resultSet.getInt("Finner_id");
        Date date = resultSet.getDate("Funntidspunkt");
        int age = resultSet.getInt("Antatt_årstall");
        Integer museumId = (Integer) resultSet.getObject("Museum_id");
        String type = resultSet.getString("Type");
        String material = resultSet.getString("Materiale");
        int weight = resultSet.getInt("Vekt");
        return new Weapon(id, location, finderId, date, age, museumId, type, material, weight);
    }
}

import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.sql.Date;
import java.util.List;
import java.util.Scanner;

public class PartOneMain {
    // This main-method is meant to handle everything in Part 1 of this exam
    // meaning populating the database from the contents of the .txt file

    public static void main(String[] args) {

        try {
            File file = new File("files/funn.txt");
            Scanner scanner = new Scanner(file);

            List<String> personData = new ArrayList<>();
            List<String> museumData = new ArrayList<>();
            List<String> findingsData = new ArrayList<>();

            List<String> currentList = personData;


            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();


                if (line.equals("Personer:")) {
                    currentList = personData;
                    continue;
                }

                if (line.equals("Museer:")) {
                    currentList = museumData;
                    continue;
                }

                if (line.equals("Funn:")) {
                    currentList = findingsData;
                    continue;
                }

                currentList.add(line);
            }

            ParsePeople(String.join("\n", personData));
            ParseMuseums(String.join("\n", museumData));
            ParseFindings(String.join("\n", findingsData));

            System.out.println("Successfully populated the database");

        } catch (Exception e) {
            System.err.println("Error reading file");
            e.printStackTrace();
        }
    }
    private static void ParsePeople(String input) {
        FindingsRepository findingsRepository = new FindingsRepository();

        List<Person> people = new ArrayList<>();

        Scanner scanner = new Scanner(input);

        int peopleCount = Integer.parseInt(scanner.nextLine());

        for (int i = 0; i < peopleCount; i++) {
            int personId = Integer.parseInt(scanner.nextLine());

            String name = scanner.nextLine();
            String phone = scanner.nextLine();
            String email = scanner.nextLine();

            people.add(new Person(personId, name, phone, email));
        }

        for (Person person : people) {
            findingsRepository.insertPersonIntoDB(person);
        }
    }

    private static void ParseMuseums(String input) {
        FindingsRepository findingsRepository = new FindingsRepository();

        List<Museum> museums = new ArrayList<>();
        Scanner scanner = new Scanner(input);

        int museumCount = Integer.parseInt(scanner.nextLine());
        for (int i = 0; i < museumCount; i++) {
            int museumId = Integer.parseInt(scanner.nextLine());

            String museumName = scanner.nextLine();
            String location = scanner.nextLine();

            museums.add(new Museum(museumId, museumName, location));
        }

        for (Museum museum : museums) {
            findingsRepository.insertMuseumIntoDB(museum);
        }
    }

    private static void ParseFindings(String input) throws ParseException {
        FindingsRepository findingsRepository = new FindingsRepository();
        Scanner scanner = new Scanner(input);
        List<List<String>> discoveries = new ArrayList<>();

        int discoveryIndex = 0;

        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();

            if (discoveryIndex >= discoveries.size()) {
                discoveries.add(new ArrayList<>());
            }

            if (line.equals("-------")) {
                discoveryIndex++;

                if (scanner.hasNextLine()) {
                    discoveries.add(new ArrayList<>());
                }

                continue;
            }

            discoveries.get(discoveryIndex).add(line);
        }

        List<Jewelry> jewelryDiscoveries = new ArrayList<>();
        List<Coin> coinDiscoveries = new ArrayList<>();
        List<Weapon> weaponDiscoveries = new ArrayList<>();

        for (List<String> discovery : discoveries) {

            int id = Integer.parseInt(discovery.get(0));

            String location = discovery.get(1);
            int finderId = Integer.parseInt(discovery.get(2));

            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            java.util.Date utilDate = simpleDateFormat.parse(discovery.get(3));
            Date date = new Date(utilDate.getTime());

            int age = Integer.parseInt(discovery.get(4));

            Integer museumId = null;

            if(!discovery.get(5).isEmpty()) {
                museumId = Integer.parseInt(discovery.get(5));
            }

            String findingType = discovery.get(6);

            String jewelryType = discovery.get(7);
            String weaponType = discovery.get(7);

            int coinDiameter = 0;

            if (findingType.equals("Mynt")) {
                coinDiameter =  Integer.parseInt(discovery.get(7));
            }

            String coinMetalType = discovery.get(8);
            String weaponMaterial = discovery.get(8);
            int jewelryWorth = 0;

            if (findingType.equals("Smykke")) {
                jewelryWorth = Integer.parseInt(discovery.get(8));
            }

            String jewelryImageName = "";
            int weaponWeight = 0;

            if (discovery.size() >= 10) {
                jewelryImageName = discovery.get(9);

                if (findingType.equals("Våpen")) {
                    weaponWeight = Integer.parseInt(discovery.get(9));
                }
            }

            switch (findingType) {
                case "Mynt":
                    coinDiscoveries.add(new Coin(id, location, finderId, date, age, museumId, coinDiameter, coinMetalType));
                    break;
                case "Smykke":
                    jewelryDiscoveries.add(new Jewelry(id, location, finderId, date, age, museumId, jewelryType, jewelryWorth, jewelryImageName));
                    break;
                case "Våpen":
                    weaponDiscoveries.add(new Weapon(id, location, finderId, date, age, museumId, weaponType, weaponMaterial, weaponWeight));
                    break;
            }

            for (Coin coin : coinDiscoveries) {
                findingsRepository.insertCoinIntoDB(coin);
            }

            for (Jewelry jewelry : jewelryDiscoveries) {
                findingsRepository.insertJewelryIntoDB(jewelry);
            }

            for (Weapon weapon : weaponDiscoveries) {
                findingsRepository.insertWeaponIntoDB(weapon);
            }
        }
    }
}


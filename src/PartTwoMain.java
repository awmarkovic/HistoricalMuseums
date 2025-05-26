import java.util.ArrayList;
import java.util.Scanner;

public class PartTwoMain {
    // This main-method is meant for part 2 of this exam and implements our interactive terminal.
    public static void main(String[] args) {
        promptUser();
    }

    private static void promptUser() {
        FindingsRepository findingsRepository = new FindingsRepository();

        Scanner scanner = new Scanner(System.in);

        System.out.println("Velg alternativ");

        System.out.println("1. Informasjon om alle funn");
        System.out.println("2. Informasjon om alle funn eldre enn årstall");
        System.out.println("3. Informasjon om antall registrerte funn");
        System.out.println("4. Avslutt program");

        System.out.print("\nVennligst skriv inn menyvalg (1-4): ");

        int option = scanner.nextInt();

        switch (option) {
            case 1:
                System.out.println("Valg 1");
                ArrayList<Discovery> discoveries = findingsRepository.getDiscoveries();
                for (Discovery discovery : discoveries) {
                    System.out.println(discovery);
                }
                showSeparator();
                promptUser();
                break;
            case 2:
                System.out.println("Valg 2");

                listAfterYear(findingsRepository);

                showSeparator();
                promptUser();
                break;
            case 3:
                System.out.println("Valg 3");
                int numberOfDiscoveries = findingsRepository.getNumberOfDiscoveries();

                System.out.println("Totalt antall funn: " + numberOfDiscoveries);
                showSeparator();
                promptUser();
                break;
            case 4:
                System.out.println("Valg 4, avslutter program");

                break;
            default:
                System.out.println("Ugyldig valg");

                showSeparator();
                promptUser();
                break;
        }
    }

    private static void listAfterYear(FindingsRepository findingsRepository) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("\nSkriv inn årstall: ");
        int chosenYear = scanner.nextInt();

        System.out.print("\nDu valgte årstall: ");
        System.out.print(chosenYear);

        ArrayList<Discovery> discoveries = findingsRepository.getDiscoveriesFromYear(chosenYear);
        for (Discovery discovery : discoveries) {
            System.out.println(discovery);
        }
    }

    private static void showSeparator() {
        System.out.println("\n--------------\n");
    }
}
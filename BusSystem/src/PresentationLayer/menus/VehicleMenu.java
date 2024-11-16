package PresentationLayer.menus;
import java.util.Scanner;

public class VehicleMenu {
    private Scanner scanner = new Scanner(System.in);
    public void start() {
        int choice;
        do {
            System.out.println("Vehicle Menu");
            System.out.println("1. Vehicle Type Management");
            System.out.println("2. Vehicle Management");
            System.out.println("3. Vehicle Data Management");
            System.out.println("0. Return");
            System.out.print("Enter your choice: ");

            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    VehicleTypeMenu vehicleTypeMenu = new VehicleTypeMenu();
                    vehicleTypeMenu.start();
                    break;
                case 2:
                    VehicleTableMenu vehicleTableMenu = new VehicleTableMenu();
                    vehicleTableMenu.start();
                    break;
                case 3:
                    VehicleDataMenu vehicleDataMenu = new VehicleDataMenu();
                    vehicleDataMenu.start();
                    break;
                case 0:
                    System.out.println("Exiting to main menu");
                    break;
                default:
                    System.out.println("Invalid choice. Please enter a valid option.");
                    break;
            }

        }   while (choice != 0);
    }
}

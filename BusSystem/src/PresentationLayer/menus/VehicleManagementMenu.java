package PresentationLayer.menus;

import java.util.Scanner;

public class VehicleManagementMenu {
    private final Scanner scanner = new Scanner(System.in);

    public void start() {
        int choice;
        do {
            System.out.println("Vehicle Management Menu");
            System.out.println("1. Vehicle Type");
            System.out.println("2. Vehicle Data");
            System.out.println("3. Vehicles");
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
                    VehicleDataMenu vehicleDataMenu = new VehicleDataMenu();
                    vehicleDataMenu.start();
                    break;
                case 3:
                    VehicleTableMenu vehicleTableMenu = new VehicleTableMenu();
                    vehicleTableMenu.start();
                    break;
                case 0:
                    System.out.println("Exiting to Vehicle Menu");
                    break;
                default:
                    System.out.println("Invalid choice. Please enter a valid option.");
                    break;
            }
        }while (choice != 0);
    }
}

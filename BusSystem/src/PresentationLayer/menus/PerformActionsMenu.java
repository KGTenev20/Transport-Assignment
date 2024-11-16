package PresentationLayer.menus;

import DataAccessLayer.repositories.VehicleDataRepository;

import java.util.Scanner;

public class PerformActionsMenu {
    private Scanner scanner = new Scanner(System.in);
    public void start() {
        int choice;
        do {
            System.out.println("Perform Actions Menu");
            System.out.println("1. Perform Route For Vehicle Id");
            System.out.println("2. Charge Battery For Vehicle Id");
            System.out.println("3. Charge All Low batteries");
            System.out.println("4. Charge All batteries");
            System.out.println("0. Return");
            System.out.print("Enter your choice: ");
            int vehicleId;
            VehicleDataRepository vehicleDataRepository = new VehicleDataRepository();

            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    System.out.println("Enter Vehicle Id");
                    vehicleId = scanner.nextInt();
                    vehicleDataRepository.performRoute(vehicleId);
                    break;
                case 2:
                    System.out.println("Enter Vehicle Id");
                    vehicleId = scanner.nextInt();
                    vehicleDataRepository.chargeBattery(vehicleId);
                    break;
                case 3:
                    vehicleDataRepository.chargeLowBatteries();
                    break;
                case 4:
                    vehicleDataRepository.chargeAllBatteries();
                    break;
                case 0:
                    System.out.println("Exiting Perform Actions Menu");
                    break;
                default:
                    System.out.println("Invalid choice. Please enter a valid option.");
                    break;
            }
        } while (choice != 0);
    }
}

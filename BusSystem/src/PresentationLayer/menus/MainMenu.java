package PresentationLayer.menus;

import java.util.Scanner;

public class MainMenu extends Thread{
    private Scanner scanner;

    public MainMenu() {
        this.scanner = new Scanner(System.in);
    }

    public void start() {
        int choice;
        do {
            System.out.println("Main Menu");
            System.out.println("1. Route Management");
            System.out.println("2. Vehicle Management");
            System.out.println("3. Perform Actions");
            System.out.println("4. Additional functionality");
            System.out.println("0. Exit Application");
            System.out.print("Enter your choice: ");


            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    BusRoutesMenu busRoutesMenu = new BusRoutesMenu();
                    busRoutesMenu.start();
                    break;
                case 2:
                    VehicleManagementMenu vehicleManagementMenu = new VehicleManagementMenu();
                    vehicleManagementMenu.start();
                    break;
                case 3:
                    PerformActionsMenu performActionsMenu = new PerformActionsMenu();
                    performActionsMenu.start();
                    break;
                case 4:
                    UserMenu userMenu = new UserMenu();
                    userMenu.start();
                    break;
                case 0:
                    System.out.println("Exiting Application. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice. Please enter a valid option.");
                    break;
            }
        } while (choice != 0);
    }
}
package PresentationLayer.menus;

import DataAccessLayer.models.BusRoutes;
import PresentationLayer.controller.BusRoutesController;

import java.util.List;
import java.util.Scanner;

public class BusRoutesMenu {
    private BusRoutesController busRoutesController;
    private Scanner scanner;

    public BusRoutesMenu() {
        this.busRoutesController = new BusRoutesController();
        this.scanner = new Scanner(System.in);
    }

    public void start() {
        int choice;
        do {
            System.out.println("Bus Routes Management Menu");
            System.out.println("1. Add Route");
            System.out.println("2. Update Route");
            System.out.println("3. Delete Route");
            System.out.println("4. Display Route");
            System.out.println("5. Display All Routes");
            System.out.println("0. Return");
            System.out.print("Enter your choice: ");

            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    addBusRoute();
                    break;
                case 2:
                    updateBusRoute();
                    break;
                case 3:
                    deleteBusRoute();
                    break;
                case 4:
                    viewBusRouteById();
                    break;
                case 5:
                    viewAllBusRoutes();
                    break;
                case 0:
                    System.out.println("Exiting Bus Routes Management Menu. Goodbye!");
                    break;
            }
        } while (choice != 0);
    }

    private void viewAllBusRoutes() {
        List<BusRoutes> busRoutes = busRoutesController.getAllBusRoutes();
        displayBusRoutes(busRoutes);
    }

    private void viewBusRouteById() {
        System.out.print("Enter Bus Route ID: ");
        int TypeId = scanner.nextInt();
        BusRoutes busRoutes = busRoutesController.getBusRoutesById(TypeId);
        if (busRoutes != null) {
            displayBusRoute(busRoutes);
        } else {
            System.out.println("Bus Route not found with ID: " + TypeId);
        }
    }

    private void addBusRoute() {
        System.out.println("Enter BusRoutes Details:");
        System.out.print("Mileage: ");
        int RouteMileage = scanner.nextInt();

        BusRoutes busRoutes = new BusRoutes(RouteMileage);
        busRoutesController.createBusRoute(busRoutes);
        System.out.println("Bus Route added successfully!");
    }

    private void updateBusRoute() {
        System.out.print("Enter Bus Route ID to update: ");
        int TypeId = scanner.nextInt();
        scanner.nextLine();

        BusRoutes existingBusRoute = busRoutesController.getBusRoutesById(TypeId);
        if (existingBusRoute != null) {
            System.out.println("Enter updated Bus Route Details:");
            System.out.print("Mileage: ");
            int Mileage = scanner.nextInt();

            existingBusRoute.setRouteMileage(Mileage);
            busRoutesController.updateBusRoute(existingBusRoute);
            System.out.println("Bus Route updated successfully!");
        } else {
            System.out.println("Bus Route not found with ID: " + TypeId);
        }
    }

    private void deleteBusRoute() {
        System.out.print("Enter BusRoutes ID to delete: ");
        int TypeId = scanner.nextInt();
        scanner.nextLine();

        BusRoutes existingBusRoute = busRoutesController.getBusRoutesById(TypeId);
        if (existingBusRoute != null) {
            busRoutesController.deleteBusRoute(TypeId);
            System.out.println("Bus Route deleted successfully!");
        } else {
            System.out.println("Bus Route not found with ID: " + TypeId);
        }
    }

    private void displayBusRoutes(List<BusRoutes> busRoutes) {
        System.out.println("List of Bus Routes:");
        for (BusRoutes busRoute : busRoutes) {
            displayBusRoute(busRoute);
        }
    }

    private void displayBusRoute(BusRoutes busRoutes) {
        System.out.println("Bus Route ID: " + busRoutes.getRouteId());
        System.out.println("Bus Route Mileage: " + busRoutes.getRouteMileage());
        System.out.println("---------------------------------");
    }
}

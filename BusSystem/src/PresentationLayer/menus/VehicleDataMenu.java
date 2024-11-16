package PresentationLayer.menus;

import DataAccessLayer.models.VehicleData;
import PresentationLayer.controller.VehicleDataController;

import java.util.List;
import java.util.Scanner;

public class VehicleDataMenu {
    private VehicleDataController vehicleDataController;
    private Scanner scanner;

    public VehicleDataMenu() {
        this.vehicleDataController = new VehicleDataController();
        this.scanner = new Scanner(System.in);
    }

    public void start() {
        int choice;
        do {
            System.out.println("Vehicle Data Menu");
            System.out.println("1. Display Data For Vehicle Id");
            System.out.println("2. Display Data For All Vehicles");
            System.out.println("3. Update Vehicle Route");
            System.out.println("0. Return");
            System.out.println("Enter your choice: ");

            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    viewVehicleDataById();
                    break;
                case 2:
                    viewAllVehicleDatas();
                    break;
                case 3:
                    updateVehicleData();
                    break;
                case 0:
                    System.out.println("Exiting Vehicle Data Management Menu. Goodbye!");
                    break;
            }
        } while (choice != 0);
    }

    private void viewAllVehicleDatas() {
        List<VehicleData> vehicleDatas = vehicleDataController.getAllVehicleData();
        displayVehicleDatas(vehicleDatas);
    }

    private void viewVehicleDataById() {
        System.out.println("Enter Vehicle Data ID: ");
        System.out.println("Enter 0 to return");
        int VehicleId = scanner.nextInt();
        scanner.nextLine();
        if(VehicleId == 0){
            VehicleDataMenu vehicleDataMenu = new VehicleDataMenu();
            vehicleDataMenu.start();
        }
        VehicleData vehicleData = vehicleDataController.getVehicleDataById(VehicleId);
        if (vehicleData != null) {
            displayVehicleData(vehicleData);
        } else {
            System.out.println("Vehicle Data not found with ID: " + VehicleId);
        }
    }

    private void addVehicleData() {
        System.out.println("Enter Vehicle Data Details:");
        System.out.println("Vehicle Id");
        int VehicleId = scanner.nextInt();

        VehicleDataController vehicleDataController = new VehicleDataController();
        VehicleData vehicleData =  vehicleDataController.getVehicleDataById(VehicleId);

        int TypeId = vehicleData.getVehicleTypeId();

        int CurrentBattery = vehicleData.getCurrentBattery();

        Boolean ChargingFlag = vehicleData.isChargingFlag();

        System.out.print("Route Id: ");
        int RouteId = scanner.nextInt();

        VehicleData newVehicleData = new VehicleData(VehicleId, TypeId, CurrentBattery, ChargingFlag, RouteId);
        vehicleDataController.createVehicleData(newVehicleData);
        System.out.println("Vehicle Data added successfully!");
    }

    private void updateVehicleData() {
        System.out.print("Enter Vehicle Data Id to update: ");
        int VehicleId = scanner.nextInt();
        scanner.nextLine();

        VehicleDataController vehicleDataController = new VehicleDataController();
        VehicleData vehicleData =  vehicleDataController.getVehicleDataById(VehicleId);

        VehicleData existingVehicleData = vehicleDataController.getVehicleDataById(VehicleId);
        if (existingVehicleData != null) {
            System.out.println("Enter updated Vehicle Data Details:");

            int TypeId = vehicleData.getVehicleTypeId();

            int CurrentBattery = vehicleData.getCurrentBattery();

            Boolean ChargingFlag = vehicleData.isChargingFlag();

            System.out.print("Route Id: ");
            int RouteId = scanner.nextInt();

            existingVehicleData.setVehicleTypeId(TypeId);
            existingVehicleData.setCurrentBattery(CurrentBattery);
            existingVehicleData.setChargingFlag(ChargingFlag);
            existingVehicleData.setRouteId(RouteId);

            vehicleDataController.updateVehicleData(existingVehicleData);

            System.out.println("Vehicle Data updated successfully!");
        } else {
            System.out.println("Vehicle Data not found with Vehicle Id:" + VehicleId);
        }
    }

//    private void deleteVehicleData() {
//        System.out.println("Enter Vehicle Data Id to delete: ");
//        int VehicleId = scanner.nextInt();
//        scanner.nextLine();
//
//        VehicleData existingVehicleData = vehicleDataController.getVehicleDataById(VehicleId);
//        if (existingVehicleData != null) {
//            vehicleDataController.deleteVehicleData(VehicleId);
//            System.out.println("Vehicle Data deleted successfully!");
//        } else {
//            System.out.println("Vehicle Data not found with Vehicle Id: " + VehicleId);
//        }
//    }

    private void displayVehicleDatas(List<VehicleData> vehicleDatas) {
        System.out.println("List of Vehicle Datas:");
        for (VehicleData vehicleData : vehicleDatas) {
            displayVehicleData(vehicleData);
        }
    }

    private void displayVehicleData(VehicleData vehicleData) {
        System.out.println("Vehicle Id: " + vehicleData.getVehicleId());
        System.out.println("Vehicle Type Id: " + vehicleData.getVehicleTypeId());
        System.out.println("Current Battery: " + vehicleData.getCurrentBattery());
        System.out.println("Needs Charging: " + vehicleData.isChargingFlag());
        System.out.println("Route Id: " + vehicleData.getRouteId());
        System.out.println("---------------------------------");
    }
}

package PresentationLayer.menus;

import DataAccessLayer.models.VehicleData;
import DataAccessLayer.models.VehicleTable;
import DataAccessLayer.models.VehicleTypes;
import DataAccessLayer.repositories.VehicleDataRepository;
import PresentationLayer.controller.VehicleDataController;
import PresentationLayer.controller.VehicleTableController;
import PresentationLayer.controller.VehicleTypeController;


import java.util.List;
import java.util.Scanner;

public class VehicleTableMenu {
    private VehicleTableController vehicleTableController;
    private Scanner scanner;

    public VehicleTableMenu() {
        this.vehicleTableController = new VehicleTableController();
        this.scanner = new Scanner(System.in);
    }

    public void start() {
        int choice;
        do {
            System.out.println("Vehicle Table Management Menu");
            System.out.println("1. Create Vehicle");
            System.out.println("2. Update Vehicle");
            System.out.println("3. Delete Vehicle");
            System.out.println("4. Display Vehicle For Id");
            System.out.println("5. Display Vehicle For Name");
            System.out.println("6. Display All Vehicles");
            System.out.println("0. Return");
            System.out.print("Enter your choice: ");

            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    addVehicleTable();
                    break;
                case 2:
                    updateVehicleTable();
                    break;
                case 3:
                    deleteVehicleTable();
                    break;
                case 4:
                    viewVehicleTableById();
                    break;
                case 5:
                    viewVehicleTableByName();
                    break;
                case 6:
                    viewAllVehicleTables();
                    break;
                case 0:
                    System.out.println("Exiting Vehicle Table Management Menu. Goodbye!");
                    break;
            }
        } while (choice != 0);
    }

    private void viewAllVehicleTables() {
        List<VehicleTable> vehicleTables = vehicleTableController.getAllVehicleTable();
        displayVehicleTables(vehicleTables);
    }

    private void viewVehicleTableById() {
        System.out.print("Enter Vehicle ID: ");
        System.out.println("Enter 0 to return");
        int Id = scanner.nextInt();
        scanner.nextLine();
        if(Id == 0){
            VehicleTableMenu vehicleTableMenu = new VehicleTableMenu();
            vehicleTableMenu.start();
        }
        VehicleTable vehicleTable = vehicleTableController.getVehicleTableById(Id);
        if (vehicleTable != null) {
            displayVehicleTable(vehicleTable);
        } else {
            System.out.println("Vehicle Table not found with ID: " + Id);
        }
    }

    private void addVehicleTable() {
        //Method for creating Vehicle and corresponding vehicle data

        //Collecting User input
        System.out.println("Enter Vehicle Table Details:");
        System.out.println("Enter 0 to return");

        System.out.print("Type Id: ");
        int TypeId = scanner.nextInt();
        scanner.nextLine();
        if(TypeId == 0){
            VehicleTableMenu vehicleTableMenu = new VehicleTableMenu();
            vehicleTableMenu.start();
        }

        System.out.print("Name: ");
        String Name = scanner.nextLine();
        scanner.nextLine();
        if(Name == "0"){
            VehicleTableMenu vehicleTableMenu = new VehicleTableMenu();
            vehicleTableMenu.start();
        }

        VehicleTypeController vehicleTypeController = new VehicleTypeController();
        VehicleTypes vehicleTypes = vehicleTypeController.getVehicleTypeById(TypeId);

        System.out.println("Enter battery charge. Use 0 for max.");
        System.out.print("Battery: ");
        int Battery = scanner.nextInt();
        scanner.nextLine();
        if(Battery == 0){
            Battery = vehicleTypes.getBatteryCapacity();
        }


        boolean ChargingFlag;
        if(Battery < (vehicleTypes.getBatteryCapacity() * 0.2)){
            ChargingFlag = true;
        }
        else {
            ChargingFlag = false;
        }

        System.out.println("Route Id:");
        int RouteId = scanner.nextInt();
        scanner.nextLine();
        if(RouteId == 0){
            VehicleTableMenu vehicleTableMenu = new VehicleTableMenu();
            vehicleTableMenu.start();
        }

        VehicleTableController newVehicleTableController = new VehicleTableController();
        VehicleTable newVehicleTable = new VehicleTable(TypeId, Name);
        newVehicleTableController.createVehicleTable(newVehicleTable);


        newVehicleTable = newVehicleTableController.getVehicleTableByName(Name);

        //Initiating vehicle table object and vehicle data object
        VehicleDataController newVehicleData = new VehicleDataController();
        VehicleData vehicleData = new VehicleData(newVehicleTable.getId(), TypeId, Battery, ChargingFlag, RouteId);
        newVehicleData.createVehicleData(vehicleData);


        //Creating vehicle table object in database
//        vehicleTableController.createVehicleTable(newVehicleTable);
        System.out.println("Vehicle Table added successfully!");
    }

    private void updateVehicleTable() {
        System.out.print("Enter Vehicle Table ID to update: ");
        System.out.println("Enter 0 to return");
        int Id = scanner.nextInt();
        scanner.nextLine();
        if(Id == 0){
            VehicleTableMenu vehicleTableMenu = new VehicleTableMenu();
            vehicleTableMenu.start();
        }
        VehicleTable existingVehicleTable = vehicleTableController.getVehicleTableById(Id);
        if (existingVehicleTable != null) {
            System.out.println("Enter updated Vehicle Table Details:");
            System.out.print("Type Id: ");
            int TypeId = scanner.nextInt();
            scanner.nextLine();
            System.out.print("Name: ");
            String Name = scanner.nextLine();
            scanner.nextLine();

            existingVehicleTable.setTypeId(TypeId);
            existingVehicleTable.setName(Name);
            vehicleTableController.updateVehicleTable(existingVehicleTable);
            System.out.println("Vehicle Table updated successfully!");
        } else {
            System.out.println("Vehicle Table not found with ID: " + Id);
        }
    }

    private void deleteVehicleTable() {
        System.out.print("Enter Vehicle Table ID to delete: ");
        System.out.println("Enter 0 to return");
        int VehicleId = scanner.nextInt();
        scanner.nextLine();
        if(VehicleId == 0){
            VehicleTableMenu vehicleTableMenu = new VehicleTableMenu();
            vehicleTableMenu.start();
        }
        VehicleTable existingVehicleTable = vehicleTableController.getVehicleTableById(VehicleId);
        if (existingVehicleTable != null) {
            vehicleTableController.deleteVehicleTable(VehicleId);
            System.out.println("Vehicle Table deleted successfully!");
        } else {
            System.out.println("Vehicle Table not found with ID: " + VehicleId);
        }
    }

    private void viewVehicleTableByName() {
        System.out.print("Enter Vehicle Name: ");
        System.out.println("Enter 0 to return");
        String Name = scanner.nextLine();
        if(Name == "0"){
            VehicleTableMenu vehicleTableMenu = new VehicleTableMenu();
            vehicleTableMenu.start();
        }
        VehicleTable vehicleTable = vehicleTableController.getVehicleTableByName(Name);
        if (vehicleTable != null) {
            displayVehicleTable(vehicleTable);
        } else {
            System.out.println("Vehicle Table not found with Name: " + Name);
        }
    }

//    private void chargeBattery(){
//        System.out.println("Enter Vehicle Id of the vehicle to charge battery");
//        System.out.println("Enter 0 to return");
//        int VehicleId = scanner.nextInt();
//        scanner.nextLine();
//        if(VehicleId == 0){
//            VehicleTableMenu vehicleTableMenu = new VehicleTableMenu();
//            vehicleTableMenu.start();
//        }
//        VehicleDataRepository vehicleDataRepository = new VehicleDataRepository();
//        vehicleDataRepository.chargeBattery(VehicleId);
//        System.out.println("Battery charged");
//    }
//
//    private void performRoute(){
//        System.out.println("Enter Vehicle Id of the vehicle to perform route");
//        System.out.println("Enter 0 to return");
//        int VehicleId = scanner.nextInt();
//        scanner.nextLine();
//        if(VehicleId == 0){
//            VehicleTableMenu vehicleTableMenu = new VehicleTableMenu();
//            vehicleTableMenu.start();
//        }
//        VehicleDataRepository vehicleDataRepository = new VehicleDataRepository();
//        vehicleDataRepository.performRoute(VehicleId);
//    }

    private void displayVehicleTables(List<VehicleTable> vehicleTables) {
        System.out.println("List of Vehicle Tables:");
        for (VehicleTable vehicleTable : vehicleTables) {
            displayVehicleTable(vehicleTable);
        }
    }

    private void displayVehicleTable(VehicleTable vehicleTable) {
        System.out.println("Id: " + vehicleTable.getId());
        System.out.println("Type Id: " + vehicleTable.getTypeId());
        System.out.println("Name: " + vehicleTable.getName());
        System.out.println("---------------------------------");
    }
}

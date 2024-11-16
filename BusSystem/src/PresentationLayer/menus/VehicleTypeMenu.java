package PresentationLayer.menus;

import PresentationLayer.controller.VehicleTypeController;
import DataAccessLayer.models.VehicleTypes;

import java.util.List;
import java.util.Scanner;

public class VehicleTypeMenu{
    private VehicleTypeController vehicleTypeController;
    private Scanner scanner;

    public VehicleTypeMenu() {
        this.vehicleTypeController = new VehicleTypeController();
        this.scanner = new Scanner(System.in);
    }

    public void start() {
        int choice;
        do {
            System.out.println("Vehicle Type");
            System.out.println("1. Add Vehicle Type");
            System.out.println("2. Update Vehicle Type");
            System.out.println("3. Delete Vehicle Type");
            System.out.println("4. Display Vehicle Type");
            System.out.println("5. Display All Vehicle Types");
            System.out.println("0. Return");
            System.out.print("Enter your choice: ");

            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    addVehicleType();
                    break;
                case 2:
                    updateVehicleType();
                    break;
                case 3:
                    deleteVehicleType();
                    break;
                case 4:
                    viewVehicleTypeById();
                    break;
                case 5:
                    viewAllVehicleTypes();
                    break;
                case 0:
                    System.out.println("Exiting Vehicle Type Management Menu. Goodbye!");
                    break;
            }
        } while (choice != 0);
    }

    private void viewAllVehicleTypes() {
        List<VehicleTypes> vehicleTypes = vehicleTypeController.getAllVehicleTypes();
        displayVehicleTypes(vehicleTypes);
    }

    private void viewVehicleTypeById() {
        System.out.print("Enter Vehicle Type ID: ");
        System.out.println("Enter 0 to return");
        int TypeId = scanner.nextInt();
        if(TypeId == 0){
            VehicleTypeMenu vehicleTypeMenu = new VehicleTypeMenu();
            vehicleTypeMenu.start();
        }
        VehicleTypes vehicleTypes = vehicleTypeController.getVehicleTypeById(TypeId);
        if (vehicleTypes != null) {
            displayVehicleType(vehicleTypes);
        } else {
            System.out.println("Vehicle type not found with ID: " + TypeId);
        }
    }

    private void addVehicleType() {
        System.out.println("Enter Vehicle Type Details:");
        System.out.println("Enter 0 to return");
        System.out.print("Type Name: ");
        String TypeName = scanner.nextLine();
        if(TypeName == "0"){
            VehicleTypeMenu vehicleTypeMenu = new VehicleTypeMenu();
            vehicleTypeMenu.start();
        }
        System.out.print("Battery capacity: ");
        int BatteryCapacity = scanner.nextInt();
        if(BatteryCapacity == 0){
            VehicleTypeMenu vehicleTypeMenu = new VehicleTypeMenu();
            vehicleTypeMenu.start();
        }
        VehicleTypes newVehicleType = new VehicleTypes(TypeName, BatteryCapacity);
        vehicleTypeController.createVehicleType(newVehicleType);
        System.out.println("Vehicle Type added successfully!");
    }

    private void updateVehicleType() {
        System.out.print("Enter Vehicle Type ID to update: ");
        System.out.println("Enter 0 to return");
        int TypeId = scanner.nextInt();
        scanner.nextLine();
        if(TypeId == 0){
            VehicleTypeMenu vehicleTypeMenu = new VehicleTypeMenu();
            vehicleTypeMenu.start();
        }
        VehicleTypes existingVehicleType = vehicleTypeController.getVehicleTypeById(TypeId);
        if (existingVehicleType != null) {
            System.out.println("Enter updated Vehicle Type Details:");
            System.out.print("Type Name: ");
            String typeName = scanner.nextLine();
            scanner.nextLine();
            System.out.print("Battery capacity: ");
            int batteryCapacity = scanner.nextInt();
            scanner.nextLine();

            existingVehicleType.setTypeName(typeName);
            existingVehicleType.setBatteryCapacity(batteryCapacity);
            vehicleTypeController.updateVehicleType(existingVehicleType);
            System.out.println("VehicleType updated successfully!");
        } else {
            System.out.println("Vehicle Type not found with ID: " + TypeId);
        }
    }

    private void deleteVehicleType() {
        System.out.print("Enter Vehicle Type ID to delete: ");
        System.out.println("Enter 0 to return");
        int TypeId = scanner.nextInt();
        scanner.nextLine();
        if(TypeId == 0){
            VehicleTypeMenu vehicleTypeMenu = new VehicleTypeMenu();
            vehicleTypeMenu.start();
        }
        System.out.println("By deleting the vehicle id you would also delete the vehicle/s with the same type id");
        System.out.println("Would you like to continue? Y/N");
        String deleteChoice = scanner.nextLine();
        scanner.nextLine();
        if(deleteChoice == "Y") {
            VehicleTypes existingVehicleType = vehicleTypeController.getVehicleTypeById(TypeId);
            if (existingVehicleType != null) {
                vehicleTypeController.deleteVehicleType(TypeId);
                System.out.println("Vehicle type deleted successfully!");
            } else {
                System.out.println("Vehicle Type not found with ID: " + TypeId);
            }
        }
    }

    private void displayVehicleTypes(List<VehicleTypes> vehicleTypes) {
        System.out.println("List of Vehicle Types:");
        for (VehicleTypes vehicleType : vehicleTypes) {
            displayVehicleType(vehicleType);
        }
    }

    private void displayVehicleType(VehicleTypes vehicleTypes) {
        System.out.println("Type Id: " + vehicleTypes.getTypeId());
        System.out.println("Type Name: " + vehicleTypes.getTypeName());
        System.out.println("Battery Capacity: " + vehicleTypes.getBatteryCapacity());
        System.out.println("---------------------------------");
    }
}
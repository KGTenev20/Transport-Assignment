package PresentationLayer.controller;

import BusinessLayer.service.VehicleTableService;
import DataAccessLayer.models.VehicleTable;


import java.util.List;

public class VehicleTableController {
    private VehicleTableService vehicleTableService;

    public VehicleTableController() {
        this.vehicleTableService = new VehicleTableService();
    }

    public List<VehicleTable> getAllVehicleTable() {
        return vehicleTableService.getAllVehicleTable();
    }

    public VehicleTable getVehicleTableById(int Id) {
        return vehicleTableService.getVehicleTableById(Id);
    }

    public VehicleTable getVehicleTableByName(String Name) {
        return vehicleTableService.getVehicleTableByName(Name);
    }

    public void updateVehicleTable(VehicleTable vehicleTable) {
        vehicleTableService.updateVehicleTable(vehicleTable);
    }

    public void deleteVehicleTable(int VehicleId) {
        vehicleTableService.deleteVehicleTable(VehicleId);
    }

    public void createVehicleTable(VehicleTable vehicleTable) {
        vehicleTableService.createVehicleTable(vehicleTable);
    }
}

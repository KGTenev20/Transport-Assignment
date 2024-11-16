package PresentationLayer.controller;

import BusinessLayer.service.BusRoutesService;
import BusinessLayer.service.VehicleDataService;
import DataAccessLayer.models.BusRoutes;
import DataAccessLayer.models.VehicleData;

import java.util.List;

public class VehicleDataController {
    private VehicleDataService vehicleDataService;

    public VehicleDataController() {
        this.vehicleDataService = new VehicleDataService();
    }

    public List<VehicleData> getAllVehicleData() {
        return vehicleDataService.getAllVehicleData();
    }

    public VehicleData getVehicleDataById(int VehicleId) {
        return vehicleDataService.getVehicleDataById(VehicleId);
    }

    public void createVehicleData(VehicleData vehicleData) {
        vehicleDataService.createVehicleData(vehicleData);
    }

    public void updateVehicleData(VehicleData vehicleData) {
        vehicleDataService.updateVehicleData(vehicleData);
    }

    public void deleteVehicleData(int VehicleId) {
        vehicleDataService.deleteVehicleData(VehicleId);
    }
}

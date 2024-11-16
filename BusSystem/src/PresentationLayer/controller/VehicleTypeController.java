package PresentationLayer.controller;

import BusinessLayer.service.VehicleTypeService;
import DataAccessLayer.models.VehicleTypes;

import java.util.List;

public class VehicleTypeController {
    private VehicleTypeService vehicleTypeService;

    public VehicleTypeController() {
        this.vehicleTypeService = new VehicleTypeService();
    }

    public List<VehicleTypes> getAllVehicleTypes() {
        return vehicleTypeService.getAllVehicleTypes();
    }

    public VehicleTypes getVehicleTypeById(int TypeId) {
        return vehicleTypeService.getVehicleTypeById(TypeId);
    }

    public void updateVehicleType(VehicleTypes vehicleTypes) {
        vehicleTypeService.updateVehicleType(vehicleTypes);
    }

    public void deleteVehicleType(int TypeId) {
        vehicleTypeService.deleteVehicleType(TypeId);
    }

    public void createVehicleType(VehicleTypes vehicleTypes) {
        vehicleTypeService.createVehicleType(vehicleTypes);
    }
}

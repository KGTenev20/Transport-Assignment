package BusinessLayer.service;

import DataAccessLayer.models.VehicleTypes;
import DataAccessLayer.repositories.VehicleTypeRepository;

import java.util.List;

public class VehicleTypeService {
    private VehicleTypeRepository vehicleTypeRepository;

    public VehicleTypeService() {
        this.vehicleTypeRepository = new VehicleTypeRepository();
    }

    public List<VehicleTypes> getAllVehicleTypes() {
        return VehicleTypeRepository.getAllVehicleTypes();
    }

    public VehicleTypes getVehicleTypeById(int TypeId) {
        return VehicleTypeRepository.getVehicleTypeById(TypeId);
    }

    public void updateVehicleType(VehicleTypes vehicleTypes) {
        VehicleTypeRepository.updateVehicleType(vehicleTypes);
    }

    public void deleteVehicleType(int TypeId) {
        VehicleTypeRepository.deleteVehicleType(TypeId);
    }

    public void createVehicleType(VehicleTypes vehicleTypes) {
        VehicleTypeRepository.createVehicleType(vehicleTypes);
    }
}
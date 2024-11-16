package BusinessLayer.service;

import DataAccessLayer.models.VehicleData;
import DataAccessLayer.repositories.VehicleDataRepository;

import java.util.List;

public class VehicleDataService {
    private VehicleDataRepository vehicleDataRepository;

    public VehicleDataService(){
        this.vehicleDataRepository = new VehicleDataRepository();
    }

    public List<VehicleData> getAllVehicleData() {
        return VehicleDataRepository.getAllVehicleDataTable();
    }

    public VehicleData getVehicleDataById(int VehicleId) {
        return VehicleDataRepository.getVehicleDataById(VehicleId);
    }

    public void updateVehicleData(VehicleData vehicleData) {
        VehicleDataRepository.updateVehicleData(vehicleData);
    }

    public void deleteVehicleData(int VehicleId) {
        VehicleDataRepository.deleteVehicleData(VehicleId);
    }

    public void createVehicleData(VehicleData vehicleData) {
        VehicleDataRepository.createVehicleData(vehicleData);
    }
}

package BusinessLayer.service;

import DataAccessLayer.models.VehicleTable;
import DataAccessLayer.repositories.VehicleTableRepository;

import java.util.List;

public class VehicleTableService {
    private VehicleTableRepository vehicleTableRepository;

    public VehicleTableService() {
        this.vehicleTableRepository = new VehicleTableRepository();
    }

    public List<VehicleTable> getAllVehicleTable() {
        return VehicleTableRepository.getAllVehicleTable();
    }

    public VehicleTable getVehicleTableById(int Id) {
        return VehicleTableRepository.getVehicleTableById(Id);
    }

    public void updateVehicleTable(VehicleTable vehicleTable) {
        VehicleTableRepository.updateVehicleTable(vehicleTable);
    }

    public void deleteVehicleTable(int VehicleId) {
        VehicleTableRepository.deleteVehicleTable(VehicleId);
    }

    public void createVehicleTable(VehicleTable vehicleTable) {
        VehicleTableRepository.createVehicleTable(vehicleTable);
    }

    public VehicleTable getVehicleTableByName(String Name){
        return VehicleTableRepository.getVehicleTableByName(Name);
    }
}

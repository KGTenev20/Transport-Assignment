import static org.junit.Assert.*;

import DataAccessLayer.models.VehicleData;
import DataAccessLayer.repositories.VehicleDataRepository;
import PresentationLayer.controller.BusRoutesController;
import PresentationLayer.controller.VehicleDataController;
import PresentationLayer.controller.VehicleTypeController;
import org.junit.Test;

public class VehicleDataRepositoryTest {

    @Test
    public void testCreateVehicleData() {
        VehicleDataController vehicleDataController = new VehicleDataController();
        VehicleDataRepository vehicleDataRepository = new VehicleDataRepository(vehicleDataController, new BusRoutesController(), new VehicleTypeController());

        VehicleData newVehicleData = new VehicleData();
        newVehicleData.setVehicleId(2);
        newVehicleData.setVehicleTypeId(2);
        newVehicleData.setCurrentBattery(80);
        newVehicleData.setChargingFlag(false);
        newVehicleData.setRouteId(2);

        vehicleDataRepository.createVehicleData(newVehicleData);

        // Retrieve the created vehicle data
        VehicleData createdVehicleData = vehicleDataController.getVehicleDataById(2);

        assertNotNull(createdVehicleData);
        assertEquals(2, createdVehicleData.getVehicleId());
        assertEquals(2, createdVehicleData.getVehicleTypeId());
        assertEquals(80, createdVehicleData.getCurrentBattery());
        assertFalse(createdVehicleData.isChargingFlag());
        assertEquals(2, createdVehicleData.getRouteId());
    }

    @Test
    public void testUpdateVehicleData() {
        VehicleDataController vehicleDataController = new VehicleDataController();
        VehicleDataRepository vehicleDataRepository = new VehicleDataRepository(vehicleDataController, new BusRoutesController(), new VehicleTypeController());

        // Assuming vehicle with id 1 already exists in the database
        VehicleData existingVehicleData = new VehicleData();
        existingVehicleData.setVehicleId(1);
        existingVehicleData.setVehicleTypeId(1);
        existingVehicleData.setCurrentBattery(60);
        existingVehicleData.setChargingFlag(false);
        existingVehicleData.setRouteId(1);

        // Update the battery level
        existingVehicleData.setCurrentBattery(70);

        // Call the update method
        vehicleDataRepository.updateVehicleData(existingVehicleData);

        // Retrieve the updated vehicle data
        VehicleData updatedVehicleData = vehicleDataController.getVehicleDataById(1);

        assertNotNull(updatedVehicleData);
        assertEquals(1, updatedVehicleData.getVehicleId());
        assertEquals(1, updatedVehicleData.getVehicleTypeId());
        assertEquals(70, updatedVehicleData.getCurrentBattery());
        assertFalse(updatedVehicleData.isChargingFlag());
        assertEquals(1, updatedVehicleData.getRouteId());
    }

    @Test
    public void testDeleteVehicleData() {
        VehicleDataController vehicleDataController = new VehicleDataController();
        VehicleDataRepository vehicleDataRepository = new VehicleDataRepository(vehicleDataController, new BusRoutesController(), new VehicleTypeController());

        // Assuming vehicle with id 3 already exists in the database
        int vehicleIdToDelete = 3;

        // Call the delete method
        vehicleDataRepository.deleteVehicleData(vehicleIdToDelete);

        // Attempt to retrieve the deleted vehicle data
        VehicleData deletedVehicleData = vehicleDataController.getVehicleDataById(vehicleIdToDelete);

        assertNull(deletedVehicleData);
    }
}

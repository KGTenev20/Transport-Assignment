import DataAccessLayer.models.BusRoutes;
import DataAccessLayer.models.VehicleData;
import DataAccessLayer.models.VehicleTypes;
import DataAccessLayer.repositories.VehicleDataRepository;
import PresentationLayer.controller.BusRoutesController;
import PresentationLayer.controller.VehicleDataController;
import PresentationLayer.controller.VehicleTypeController;
import org.junit.jupiter.api.*;

import static org.junit.Assert.*;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertTrue;

public class PerformRouteTest {
    @Test
        public void testPerformRoute() {
        try{
            // Create a mock VehicleData object with initial values
            VehicleData vehicleData = new VehicleData();
            vehicleData.setVehicleTypeId(2);
            vehicleData.setCurrentBattery(50); // assuming an initial battery level
            vehicleData.setChargingFlag(false);
            vehicleData.setRouteId(2);

            // Create a mock BusRoutes object with the required route mileage
            BusRoutes busRoutes = new BusRoutes();
            busRoutes.setRouteMileage(30); // set a route mileage value

            // Create actual instances of controllers
            VehicleDataController vehicleDataController = new VehicleDataController();
            BusRoutesController busRoutesController = new BusRoutesController();
            VehicleTypeController vehicleTypeController = new VehicleTypeController();

            // Set initial data in controllers (assuming you have proper methods for setting data)
            vehicleDataController.createVehicleData(vehicleData);
            busRoutesController.createBusRoute(busRoutes);

            // Create an instance of the repository with controllers passed to the constructor
            VehicleDataRepository vehicleDataRepository = new VehicleDataRepository(vehicleDataController, busRoutesController, vehicleTypeController);

            // Call performRoute method
            vehicleDataRepository.performRoute(vehicleData.getVehicleId());

            // Retrieve updated vehicleData after calling performRoute
            VehicleData updatedVehicleData = vehicleDataController.getVehicleDataById(1);

            // Check if the charging flag is set based on the battery level
            assertTrue(updatedVehicleData.isChargingFlag());
        }catch (Exception e){
           System.out.println(e.getMessage());
        }
    }
}

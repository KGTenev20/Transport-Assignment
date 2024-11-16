package DataAccessLayer.repositories;

import DataAccessLayer.models.BusRoutes;
import DataAccessLayer.models.VehicleData;
import DataAccessLayer.models.VehicleTypes;
import PresentationLayer.controller.BusRoutesController;
import PresentationLayer.controller.VehicleDataController;
import DataAccessLayer.repositories.BusRoutesRepository;

import PresentationLayer.controller.VehicleTypeController;
import utils.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class VehicleDataRepository {
    private static Connection connection = null;

    private VehicleDataController vehicleDataController;
    private BusRoutesController busRoutesController;
    private VehicleTypeController vehicleTypeController;

    public VehicleDataRepository(){
        DBConnection dbConnection = new DBConnection();
        connection = DBConnection.getInstance();
    }

    public VehicleDataRepository(VehicleDataController vehicleDataController, BusRoutesController busRoutesController, VehicleTypeController vehicleTypeController) {
        this.vehicleDataController = vehicleDataController;
        this.busRoutesController = busRoutesController;
        this.vehicleTypeController = vehicleTypeController;
    }

    private static VehicleData mapResultSetToVehicleData(ResultSet resultSet) throws SQLException{
        VehicleData vehicleData = new VehicleData();

        vehicleData.setVehicleId(resultSet.getInt("VehicleId"));
        vehicleData.setVehicleTypeId(resultSet.getInt("VehicleTypeId"));
        vehicleData.setCurrentBattery(resultSet.getInt("CurrentBattery"));
        vehicleData.setChargingFlag(resultSet.getBoolean("ChargingFlag"));
        vehicleData.setRouteId(resultSet.getInt("RouteId"));
        return vehicleData;
    }

    public static List<VehicleData> getAllVehicleDataTable(){
        List<VehicleData> vehicleData = new ArrayList<>();

        String query = "SELECT * FROM VehicleData";

        try{
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();

            while(resultSet.next()){
                vehicleData.add(mapResultSetToVehicleData(resultSet));
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return vehicleData;
    }

    public static VehicleData getVehicleDataById(int VehicleId){
        String query = "SELECT * FROM VehicleData WHERE VehicleId = ?";
        try{
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, VehicleId);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                return mapResultSetToVehicleData(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static void createVehicleData(VehicleData vehicleData){
        String query = "INSERT INTO VehicleData (VehicleId, VehicleTypeId, CurrentBattery, ChargingFlag, RouteId) VALUES (?, ?, ?, ?, ?)";

        try{
            PreparedStatement preparedStatement = connection.prepareStatement(query);

            preparedStatement.setInt(1, vehicleData.getVehicleId());
            preparedStatement.setInt(2, vehicleData.getVehicleTypeId());
            preparedStatement.setInt(3, vehicleData.getCurrentBattery());
            preparedStatement.setBoolean(4, vehicleData.isChargingFlag());
            preparedStatement.setInt(5, vehicleData.getRouteId());

            //check if there is vehicle data already existing with vehicle id
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void updateVehicleData(VehicleData vehicleData){
        String query = "UPDATE VehicleData SET VehicleTypeId = ?, CurrentBattery = ?, ChargingFlag = ?, RouteId = ? WHERE VehicleId = ?";
        try{
            PreparedStatement preparedStatement = connection.prepareStatement(query);

            preparedStatement.setInt(1, vehicleData.getVehicleTypeId());
            preparedStatement.setInt(2, vehicleData.getCurrentBattery());
            preparedStatement.setBoolean(3, vehicleData.isChargingFlag());
            preparedStatement.setInt(4, vehicleData.getRouteId());
            preparedStatement.setInt(5, vehicleData.getVehicleId());

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void deleteVehicleData(int VehicleId) {
        String query = "DELETE FROM VehicleData WHERE VehicleId = ?";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);

            preparedStatement.setInt(1, VehicleId);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void chargeBattery(int vehicleId){
        VehicleDataController vehicleDataController = new VehicleDataController();
        VehicleData vehicleData = vehicleDataController.getVehicleDataById(vehicleId);

        VehicleTypeController vehicleTypeController = new VehicleTypeController();
        VehicleTypes vehicleTypes = vehicleTypeController.getVehicleTypeById(vehicleData.getVehicleTypeId());

        vehicleData.setCurrentBattery(vehicleTypes.getBatteryCapacity());
        vehicleData.setChargingFlag(false);
        vehicleDataController.updateVehicleData(vehicleData);
    }

    public void chargeLowBatteries(){

        VehicleDataController vehicleDataController = new VehicleDataController();
        List<VehicleData> vehicleDataList = vehicleDataController.getAllVehicleData();

        for (VehicleData vehicleData : vehicleDataList) {

            if(vehicleData.isChargingFlag() == true)
            {
                VehicleDataRepository vehicleDataRepository = new VehicleDataRepository();
                vehicleDataRepository.chargeBattery(vehicleData.getVehicleId());
            }
        }
    }

    public void chargeAllBatteries(){
        VehicleDataController vehicleDataController = new VehicleDataController();
        List<VehicleData> vehicleDataList = vehicleDataController.getAllVehicleData();

        for (VehicleData vehicleData : vehicleDataList) {
                VehicleDataRepository vehicleDataRepository = new VehicleDataRepository();
                vehicleDataRepository.chargeBattery(vehicleData.getVehicleId());
        }
    }

    public void performRoute(int vehicleId){
        VehicleDataController vehicleDataController = new VehicleDataController();
        VehicleData vehicleData = vehicleDataController.getVehicleDataById(vehicleId);

        BusRoutesController busRoutesController = new BusRoutesController();
        BusRoutes busRoutes = busRoutesController.getBusRoutesById(vehicleData.getRouteId());

        VehicleTypeController vehicleTypeController = new VehicleTypeController();
        VehicleTypes vehicleTypes = vehicleTypeController.getVehicleTypeById(vehicleData.getVehicleTypeId());

        if(vehicleData.getCurrentBattery() < busRoutes.getRouteMileage()){
            System.out.println("Not enough battery to perform route for vehicle" + vehicleData.getVehicleId() + "with route" + vehicleData.getRouteId());
        }
        else {
            vehicleData.setCurrentBattery(vehicleData.getCurrentBattery() - busRoutes.getRouteMileage());
            if(vehicleData.getCurrentBattery() <  (vehicleTypes.getBatteryCapacity() * 0.2))
            {
                vehicleData.setChargingFlag(true);
                vehicleDataController.updateVehicleData(vehicleData);
            }
            else{
                vehicleData.setChargingFlag(false);
                vehicleDataController.updateVehicleData(vehicleData);
            }
        }
    }

}

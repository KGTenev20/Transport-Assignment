package DataAccessLayer.repositories;

import DataAccessLayer.models.VehicleTable;
import utils.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class VehicleTableRepository {
    private static Connection connection = null;

    public VehicleTableRepository(){
        DBConnection dbConnection = new DBConnection();
        connection = DBConnection.getInstance();
    }

    private static VehicleTable mapResultSetToVehicleTable(ResultSet resultSet) throws SQLException{
        VehicleTable vehicleTable = new VehicleTable();

        vehicleTable.setId(resultSet.getInt("Id"));
        vehicleTable.setTypeId(resultSet.getInt("TypeId"));
        vehicleTable.setName(resultSet.getString("Name"));
        return vehicleTable;
    }

    public static List<VehicleTable> getAllVehicleTable(){
        List<VehicleTable> vehicleTables = new ArrayList<>();

        String query = "SELECT * FROM VehicleTable";

        try{
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();

            while(resultSet.next()){
                vehicleTables.add(mapResultSetToVehicleTable(resultSet));
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return vehicleTables;
    }

    public static VehicleTable getVehicleTableById(int Id){
        String query = "SELECT * FROM VehicleTable WHERE Id = ?";
        try{
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, Id);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                return mapResultSetToVehicleTable(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static VehicleTable getVehicleTableByName(String Name){
        String query = "SELECT * FROM VehicleTable WHERE Name = ?";
        try{
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, Name);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                return mapResultSetToVehicleTable(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static void createVehicleTable(VehicleTable vehicleTable){
        String query = "INSERT INTO VehicleTable (TypeId, Name) VALUES (?, ?)";

        try{
            PreparedStatement preparedStatement = connection.prepareStatement(query);

            preparedStatement.setInt(1, vehicleTable.getTypeId());
            preparedStatement.setString(2, vehicleTable.getName());

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void updateVehicleTable(VehicleTable vehicleTable){
        String query = "UPDATE VehicleTable SET TypeId = ?, Name = ? WHERE Id = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);

            preparedStatement.setInt(1, vehicleTable.getTypeId());
            preparedStatement.setString(2, vehicleTable.getName());
            preparedStatement.setInt(3, vehicleTable.getId());

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void deleteVehicleTable(int vehicleId) {
        deleteVehicleData(vehicleId);
        String deleteVehicleQuery = "DELETE FROM VehicleTable WHERE Id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(deleteVehicleQuery)) {
            preparedStatement.setInt(1, vehicleId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void deleteVehicleData(int vehicleId) {
        String deleteVehicleDataQuery = "DELETE FROM VehicleData WHERE VehicleId = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(deleteVehicleDataQuery)) {
            preparedStatement.setInt(1, vehicleId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

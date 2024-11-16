package DataAccessLayer.repositories;

import DataAccessLayer.models.VehicleTypes;
import utils.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class VehicleTypeRepository {
    private static Connection connection = null;
    public VehicleTypeRepository() {
        DBConnection dbConnection = new DBConnection();
        connection = DBConnection.getInstance();
    }

    private static VehicleTypes mapResultSetToVehicleType(ResultSet resultSet) throws SQLException{
        VehicleTypes vehicleType = new VehicleTypes();

        vehicleType.setTypeId(resultSet.getInt("TypeId"));
        vehicleType.setTypeName(resultSet.getString("TypeName"));
        vehicleType.setBatteryCapacity(resultSet.getInt("BatteryCapacity"));
        return vehicleType;
    }

    public static List<VehicleTypes> getAllVehicleTypes() {
        List<VehicleTypes> vehicleTypes = new ArrayList<>();

        String query = "SELECT * FROM VehicleTypes";

        try{
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                vehicleTypes.add(mapResultSetToVehicleType(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return vehicleTypes;
    }

    public static VehicleTypes getVehicleTypeById(int typeId) {
        String query = "SELECT * FROM VehicleTypes WHERE TypeId = ?";

        try{
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, typeId);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                return mapResultSetToVehicleType(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static void createVehicleType(VehicleTypes vehicleType) {
        String query = "INSERT INTO VehicleTypes (TypeName, BatteryCapacity) VALUES (?, ?)";

        try{
            PreparedStatement preparedStatement = connection.prepareStatement(query);

            preparedStatement.setString(1, vehicleType.getTypeName());
            preparedStatement.setInt(2, vehicleType.getBatteryCapacity());

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void updateVehicleType(VehicleTypes vehicleType) {
        String query = "UPDATE VehicleTypes SET TypeName = ?, BatteryCapacity = ? WHERE TypeId = ?";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);

            preparedStatement.setString(1, vehicleType.getTypeName());
            preparedStatement.setInt(2, vehicleType.getBatteryCapacity());
            preparedStatement.setInt(3, vehicleType.getTypeId());

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public static void deleteVehicleType(int TypeId) {
        String query = "DELETE FROM VehicleTypes WHERE TypeId = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);

            preparedStatement.setInt(1, TypeId);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void deleteVehicleTable(int TypeId) {
        deleteVehicleType(TypeId);
        String deleteVehicleQuery = "DELETE FROM VehicleTable WHERE TypeId = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(deleteVehicleQuery)) {
            preparedStatement.setInt(1, TypeId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

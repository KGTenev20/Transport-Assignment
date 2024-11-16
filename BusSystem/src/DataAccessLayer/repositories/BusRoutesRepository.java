package DataAccessLayer.repositories;

import DataAccessLayer.models.BusRoutes;
import utils.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BusRoutesRepository {

    private static Connection connection = null;
    public BusRoutesRepository() {
        DBConnection dbConnection = new DBConnection();
        connection = DBConnection.getInstance();
    }

    private static BusRoutes mapResultSetToBusRoutes(ResultSet resultSet) throws SQLException {
        BusRoutes busRoutes = new BusRoutes();
        busRoutes.setRouteId(resultSet.getInt("RouteId"));
        busRoutes.setRouteMileage(resultSet.getInt("RouteMileage"));
        return busRoutes;
    }

    public static List<BusRoutes> getAllBusRoutes(){
        List<BusRoutes> busRoutes = new ArrayList<>();

        String query = "SELECT * FROM BusRoutes";

        try{
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                busRoutes.add(mapResultSetToBusRoutes(resultSet));
            }
        } catch(SQLException e){
            e.printStackTrace();
        }

        return busRoutes;
    }

    public BusRoutes getBusRouteById(int RouteId){
        String query = "SELECT * FROM BusRoutes WHERE RouteId = ?";

        try{
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, RouteId);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                return mapResultSetToBusRoutes(resultSet);
            }
        } catch(SQLException e){
            e.printStackTrace();
        }

        return null;
    }

    public void createBusRoutes(BusRoutes busRoutes){
        String query = "INSERT INTO BusRoutes (RouteMileage) VALUES (?)";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);

            preparedStatement.setInt(1, busRoutes.getRouteMileage());

            preparedStatement.executeUpdate();
        } catch(SQLException e){
            e.printStackTrace();
        }
    }

    public void updateBusRoutes(BusRoutes updatedBusRoutes){
        String query = "UPDATE BusRoutes SET RouteMileage = ? WHERE RouteId = ?";

        try{
            PreparedStatement preparedStatement = connection.prepareStatement(query);

            preparedStatement.setInt(1, updatedBusRoutes.getRouteMileage());
            preparedStatement.setInt(2, updatedBusRoutes.getRouteId());

            int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected == 0) {
                System.out.println("No Bus Routes found with ID: " + updatedBusRoutes.getRouteId());
            } else {
                System.out.println("Bus Routes updated successfully!");
            }
        } catch(SQLException e){
            e.printStackTrace();
        }
    }

    public void deleteBusRoutes(int BusRoutesId){
        String query = "DELETE FROM BusRoutes WHERE RouteId = ?";

        try{
            PreparedStatement preparedStatement = connection.prepareStatement(query);

            preparedStatement.setInt(1, BusRoutesId);

            preparedStatement.executeUpdate();
        } catch(SQLException e){
            e.  printStackTrace();
        }
    }
}

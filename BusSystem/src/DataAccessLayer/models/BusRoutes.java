package DataAccessLayer.models;

public class BusRoutes {

    int RouteId;
    int RouteMileage;

    public BusRoutes(){
        RouteId = 0;
        RouteMileage = 0;
    }

    public BusRoutes(int RouteMileage){
        this.RouteMileage = RouteMileage;
    }

    public int getRouteId() {
        return RouteId;
    }

    public void setRouteId(int routeId) {
        RouteId = routeId;
    }

    public int getRouteMileage() {
        return RouteMileage;
    }

    public void setRouteMileage(int routeMileage) {
        RouteMileage = routeMileage;
    }
}
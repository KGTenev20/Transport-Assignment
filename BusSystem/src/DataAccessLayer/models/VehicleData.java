package DataAccessLayer.models;

public class VehicleData{

    int VehicleId;
    int VehicleTypeId;
    int CurrentBattery;
    boolean ChargingFlag;
    int RouteId;

    public VehicleData(){
        VehicleId = 0;
        VehicleTypeId = 0;
        CurrentBattery = 0;
        ChargingFlag = false;
        RouteId = 0;
    }

    public VehicleData(int vehicleId, int vehicleTypeId, int currentBattery, boolean chargingFlag, int routeId){
        this.VehicleId = vehicleId;
        this.VehicleTypeId = vehicleTypeId;
        this.CurrentBattery = currentBattery;
        this.ChargingFlag = chargingFlag;
        this.RouteId = routeId;
    }

    public int getVehicleId() {
        return VehicleId;
    }

    public void setVehicleId(int vehicleId) {
        VehicleId = vehicleId;
    }

    public int getVehicleTypeId() {
        return VehicleTypeId;
    }

    public void setVehicleTypeId(int vehicleTypeId) {
        VehicleTypeId = vehicleTypeId;
    }

    public int getCurrentBattery() {
        return CurrentBattery;
    }

    public void setCurrentBattery(int currentBattery) {
        CurrentBattery = currentBattery;
    }

    public boolean isChargingFlag() {
        return ChargingFlag;
    }

    public void setChargingFlag(boolean chargingFlag) {
        ChargingFlag = chargingFlag;
    }

    public int getRouteId() {
        return RouteId;
    }

    public void setRouteId(int routeId) {
        RouteId = routeId;
    }
}

/**/



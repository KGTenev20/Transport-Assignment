package DataAccessLayer.models;

public class VehicleTypes {

    int TypeId;
    String TypeName;
    int BatteryCapacity;

    public VehicleTypes(){
        this.TypeId = 0;
        this.TypeName = "";
        this.BatteryCapacity = 0;
    }

    public VehicleTypes(String TypeName, int BatteryCapacity){
        this.TypeName = TypeName;
        this.BatteryCapacity = BatteryCapacity;
    }

    public int getTypeId() {
        return TypeId;
    }

    public void setTypeId(int typeId) {
        TypeId = typeId;
    }

    public String getTypeName() {
        return TypeName;
    }

    public void setTypeName(String typeName) {
        TypeName = typeName;
    }

    public int getBatteryCapacity() {
        return BatteryCapacity;
    }

    public void setBatteryCapacity(int batteryCapacity) {
        BatteryCapacity = batteryCapacity;
    }
}

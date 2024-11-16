package DataAccessLayer.models;

public class VehicleTable {

    int Id;
    int TypeId;
    String Name;

    public VehicleTable(){
        Id = 0;
        TypeId = 0;
        Name = "Not defined";
    }

    public VehicleTable(int TypeId, String Name){
        this.TypeId = TypeId;
        this.Name= Name;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public int getTypeId() {
        return TypeId;
    }

    public void setTypeId(int typeId) {
        TypeId = typeId;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }
}

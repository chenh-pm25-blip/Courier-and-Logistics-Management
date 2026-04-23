import java.util.ArrayList;

public class Admin extends User {
    private String employeeId;
    private ArrayList<Vehicle> vehicleList = new ArrayList<>();
    private ArrayList<MaintenanceRecord> recordList = new ArrayList<>();

    public Admin(String userId, String username, String password, String employeeId) {
        super(userId, username, password);
        this.employeeId = employeeId;
    }

    public void addRecord(MaintenanceRecord r) {
        recordList.add(r);
        System.out.println("Maintenance record added successfully!");
    }

    public void manageFleet() {
        if (vehicleList.isEmpty()) {
            System.out.println("No vehicles found.");
            return;
        }
        for (Vehicle v : vehicleList) {
            System.out.println(v);
        }
    }

    public void addVehicle(Vehicle v) {
        vehicleList.add(v);
    }

    public void deleteVehicle(String id) {
        vehicleList.removeIf(v -> v.getVehicleID().equals(id));
        System.out.println("Vehicle deleted.");
    }

    public void assignDriver() { }
    public void scheduleMaintenance() { }
    public void rescheduleShipment() { }

    public String getEmployeeId() {
        return employeeId;
    }
}
import java.util.Date;
import java.util.ArrayList;

public class Vehicle {
    private String vehicleId;
    private double mileage;
    private Date lastServiceDate;
    
    private ArrayList<MaintenanceRecord> records;

    private static Vehicle[] fleet = new Vehicle[20];
    private static int vehicleCount = 0;

    public Vehicle(String vehicleId, double mileage) {
        this.vehicleId = vehicleId;
        this.mileage = mileage;
        this.lastServiceDate = new Date();
        this.records = new ArrayList<MaintenanceRecord>();
    }

    public static void initializeVehicleData() {
        if (vehicleCount == 0) {
            addVehicleToFleet(new Vehicle("V001", 8500.0));
            addVehicleToFleet(new Vehicle("V002", 12500.5));
            addVehicleToFleet(new Vehicle("V003", 500.0));
            addVehicleToFleet(new Vehicle("V004", 9900.0));
            System.out.println("System: Vehicle data initialized.");
        }
    }

    public void updateMileage(double extraMileage) {
        if (extraMileage > 0) {
            this.mileage += extraMileage;
            System.out.println("System: Mileage updated for " + vehicleId + " to " + mileage + "km.");
        } else {
            System.out.println("Error: Maintenance mileage input must be a positive value.");
        }
    }

    public boolean checkMaintenanceStatus() {
        double serviceThreshold = 10000.0;
        return this.mileage >= serviceThreshold;
    }

    public void setMaintenanceDate(Date serviceDate) {
        this.lastServiceDate = serviceDate;
    }

    public void addMaintenanceRecord(MaintenanceRecord record) {
        if (record != null) {
            this.records.add(record);
        }
    }

    public static Vehicle findVehicle(String id) {
        for (int i = 0; i < vehicleCount; i++) {
            if (fleet[i] != null && fleet[i].getVehicleId().equalsIgnoreCase(id)) {
                return fleet[i];
            }
        }
        return null;
    }

    public static void addVehicleToFleet(Vehicle v) {
        if (vehicleCount < fleet.length) {
            fleet[vehicleCount++] = v;
        } else {
            System.out.println("Error: Fleet storage capacity reached.");
        }
    }

    // --- GETTERS ---
    public String getVehicleId() { 
    	return vehicleId; 
    }
    public double getMileage() { 
    	return mileage; 
    }
    public static Vehicle[] getFleet() { 
    	return fleet; 
    }
    public static int getVehicleCount() { 
    	return vehicleCount; 
    }

    @Override
    public String toString() {
        String status = checkMaintenanceStatus() ? "SERVICE REQUIRED" : "OPERATIONAL";
        return "Vehicle: " + vehicleId + " | Mileage: " + mileage + "km | Status: " + status;
    }
}
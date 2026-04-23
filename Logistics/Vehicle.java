import java.util.Date;

public class Vehicle {
    private String vehicleId;
    private double mileage;
    private Date lastServiceDate;

    public Vehicle(String vehicleId, double mileage, Date lastServiceDate) {
        this.vehicleId = vehicleId;
        this.mileage = mileage;
        this.lastServiceDate = lastServiceDate;
    }

    public void updateMileage() {
    }
    
    public boolean checkMaintenanceStatus() { 
    	return true; 
    }
    
    public void setMaintenanceDate() {
    }
    
    public String getVehicleID() { 
    	return vehicleId; 
    }

    @Override
    public String toString() {
        return "VehicleID: " + vehicleId + " | Mileage: " + mileage + " | Last Service: " + lastServiceDate;
    }
}
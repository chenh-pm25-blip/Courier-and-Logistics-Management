import java.util.List;
import java.util.ArrayList;

public class Courier extends User {
    private String courierId;
    private Vehicle vehicleAssigned;
    private List<Shipment> deliveryList;

    public Courier(String userId, String username, String password, String courierId, Vehicle vehicleAssigned) {
        super(userId, username, password); 
        this.courierId = courierId;
        this.vehicleAssigned = vehicleAssigned;
        this.deliveryList = new ArrayList<>(); // Empty list by default
    }

    public String getCourierId() { 
    	return courierId; 
    }
    
    public Vehicle getVehicleAssigned() { 
    	return vehicleAssigned; 
    }
    public void setVehicleAssigned(Vehicle vehicle) { 
    	this.vehicleAssigned = vehicle; 
    }

    public List<Shipment> viewDeliveryList() { 
    	return deliveryList; 
    }
    
    public void updatePackageStatus() { 
    }
    
    public void logFailedAttempt() { 
    }

    @Override
    public String toString() {
        return "User ID: " + getUserId() + " | Username: " + getUsername() + " | Courier ID: " + courierId + 
               "\nAssigned Vehicle: " + (vehicleAssigned != null ? vehicleAssigned.getVehicleId() : "None");
    }
}
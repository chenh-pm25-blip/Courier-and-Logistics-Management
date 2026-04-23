public class Courier extends User {
    private String username;
    private String courierId;
    private Vehicle assignedVehicle;

    public Courier(String userId, String username, String password, String courierId, Vehicle assignedVehicle) {
        super(userId, username, password);
        this.courierId = courierId;
        this.assignedVehicle = assignedVehicle;
    }

    public Courier(String userId, String username, String password) {
        super(userId, username, password);
        this.courierId = userId;
        this.username = username;
    }

    public String getCourierId() { 
    	return courierId; 
    }
    public void setVehicleAssigned(Vehicle v) { 
    	this.assignedVehicle = v; 
    }

    @Override
    public String toString() {
        String vId = (assignedVehicle != null) ? assignedVehicle.getVehicleId() : "None";
        return super.toString() + " | Courier ID: " + courierId + " | Username: " + username + " | Vehicle: " + vId;
    }
}
public class Courier extends User {
    private String username;
    private String courierId;
    private Vehicle assignedVehicle;
    
    private Shipment[] assignedShipments = new Shipment[20];
    private int assignedCount = 0;

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
    
    public void assignShipment(Shipment s) {
        if (assignedCount < assignedShipments.length) {
            assignedShipments[assignedCount++] = s;
        } else {
            System.out.println("Error: Courier cannot take more shipments.");
        }
    }
    
    public Shipment[] getAssignedShipments() {
        return assignedShipments;
    }
    
    public int getAssignedCount() {
        return assignedCount;
    }

    @Override
    public String toString() {
        String vId = (assignedVehicle != null) ? assignedVehicle.getVehicleId() : "None";
        return super.toString() + " | Courier ID: " + courierId + " | Username: " + username + " | Vehicle: " + vId;
    }
}
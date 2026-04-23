import java.util.Date;

public class Shipment {
    private String trackingId;
    private String shippingSpeed;
    private double shippingFee;
    private String status;
    private Date createdAt;
    private int failedAttempts;
    
    private Package pkg;
    private Address originAddress;
    private Address destinationAddress;

    public Shipment(String trackingId, String shippingSpeed, Package pkg) {
        this.trackingId = trackingId;
        this.shippingSpeed = shippingSpeed;
        this.pkg = pkg;
        this.status = "Picked Up";
        this.createdAt = new Date();
        this.failedAttempts = 0;
        this.shippingFee = calculateFee();
    }

    public double calculateFee() {
    	if (pkg == null) {
        	System.out.println("Error: No package associated with this shipment.");
        	return 0.0;
    	}

    	double baseRate = 5.0; // Default Standard rate
    	if (shippingSpeed != null && shippingSpeed.equalsIgnoreCase("Express")) {
        	baseRate = 15.0;
    	}

    	this.shippingFee = pkg.getWeight() * baseRate; 
    
    	return this.shippingFee;
	}

    public void updateStatus(String newStatus) {
        this.status = newStatus;
        System.out.println("System: Tracking ID " + trackingId + " updated to [" + status + "]");
    }

    public void reschedule() {
        this.failedAttempts++;
        this.status = "Rescheduled - Attempt " + failedAttempts;
        System.out.println("Log: Delivery failed. Package rescheduled.");
    }

    public String getTrackingStatus() {
        return "Shipment [" + trackingId + "] is currently: " + status;
    }

    public String getTrackingId() { 
    	return trackingId; 
    }
    public void setTrackingId(String trackingId) { 
    	this.trackingId = trackingId; 
    }
    public double getShippingFee() { 
    	return shippingFee; 
    }
    
    @Override
    public String toString() {
        return "Shipment ID: " + trackingId + " | Status: " + status + " | Fee: RM" + shippingFee;
    }
}
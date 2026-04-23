public class Sender extends User {
    private String senderId;
    private Address defaultPickupAddress;
    private String paymentDetails;

    public Sender(String userId, String username, String password, String senderId) {
        super(userId, username, password);
        this.senderId = senderId;
        this.defaultPickupAddress = new Address("123 Uni Lane", "Penang", "North", "11200");
        this.paymentDetails = "Credit Card";
    }

    public Shipment createShipmentRequest(Package pkg, Address destination, String speed) {
        Shipment newShipment = new Shipment("TEMP-ID", speed, pkg);
        System.out.println("System: Shipment request created by " + getSenderId());
        return newShipment;
    }

    public boolean makePayment() {
        System.out.println("System: Processing payment for Sender ID: " + senderId);
        return true; 
    }

    public String trackParcel(String trackingId) {
        return "Shipment [" + trackingId + "] status: In Transit.";
    }

    public String getSenderId() { 
        return senderId; 
    }
    
    public Address getDefaultPickupAddress() { 
        return defaultPickupAddress; 
    }

    public void setPaymentDetails(String paymentDetails) {
        this.paymentDetails = paymentDetails;
    }
    
    @Override
    public String toString() {
        return super.toString() + " | Sender ID: " + senderId + " | Payment: " + paymentDetails;
    }
}
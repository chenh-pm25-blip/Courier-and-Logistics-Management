public class Address {
    private String street;
    private String city;
    private String region;
    private String postalCode;

    public Address(String street, String city, String region, String postalCode) {
        this.street = street;
        this.city = city;
        this.region = region;
        this.postalCode = postalCode;
    }

    public String getFullAddress() { 
        return street + ", " + city + ", " + region + " " + postalCode; 
    }
    
    public boolean validateAddress() { 
        return street != null && !street.isEmpty() && city != null && !city.isEmpty(); 
    }
    
    public String getRegion() { 
    	return region; 
    }
}
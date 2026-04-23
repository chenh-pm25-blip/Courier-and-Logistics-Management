public class Package {
    private String packageId;
    private double weight;
    private String dimensions;
    private String contentType;

    public Package(String packageId, double weight, String dimensions, String contentType) {
        this.packageId = packageId;
        this.weight = weight;
        this.dimensions = dimensions;
        this.contentType = contentType;
    }

    public double getWeight() {
        return weight;
    }

    // Getters and Setters
    public String getPackageId() { 
        return packageId; 
    }

    public String getDimensions() { 
        return dimensions; 
    }

    public String getContentType() { 
        return contentType; 
    }

    public void setWeight(double weight) {
        if (weight > 0) {
            this.weight = weight;
        }
    }

    @Override
    public String toString() {
        return "Package ID: " + packageId + " (" + weight + "kg, " + contentType + ")";
    }
}

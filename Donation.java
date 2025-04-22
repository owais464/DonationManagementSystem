public class Donation {
    private String donorPhone;
    private String item;
    private String quantity;
    private String amount;
    private String aadhar;
    private String email;

    public Donation(String donorPhone, String item, String quantity, String amount, String aadhar, String email) {
        this.donorPhone = donorPhone;
        this.item = item;
        this.quantity = quantity;
        this.amount = amount;
        this.aadhar = aadhar;
        this.email = email;
    }

    @Override
    public String toString() {
        return donorPhone + "," + item + "," + quantity + "," + amount + "," + aadhar + "," + email;
    }

    // Getters
    public String getDonorPhone() {
        return donorPhone;
    }

    public String getItem() {
        return item;
    }

    public String getQuantity() {
        return quantity;
    }

    public String getAmount() {
        return amount;
    }

    public String getAadhar() {
        return aadhar;
    }

    public String getEmail() {
        return email;
    }

    // Setters (if needed for future modifications)
    public void setDonorPhone(String donorPhone) {
        this.donorPhone = donorPhone;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public void setAadhar(String aadhar) {
        this.aadhar = aadhar;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    // Additional utility method
    public static Donation fromString(String str) {
        String[] parts = str.split(",");
        if (parts.length != 6) return null;
        return new Donation(parts[0], parts[1], parts[2], parts[3], parts[4], parts[5]);
    }
}
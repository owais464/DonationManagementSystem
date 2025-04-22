public class Request {
    private String receiverPhone;
    private String item;
    private String quantity;
    private boolean urgent;
    private String address;
    private String aadhar;
    private String email;
    private String reason;
    
    public Request(String receiverPhone, String item, String quantity, boolean urgent, 
                  String address, String aadhar, String email, String reason) {
        this.receiverPhone = receiverPhone;
        this.item = item;
        this.quantity = quantity;
        this.urgent = urgent;
        this.address = address;
        this.aadhar = aadhar;
        this.email = email;
        this.reason = reason;
    }
    
    @Override
    public String toString() {
        return receiverPhone + "," + item + "," + quantity + "," + urgent + "," + 
               address + "," + aadhar + "," + email + "," + reason;
    }
    
    public String getReceiverPhone() {
        return receiverPhone;
    }
    
    public String getItem() {
        return item;
    }
    
    public String getQuantity() {
        return quantity;
    }
    
    public boolean isUrgent() {
        return urgent;
    }
    
    public String getAddress() {
        return address;
    }
    
    public String getAadhar() {
        return aadhar;
    }
    
    public String getEmail() {
        return email;
    }
    
    public String getReason() {
        return reason;
    }
    }
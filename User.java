public class User {
    private String name;
    private String phone;
    private String password;
    private String role;

    public User(String name, String phone, String password, String role) {
        this.name = name;
        this.phone = phone;
        this.password = password;
        this.role = role;
    }

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

    public String getPassword() {
        return password;
    }

    public String getRole() {
        return role;
    }

    @Override
    public String toString() {
        return name + "," + phone + "," + password + "," + role;
    }

    public static User fromString(String str) {
        String[] parts = str.split(",");
        if (parts.length != 4) return null;
        
        String role = parts[3];
        if (role.equals("donor")) {
            return new Donor(parts[0], parts[1], parts[2], parts[3]);
        } else if (role.equals("receiver")) {
            return new Receiver(parts[0], parts[1], parts[2], parts[3]);
        } else if (role.equals("admin")) {
            return new User(parts[0], parts[1], parts[2], parts[3]); // Regular User with admin role
        } else {
            return new User(parts[0], parts[1], parts[2], parts[3]);
        }
    }
}

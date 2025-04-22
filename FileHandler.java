import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileHandler {
    private static final String USERS_FILE = "users.txt";
    private static final String DONATIONS_FILE = "donations.txt";
    private static final String REQUESTS_FILE = "requests.txt";

    public static void saveUser(User user) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(USERS_FILE, true))) {
            writer.write(user.toString());
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static boolean userExists(String phone) {
        try (BufferedReader reader = new BufferedReader(new FileReader(USERS_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length >= 2 && parts[1].equals(phone)) {
                    return true;
                }
            }
        } catch (IOException e) {
            // File doesn't exist yet, so no users exist
        }
        return false;
    }

    public static User authenticateUser(String phone, String password) {
        try (BufferedReader reader = new BufferedReader(new FileReader(USERS_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                User user = User.fromString(line);
                if (user != null && user.getPhone().equals(phone) && user.getPassword().equals(password)) {
                    return user;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void saveDonation(Donation donation) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(DONATIONS_FILE, true))) {
            writer.write(donation.toString());
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void saveRequest(Request request) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(REQUESTS_FILE, true))) {
            writer.write(request.toString());
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Object[][] getAllDonorsWithDonations() {
        List<User> donors = new ArrayList<>();
        List<Integer> donationCounts = new ArrayList<>();
        
        // First get all donors
        try (BufferedReader reader = new BufferedReader(new FileReader(USERS_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                User user = User.fromString(line);
                if (user != null && user.getRole().equals("donor")) {
                    donors.add(user);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        // Then count donations for each donor
        for (User donor : donors) {
            int count = 0;
            try (BufferedReader reader = new BufferedReader(new FileReader(DONATIONS_FILE))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    String[] parts = line.split(",");
                    if (parts.length > 0 && parts[0].equals(donor.getPhone())) {
                        count++;
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            donationCounts.add(count);
        }
        
        // Convert to Object[][]
        Object[][] result = new Object[donors.size()][3];
        for (int i = 0; i < donors.size(); i++) {
            result[i][0] = donors.get(i).getName();
            result[i][1] = donors.get(i).getPhone();
            result[i][2] = donationCounts.get(i);
        }
        return result;
    }

    public static Object[][] getAllReceiversWithRequests() {
        List<User> receivers = new ArrayList<>();
        List<Integer> requestCounts = new ArrayList<>();
        
        // First get all receivers
        try (BufferedReader reader = new BufferedReader(new FileReader(USERS_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                User user = User.fromString(line);
                if (user != null && user.getRole().equals("receiver")) {
                    receivers.add(user);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        // Then count requests for each receiver
        for (User receiver : receivers) {
            int count = 0;
            try (BufferedReader reader = new BufferedReader(new FileReader(REQUESTS_FILE))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    String[] parts = line.split(",");
                    if (parts.length > 0 && parts[0].equals(receiver.getPhone())) {
                        count++;
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            requestCounts.add(count);
        }
        
        // Convert to Object[][]
        Object[][] result = new Object[receivers.size()][3];
        for (int i = 0; i < receivers.size(); i++) {
            result[i][0] = receivers.get(i).getName();
            result[i][1] = receivers.get(i).getPhone();
            result[i][2] = requestCounts.get(i);
        }
        return result;
    }

    public static void removeUser(String phone) {
        // Remove from users file
        List<String> userLines = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(USERS_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length >= 2 && !parts[1].equals(phone)) {
                    userLines.add(line);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(USERS_FILE))) {
            for (String line : userLines) {
                writer.write(line);
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        // Remove associated donations/requests
        if (isDonor(phone)) {
            List<String> donationLines = new ArrayList<>();
            try (BufferedReader reader = new BufferedReader(new FileReader(DONATIONS_FILE))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    String[] parts = line.split(",");
                    if (parts.length > 0 && !parts[0].equals(phone)) {
                        donationLines.add(line);
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(DONATIONS_FILE))) {
                for (String line : donationLines) {
                    writer.write(line);
                    writer.newLine();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            List<String> requestLines = new ArrayList<>();
            try (BufferedReader reader = new BufferedReader(new FileReader(REQUESTS_FILE))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    String[] parts = line.split(",");
                    if (parts.length > 0 && !parts[0].equals(phone)) {
                        requestLines.add(line);
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(REQUESTS_FILE))) {
                for (String line : requestLines) {
                    writer.write(line);
                    writer.newLine();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private static boolean isDonor(String phone) {
        try (BufferedReader reader = new BufferedReader(new FileReader(USERS_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length >= 4 && parts[1].equals(phone) && parts[3].equals("donor")) {
                    return true;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static List<Donation> getDonationsForUser(String phone) {
        List<Donation> donations = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(DONATIONS_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length >= 6 && parts[0].equals(phone)) {
                    donations.add(new Donation(parts[0], parts[1], parts[2], parts[3], parts[4], parts[5]));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return donations;
    }

    public static List<Request> getRequestsForUser(String phone) {
        List<Request> requests = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(REQUESTS_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length >= 8 && parts[0].equals(phone)) {
                    requests.add(new Request(parts[0], parts[1], parts[2], Boolean.parseBoolean(parts[3]), parts[4], parts[5], parts[6], parts[7]));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return requests;
    }

    public static List<Request> getUrgentRequests() {
        List<Request> requests = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(REQUESTS_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length >= 8 && Boolean.parseBoolean(parts[3])) {
                    requests.add(new Request(parts[0], parts[1], parts[2], true, parts[4], parts[5], parts[6], parts[7]));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return requests;
    }

    public static List<Donation> getAllDonations() {
        List<Donation> donations = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(DONATIONS_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length >= 6) {
                    donations.add(new Donation(parts[0], parts[1], parts[2], parts[3], parts[4], parts[5]));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return donations;
    }
    public static boolean isAdmin(String phone) {
        try (BufferedReader reader = new BufferedReader(new FileReader(USERS_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length >= 4 && parts[1].equals(phone) && parts[3].equals("admin")) {
                    return true;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }
}
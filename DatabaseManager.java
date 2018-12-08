import java.io.*;
import java.util.ArrayList;

/**
 * <h1>Database Manager</h1>
 * 
 * Used to locally save and retrieve data.
 */

public class DatabaseManager {

    /**
     * Creates an ArrayList of Vehicles from the passed CSV file. The values are in
     * the CSV file as followed:
     * <ol>
     * <li>Vehicle Type (Truck/Drone/Cargo Plane)</li>
     * <li>Vehicle License Plate</li>
     * <li>Maximum Carry Weight</li>
     * </ol>
     * If filePath does not exist, a blank ArrayList will be returned.
     * 
     * @param file CSV File
     * @return ArrayList of vehicles
     */
    public static ArrayList<Vehicle> loadVehicles(File file) {
        file = file.getAbsoluteFile();
        if (!(file.exists())) { //      C:/Users/Colin Witt/IdeaProjects/Project05/src/files/VehicleList.csv
            return new ArrayList<Vehicle>();
        } else {
            try {
                BufferedReader buf = new BufferedReader(new FileReader(file));
                ArrayList<String> lines = new ArrayList<String>();
                String line;
                while ((line = buf.readLine()) != null) {
                    lines.add(line);
                }
                buf.close();
                ArrayList<Vehicle> v = new ArrayList<Vehicle>();
                for (int x = 0; x < lines.size(); x++) {
                    String[] parts = lines.get(x).split(",");
                    if (parts[0].equals("Truck")) {
                        v.add(new Truck(parts[1], Double.parseDouble(parts[2])));
                    } else if (parts[0].equals("Drone")) {
                        v.add(new Drone(parts[1], Double.parseDouble(parts[2])));
                    } else if (parts[0].equals("CargoPlane")) {
                        v.add(new CargoPlane(parts[1], Double.parseDouble(parts[2])));
                    }
                }
                return v;
            } catch (IOException e) {
                return new ArrayList<Vehicle>();
            }
        }
    }

    
    
    
    
    /**
     * Creates an ArrayList of Packages from the passed CSV file. The values are in
     * the CSV file as followed:
     * <ol>
     * <li>ID</li>
     * <li>Product Name</li>
     * <li>Weight</li>
     * <li>Price</li>
     * <li>Address Name</li>
     * <li>Address</li>
     * <li>City</li>
     * <li>State</li>
     * <li>ZIP Code</li>
     * </ol>
     * 
     * If filePath does not exist, a blank ArrayList will be returned.
     * 
     * @param file CSV File
     * @return ArrayList of packages
     */
    public static ArrayList<Package> loadPackages(File file) {
        file = file.getAbsoluteFile();
    	if (!(file.exists())) {
    	    return new ArrayList<Package>();
        } else {
    	    try {
    	        BufferedReader buf = new BufferedReader(new FileReader(file));
                ArrayList<String> lines = new ArrayList<String>();
                String line;
                while ((line = buf.readLine()) != null) {
                    lines.add(line);
                }
                buf.close();
                ArrayList<Package> p = new ArrayList<Package>();
                for (int x = 0; x < lines.size(); x++) {
                    String[] parts = lines.get(x).split(",");
                    ShippingAddress sa = new ShippingAddress(parts[4], parts[5], parts[6], parts[7], Integer.parseInt(parts[8]));
                    p.add(new Package(parts[0], parts[1], Double.parseDouble(parts[2]), Double.parseDouble(parts[3]), sa));
                }
                return p;
            } catch (IOException e) {
                return new ArrayList<Package>();
            }
        }
    }
    
    
    
    
    

    /**
     * Returns the total Profits from passed text file. If the file does not exist 0
     * will be returned.
     * 
     * @param file file where profits are stored
     * @return profits from file
     */
    public static double loadProfit(File file) {
    	file = file.getAbsoluteFile();
        if (!(file.exists())) {
            return 0;
        }
        try {
            BufferedReader buf = new BufferedReader(new FileReader(file));
            String p = buf.readLine();
            buf.close();
            return Double.parseDouble(p);
        } catch (IOException e) {
            return 0;
        }
    }

    
    
    
    
    /**
     * Returns the total number of packages shipped stored in the text file. If the
     * file does not exist 0 will be returned.
     * 
     * @param file file where number of packages shipped are stored
     * @return number of packages shipped from file
     */
    public static int loadPackagesShipped(File file) {
        file = file.getAbsoluteFile();
        if (!(file.exists())) {
            return 0;
        }
        try {
            BufferedReader buf = new BufferedReader(new FileReader(file));
            String p = buf.readLine();
            buf.close();
            return Integer.parseInt(p);
        } catch (IOException e) {
            return 0;
        }
    }

    
    
    
    /**
     * Returns whether or not it was Prime Day in the previous session. If file does
     * not exist, returns false.
     * 
     * @param file file where prime day is stored
     * @return whether or not it is prime day
     */
    public static boolean loadPrimeDay(File file) {
    	file = file.getAbsoluteFile();
        if (!(file.exists())) {
            return false;
        }
        try {
            BufferedReader buf = new BufferedReader(new FileReader(file));
            int pd = Integer.parseInt(buf.readLine());
            buf.close();
            if (pd == 0) {
                return false;
            }
            return true;
        } catch (IOException e) {
            return false;
        }
    }


    
    
    
    
    /**
     * Saves (writes) vehicles from ArrayList of vehicles to file in CSV format one vehicle per line.
     * Each line (vehicle) has following fields separated by comma in the same order.
     * <ol>
     * <li>Vehicle Type (Truck/Drone/Cargo Plane)</li>
     * <li>Vehicle License Plate</li>
     * <li>Maximum Carry Weight</li>
     * </ol>
     * 
     * @param file     File to write vehicles to
     * @param vehicles ArrayList of vehicles to save to file
     */
    public static void saveVehicles(File file, ArrayList<Vehicle> vehicles) {
    	file = file.getAbsoluteFile();
    	if (file.exists()) {
    	    try {
                FileOutputStream out = new FileOutputStream(file);
                for(int x = 0; x < vehicles.size(); x++) {
                    String data = "";
                    Vehicle v = vehicles.get(x);
                    if (v instanceof Truck) {
                        data += "Truck,";
                    } else if (v instanceof Drone) {
                        data += "Drone,";
                    } else if (v instanceof CargoPlane) {
                        data += "CargoPlane,";
                    }
                    data += v.getLicensePlate() + ",";
                    data += v.getMaxWeight() + "\n";
                    out.write(data.getBytes());
                }
                out.close();
            } catch (IOException e) {

            }
        }
    }

    
    
    
    /**
     * Saves (writes) packages from ArrayList of package to file in CSV format one package per line.
     * Each line (package) has following fields separated by comma in the same order.
     * <ol>
     * <li>ID</li>
     * <li>Product Name</li>
     * <li>Weight</li>
     * <li>Price</li>
     * <li>Address Name</li>
     * <li>Address</li>
     * <li>City</li>
     * <li>State</li>
     * <li>ZIP Code</li>
     * </ol>
     * 
     * @param file     File to write packages to
     * @param packages ArrayList of packages to save to file
     */
    public static void savePackages(File file, ArrayList<Package> packages) {
    	file = file.getAbsoluteFile();
    	if (file.exists()) {
    	    try {
    	        FileOutputStream out = new FileOutputStream(file);
    	        for (int x = 0; x < packages.size(); x++) {
    	            String data = "";
    	            Package p = packages.get(x);
    	            data += p.getID() + ",";
    	            data += p.getProduct() + ",";
    	            data += p.getWeight() + ",";
    	            data += p.getPrice() + ",";
    	            ShippingAddress sa = p.getDestination();
    	            data += sa.getName() + ",";
                    data += sa.getAddress() + ",";
                    data += sa.getCity() + ",";
                    data += sa.getState() + ",";
                    data += sa.getZipCode() + "\n";
                    out.write(data.getBytes());
                }
                out.close();
            } catch (IOException e) {

            }
        }
    }

    
    
    
    /**
     * Saves profit to text file.
     * 
     * @param file   File to write profits to
     * @param profit Total profits
     */

    public static void saveProfit(File file, double profit) {
    	file = file.getAbsoluteFile();
    	if (file.exists()) {
    	    try {
                FileOutputStream out = new FileOutputStream(file);
                String data = "" + profit;
                out.write(data.getBytes());
                out.close();
            } catch (IOException e) {

            }
    	}
    }

    
    
    
    
    /**
     * Saves number of packages shipped to text file.
     * 
     * @param file      File to write profits to
     * @param nPackages Number of packages shipped
     */

    public static void savePackagesShipped(File file, int nPackages) {
        file = file.getAbsoluteFile();
        if (file.exists()) {
            try {
                FileOutputStream out = new FileOutputStream(file);
                String data = "" + nPackages;
                out.write(data.getBytes());
                out.close();
            } catch (IOException e) {

            }
        }
    }

    
    
    
    
    
    /**
     * Saves status of prime day to text file. If it is primeDay "1" will be
     * writtern, otherwise "0" will be written.
     * 
     * @param file     File to write profits to
     * @param primeDay Whether or not it is Prime Day
     */

    public static void savePrimeDay(File file, boolean primeDay) {
        file = file.getAbsoluteFile();
        if (file.exists()) {
            try {
                FileOutputStream out = new FileOutputStream(file);
                String data;
                if (primeDay) {
                    data = "1";
                } else {
                    data = "0";
                }
                out.write(data.getBytes());
                out.close();
            } catch (IOException e) {

            }
        }
    }
}

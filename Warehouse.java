import javax.xml.crypto.Data;
import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * <h1>Warehouse</h1>
 */

public class Warehouse {
	final static String folderPath = "files/";
    final static File VEHICLE_FILE = new File(folderPath + "VehicleList.csv");
    final static File PACKAGE_FILE = new File(folderPath + "PackageList.csv");
    final static File PROFIT_FILE = new File(folderPath + "Profit.txt");
    final static File N_PACKAGES_FILE = new File(folderPath + "NumberOfPackages.txt");
    final static File PRIME_DAY_FILE = new File(folderPath + "PrimeDay.txt");
    final static double PRIME_DAY_DISCOUNT = .15;
    final static String DEFAULT_MENU = "==========Options==========\n" +
            "1) Add Package\n" +
            "2) Add Vehicle\n" +
            "3) Activate Prime Day\n" +
            "4) Send Vehicle\n" +
            "5) Print Statistics\n" +
            "6) Exit\n" +
            "===========================";
    final static String PRIME_MENU = "==========Options==========\n" +
            "1) Add Package\n" +
            "2) Add Vehicle\n" +
            "3) Deactivate Prime Day\n" +
            "4) Send Vehicle\n" +
            "5) Print Statistics\n" +
            "6) Exit\n" +
            "===========================";
    final static String VEHICLE_OPTIONS = "Vehicle Options:\n" +
            "1) Truck\n" +
            "2) Drone\n" +
            "3) Cargo Plane";

    /**
     * Main Method
     * 
     * @param args list of command line arguements
     */
    public static void main(String[] args) {

    	//1) load data (vehicle, packages, profits, packages shipped and primeday) from files using DatabaseManager

    	ArrayList<Vehicle> vehi = DatabaseManager.loadVehicles(VEHICLE_FILE);
        ArrayList<Package> pack = DatabaseManager.loadPackages(PACKAGE_FILE);
        double prof = DatabaseManager.loadProfit(PROFIT_FILE);
        int sent = DatabaseManager.loadPackagesShipped(N_PACKAGES_FILE);
        boolean prime = DatabaseManager.loadPrimeDay(PRIME_DAY_FILE);
    	
    	//2) Show menu and handle user inputs



        String menu = DEFAULT_MENU;
        if (prime) {
            menu = PRIME_MENU;
        }

    	boolean finished = false;
    	Scanner input = new Scanner(System.in);
    	while (!finished) {
            System.out.println(menu);
            int choice = input.nextInt();
            if (choice == 1) {
                input.nextLine(); //to counteract nextline issues
                System.out.println("Enter package ID:");
                String id = input.nextLine();
                System.out.println("Enter Product Name:");
                String prod = input.nextLine();
                System.out.println("Enter Weight:");
                double weight = input.nextDouble();
                System.out.println("Enter Price:");
                double price = input.nextDouble();
                input.nextLine(); //to counteract nextline issues
                System.out.println("Enter Buyer Name:");
                String name = input.nextLine();
                System.out.println("Enter Address:");
                String address = input.nextLine();
                System.out.println("Enter City:");
                String city = input.nextLine();
                System.out.println("Enter State:");
                String state = input.nextLine();
                System.out.println("Enter ZIP Code:");
                int zip = input.nextInt();
                pack.add(new Package(id, prod, weight, price, new ShippingAddress(name, address, city, state, zip)));
            } else if (choice == 2) {
                System.out.println(VEHICLE_OPTIONS);
                int type = input.nextInt();
                System.out.println("Enter License Plate No.:");
                input.nextLine();
                String lic = input.nextLine();
                System.out.println("Enter Maximum Carry Weight:");
                double weight = input.nextDouble();
                if (type == 1) {
                    vehi.add(new Truck(lic, weight));
                } else if (type == 2) {
                    vehi.add(new Drone(lic, weight));
                } else if (type == 3) {
                    vehi.add(new CargoPlane(lic, weight));
                }
            } else if (choice == 3) {
                if (prime) {
                    prime = false;
                    menu = DEFAULT_MENU;
                    for (int x = 0; x < pack.size(); x++) {
                        pack.get(x).setPrice(pack.get(x).getPrice() / 0.85);
                    }
                } else {
                    prime = true;
                    menu = PRIME_MENU;
                    for (int x = 0; x < pack.size(); x++) {
                        pack.get(x).setPrice(pack.get(x).getPrice() * 0.85);
                    }
                }
            } else if (choice == 4) {
                //TODO
            } else if (choice == 5) {
                //TODO
            } else if (choice == 6) {
                finished = true;
            }
        }
    	
    	//3) save data (vehicle, packages, profits, packages shipped and primeday) to files (overwriting them) using DatabaseManager

        DatabaseManager.saveVehicles(VEHICLE_FILE, vehi);
        DatabaseManager.savePackages(PACKAGE_FILE, pack);
        DatabaseManager.saveProfit(PROFIT_FILE, prof);
        DatabaseManager.savePackagesShipped(N_PACKAGES_FILE, sent);
        DatabaseManager.savePrimeDay(PRIME_DAY_FILE, prime);
    }


}

import java.util.ArrayList;

/**
 * <h1>Vehicle</h1> Represents a vehicle
 */

public class Vehicle implements Profitable {
    private String licensePlate;
    private double maxWeight;
    private double currentWeight;
    private int zipDest;
    private ArrayList<Package> packages;
    private double maxRange;


    /**
     * Default Constructor
     */
    //============================================================================
    public Vehicle() {
        this.licensePlate = "";
        this.maxWeight = 0;
        this.currentWeight = 0;
        this.zipDest = 0;
        this.packages = new ArrayList<Package>();
    }
    //============================================================================


    /**
     * Constructor
     *
     * @param licensePlate license plate of vehicle
     * @param maxWeight    maximum weight of vehicle
     */
    //============================================================================
    public Vehicle(String licensePlate, double maxWeight) {
        this.licensePlate = licensePlate;
        this.maxWeight = maxWeight;
        this.currentWeight = 0;
        this.zipDest = 0;
        this.packages = new ArrayList<Package>();
    }

    //============================================================================


    /**
     * Returns the license plate of this vehicle
     *
     * @return license plate of this vehicle
     */
    public String getLicensePlate() {
        return this.licensePlate;
    }





    /**
     * Updates the license plate of vehicle
     *
     * @param licensePlate license plate to be updated to
     */
    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }







    /**
     * Returns the maximum weight this vehicle can carry
     *
     * @return the maximum weight that this vehicle can carry
     */
    public double getMaxWeight() {
        return this.maxWeight;
    }





    /**
     * Updates the maximum weight of this vehicle
     *
     * @param maxWeight max weight to be updated to
     */
    public void setMaxWeight(double maxWeight) {
        this.maxWeight = maxWeight;
    }






    /**
     * Returns the current weight of all packages inside vehicle
     *
     * @return current weight of all packages inside vehicle
     */
    public double getCurrentWeight() {
        return this.currentWeight;
    }


    /**
     * Returns the max range of the vehicle
     *
     * @return current max range of package inside vehicle
     */
    public double getMaxRange() {
        return this.maxRange;
    }

    public void setMaxRange(double maxRange) {
        this.maxRange = maxRange;
    }





    /**
     * Returns the current ZIP code desitnation of the vehicle
     *
     * @return current ZIP code destination of vehicle
     */
    public int getZipDest() {
        return this.zipDest;
    }






    /**
     * Updates the ZIP code destination of vehicle
     *
     * @param zipDest ZIP code destination to be updated to
     */
    public void setZipDest(int zipDest) {
        this.zipDest = zipDest;
    }






    /**
     * Returns ArrayList of packages currently in Vehicle
     *
     * @return ArrayList of packages in vehicle
     */
    public ArrayList<Package> getPackages() {
        return this.packages;
    }






    /**
     * Adds Package to the vehicle only if has room to carry it (adding it would not
     * cause it to go over its maximum carry weight).
     *
     * @param pkg Package to add
     * @return whether or not it was successful in adding the package
     */
    public boolean addPackage(Package pkg) {
        if (this.currentWeight + pkg.getWeight() > this.maxWeight) {
            return false;
        } else {
            packages.add(pkg);
            this.currentWeight += pkg.getWeight();
            return true;
        }
    }






    /**
     * Clears vehicle of packages and resets its weight to zero
     */
    public void empty() {
        this.packages = new ArrayList<Package>();
        this.currentWeight = 0;
        this.maxRange = 0;
    }






    /**
     * Returns true if the Vehicle has reached its maximum weight load, false
     * otherwise.
     *
     * @return whether or not Vehicle is full
     */
    public boolean isFull() {
        return this.currentWeight == this.maxWeight;
    }






    /**
     * Fills vehicle with packages with preference of date added and range of its
     * destination zip code. It will iterate over the packages intially at a range
     * of zero and fill it with as many as it can within its range without going
     * over its maximum weight. The amount the range increases is dependent on the
     * vehicle that is using this. This range it increases by after each iteration
     * is by default one.
     *
     * @param warehousePackages List of packages to add from
     */
    public void fill(ArrayList<Package> warehousePackages) {
        int bigRange = 0;
        for (int x = 0; x < warehousePackages.size(); x++) {
            if (warehousePackages.get(x).getDestination().getZipCode() - this.zipDest > bigRange) {
                bigRange = warehousePackages.get(x).getDestination().getZipCode() - this.zipDest;
            }
        }
        int range = 0;
        while (range <= bigRange) {
            for (int x = 0; x < warehousePackages.size(); x++) {
                if (warehousePackages.get(x).getWeight() + this.currentWeight <= this.maxWeight
                && (warehousePackages.get(x).getDestination().getZipCode() == this.zipDest + range
            || warehousePackages.get(x).getDestination().getZipCode() == this.zipDest - range)) {
                    addPackage(warehousePackages.get(x));
                }
            }
            range ++;
        }
        this.maxRange = bigRange;
    }


    @Override
    public double getProfit() {
        return 0;
    }

    @Override
    public String report() {
        return null;
    }

    public String type() {
        return null;
    }
}

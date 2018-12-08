/**
 * <h1>Shipping Address</h1> Represents a shipping address
 */
public class ShippingAddress {
    private String name; //e.g. (Lawson Computer Science Building)
    private String address; //e.g. Street Address (305 N University St)
    private String city; // e.g. (West Lafayette)
    private String state; // e.g. IN
    private int zipCode; //e.g. 47907

    public ShippingAddress() {
        name = "";
        address = "";
        city = "";
        state = "";
        zipCode = 0;
    }

    public ShippingAddress(String name, String address, String city, String state, int zipCode) {
        this.name = name;
        this.address = address;
        this.city = city;
        this.state = state;
        this.zipCode = zipCode;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getCity() {
        return city;
    }

    public String getState() {
        return state;
    }

    public int getZipCode() {
        return zipCode;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setCity(String city){
        this.city = city;
    }

    public void setState(String state) {
        this.state = state;
    }

    public void setZipCode(int zipCode) {
        this.zipCode = zipCode;
    }

    public String label() {
        String ret = "TO: " + name + "\n";
        ret += address + "\n";
        ret += city + ", " + state + ", " + zipCode;
        return ret;
    }
}

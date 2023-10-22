package Models;

public class InputDataForCheckout {

    private String firstName;
    private String lastName;
    private int postalCode;

    public InputDataForCheckout(String firstName, String lastName, int postalCode) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.postalCode = postalCode;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public int getPostalCode() {
        return postalCode;
    }
}

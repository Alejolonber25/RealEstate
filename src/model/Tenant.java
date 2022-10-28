package model;

public class Tenant extends Person{

    private Apartment apartment;
    public Tenant(DocumentType documentType, String documentNumber, String fullName, ContactType contactType, String contactNumber, Apartment apartment) {
        super(documentType, documentNumber, fullName, contactType, contactNumber);
        this.apartment = apartment;
    }

    public Apartment getApartment() {
        return apartment;
    }

    public void setApartment(Apartment apartment) {
        this.apartment = apartment;
    }
}

package model;

import javax.print.DocFlavor;

public class Owner extends Person {
    private String bankName;
    private String bankAccountNumber;
    private Apartment[] apartments;

    public Owner(DocumentType documentType, String documentNumber, String fullName, ContactType contactType, String contactNumber, String bankAccountNumber, String bankName, int numberOfApartments) {
        super(documentType, documentNumber, fullName, contactType, contactNumber);
        this.bankAccountNumber = bankAccountNumber;
        this.bankName = bankName;
        this.apartments = new Apartment[numberOfApartments];
    }
    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getBankAccountNumber() {
        return bankAccountNumber;
    }

    public void setBankAccountNumber(String bankAccountNumber) {
        this.bankAccountNumber = bankAccountNumber;
    }

    public Apartment[] getApartments() {
        return apartments;
    }

    public void setApartments(Apartment[] apartments) {
        this.apartments = apartments;
    }
}

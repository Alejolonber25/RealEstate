package model;

public class Person {
    private DocumentType documentType;
    private String documentNumber;
    private String fullName;
    private ContactType contactType;
    private String contactNumber;

    public Person(DocumentType documentType, String documentNumber, String fullName, ContactType contactType, String contactNumber) {
        this.documentType = documentType;
        this.documentNumber = documentNumber;
        this.fullName = fullName;
        this.contactType = contactType;
        this.contactNumber = contactNumber;
    }

    public DocumentType getDocumentType() {
        return documentType;
    }

    public void setDocumentType(DocumentType documentType) {
        this.documentType = documentType;
    }

    public String getDocumentNumber() {
        return documentNumber;
    }

    public void setDocumentNumber(String documentNumber) {
        this.documentNumber = documentNumber;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public ContactType getContactType() {
        return contactType;
    }

    public void setContactType(ContactType contactType) {
        this.contactType = contactType;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }
}

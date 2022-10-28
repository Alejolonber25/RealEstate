package ui;

import model.ContactType;
import model.DocumentType;
import model.RealEstate;

import java.util.Scanner;

public class Main {
    private final Scanner reader;
    private RealEstate realEstate;
    public Main(){
        reader = new Scanner(System.in);
        realEstate = new RealEstate();
    }
    public static void main(String[] args) {
        Main main = new Main();
        int option = 0;
        do{
            main.cleanConsole();
            option = main.getOptionShowMenu();
            main.cleanConsole();
            main.executeOption(option);
            main.reader.nextLine();
            main.reader.nextLine();
        }while(option != 0);
        main.reader.close();
    }
    public int getOptionShowMenu(){
        int option = 0;
        System.out.println("<<<<<  Menu >>>>>");
        System.out.println(
                """
                        1. Register Building.
                        2. Register Apartment.
                        3. Register Owner.
                        4. Assign apartment to owner.
                        5. Register tenant.
                        6. Consult number of available apartments in a building.
                        7. Consult monthly payment of the available apartments of a building.
                        8. Consult availability of an apartment.
                        9. Consult number of apartments rented by a person.
                        10. Consult monthly payment of apartments rented by a person.
                        0. Exit.""");
        option = reader.nextInt();
        return option;
    }
    public void executeOption(int option){

        switch (option) {
            case 1 -> uiRegisterBuilding();
            case 2 -> uiRegisterApartment();
            case 3 -> uiRegisterOwner();
            case 4 -> uiAssignApartmentToOwner();
            case 5 -> uiRegisterTenant();
            case 6 -> uiConsultNumberOfAvailableApartmentsInABuilding();
            case 7 -> uiConsultMonthlyPaymentOfTheAvailableApartmentsOfABuilding();
            case 8 -> uiConsultAvailabilityOfAnApartment();
            case 9 -> uiConsultNumberOfApartmentsRentedByAPerson();
            case 10 -> uiConsultMonthlyPaymentOfApartmentsRentedByAPerson();
            default -> {
            }
        }
    }
    public void cleanConsole(){
        System.out.print("\033[H\033[2J");
    }
    public void uiRegisterBuilding(){
        System.out.println("Register building");
        System.out.print("Name: ");
        String name = reader.next();
        System.out.print("Adress: ");
        String adress = reader.next();
        System.out.println(realEstate.registerBuilding(name, adress));
    }
    public void uiRegisterApartment(){
        System.out.println("Register apartment");
        System.out.print("Number: ");
        String number = reader.next();
        System.out.print("Number of rooms: ");
        int numberOfRooms = reader.nextInt();
        System.out.print("Number of bathrooms: ");
        int numberOfBathrooms = reader.nextInt();
        System.out.print("Does the apartment have a balcony? (Respond YES / NO): ");
        String hasABalconyString = reader.next();
        boolean hasABalcony;
        if (hasABalconyString.equalsIgnoreCase("YES")) {
            hasABalcony = true;
        } else {
            hasABalcony = false;
        }
        System.out.print("Monthly rental value: ");
        double monthlyRentalValue = reader.nextDouble();
        System.out.print("Name building: ");
        String nameBuilding = reader.next();
        System.out.println(realEstate.registerApartment(number, numberOfRooms, numberOfBathrooms, hasABalcony, monthlyRentalValue, nameBuilding));
    }
    public void uiRegisterOwner(){
        System.out.println("Register owner");
        System.out.print("Document type: ");
        DocumentType documentType = DocumentType.valueOf(reader.next());
        System.out.print("Document number: ");
        String documentNumber = reader.next();
        System.out.print("Full name: ");
        String fullName = reader.next();
        System.out.print("Contact type: ");
        ContactType contactType = ContactType.valueOf(reader.next());
        System.out.print("Contact number: ");
        String contactNumber = reader.next();
        System.out.print("Bank account number: ");
        String bankAccountNumber = reader.next();
        System.out.print("Bank name: ");
        String bankName = reader.next();
        System.out.print("Number of apartments: ");
        int numberOfApartments = reader.nextInt();
        System.out.println(realEstate.registerOwner(documentType, documentNumber, fullName, contactType, contactNumber, bankAccountNumber, bankName, numberOfApartments));
    }
    public void uiAssignApartmentToOwner(){
        System.out.println("Assign apartment to owner");
        System.out.print("Name building: ");
        String nameBuilding = reader.next();
        System.out.print("Number apartment: ");
        String numberApartment = reader.next();
        System.out.print("Document number: ");
        String documentNumber = reader.next();
        System.out.println(realEstate.assignApartmentToOwner(nameBuilding, numberApartment, documentNumber));
    }
    public void uiRegisterTenant(){
        System.out.println("Register tenant");
        System.out.print("Document type: ");
        DocumentType documentType = DocumentType.valueOf(reader.next());
        System.out.print("Document number: ");
        String documentNumber = reader.next();
        System.out.print("Full name: ");
        String fullName = reader.next();
        System.out.print("Contact Type: ");
        ContactType contactType = ContactType.valueOf(reader.next());
        System.out.print("Contact number: ");
        String contactNumber = reader.next();
        System.out.print("Name building: ");
        String nameBuilding = reader.next();
        System.out.print("Number apartment: ");
        String numberApartment = reader.next();
        System.out.println(realEstate.registerTenant(documentType, documentNumber, fullName, contactType, contactNumber, nameBuilding, numberApartment));
    }
    public void uiConsultNumberOfAvailableApartmentsInABuilding(){
        System.out.println("Consult number of available apartments in a building");
        System.out.print("Name building: ");
        String nameBuilding = reader.next();
        System.out.println(realEstate.consultNumberOfAvailableApartmentsInABuilding(nameBuilding));
    }
    public void uiConsultMonthlyPaymentOfTheAvailableApartmentsOfABuilding(){
        System.out.println("Consult monthly payment of the available apartments of a building");
        System.out.print("Name building: ");
        String nameBuilding = reader.next();
        System.out.println(realEstate.consultMonthlyPaymentOfTheAvailableApartmentsOfABuilding(nameBuilding));
    }
    public void uiConsultAvailabilityOfAnApartment(){
        System.out.println("Consult availability of an apartment");
        System.out.print("Name building: ");
        String nameBuilding = reader.next();
        System.out.print("Number apartment: ");
        String numberApartment = reader.next();
        System.out.println(realEstate.consultAvailabilityOfAnApartment(nameBuilding, numberApartment));
    }
    public void uiConsultNumberOfApartmentsRentedByAPerson(){
        System.out.println("Consult number of apartments rented by a person");
        System.out.print("Document number: ");
        String documentNumber = reader.next();
        System.out.println(realEstate.consultNumberOfApartmentsRentedByAPerson(documentNumber));
    }
    public void uiConsultMonthlyPaymentOfApartmentsRentedByAPerson(){
        System.out.println("Consult monthly payment of apartments rented by a person");
        System.out.print("Document number: ");
        String documentNumber = reader.next();
        System.out.println(realEstate.consultMonthlyPaymentOfApartmentsRentedByAPerson(documentNumber));
    }
}
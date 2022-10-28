package model;

public class RealEstate {
    private Building[] buildings;

    private Person[] people;
    private int apartmentsWithOwner = 0;
    private final int MAXIMUM_NUMBER_OF_APARTMENTS = 50;

    private final int MAXIMUM_NUMBER_OF_BUILDINGS = 5;
    private final int MAXIMUM_NUMBER_PEOPLE = 20;

    public RealEstate(){
        this.buildings = new Building[MAXIMUM_NUMBER_OF_BUILDINGS];
        this.people = new Person[MAXIMUM_NUMBER_PEOPLE];
    }

    public String registerBuilding(String name, String adress){
        String msj = "No more buildings can be added";
        boolean wasFound = false;
        for (int i = 0; i < buildings.length && !wasFound; i++) {
            if(buildings[i] == null){
                wasFound = true;
                Building building = new Building(name, adress);
                buildings[i] = building;
                msj = "Building registered";
            }
        }
        return msj;
    }
    public String registerApartment(String number, int numberOfRooms, int numberOfBathrooms, boolean hasABalcony, double monthlyRentalValue, String nameBuilding){
        String msj = "No more apartments can be added";
        int pos = searchPosBuildingByName(nameBuilding);
        if (pos != -1){
            Apartment apartment = new Apartment(number, numberOfRooms,numberOfBathrooms,hasABalcony, monthlyRentalValue);
            boolean state = buildings[pos].addApartment(apartment);
            if (state) {
                msj = "Apartment registered";
            }
        }else{
            msj = "Building not found";
        }
        return msj;
    }
    public String registerOwner(DocumentType documentType, String documentNumber, String fullName, ContactType contactType, String contactNumber, String bankAccountNumber, String bankName, int numberOfApartments){
        String msj = "Can't add more owners";
        int posTenant = searchPosPersonByDocumentNumber(documentNumber);
        if (posTenant == -1) {
            if (apartmentsWithOwner + numberOfApartments <= MAXIMUM_NUMBER_OF_APARTMENTS) {
                boolean wasFound = false;
                for (int i = 0; i < people.length && !wasFound; i++) {
                    if (people[i] == null) {
                        wasFound = true;
                        Owner owner = new Owner(documentType, documentNumber, fullName, contactType, contactNumber, bankAccountNumber, bankName, numberOfApartments);
                        people[i] = owner;
                        apartmentsWithOwner += numberOfApartments;
                        msj = "Owner registered";
                    }
                }
            } else {
                msj = "You can't own that many apartments";
            }
        }else{
            msj = "Person already exists";
        }
        return msj;
    }

    public String assignApartmentToOwner(String nameBuilding, String numberApartment ,String documentNumber){
        String msj = "The owner has no more apartments";
        int posOwner = searchPosPersonByDocumentNumber(documentNumber);
        if (posOwner != -1){
            int posBuilding = searchPosBuildingByName(nameBuilding);
            if(posBuilding != -1){
                int posApartment = searchPosApartmentByNumberBuildingAndNumberApartment(posBuilding, numberApartment);
                if (posApartment != -1){
                    if (!buildings[posBuilding].getApartments()[posApartment].getHasOwner()){
                        boolean wasFound = false;
                        for (int i = 0; i < ((Owner) people[posOwner]).getApartments().length && !wasFound; i++) {
                            if(((Owner) people[posOwner]).getApartments()[i] == null){
                                wasFound = true;
                                ((Owner) people[posOwner]).getApartments()[i] = buildings[posBuilding].getApartments()[posApartment];
                                buildings[posBuilding].getApartments()[posApartment].setHasOwner(true);
                                msj = "Assigned apartment";
                            }
                        }
                    }
                    else{
                        msj = "This apartment has owner";
                    }
                }
                else{
                    msj = "Apartment not found";
                }
            }else{
                msj = "Building not found";
            }
        }else{
            msj = "Owner not found";
        }

        return msj;
    }
    public String registerTenant(DocumentType documentType, String documentNumber, String fullName, ContactType contactType, String contactNumber, String nameBuilding, String numberApartment){
        String msj = "";
        int posTenant = searchPosPersonByDocumentNumber(documentNumber);
        if (posTenant == -1){
            int posBuilding = searchPosBuildingByName(nameBuilding);
            if(posBuilding != -1){
                int posApartment = searchPosApartmentByNumberBuildingAndNumberApartment(posBuilding, numberApartment);
                if (posApartment != -1){
                    if (buildings[posBuilding].getApartments()[posApartment].getHasOwner()){
                        if (!buildings[posBuilding].getApartments()[posApartment].getHasTenant()){
                            boolean wasFound = false;
                            for (int i = 0; i < people.length && !wasFound; i++) {
                                if (people[i] == null) {
                                    people[i] = new Tenant(documentType, documentNumber, fullName, contactType, contactNumber, buildings[posBuilding].getApartments()[posApartment]);
                                    buildings[posBuilding].getApartments()[posApartment].setHasTenant(true);
                                    wasFound = true;
                                    msj = "Registered tenant";
                                }
                            }
                        }else{
                            msj = "Apartment has tenant";
                        }
                    }else{
                        msj = "Apartment hasn't owner";
                    }

                }else{
                    msj = "Apartment not found";
                }
            }else{
                msj = "Building not found";
            }
        }else{
            msj = "Person already exists";
        }

        return msj;
    }

    public String consultNumberOfAvailableApartmentsInABuilding(String nameBuilding){
        String msj = "Building not found";
        int posBuilding = searchPosBuildingByName(nameBuilding);
        if (posBuilding != -1){
            int count = 0;
            for (int i = 0; i < buildings[posBuilding].getApartments().length; i++) {
                System.out.println(buildings[posBuilding].getApartments()[i].getHasTenant());
                if(buildings[posBuilding].getApartments()[i] != null && !buildings[posBuilding].getApartments()[i].getHasTenant()){
                    count++;
                }
            }
            msj = "Building "+buildings[posBuilding].getName()+" has "+count+" apartments";
        }
        return msj;
    }
    public String consultMonthlyPaymentOfTheAvailableApartmentsOfABuilding(String nameBuilding){
        String msj = "Building not found";
        int posBuilding = searchPosBuildingByName(nameBuilding);
        if (posBuilding != -1){
            int sumPayment = 0;
            for (int i = 0; i < buildings[posBuilding].getApartments().length; i++) {
                if(buildings[posBuilding].getApartments()[i] != null && buildings[posBuilding].getApartments()[i].getHasTenant()){
                    sumPayment += buildings[posBuilding].getApartments()[i].getMonthlyRentalValue();
                }
            }
            msj = "the monthly payment for the rented apartments in building "+buildings[posBuilding].getName()+" is "+sumPayment;
        }
        return msj;
    }
    public String consultAvailabilityOfAnApartment(String nameBuilding, String numberApartment){
        String msj = "";
        int posBuilding = searchPosBuildingByName(nameBuilding);
        if(posBuilding != -1){
            int posApartment = searchPosApartmentByNumberBuildingAndNumberApartment(posBuilding, numberApartment);
            if (posApartment != -1){
                if (buildings[posBuilding].getApartments()[posApartment] != null && !buildings[posBuilding].getApartments()[posApartment].getHasTenant()){
                    msj = "The apartment "+buildings[posBuilding].getApartments()[posApartment].getNumber()+" of building "+buildings[posBuilding].getName()+" is available";
                }else{
                    msj = "The apartment isn't available";
                }
            }else{
                msj = "Apartment not found";
            }
        }else{
            msj = "Building not found";
        }
        return msj;
    }
    public String consultNumberOfApartmentsRentedByAPerson(String documentNumber){
        String msj = "Owner not found";
        int posOwner = searchPosPersonByDocumentNumber(documentNumber);
        if (posOwner != -1){
            int count = 0;
            for (int i = 0; i < ((Owner) people[posOwner]).getApartments().length; i++) {
                if( ((Owner) people[posOwner]).getApartments()[i] != null && ((Owner) people[posOwner]).getApartments()[i].getHasTenant()){
                    count++;
                }
            }
            msj = people[posOwner].getFullName()+" has "+count+" rented apartments";
        }
        return msj;
    }
    public String consultMonthlyPaymentOfApartmentsRentedByAPerson(String documentNumber){
        String msj = "Owner not found";
        int posOwner = searchPosPersonByDocumentNumber(documentNumber);
        if (posOwner != -1){
            double payment = 0;
            for (int i = 0; i < ((Owner) people[posOwner]).getApartments().length; i++) {
                if( ((Owner) people[posOwner]).getApartments()[i] != null && ((Owner) people[posOwner]).getApartments()[i].getHasTenant()){
                    System.out.println(((Owner) people[posOwner]).getApartments()[i].getMonthlyRentalValue()*0.9);
                    payment +=  ((Owner) people[posOwner]).getApartments()[i].getMonthlyRentalValue()*0.9;
                }
            }
            msj = people[posOwner].getFullName()+" will receive "+payment+" monthly";
        }
        return msj;
    }
    public int searchPosPersonByDocumentNumber(String documentNumber){
        int pos = -1;
        boolean wasFound = false;
        for (int i = 0; i < people.length; i++) {
            if(people[i] != null && people[i].getDocumentNumber().equalsIgnoreCase(documentNumber)){
                wasFound = true;
                pos = i;
            }
        }
        return pos;
    }

    public int searchPosApartmentByNumberBuildingAndNumberApartment(int posBuilding, String numberApartment){
        int posApartment = -1;
        boolean wasFound = false;
        for (int i = 0; i < buildings[posBuilding].getApartments().length && !wasFound; i++) {
            if(buildings[posBuilding].getApartments()[i] != null && buildings[posBuilding].getApartments()[i].getNumber().equalsIgnoreCase(numberApartment)) {
                posApartment = i;
            }
        }
        return posApartment;
    }
    public int searchPosBuildingByName(String nameBuilding){
        boolean wasFound = false;
        int pos = -1;
        for (int i = 0; i < buildings.length && !wasFound; i++) {
            if(buildings[i] != null && buildings[i].getName().equalsIgnoreCase(nameBuilding)){
                wasFound = true;
                pos = i;
            }
        }
        return pos;
    }
}

package model;

public class Building {
    private String name;
    private Apartment[] apartments;
    private String adress;
    private final int MAXIMUM_NUMBER_OF_APARTMENTS = 10;


    public Building(String name, String adress) {
        this.name = name;
        this.apartments = new Apartment[MAXIMUM_NUMBER_OF_APARTMENTS];
        this.adress = adress;
    }

    public boolean addApartment(Apartment apartment){
        boolean wasFound = false;
        for (int i = 0; i < apartments.length && !wasFound; i++) {
            if(apartments[i] == null){
                apartments[i] = apartment;
                wasFound = true;
            }
        }
        return wasFound;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Apartment[] getApartments() {
        return apartments;
    }

    public void setApartments(Apartment[] apartments) {
        this.apartments = apartments;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }
}

//emam samara
//1220022
//lab section 5


package assigment;

import java.util.Scanner;

public class Driver {

    public static void main(String[] args) {

        Vehicle[] availableVehicles = {
                 new Vehicle("Car", "ABC123", "Toyota", 50.0, 0, true),
                 new Vehicle("Bike", "DEF456", "Honda", 20.0, 0, true),
                 new Vehicle("Truck", "GHI789", "Ford", 80.0, 0, true),
                 new Vehicle("Car", "JKL012", "Hyundai", 55.0, 0, true),
                 new Vehicle("Bike", "MNO345", "Yamaha", 160.0, 0, true)	 };

        Scanner inputScanner =new Scanner(System.in);
        System.out.println("enter the number of customers :");
        int numberOfCustomers;
        numberOfCustomers=inputScanner.nextInt();

        Customer []customer=new Customer[numberOfCustomers];

        for(int i =0;i<numberOfCustomers;i++) {

            customer[i] =new Customer();	
            customer[i].setNumberOfCurrentRented(0);
            String name;
            int id;
            String licenseNumber;
            int maxNumberOfCarRented;
            System.out.println("enter the name of the customer number "+(i+1));
            name=inputScanner.next();
            customer[i].setName(name);

            System.out.println("enter the ID for the cutomer number "+(i+1));
            id=inputScanner.nextInt();
            customer[i].setId(id);

            System.out.println("enter the license number for the customer number "+(i+1));
            licenseNumber=inputScanner.next();
            customer[i].setLicenseNumber(licenseNumber);

            System.out.println("enter the maximum number of cars to rent for customer number "+(i+1));
            maxNumberOfCarRented=inputScanner.nextInt();
            customer[i].setMaxNumberOfRentedCars(maxNumberOfCarRented);

            System.out.println("enter the number of cars you wish to rent :");
            int 	numOfCarsToRent=inputScanner.nextInt();

            System.out.println("the available cars for rent is ");
            for(int i1=0;i1<availableVehicles.length;i1++) {
                if(availableVehicles[i1].isAvailable()==true) {
                    System.out.println("CAR NUMBER "+(i1+1));
                    availableVehicles[i1].printInfo();
                }
            }

            for(int i3=0;i3<numOfCarsToRent;i3++) {
                for(int i2=0;i2<customer.length;i2++) {
                    if(customer[i2].getNumberOfCurrentRented() < customer[i2].getMaxNumberOfRentedCars()) {
                        String registrationNumber;
                        System.out.println("enter the vehicle number "+(i3+1)  +" registration number to rent it or type exit to stop!!");
                        registrationNumber=inputScanner.next();
                        if(registrationNumber.equals("exit")) {
                            System.out.println("Rental process ended by customer");
                            break;
                        }
                        System.out.println("enter the number of days you want to rent");
                        int rentDays=inputScanner.nextInt();

                        boolean vehicleFound = false;
                        for(int k=0;k<availableVehicles.length;k++) {
                            if(registrationNumber.equals(availableVehicles[k].getRegistrationNumber()) && availableVehicles[k].isAvailable()) {
                                System.out.println("enter the number of days you want to rent this vehicle ");
                                customer[i2].rentVehicle(availableVehicles[k], rentDays);
                                availableVehicles[k].setAvailable(false);
                                customer[i2].setNumberOfCurrentRented(customer[i2].getNumberOfCurrentRented()+1);
                                System.out.println("vehicle with registration number "+registrationNumber +" rented successfully");
                                vehicleFound = true;
                                break;
                            }
                        }

                        if (!vehicleFound) {
                            System.out.println("The vehicle is either unavailable or not found!");
                        }

                    } else {
                        System.out.println("You have reached your rental limit");
                        break;
                    }
                }
            }
        }

        while(true) {
            System.out.println("Main Menu "
                    + "Main Menu:\r\n"
                    + "1: Print Customer Information\r\n"
                    + "2: Display Total Rental Cost for a Customer\r\n"
                    + "3: Count Rented Vehicles by Type\r\n"
                    + "4: Rent a New Vehicle\r\n"
                    + "5: Return a Vehicle\r\n"
                    + "6: Display All Available Vehicles in Ascending Order of Price\r\n"
                    + "7: Display All Available Vehicles in Alphabetical Order of Type\r\n"
                    + "8: Exit\r\n");

            Scanner input = new Scanner(System.in);

            int choise=input.nextInt();

            switch (choise) {
                case 1: {
                    System.out.println("please enter the customer ID : ");
                    int id=input.nextInt();
                    findCustomerById(customer, id).printInfo();
                    break;
                }

                case 2:{
                    System.out.println("enter the customer ID : ");
                    int id=input.nextInt();
                    findCustomerById(customer, id).calculateRent();
                    break;
                }
                case 3:{
                    System.out.println("enter the customer ID : ");
                    int id=input.nextInt();
                    System.out.println("enter the vehicle Type :");
                    String type=input.next();

                    if(findCustomerById(customer, id).getNumberOfCurrentRented()<findCustomerById(customer, id).getMaxNumberOfRentedCars()) {

                        findCustomerById(customer, id).countVehiclesByType(type);
                    }else {
                        System.out.println("NO CARS RENTED!!!");
                    }
                    break;
                }

                case 4:{
                    System.out.println("enter the customer ID : ");
                    int id=input.nextInt();
                    if(findCustomerById(customer, id).getNumberOfCurrentRented()<findCustomerById(customer, id).getMaxNumberOfRentedCars()) {
                        System.out.println("enter the type of the vehicle you wish to rent :");
                        String typeString=input.next();

                        System.out.println("there is the list with that type :");

                        for(int i=0;i<availableVehicles.length;i++) {
                            if(availableVehicles[i].getType().equals(typeString)) {
                                availableVehicles[i].printInfo();
                            }
                        }

                        System.out.println("please enter the registration number of the car that you wish to rent :");
                        String regNumber=input.next();
                        System.out.println("enter the number of the rental days you want :");
                        int rentDays=input.nextInt();
                        Double total=  findVehicleByRegistrationNumber(availableVehicles, regNumber).getRentalRatePerDay()*rentDays;
                        System.out.println("the total rent cost for that car is "+total);
                        findCustomerById(customer, id).rentVehicle(findVehicleByRegistrationNumber(availableVehicles, regNumber), rentDays);
                        findVehicleByRegistrationNumber(availableVehicles, regNumber).setAvailable(false);
                        findCustomerById(customer, id).setNumberOfCurrentRented(findCustomerById(customer, id).getNumberOfCurrentRented()+1);
                    } else {
                        System.out.println("you've reached the limit to rent cars !!");
                    }
                    break;
                }

                case 5: {
                    System.out.println("enter the customer ID : ");
                    int id=input.nextInt();
                    findCustomerById(customer, id).printInfo();
                    System.out.println("enter the enter the registration number of the vehicle you wish to return :");
                    String regNumber=input.next();
                    findCustomerById(customer, id).reternVehicle(findVehicleByRegistrationNumber(availableVehicles, regNumber));
                    System.out.println("the car returned Succesfully ");
                    findVehicleByRegistrationNumber(availableVehicles, regNumber).setAvailable(true);
                    findCustomerById(customer, id).setNumberOfCurrentRented(findCustomerById(customer, id).getNumberOfCurrentRented()-1);
                    break;
                }

                case 6:{
                    displayVehiclesByPrice(availableVehicles);
                    break;
                }
                case 7:{
                    displayVehiclesByType(availableVehicles);
                    break;
                }
                case 8:{
                    System.out.println("GoodBYE!");
                    return;

                }
                default:
                    System.out.println("WRONG CHOISE !!");
            }
        }
    }

    public static Customer findCustomerById(Customer[] customers,int customerId) {

        for(int i=0;i<customers.length;i++) {
            if(customers[i].getId()==customerId) {
                return customers[i];
            }
        }
        return null;

    }

    public static Vehicle findVehicleByRegistrationNumber(Vehicle[]vehicles, String regNumber) {

        for(int i =0;i<vehicles.length;i++) {
            if(vehicles[i].getRegistrationNumber().equals(regNumber)) {
                return vehicles[i];
            }
        }
        return null;

    }

    public static void displayVehiclesByPrice(Vehicle[] vehicles) {
        for (int i = 0; i < vehicles.length - 1; i++) {
            for (int j = 0; j < vehicles.length - i - 1; j++) {
                if (vehicles[j].getRentalRatePerDay() > vehicles[j + 1].getRentalRatePerDay()) {
                    Vehicle temp = vehicles[j];
                    vehicles[j] = vehicles[j + 1];
                    vehicles[j + 1] = temp;
                }
            }
        }
        for(int i=0;i<vehicles.length;i++) {
            vehicles[i].printInfo();
        }
    }

    public static void displayVehiclesByType(Vehicle[] vehicles) {
        for (int i = 0; i < vehicles.length - 1; i++) {
            for (int j = 0; j < vehicles.length - i - 1; j++) {
                if (vehicles[j].isAvailable() && vehicles[j].getType().compareTo(vehicles[j + 1].getType()) > 0) {
                    Vehicle temp = vehicles[j];
                    vehicles[j] = vehicles[j + 1];
                    vehicles[j + 1] = temp;
                }
            }
        }
        for(int i=0;i<vehicles.length;i++) {
            if(vehicles[i].isAvailable()) {
                vehicles[i].printInfo();
            }
        }
    }
}

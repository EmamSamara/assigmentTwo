//emam samara
//1220022
//lab section 5



package assigment;


public class Customer {

	private String Name;
	private int id;
	private String licenseNumber ;
	private int numberOfCurrentRented;
	private int maxNumberOfRentedCars;
	private Vehicle[] rentedVehicles;
	
	public Customer () {
        this.rentedVehicles = new Vehicle[maxNumberOfRentedCars];

	}
	public Customer(String name, int id, String licenseNumber, int numberOfCurrentRented, int maxNumberOfRentedCars) {
	    this.Name = name;
	    this.id = id;
	    this.licenseNumber = licenseNumber;
	    this.numberOfCurrentRented = numberOfCurrentRented;
	    this.maxNumberOfRentedCars = maxNumberOfRentedCars;
	    this.rentedVehicles = new Vehicle[maxNumberOfRentedCars];
	}

	
	 
	 
	
public String getName() {
	return Name;}

     public void setName(String name) {
		this.Name=name;}
   
     
     
     
	public int getId() {
		return id;}
	
	public void setId(int id) {
		this.id = id;}
	
	
	
	public String getLicenseNumber() {
		return licenseNumber;}
	
	public void setLicenseNumber(String licenseNumber) {
		this.licenseNumber = licenseNumber;}
	
	
	
public int getNumberOfCurrentRented() {
	return numberOfCurrentRented;}

	public void setNumberOfCurrentRented(int numberOfCurrentRented) {
		this.numberOfCurrentRented=numberOfCurrentRented;	}
	
	
	
	public int getMaxNumberOfRentedCars() {
		return maxNumberOfRentedCars;}
	
	public void setMaxNumberOfRentedCars(int maxNumberOfRentedCars) {
		this.maxNumberOfRentedCars=maxNumberOfRentedCars;}
	
	
	
	public void rentVehicle(Vehicle vehicle , int days) {
		if (numberOfCurrentRented<maxNumberOfRentedCars) {
			numberOfCurrentRented++;
			
			for(int i=0;i<rentedVehicles.length;i++) {
				if(rentedVehicles[i]==null) {
					rentedVehicles[i]=vehicle;
					vehicle.setAvailable(false);
				}
			}
		
			System.err.println("car rented succesfully");
		}else {
			System.out.println("you've reached the limit to rent another car");
		}
	}
	
	public void reternVehicle(Vehicle vehicle) {
		if(numberOfCurrentRented>0) {
			numberOfCurrentRented--;
			
			for(int i=0;i<rentedVehicles.length;i++) {
				if(rentedVehicles[i].getRegistrationNumber()==vehicle.getRegistrationNumber()) {
					rentedVehicles[i]=null;
					System.out.println("the Car returned Succesfully ");
				}
			}
			
		}else {
			System.out.println("ERROR!!! you have no rented cars");
		}
	}
	
	
	
	public void calculateRent() {
		if(rentedVehicles!=null) {
		double sum=0;
		for(int i=0;i<rentedVehicles.length;i++) {
			double Rent=(rentedVehicles[i].getRentalRatePerDay())*(rentedVehicles[i].getRentDays());
			sum=sum+Rent;
		}
		System.out.println("the total rent cost is %.2f"+sum);
		}
		else {
			System.out.println("you have no rented cars !!");
		}
	}
	
	
	public void calculateRent(String type) {
		if(rentedVehicles!=null) {
			
			double sum=0;
			for(int i=0;i<rentedVehicles.length;i++) {
				
				if(rentedVehicles[i].getType()==type) {
					double rent =	rentedVehicles[i].getRentalRatePerDay()*rentedVehicles[i].getRentDays();
					sum =sum +rent;
					
				}
			}
			
			System.out.println("the total cost for the type "+type+" cars you've rented is %.2f"+sum);
			
			
		}else {
			System.out.println("you have no rented cars !!");
		}
	}
	
	
	
	public void countVehiclesByType(String type) {
	    if (rentedVehicles != null) {
	        int count = 0;

	        for (int i = 0; i < rentedVehicles.length; i++) {
	            if (rentedVehicles[i] != null && rentedVehicles[i].getType().equals(type)) {
	                count++;
	            }
	        }

	        System.out.println("The number of " + type + " cars you've rented is: " + count);
	    } else {
	        System.out.println("You have no rented cars!");
	    }
	}
	

	
	
	
	
	
	
	
	

	
	public void printInfo() {
		System.out.println("Customer Name : "+Name);
		System.out.println("Customer ID : "+id);
		System.out.println("Customer License Number : "+licenseNumber);
		System.out.println("Customer Max Number Of Rented Cars : "+maxNumberOfRentedCars);
		
		System.out.println("rented vehicles :");
		if(rentedVehicles!=null) {
		for(int i=0;i<rentedVehicles.length;i++) {
				rentedVehicles[i].printInfo();
		}
		}
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
	
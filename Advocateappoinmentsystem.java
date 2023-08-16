package advocate;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Advocateappoinmentsystem {
          
	 private static Scanner sc = new Scanner(System.in);
	 private static DatabaseManager dbManager = new DatabaseManager();

	public static void main(String[] args) {
		 while (true) {
			    System.out.println("|--------------------------------------------------|");
			    System.out.println("|--------AdvocateAppoinmentsystem Main Menu:-------|");
		        System.out.println("|--------------------------------------------------|");
		        System.out.println("1. Customer");
		        System.out.println("2. Advocate");
		        System.out.println("3. Appointment");
		        System.out.println("4. Service");
		        System.out.println("0. Exit");
		        System.out.println("Enter your choice: ");
				int ch = sc .nextInt();
		        switch (ch) {
		        case 1:
		            customerMenu();
		            break;
		        case 2:
		            advocateMenu();
		            break;
		        case 3:
		           appointmentMenu();
		            break;
		        case 4:
		           serviceMenu();
		            break;
		        case 0:
		            System.out.println("Exiting...");
		            System.exit(0);
		        default:
		            System.out.println("Invalid choice. Please try again.");
		    }
		}
	}

	private static void customerMenu() {
	    while (true) {
	        System.out.println("********Customer Menu:");
	        System.out.println("1. Register Customer");
	        System.out.println("2. Modify Customer details");
	        System.out.println("3. Delete Customer record");
	        System.out.println("4. View single record");
	        System.out.println("5. View all records");
	        System.out.println("0. Back to Main Menu");

	       System.out.println("Please Enter your choice: ");
             int ch = sc.nextInt();
	        switch (ch) {
	            case 1:
	            	registerCustomer();
	                break;
	            case 2:
                    modifyCustomerDetails();
                    break;
                case 3:
                    deleteCustomerRecord();
                    break;
                case 4:
                    viewSingleCustomer();
                    break;
                case 5:
                    viewAllCustomers();
                    break;
                case 0:
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
	}
	private static void registerCustomer() {
		System.out.println("Enter customer id: ");
        int customer_id = sc.nextInt(); 
        sc.nextLine(); 
       System.out.println("Enter customer name: ");
       String name = sc.nextLine();
        System.out.println("Enter customer email: ");
        String email = sc.nextLine();
        System.out.println("Enter customer phone: ");
        String phone = sc.nextLine();
        System.out.println("Enter customer address: ");
        String address = sc.nextLine();
        dbManager.registerCustomer(customer_id, name, email, phone, address);
        System.out.println("Customer registered successfully!");
    }

    private static void modifyCustomerDetails() {
        System.out.println("Enter customer ID: ");
        int customerId = sc.nextInt();
        sc.nextLine(); 
        Customer customer = dbManager.viewCustomer(customerId);

        if (customer != null) {
            System.out.println("Enter new name: ");
            String newName = sc.nextLine();
            System.out.println("Enter new email: ");
            String newEmail = sc.nextLine();
            System.out.println("Enter new phone: ");
            String newPhone = sc.nextLine();
            System.out.println("Enter new address: ");
            String newAddress = sc.nextLine();
            dbManager.updateCustomer(customerId, newName, newEmail, newPhone, newAddress);
            System.out.println("Customer details updated successfully!");
        } else {
            System.out.println("Customer not found.");
        }
    }

    private static void deleteCustomerRecord() {
        System.out.println("Enter customer ID: ");
        int customerId = sc.nextInt();

        Customer customer = dbManager.viewCustomer(customerId);

        if (customer != null) {
            dbManager.deleteCustomer(customerId);
            System.out.println("Customer record deleted successfully!");
        } else {
            System.out.println("Customer not found.");
        }
    }

    private static void viewSingleCustomer() {
        System.out.println("Enter customer ID: ");
        int customerId = sc.nextInt();
       
        Customer customer = dbManager.viewCustomer(customerId);

        if (customer != null) {
            System.out.println("Customer Details:");
            System.out.println("ID: " + customer.getCustomer_id());
            System.out.println("Name: " + customer.getName());
            System.out.println("Email: " + customer.getEmail());
            System.out.println("Phone: " + customer.getPhone());
            System.out.println("Address: " + customer.getAddress());
        } else {
            System.out.println("Customer not found.");
        }
    }

    private static void viewAllCustomers() {
        System.out.println("All Customers:");
        List<Customer> customers = dbManager.viewAllCustomers();
        
        if (customers.isEmpty()) {
            System.out.println("No customers found.");
        } else {
            for (Customer customer : customers) {
                System.out.println("ID: " + customer.getCustomer_id() + ", Name: " + customer.getName() + ", Email: " + customer.getEmail() + ", Phone: " + customer.getPhone() + ", Address: " + customer.getAddress());
            }
        }
    }
    
    private static void advocateMenu() {
        while (true) {
        	System.out.println("********Advocate Menu:");
            System.out.println("1. Register Advocate");
            System.out.println("2. View Single Advocate");
            System.out.println("3. View All Advocates");
            System.out.println("0. Back to Main Menu");
            int choice = getIntInput("Enter your choice: ");

            switch (choice) {
                case 1:
                    registerAdvocate();
                    break;
                case 2:
                    viewSingleAdvocate();
                    break;
                case 3:
                    viewAllAdvocates();
                    break;
                case 4:
                    addService();
                    break;
                case 5:
                    viewSingleService();
                    break;
                case 6:
                    viewAllServices();
                    break;
                case 0:
                    return; 
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void registerAdvocate() {
        System.out.println("Enter advocate details:");
        System.out.println("Advocate id: ");
        int advocate_id = sc.nextInt();
        sc.nextLine();
        System.out.println("Advocate Name: ");
        String name = sc.nextLine();
        System.out.println("Email: ");
        String email = sc.nextLine();
        System.out.println("Phone: ");
        String phone = sc.nextLine();
        System.out.println("Specialization: ");
        String specialization = sc.nextLine();

        dbManager.registerAdvocate(advocate_id, name, email, phone, specialization);
        System.out.println("Advocate registered successfully!");
    }

    private static void viewSingleAdvocate() {
        int advocateId = getIntInput("Enter advocate ID: ");
        Advocate advocate = dbManager.viewAdvocate(advocateId);

        if (advocate != null) {
            displayAdvocateInfo(advocate);
        } else {
            System.out.println("Advocate not found.");
        }
    }

    private static void viewAllAdvocates() {
        List<Advocate> advocates = dbManager.viewAllAdvocates();

        if (advocates.isEmpty()) {
            System.out.println("No advocates found.");
        } else {
            for (Advocate advocate : advocates) {
                displayAdvocateInfo(advocate);
            }
        }
    }

    private static void displayAdvocateInfo(Advocate advocate) {
        System.out.println("Advocate Details:");
        System.out.println("ID: " + advocate.getAdvocate_id());
        System.out.println("Name: " + advocate.getName());
        System.out.println("Email: " + advocate.getEmail());
        System.out.println("Contact Number: " + advocate.getContactNumber());
        System.out.println("Specialization: " + advocate.getSpecialization());
    }

    private static void displayServiceInfo(Service service) {
        System.out.println("Service Details:");
        System.out.println("Service ID: " + service.getService_id());
        System.out.println("Name: " + service.getName());
    }
  
    private static void appointmentMenu() {
        while (true) {
        	 System.out.println("********Appointment Menu:");
             System.out.println("1. Book an appointment");
             System.out.println("2. Modify appointment details");
             System.out.println("3. Delete an appointment");
             System.out.println("4. View single appointment");
             System.out.println("5. View all appointments");
             System.out.println("0. Back to Main Menu");
            System.out.println("Enter your choice: ");
            int ch = sc.nextInt();
            switch (ch) {
                case 1:
                    bookAppointment();
                    break;
                case 2:
                    modifyAppointmentDetails();
                    break;
                case 3:
                    deleteAppointment();
                    break;
                case 4:
                    viewSingleAppointment();
                    break;
                case 5:
                    viewAllAppointments();
                    break;
                case 0:
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }         
       
 
    private static void bookAppointment() {
    	System.out.println("Enter Appointment ID: ");
        int appointmentId = getIntInput("Appointment ID: ");
        
        
        System.out.println("Enter customer ID: ");
        int customerId = getIntInput("Customer ID: ");

        System.out.println("Enter advocate ID: ");
        int advocateId = getIntInput("Advocate ID: ");

        System.out.println("Enter appointment date (yyyy-MM-dd): ");
        Date appointmentDate = getDateInput("Appointment Date: ");

        System.out.println("Choose a service from the following list:");
        displayAllServices();
        int serviceId = getIntInput("Service ID: ");

        dbManager.bookAppointment( appointmentId, customerId, advocateId, appointmentDate, serviceId);
        System.out.println("Appointment booked successfully!");
    }
         
    private static void modifyAppointmentDetails() {
        System.out.println("Enter appointment ID: ");
        int appointmentId = sc.nextInt();
        

        Appointment appointment = dbManager.viewAppointment(appointmentId);

        if (appointment != null) {
            System.out.print("Enter new date (yyyy-MM-dd): ");
            String newDateTimeStr = sc.nextLine();
            Date newAppointmentDate = getDateInput(newDateTimeStr);

            System.out.println("Enter new service: ");
            String newService = sc.nextLine();

            dbManager.modifyAppointment(appointmentId, newAppointmentDate, newService);
            System.out.println("Appointment details modified successfully!");
        } else {
            System.out.println("Appointment not found.");
        }
    }

    private static void deleteAppointment() {
        System.out.println("Enter appointment ID: ");
        int appointmentId = sc.nextInt();
        
        Appointment appointment = dbManager.viewAppointment(appointmentId);

        if (appointment != null) {
            dbManager.deleteAppointment(appointmentId);
            System.out.println("Appointment deleted successfully!");
        } else {
            System.out.println("Appointment not found.");
        }
    }

    private static void viewSingleAppointment() {
        System.out.println("Enter appointment ID: ");
        int appointmentId = sc.nextInt();
       

        Appointment appointment = dbManager.viewAppointment(appointmentId);

        if (appointment != null) {
            displayAppointmentInfo(appointment);
        } else {
            System.out.println("Appointment not found.");
        }
    }

    private static void viewAllAppointments() {
        List<Appointment> appointments = dbManager.viewAllAppointments();

        if (appointments.isEmpty()) {
            System.out.println("No appointments found.");
        } else {
            for (Appointment appointment : appointments) {
                displayAppointmentInfo(appointment);
            }
        }
    }

    private static void displayAppointmentInfo(Appointment appointment) {
        System.out.println("-----Appointment Details:");
        System.out.println("Appointment ID: " + appointment.getAppointment_id());
        System.out.println("Customer: " + appointment.getCustomer().getName());
        System.out.println("Advocate: " + appointment.getAdvocate().getName());
        System.out.println("Date: " + appointment.getAppointmentDate());
        System.out.println("Service: " + appointment.getService());
    }
    private static void displayAllServices() {
        List<Service> services = dbManager.viewAllServices();
        for (Service service : services) {
            System.out.println("Service ID: " + service.getService_id() + " - " + service.getName());
        }
    }


	private static Date getDateInput(String prompt) {
        while (true) {
            String input = getStringInput(prompt);
            try {
                return new SimpleDateFormat("yyyy-MM-dd").parse(input);
            } catch (ParseException e) {
                System.out.println("Invalid date format. Please use yyyy-MM-dd.");
            }
        }
    }

	
	private static void serviceMenu() {
	    while (true) {
	    	System.out.println("*********Service Menu:");
		    System.out.println("1. Add Service");
		    System.out.println("2. View Single Service");
		    System.out.println("3. View All Services");
		    System.out.println("4. Modify the service Details");
		    System.out.println("0. Back to Main Menu");
		    
	        int choice = getIntInput("Enter your choice: ");

	        switch (choice) {
	            case 1:
	                addService();
	                break;
	            case 2:
	                viewSingleService();
	                break;
	            case 3:
	                viewAllServices();
	                break;
	            case 4:
	            	modifyService();
	            	break;
	            case 0:
	                return; 
	            default:
	                System.out.println("Invalid choice. Please try again.");
	        }
	    }
	}

	

	private static void addService() {
		        System.out.println("Enter the service id: ");
		        int service_id = sc.nextInt();
		        sc.nextLine();
	    		System.out.println("Enter the service name: ");
	    		String serviceName = sc.nextLine();
	    dbManager.addService(service_id, serviceName);
	    System.out.println("Service added successfully!");
	}

	private static void viewSingleService() {
	    int serviceId = getIntInput("Enter service ID: ");
	    Service service = dbManager.viewService(serviceId);

	    if (service != null) {
	        displayServiceInfo(service);
	    } else {
	        System.out.println("Service not found.");
	    }
	}

	private static void viewAllServices() {
	    List<Service> services = dbManager.viewAllServices();

	    if (services.isEmpty()) {
	        System.out.println("No services found.");
	    } else {
	        for (Service service : services) {
	            displayServiceInfo(service);
	        }
	    }
	}
	private static void modifyService() {
	    int serviceId = getIntInput("Enter service ID to modify: ");
	    Service service = dbManager.viewService(serviceId);
                 sc.nextLine();
	    if (service != null) {
	        String newName = getStringInput("Enter new name for the service: ");
	        dbManager.modifyService(serviceId, newName);
	        System.out.println("Service modified successfully!");
	    } else {
	        System.out.println("Service not found.");
	    }
	}
	    
	    private static String getStringInput(String prompt) {
			System.out.println(prompt);
	        while (!sc.hasNextLine()) {
	            System.out.println("Invalid input. Please enter a string: ");
	            
	        }
	        return sc.nextLine();
		}
	private static int getIntInput(String prompt) {
        System.out.println(prompt);
        while (!sc.hasNextInt()) {
            System.out.println("Invalid input. Please enter a number: ");
            
        }
        return sc.nextInt();
    }
}
	
	
    


package advocate;


import java.util.Date;

public class Appointment {
    private int appointment_id;
    private Customer customer;
    private Advocate advocate;
    private Date appointmentDate;
    private String service;

    public Appointment(int id, Customer customer, Advocate advocate, Date appointmentDate, String service) {
        this.setAppointment_id(id);
        this.setCustomer(customer);
        this.setAdvocate(advocate);
        this.setAppointmentDate(appointmentDate);
        this.setService(service);
    }

	public int getAppointment_id() {
		return appointment_id;
	}

	public void setAppointment_id(int appointment_id) {
		this.appointment_id = appointment_id;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public Date getAppointmentDate() {
		return appointmentDate;
	}

	public void setAppointmentDate(Date appointmentDate) {
		this.appointmentDate = appointmentDate;
	}

	public Advocate getAdvocate() {
		return advocate;
	}

	public void setAdvocate(Advocate advocate) {
		this.advocate = advocate;
	}

	public String getService() {
		return service;
	}

	public void setService(String service) {
		this.service = service;
	}

  
}
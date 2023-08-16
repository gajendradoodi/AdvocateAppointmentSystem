package advocate;

public class Advocate {
    private int advocate_id;
    private String name;
    private String email;
    private String contactNumber;
    private String specialization;
   

    public Advocate(int id, String name, String email, String contactNumber, String specialization) {
        this.setAdvocate_id(id);
        this.setName(name);
        this.setEmail(email);
        this.setContactNumber(contactNumber);
        this.setSpecialization(specialization);
       
    }

    public String getEmail() {
		
		return email;
	}

	public String setEmail(String email) {
		
		return email;
	}
	
	public int getAdvocate_id() {
		return advocate_id;
	}

	public void setAdvocate_id(int advocate_id) {
		this.advocate_id = advocate_id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSpecialization() {
		return specialization;
	}

	public void setSpecialization(String specialization) {
		this.specialization = specialization;
	}

	public String getContactNumber() {
		return contactNumber;
	}

	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}

	
          
    
}
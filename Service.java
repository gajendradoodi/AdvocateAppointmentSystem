package advocate;

public class Service {
    private int service_id;
    private String name;
  

    public Service(int id, String name) {
        this.setService_id(id);
        this.setName(name);
      
    }

	public int getService_id() {
		return service_id;
	}

	public void setService_id(int service_id) {
		this.service_id = service_id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
package advocate;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;

public class DatabaseManager {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/appointment_system";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "gajendra#750";

    private Connection connection;

    public DatabaseManager() {
        try {
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Connection getConnection() {
        return connection;
    }

    public void closeConnection() {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    public void registerCustomer(int customer_id, String name, String email, String phone, String address) {
        String sql = "INSERT INTO customers (customer_id, name, email, phone, address) VALUES (?, ?, ?, ?, ?)";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
        	statement.setInt(1, customer_id);
            statement.setString(2, name);
            statement.setString(3, email);
            statement.setString(4, phone);
            statement.setString(5, address);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateCustomer(int customerId, String name, String email, String phone, String address) {
        String sql = "UPDATE customers SET name = ?, email = ?, phone = ?, address = ? WHERE customer_id = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, name);
            statement.setString(2, email);
            statement.setString(3, phone);
            statement.setString(4, address);
            statement.setInt(5, customerId);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteCustomer(int customerId) {
        String sql = "DELETE FROM customers WHERE customer_id = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, customerId);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Customer viewCustomer(int customerId) {
        String sql = "SELECT * FROM customers WHERE customer_id = ?";
        Customer customer = null;

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, customerId);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    customer = new Customer(
                        resultSet.getInt("customer_id"),
                        resultSet.getString("name"),
                        resultSet.getString("email"),
                        resultSet.getString("phone"),
                        resultSet.getString("address")
                    );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return customer;
    }

    public List<Customer> viewAllCustomers() {
        List<Customer> customers = new ArrayList<>();
        String sql = "SELECT * FROM customers";

        try (PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                Customer customer = new Customer(
                    resultSet.getInt("customer_id"),
                    resultSet.getString("name"),
                    resultSet.getString("email"),
                    resultSet.getString("phone"),
                    resultSet.getString("address")
                );
                customers.add(customer);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return customers;
    }
    public void registerAdvocate(int advocate_id, String name, String email, String phone, String specialization) {
        String sql = "INSERT INTO advocates (advocate_id, name, email, phone, specialization) VALUES (?, ?, ?, ?, ?)";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
        	statement.setInt(1, advocate_id);
            statement.setString(2, name);
            statement.setString(3, email);
            statement.setString(4, phone);
            statement.setString(5, specialization);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Advocate viewAdvocate(int advocateId) {
        String sql = "SELECT * FROM advocates WHERE advocate_id = ?";
        Advocate advocate = null;

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, advocateId);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    advocate = new Advocate(
                        resultSet.getInt("advocate_id"),
                        resultSet.getString("name"),
                        resultSet.getString("email"),
                        resultSet.getString("phone"),
                        resultSet.getString("specialization")
                    );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return advocate;
    }

    public List<Advocate> viewAllAdvocates() {
        List<Advocate> advocates = new ArrayList<>();
        String sql = "SELECT * FROM advocates";

        try (PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                Advocate advocate = new Advocate(
                    resultSet.getInt("advocate_id"),
                    resultSet.getString("name"),
                    resultSet.getString("email"),
                    resultSet.getString("phone"),
                    resultSet.getString("specialization")
                );
                advocates.add(advocate);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return advocates;
    }
    public void addService(int id, String service_name) {
        String sql = "INSERT INTO services (service_id, service_name) VALUES (?, ?)";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
        	statement.setInt(1, id);
            statement.setString(2, service_name);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace(); 
        }
    }

    public Service viewService(int serviceId) {
        String sql = "SELECT * FROM services WHERE service_id = ?";
        Service service = null;

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, serviceId);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    service = new Service(
                        resultSet.getInt("service_id"),
                        resultSet.getString("service_name")
                    );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return service;
    }

    public List<Service> viewAllServices() {
        List<Service> services = new ArrayList<>();
        String sql = "SELECT * FROM services";

        try (PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                Service service = new Service(
                    resultSet.getInt("service_id"),
                    resultSet.getString("service_name")
                );
                services.add(service);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return services;
    }
    public void modifyService(int serviceId, String newName) {
        String sql = "UPDATE services SET service_name = ? WHERE service_id = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, newName);
            statement.setInt(2, serviceId);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void bookAppointment(int  appointmentId, int customerId, int advocateId, Date appointmentDate, int serviceId) {
        String sql = "INSERT INTO appointments (appointment_id, customer_id, advocate_id, appointment_date, service) VALUES (?, ?, ?, ?, ?)";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
        	statement.setInt(1, appointmentId);
            statement.setInt(2, customerId);
            statement.setInt(3, advocateId);
            statement.setDate(4, new java.sql.Date(appointmentDate.getTime()));
            statement.setInt(5, serviceId);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void modifyAppointment(int appointmentId, Date newAppointmentDate, String newService) {
        String sql = "UPDATE appointments SET appointment_date = ?, service = ? WHERE appointment_id = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setDate(1, new java.sql.Date(newAppointmentDate.getTime()));
            statement.setString(2, newService);
            statement.setInt(3, appointmentId);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
   
    public void deleteAppointment(int appointmentId) {
        String sql = "DELETE FROM appointments WHERE appointment_id = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, appointmentId);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Appointment viewAppointment(int appointmentId) {
        String sql = "SELECT * FROM appointments WHERE appointment_id = ?";
        Appointment appointment = null;

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, appointmentId);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    int customerId = resultSet.getInt("customer_id");
                    int advocateId = resultSet.getInt("advocate_id");
                    Date appointmentDate = resultSet.getTimestamp("appointment_date");
                    String service = resultSet.getString("service");
                    
                    Customer customer = viewCustomer(customerId);
                    Advocate advocate = viewAdvocate(advocateId);

                    appointment = new Appointment(appointmentId, customer, advocate, appointmentDate, service);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return appointment;
    }

    public List<Appointment> viewAllAppointments() {
        List<Appointment> appointments = new ArrayList<>();
        String sql = "SELECT * FROM appointments";

        try (PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                int appointmentId = resultSet.getInt("appointment_id");
                int customerId = resultSet.getInt("customer_id");
                int advocateId = resultSet.getInt("advocate_id");
                Date appointmentDate = resultSet.getTimestamp("appointment_date");
                String service = resultSet.getString("service");

                Customer customer = viewCustomer(customerId);
                Advocate advocate = viewAdvocate(advocateId);

                Appointment appointment = new Appointment(appointmentId, customer, advocate, appointmentDate, service);
                appointments.add(appointment);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return appointments;
    }

    

}
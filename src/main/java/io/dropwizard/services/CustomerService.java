package io.dropwizard.services;

import io.dropwizard.models.Customer;
import io.dropwizard.persistence.DAO.CustomerDAO;

import java.util.List;

public class CustomerService {
    CustomerDAO dao;

    private Customer customer;

    public CustomerService() {
        this.dao = new CustomerDAO();
    }

    ProjectService service = new ProjectService();

    public List<Customer> getCustomer() {
        return dao.getCustomer();
    }

    public Customer getCustomerByName(String customerName) {
       return dao.getCustomerByName(customerName);
    }

    public void addCustomer(String klantnaam, String projectnaam, String onderwerpnaam) {

        dao.addCustomer(klantnaam);

        customer = dao.getCustomerByName(klantnaam);

        service.addProject(customer.getCustomerId(), projectnaam, onderwerpnaam);

    }
    }


package io.dropwizard.services;

import io.dropwizard.models.Customer;
import io.dropwizard.persistence.DAO.CustomerDAO;

import java.util.List;

public class CustomerService {
    CustomerDAO dao = new CustomerDAO();

    public CustomerService(){

    }

    public List<Customer> getCustomer() {
        return dao.getCustomer();
    }

    public Customer getCustomerByName(String customerName) {
       return dao.getCustomerByName(customerName);
    }
}

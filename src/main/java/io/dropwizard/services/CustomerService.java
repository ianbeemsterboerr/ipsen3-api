package io.dropwizard.services;

import io.dropwizard.models.Customer;
import io.dropwizard.persistence.DAO.CustomerDAO;

import java.util.List;

public class CustomerService {
    CustomerDAO dao;

    public CustomerService(CustomerDAO dao){
        this.dao = dao;
    }

    public List<Customer> getCustomer() {
        return dao.getCustomer();
    }

    public Customer getCustomerByName(String customerName) {
       return dao.getCustomerByName(customerName);
    }
}

package io.dropwizard.services;
import io.dropwizard.models.Customer;
import io.dropwizard.persistence.DAO.CustomerDAO;

import javax.inject.Inject;
import java.util.List;

public class CustomerService {
    CustomerDAO dao;

    @Inject
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

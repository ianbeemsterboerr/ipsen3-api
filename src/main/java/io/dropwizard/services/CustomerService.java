package io.dropwizard.services;

import io.dropwizard.models.Customer;
import io.dropwizard.persistence.DAO.CustomerDAO;

import java.util.List;

public class CustomerService {
    private CustomerDAO dao;

    private Customer customer;
    private ProjectService service;

    public CustomerService(CustomerDAO dao, ProjectService service){
        this.dao = dao;
        this.service = service;
    }



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
    }}


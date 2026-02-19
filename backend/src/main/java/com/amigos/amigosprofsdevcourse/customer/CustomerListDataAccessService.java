package com.amigos.amigosprofsdevcourse.customer;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository("list")
public class CustomerListDataAccessService implements CustomerDao{
    // db
    private static final List<Customer> customers;

    static {
        customers = new ArrayList<>();
        customers.add(new Customer(1, "Alex", "alex@gmail.com", 30, "UA"));
        customers.add(new Customer(2, "July", "july@gmail.com", 21, "UA"));
        customers.add(new Customer(3, "Jamila", "jamila@gmail.com", 45, "UA"));
    }

    @Override
    public List<Customer> getAllCustomers() {
        return customers;
    }

    @Override
    public Optional<Customer> getCustomerById(Integer customerId) {
        return customers.stream()
                .filter(c -> c.getId().equals(customerId))
                .findFirst();
    }

    @Override
    public Optional<Customer> getCustomerByEmail(String email) {
        return customers
                .stream()
                .filter(c -> c.getEmail().equals(email))
                .findFirst();
    }

    @Override
    public void insertCustomer(Customer customer) {
        customers.add(customer);
    }

    @Override
    public void removeCustomer(Integer id) {
        customers.remove(id);
    }

    @Override
    public void updateCustomer(Customer customer) {

    }
}

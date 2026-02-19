package com.amigos.amigosprofsdevcourse.customer;

import java.util.List;
import java.util.Optional;

public interface CustomerDao {
    List<Customer> getAllCustomers();
    Optional<Customer> getCustomerById(Integer customerId);
    Optional<Customer> getCustomerByEmail(String email);
    void insertCustomer(Customer customer);
    void removeCustomer(Integer id);
    void updateCustomer(Customer customer);
}

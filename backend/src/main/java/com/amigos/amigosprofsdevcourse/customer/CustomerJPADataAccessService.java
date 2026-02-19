package com.amigos.amigosprofsdevcourse.customer;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository("jpa")
@AllArgsConstructor
public class CustomerJPADataAccessService implements CustomerDao{
    private final CustomerRepository customerRepository;

    @Override
    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    @Override
    public Optional<Customer> getCustomerById(Integer customerId) {
        return customerRepository.findById(customerId);
    }

    @Override
    public Optional<Customer> getCustomerByEmail(String email) {
        return customerRepository.findByEmail(email);
    }

    @Override
    public void insertCustomer(Customer customer) {
        customerRepository.save(customer);
    }

    @Override
    public void removeCustomer(Integer id) {
        customerRepository.deleteById(id);
        customerRepository.flush();
    }

    @Override
    public void updateCustomer(Customer customer) {
        customerRepository.save(customer);
    }
}

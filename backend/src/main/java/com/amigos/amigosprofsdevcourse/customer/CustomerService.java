package com.amigos.amigosprofsdevcourse.customer;

import com.amigos.amigosprofsdevcourse.exception.ResourceExist;
import com.amigos.amigosprofsdevcourse.exception.ResourceNotFound;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {
    private final CustomerDao customerDao;

    public CustomerService(@Qualifier("jdbc") CustomerDao customerDao) {
        this.customerDao = customerDao;
    }

    public List<Customer> getAllCustomers() {
        return customerDao.getAllCustomers();
    }

    public Customer getCustomerById(Integer customerId) {
        return customerDao
                .getCustomerById(customerId)
                .orElseThrow(() -> new ResourceNotFound("Customer not found with id - " + customerId));
    }

    public void createCustomer(CustomerRegistrationRequest customerRegistrationRequest) {
        if (customerDao.getCustomerByEmail(customerRegistrationRequest.getEmail()).isPresent()) {
            throw new ResourceExist("Customer with email - %s has been exists".formatted(customerRegistrationRequest.getEmail()));
        }

        customerDao.insertCustomer(Customer.builder()
                        .name(customerRegistrationRequest.getName())
                        .email(customerRegistrationRequest.getEmail())
                        .age(customerRegistrationRequest.getAge())
                        .country(customerRegistrationRequest.getCountry())
                        .build()
        );
    }

    public void removeCustomer(Integer customerId) {
        if (customerDao.getCustomerById(customerId).isEmpty()) {
            throw new ResourceNotFound("Customer with id - %s has been exists".formatted(customerId));
        }

        customerDao.removeCustomer(customerId);
    }

    public void updateCustomer(Integer customerId, CustomerUpdateRequest customerUpdateRequest) {
        Customer customer = customerDao.getCustomerById(customerId).orElseThrow(
                () -> new ResourceNotFound("Customer not found with id - " + customerId)
        );

        if (customerUpdateRequest.getEmail() != null &&
                !customerUpdateRequest.getEmail().isEmpty() &&
                !customer.getEmail().equals(customerUpdateRequest.getEmail())) {
            customer.setEmail(customerUpdateRequest.getEmail());
        }

        if (customerUpdateRequest.getName() != null &&
                !customerUpdateRequest.getName().isEmpty() &&
                !customer.getName().equals(customerUpdateRequest.getName())) {
            customer.setName(customerUpdateRequest.getName());
        }

        if (customerUpdateRequest.getAge() != null &&
                customerUpdateRequest.getAge() > 0 &&
                !customer.getAge().equals(customerUpdateRequest.getAge())) {
            customer.setAge(customerUpdateRequest.getAge());
        }
        System.out.println(customerUpdateRequest);
    }
}

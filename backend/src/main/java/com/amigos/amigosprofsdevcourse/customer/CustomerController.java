package com.amigos.amigosprofsdevcourse.customer;

import jakarta.websocket.server.PathParam;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/customers")
@AllArgsConstructor
public class CustomerController {
    private final CustomerService customerService;



    @GetMapping
    public List<Customer> getCustomers() {
        return customerService.getAllCustomers();
    }

    @GetMapping("{customerId}")
    public Customer getCustomer(@PathVariable Integer customerId) {
        System.out.println(111);
        return customerService.getCustomerById(customerId);
    }

    @PostMapping
    public void registerCustomer(
            @RequestBody CustomerRegistrationRequest customerRegistrationRequest
    ) {
        customerService.createCustomer(customerRegistrationRequest);
    }

    @DeleteMapping("{customerId}")
    public void deleteCustomer(
            @PathVariable Integer customerId
    ) {
        customerService.removeCustomer(customerId);
    }

    @PutMapping("{customerId}")
    public void updateCustomer(
            @PathVariable Integer customerId,
            @RequestBody CustomerUpdateRequest customerUpdateRequest
    ) {
        customerService.updateCustomer(customerId, customerUpdateRequest);
    }
}

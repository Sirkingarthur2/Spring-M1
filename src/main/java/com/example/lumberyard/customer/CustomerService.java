package com.example.lumberyard.customer;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {
    private final CustomerRepository customerRepository;

    @Autowired
    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public List<Customer> getCustomers() {
        return customerRepository.findAll();
    }

    public Customer getCustomerById(Long customerId) {
        return customerRepository.findById(customerId)
                .orElseThrow(() -> new IllegalStateException("Customer with id " + customerId + " does not exist"));
    }

    public void deleteCustomer(Long customerId) {
        boolean exists = customerRepository.existsById(customerId);
        if (!exists) {
            throw new IllegalStateException("Customer with id " + customerId + " does not exist");
        }
        customerRepository.deleteById(customerId);
    }
    public void addNewCustomer(Customer customer) {
        Optional<Customer> customerOptional = customerRepository.findCustomerByEmail(customer.getEmail());
        if (customerOptional.isPresent()) {
            throw new IllegalStateException("Email taken");
        }
        customerRepository.save(customer);
    }

    @Transactional
    public void updateCustomer(Long customerId, String name, String email, Integer memberDurationInMonths) {
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new IllegalStateException("Customer with id " + customerId + " does not exist"));

        if (name != null && !name.isEmpty() && !customer.getName().equals(name)) {
            customer.setName(name);
        }

        if (email != null && email.length() > 0 && !customer.getEmail().equals(email)) {
            Optional<Customer> customerOptional = customerRepository.findCustomerByEmail(email);
            if (customerOptional.isPresent()) {
                throw new IllegalStateException("Email taken");
            }
            customer.setEmail(email);
        }

        if (memberDurationInMonths != null && !customer.getMemberDurationInMonths().equals(memberDurationInMonths)) {
            customer.setMemberDurationInMonths(memberDurationInMonths);
        }
    }
}


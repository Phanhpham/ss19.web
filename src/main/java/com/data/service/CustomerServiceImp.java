package com.data.service;

import com.data.model.Customer;
import com.data.repository.CustomerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service

public class CustomerServiceImp implements CustomerService{
    @Autowired
    private CustomerRepo customerRepo;
    @Override
    public List<Customer> getAll() {
        return customerRepo.getAll();
    }

    @Override
    public boolean addCustomer(Customer customer) {
        return customerRepo.addCustomer(customer);
    }
}

package com.miguel.apirest.service;

import com.miguel.apirest.model.dto.CustomerDto;
import com.miguel.apirest.model.entity.Customer;

import java.util.List;

public interface ICustomerService {
    List<Customer> listAllCustomer();
    Customer save(CustomerDto customer);
    Customer findById(Integer id);
    void delete(Customer customer);
    boolean existsById(Integer id);
}

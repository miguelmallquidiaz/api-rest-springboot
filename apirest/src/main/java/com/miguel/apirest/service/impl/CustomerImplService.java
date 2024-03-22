package com.miguel.apirest.service.impl;

import com.miguel.apirest.model.dao.CustomerDao;
import com.miguel.apirest.model.dto.CustomerDto;
import com.miguel.apirest.model.entity.Customer;
import com.miguel.apirest.service.ICustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CustomerImplService implements ICustomerService {
    @Autowired
    private CustomerDao customerDao;

    @Override
    public List<Customer> listAllCustomer() {
        return (List) customerDao.findAll();
    }

    @Transactional
    @Override
    public Customer save(CustomerDto customerDto) {
        Customer customer = Customer.builder()
                .idCustomer(customerDto.getIdCustomer())
                .firstName(customerDto.getFirstName())
                .lastName(customerDto.getLastName())
                .email(customerDto.getEmail())
                .registrationDate(customerDto.getRegistrationDate())
                .build();
        return customerDao.save(customer);
    }
    @Transactional(readOnly = true)
    @Override
    public Customer findById(Integer id) {
        return customerDao.findById(id).orElse(null);
    }
    @Transactional
    @Override
    public void delete(Customer customer) {
        customerDao.delete(customer);
    }

    @Override
    public boolean existsById(Integer id) {
        return customerDao.existsById(id);
    }
}

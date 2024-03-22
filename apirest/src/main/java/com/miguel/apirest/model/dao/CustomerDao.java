package com.miguel.apirest.model.dao;

import com.miguel.apirest.model.entity.Customer;
import org.springframework.data.repository.CrudRepository;

public interface CustomerDao extends CrudRepository<Customer, Integer> {
}

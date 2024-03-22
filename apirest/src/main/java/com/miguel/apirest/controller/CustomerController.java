package com.miguel.apirest.controller;

import com.miguel.apirest.model.dto.CustomerDto;
import com.miguel.apirest.model.entity.Customer;
import com.miguel.apirest.model.payload.MensajeResponse;
import com.miguel.apirest.service.ICustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class CustomerController {
    @Autowired
    private ICustomerService customerService;


    @GetMapping("customers")
    //@ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> showAllCustomers(){
        List<Customer> getList = customerService.listAllCustomer();
        if(getList == null){
            return new ResponseEntity<>(
                    MensajeResponse.builder()
                            .mensaje("No logs 😪")
                            .object(null)
                            .build()
                    , HttpStatus.OK);
        }
        return new ResponseEntity<>(
                MensajeResponse.builder()
                        .mensaje("")
                        .object(getList)
                        .build()
                , HttpStatus.OK);
    }

    @PostMapping("customer")
    public ResponseEntity<?> create(@RequestBody CustomerDto customerDto) {
        Customer customerSave = null;
        try {
            customerSave = customerService.save(customerDto);
            return new ResponseEntity<>(MensajeResponse.builder()
                    .mensaje("Saved Successfully 😎")
                    .object(customerDto.builder()
                            .idCustomer(customerSave.getIdCustomer())
                            .firstName(customerSave.getFirstName())
                            .lastName(customerSave.getLastName())
                            .email(customerSave.getEmail())
                            .registrationDate(customerSave.getRegistrationDate())
                            .build())
                    .build()
                    , HttpStatus.CREATED);
        } catch (DataAccessException exDt) {
            return new ResponseEntity<>(
                    MensajeResponse.builder()
                            .mensaje(exDt.getMessage())
                            .object(null)
                            .build()
                    , HttpStatus.METHOD_NOT_ALLOWED);
        }
    }

    @PutMapping("customer/{id}")
    //@ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> update(@RequestBody CustomerDto customerDto, @PathVariable Integer id){
        Customer customerUpdate = null;
        try{
            if(customerService.existsById(id)) {
                customerDto.setIdCustomer(id);
                customerUpdate = customerService.save(customerDto);
                return new ResponseEntity<>( MensajeResponse.builder()
                        .mensaje("Saved Successfully 😎")
                        .object(CustomerDto.builder()
                                .idCustomer(customerUpdate.getIdCustomer())
                                .firstName(customerUpdate.getFirstName())
                                .lastName(customerUpdate.getLastName())
                                .email(customerUpdate.getEmail())
                                .registrationDate(customerUpdate.getRegistrationDate())
                                .build())
                        .build()
                        ,HttpStatus.CREATED);
            }else{
                return new ResponseEntity<>(
                        MensajeResponse.builder()
                                .mensaje("record not found 😪")
                                .object(null)
                                .build()
                        , HttpStatus.NOT_FOUND);
            }
        }catch (DataAccessException exDt){
            return new ResponseEntity<>(
                    MensajeResponse.builder()
                            .mensaje(exDt.getMessage())
                            .object(null)
                            .build()
                    , HttpStatus.METHOD_NOT_ALLOWED);
        }
    }
    @DeleteMapping("customer/{id}")
    //@ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<?> delete(@PathVariable Integer id){
        try{
            Customer customerDelete = customerService.findById(id);
            customerService.delete(customerDelete);
            return new ResponseEntity<>(customerDelete, HttpStatus.NO_CONTENT);
        }catch (DataAccessException exDt){
            return new ResponseEntity<>(
                    MensajeResponse.builder()
                        .mensaje(exDt.getMessage())
                        .object(null)
                        .build()
                    , HttpStatus.METHOD_NOT_ALLOWED);
        }
    }
    @GetMapping("customer/{id}")
    //@ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> showById(@PathVariable Integer id){
        Customer customer = customerService.findById(id);
        if(customer == null){
            return new ResponseEntity<>(
                    MensajeResponse.builder()
                            .mensaje("record not found 😪")
                            .object(null)
                            .build()
                    , HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(
                MensajeResponse.builder()
                        .mensaje("")
                        .object(CustomerDto.builder()
                                .idCustomer(customer.getIdCustomer())
                                .firstName(customer.getFirstName())
                                .lastName(customer.getLastName())
                                .email(customer.getEmail())
                                .registrationDate(customer.getRegistrationDate())
                                .build())
                        .build()
                , HttpStatus.OK);
    }
}

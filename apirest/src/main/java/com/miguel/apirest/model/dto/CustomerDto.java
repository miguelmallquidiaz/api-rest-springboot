package com.miguel.apirest.model.dto;

import lombok.*;

import java.io.Serializable;
import java.util.Date;

@Data
@ToString
@Builder
public class CustomerDto implements Serializable {
    private Integer idCustomer;
    private String firstName;
    private String lastName;
    private String email;
    private Date registrationDate;
}

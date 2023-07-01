package com.github.alecmus.reactivemongo.mappers;

import com.github.alecmus.reactivemongo.domain.Customer;
import com.github.alecmus.reactivemongo.model.CustomerDTO;
import org.mapstruct.Mapper;

@Mapper
public interface CustomerMapper {

    CustomerDTO customerToCustomerDto(Customer customer);

    Customer customerDtoToCustomer(CustomerDTO customerDTO);
}

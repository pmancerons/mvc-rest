package curso.springframework.mvcrest.services;

import curso.springframework.mvcrest.NotFoundException;
import curso.springframework.mvcrest.api.v1.model.CustomerDTO;
import curso.springframework.mvcrest.domain.Customer;

import java.util.List;

public interface CustomerService {

    List<CustomerDTO> getAllCustomers();

    CustomerDTO getCustomerById(Long id) throws NotFoundException;

    CustomerDTO createNewCustomer(CustomerDTO customerDTO);
}

package curso.springframework.mvcrest.services;

import curso.springframework.mvcrest.exceptions.NotFoundException;
import curso.springframework.mvcrest.api.v1.model.CustomerDTO;

import java.util.List;

public interface CustomerService {

    List<CustomerDTO> getAllCustomers();

    CustomerDTO getCustomerById(Long id) throws NotFoundException;

    CustomerDTO createNewCustomer(CustomerDTO customerDTO);

    CustomerDTO updateCustomer(Long id, CustomerDTO customerDTO);

    void deleteById(Long id);
}

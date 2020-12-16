package curso.springframework.mvcrest.services;

import curso.springframework.mvcrest.NotFoundException;
import curso.springframework.mvcrest.api.v1.mapper.CustomerMapper;
import curso.springframework.mvcrest.api.v1.model.CustomerDTO;
import curso.springframework.mvcrest.domain.Customer;
import curso.springframework.mvcrest.repositories.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CustomerServiceImpl implements CustomerService {

    public static final String API_URL = "/api/v1/customers/";

    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;

    public CustomerServiceImpl(CustomerRepository customerRepository, CustomerMapper customerMapper) {
        this.customerRepository = customerRepository;
        this.customerMapper = customerMapper;
    }

    @Override
    public List<CustomerDTO> getAllCustomers() {
        return customerRepository.findAll()
                .stream()
                .map(customer -> {
                    CustomerDTO customerDTO = customerMapper.customerToCustomerDTO(customer);
                    customerDTO.setUrl(API_URL + customer.getId());
                    return customerDTO;
                })
                .collect(Collectors.toList());
    }

    @Override
    public CustomerDTO getCustomerById(Long id) throws NotFoundException {
        Optional<Customer> optionalCustomer = customerRepository.findById(id);

        if(!optionalCustomer.isPresent()){
            throw new NotFoundException();
        }

        return customerMapper.customerToCustomerDTO(optionalCustomer.get());
    }

    @Override
    public CustomerDTO createNewCustomer(CustomerDTO customerDTO) {
        Customer customerToSave = customerMapper.customerDTOtoCustomer(customerDTO);

        Customer customerSaved = customerRepository.save(customerToSave);

        CustomerDTO customerDTOSaved = customerMapper.customerToCustomerDTO(customerSaved);
        customerDTOSaved.setUrl(API_URL + customerSaved.getId());

        return customerDTOSaved;
    }
}

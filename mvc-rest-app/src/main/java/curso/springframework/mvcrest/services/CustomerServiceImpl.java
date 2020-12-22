package curso.springframework.mvcrest.services;

import curso.springframework.model.CustomerDTO;
import curso.springframework.mvcrest.controller.v1.CustomerController;
import curso.springframework.mvcrest.exceptions.NotFoundException;
import curso.springframework.mvcrest.repositories.CustomerRepository;
import curso.springframework.mvcrest.api.v1.mapper.CustomerMapper;
import curso.springframework.mvcrest.domain.Customer;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CustomerServiceImpl implements CustomerService {


    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;

    public CustomerServiceImpl(CustomerRepository customerRepository, CustomerMapper customerMapper) {
        this.customerRepository = customerRepository;
        this.customerMapper = customerMapper;
    }

    private String getCustomerURL(Long id){
        return CustomerController.CUSTOMER_URL + "/" + id;
    }

    @Override
    public List<CustomerDTO> getAllCustomers() {
        return customerRepository.findAll()
                .stream()
                .map(customer -> {
                    CustomerDTO customerDTO = customerMapper.customerToCustomerDTO(customer);
                    customerDTO.setCustomerUrl(getCustomerURL(customer.getId()));
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

        Customer customer = optionalCustomer.get();
        CustomerDTO customerDTO = customerMapper.customerToCustomerDTO(customer);
        customerDTO.setCustomerUrl(getCustomerURL(customer.getId()));

        return customerDTO;
    }

    @Override
    public CustomerDTO createNewCustomer(CustomerDTO customerDTO) {
        Customer customerToSave = customerMapper.customerDTOtoCustomer(customerDTO);

        return saveCustomerAndReturnDTO(customerToSave);
    }

    private CustomerDTO saveCustomerAndReturnDTO(Customer customerToSave) {
        Customer customerSaved = customerRepository.save(customerToSave);

        CustomerDTO customerDTOSaved = customerMapper.customerToCustomerDTO(customerSaved);
        customerDTOSaved.setCustomerUrl(getCustomerURL(customerSaved.getId()));

        return customerDTOSaved;
    }

    @Override
    public CustomerDTO updateCustomer(Long id, CustomerDTO customerDTO) {
        Customer customerToSave = customerMapper.customerDTOtoCustomer(customerDTO);
        customerToSave.setId(id);

        return saveCustomerAndReturnDTO(customerToSave);
    }

    @Override
    public void deleteById(Long id) {
        customerRepository.deleteById(id);
    }
}

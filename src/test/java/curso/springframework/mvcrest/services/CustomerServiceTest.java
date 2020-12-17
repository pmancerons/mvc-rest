package curso.springframework.mvcrest.services;

import curso.springframework.mvcrest.api.v1.mapper.CustomerMapper;
import curso.springframework.mvcrest.api.v1.model.CustomerDTO;
import curso.springframework.mvcrest.controller.v1.CustomerController;
import curso.springframework.mvcrest.domain.Customer;
import curso.springframework.mvcrest.repositories.CustomerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class CustomerServiceTest {

    public static final long ID = 3l;
    public static final String FIRST_NAME = "test";
    public static final String LAST_NAME = "customer";

    CustomerService customerService;

    @Mock
    CustomerRepository customerRepository;

    @BeforeEach
    void setUp() {
        customerService = new CustomerServiceImpl(customerRepository, CustomerMapper.INSTANCE);
    }

    @Test
    void getAllCustomers() {
        List<Customer> customers = Arrays.asList(new Customer(), new Customer(), new Customer());

        Mockito.when(customerRepository.findAll()).thenReturn(customers);

        List<CustomerDTO> customersDTO = customerService.getAllCustomers();

        assertEquals(3, customersDTO.size());
    }

    @Test
    void getCustomerById() {
        Customer cus = new Customer();
        cus.setId(ID);
        cus.setFirstName(FIRST_NAME);
        cus.setLastName(LAST_NAME);

        Mockito.when(customerRepository.findById(ArgumentMatchers.anyLong())).thenReturn(Optional.of(cus));

        CustomerDTO customerDTO = customerService.getCustomerById(ID);

        assertEquals(FIRST_NAME,customerDTO.getFirstName());
        assertEquals(LAST_NAME,customerDTO.getLastName());
    }

    @Test
    void createNewCustomer(){
        Customer cus = new Customer();
        cus.setId(ID);
        cus.setFirstName(FIRST_NAME);
        cus.setLastName(LAST_NAME);

        Mockito.when(customerRepository.save(ArgumentMatchers.any())).thenReturn(cus);

        CustomerDTO customerDTO = customerService.createNewCustomer(new CustomerDTO());

        assertEquals(FIRST_NAME,customerDTO.getFirstName());
        assertEquals(LAST_NAME,customerDTO.getLastName());
        assertEquals(CustomerController.CUSTOMER_URL + "/" + ID,customerDTO.getUrl());
    }


    @Test
    void updateCustomer(){
        Customer cus = new Customer();
        cus.setId(ID);
        cus.setFirstName(FIRST_NAME);
        cus.setLastName(LAST_NAME);

        Mockito.when(customerRepository.save(ArgumentMatchers.any())).thenReturn(cus);

        CustomerDTO customerDTO = customerService.updateCustomer(ID,new CustomerDTO());

        assertEquals(FIRST_NAME,customerDTO.getFirstName());
        assertEquals(LAST_NAME,customerDTO.getLastName());
        assertEquals(CustomerController.CUSTOMER_URL + "/" + ID,customerDTO.getUrl());
    }

    @Test
    void deleteCustomer(){
        Long id  = 1l;

        customerService.deleteById(id);

        Mockito.verify(customerRepository, Mockito.times(1)).deleteById(ArgumentMatchers.anyLong());
    }
}
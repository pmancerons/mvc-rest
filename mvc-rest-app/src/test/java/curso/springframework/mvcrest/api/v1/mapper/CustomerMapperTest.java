package curso.springframework.mvcrest.api.v1.mapper;

import curso.springframework.mvcrest.api.v1.mapper.CustomerMapper;
import curso.springframework.mvcrest.api.v1.model.CustomerDTO;
import curso.springframework.mvcrest.domain.Customer;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CustomerMapperTest {

    public static final long ID = 2l;
    public static final String FIRST_NAME = "test";
    public static final String LAST_NAME = "customer";
    CustomerMapper customerMapper = CustomerMapper.INSTANCE;

    @Test
    void customerToCustomerDTO() {
        Customer cus = new Customer();
        cus.setId(ID);
        cus.setFirstName(FIRST_NAME);
        cus.setLastName(LAST_NAME);

        CustomerDTO customerDTO = customerMapper.customerToCustomerDTO(cus);

        assertNotNull(customerDTO);
        assertEquals(FIRST_NAME, customerDTO.getFirstName());
        assertEquals(LAST_NAME, customerDTO.getLastName());
    }


    @Test
    void customerDTOToCustomer() {
        CustomerDTO cus = new CustomerDTO();
        cus.setFirstName(FIRST_NAME);
        cus.setLastName(LAST_NAME);

        Customer customer = customerMapper.customerDTOtoCustomer(cus);

        assertNotNull(customer);
        assertEquals(FIRST_NAME, customer.getFirstName());
        assertEquals(LAST_NAME, customer.getLastName());
    }
}
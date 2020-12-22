package curso.springframework.mvcrest.controller.v1;

import curso.springframework.model.CustomerDTO;
import curso.springframework.mvcrest.controller.RestResponseEntityExceptionHandler;
import curso.springframework.mvcrest.exceptions.NotFoundException;
import curso.springframework.mvcrest.services.CustomerService;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;

@ExtendWith(MockitoExtension.class)
class CustomerControllerTest extends AbstractRestControllerTest {

    public static final String FIRST_NAME = "first";
    public static final String LAST_NAME = "customer";
    @Mock
    CustomerService customerService;

    @InjectMocks
    CustomerController customerController;

    MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(customerController)
                .setControllerAdvice(new RestResponseEntityExceptionHandler())
                .build();
    }

    @Test
    public void testGetAllCustomers() throws Exception{
        CustomerDTO customerDTO1 = new CustomerDTO();
        customerDTO1.setFirstName(FIRST_NAME);
        customerDTO1.setLastName(LAST_NAME);

        CustomerDTO customerDTO2 = new CustomerDTO();
        customerDTO2.setFirstName("second");
        customerDTO2.setLastName(LAST_NAME);

        List<CustomerDTO> customersDTO = Arrays.asList(customerDTO1,customerDTO2);

        Mockito.when(customerService.getAllCustomers()).thenReturn(customersDTO);

        mockMvc.perform(MockMvcRequestBuilders.get(CustomerController.CUSTOMER_URL )
                    .contentType(MediaType.APPLICATION_JSON)
                    .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.customers", Matchers.hasSize(2)));

        mockMvc.perform(MockMvcRequestBuilders.get(CustomerController.CUSTOMER_URL )
                    .contentType(MediaType.APPLICATION_JSON)
                    .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.customers", Matchers.hasSize(2)));
    }

    @Test
    public void testGetCustomerById() throws Exception{
        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setFirstName( FIRST_NAME);
        customerDTO.setLastName(LAST_NAME);

        Mockito.when(customerService.getCustomerById(ArgumentMatchers.anyLong())).thenReturn(customerDTO);

        mockMvc.perform(MockMvcRequestBuilders.get(CustomerController.CUSTOMER_URL + "/1")
                    .contentType(MediaType.APPLICATION_JSON)
                    .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.firstName", Matchers.equalTo(FIRST_NAME)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.lastName", Matchers.equalTo(LAST_NAME)));

    }

    @Test
    public void testCreateNewCustomer() throws Exception{
        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setFirstName( FIRST_NAME);
        customerDTO.setLastName(LAST_NAME);

        CustomerDTO returnDTO = new CustomerDTO();
        returnDTO.setFirstName(customerDTO.getFirstName());
        returnDTO.setLastName(customerDTO.getLastName());
        returnDTO.setCustomerUrl("/api/v1/customers/1");

        Mockito.when(customerService.createNewCustomer(ArgumentMatchers.any())).thenReturn(returnDTO);

        mockMvc.perform(MockMvcRequestBuilders.post(CustomerController.CUSTOMER_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .accept(MediaType.APPLICATION_JSON)
                    .content(asJsonString(customerDTO)))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.firstName", Matchers.equalTo(FIRST_NAME)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.lastName", Matchers.equalTo(LAST_NAME)));

    }


    @Test
    public void testUpdateCustomer() throws Exception{
        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setFirstName( FIRST_NAME);
        customerDTO.setLastName(LAST_NAME);

        CustomerDTO returnDTO = new CustomerDTO();
        returnDTO.setFirstName(customerDTO.getFirstName());
        returnDTO.setLastName(customerDTO.getLastName());
        returnDTO.setCustomerUrl("/api/v1/customers/1");

        Mockito.when(customerService.updateCustomer(ArgumentMatchers.anyLong(),ArgumentMatchers.any())).thenReturn(returnDTO);

        mockMvc.perform(MockMvcRequestBuilders.put(CustomerController.CUSTOMER_URL + "/1")
                    .contentType(MediaType.APPLICATION_JSON)
                    .accept(MediaType.APPLICATION_JSON)
                    .content(asJsonString(customerDTO)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.firstName", Matchers.equalTo(FIRST_NAME)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.lastName", Matchers.equalTo(LAST_NAME)));

    }

    @Test
    void deleteCustomerById() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.delete(CustomerController.CUSTOMER_URL + "/1"))
                .andExpect(MockMvcResultMatchers.status().isOk());

        Mockito.verify(customerService,Mockito.times(1)).deleteById(ArgumentMatchers.anyLong());

    }

    @Test
    public void testNotFoundException() throws Exception {

        Mockito.when(customerService.getCustomerById(ArgumentMatchers.anyLong())).thenThrow(NotFoundException.class);

        mockMvc.perform(MockMvcRequestBuilders.get(CustomerController.CUSTOMER_URL + "/222")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }
}
package curso.springframework.mvcrest.controller.v1;

import curso.springframework.mvcrest.api.v1.model.CustomerDTO;
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

import static org.junit.jupiter.api.Assertions.*;

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
        mockMvc = MockMvcBuilders.standaloneSetup(customerController).build();
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

        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/customers")
                    .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.customers", Matchers.hasSize(2)));

        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/customers/")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.customers", Matchers.hasSize(2)));
    }

    @Test
    public void testGetCustomerById() throws Exception{
        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setFirstName( FIRST_NAME);
        customerDTO.setLastName(LAST_NAME);

        Mockito.when(customerService.getCustomerById(ArgumentMatchers.anyLong())).thenReturn(customerDTO);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/customers/1")
                .contentType(MediaType.APPLICATION_JSON))
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
        returnDTO.setUrl("/api/v1/customers/1");

        Mockito.when(customerService.createNewCustomer(ArgumentMatchers.any())).thenReturn(returnDTO);

        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/customers/")
                     .contentType(MediaType.APPLICATION_JSON)
                    .content(asJsonString(customerDTO)))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.firstName", Matchers.equalTo(FIRST_NAME)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.lastName", Matchers.equalTo(LAST_NAME)));

    }
}
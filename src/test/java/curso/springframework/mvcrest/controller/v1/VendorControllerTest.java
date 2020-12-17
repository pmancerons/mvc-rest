package curso.springframework.mvcrest.controller.v1;

import curso.springframework.mvcrest.api.v1.model.VendorDTO;
import curso.springframework.mvcrest.controller.RestResponseEntityExceptionHandler;
import curso.springframework.mvcrest.exceptions.NotFoundException;
import curso.springframework.mvcrest.services.VendorService;
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
class VendorControllerTest extends AbstractRestControllerTest {
    
    public static final String NAME = "test vendor";
    
    @Mock
    VendorService vendorService;
    
    @InjectMocks
    VendorController vendorController;
    
    MockMvc mockMvc;
    
    @BeforeEach
    void setUp(){
        mockMvc = MockMvcBuilders.standaloneSetup(vendorController)
                .setControllerAdvice(new RestResponseEntityExceptionHandler())
                .build();
    }


    @Test
    public void testGetAllVendors() throws Exception{
        VendorDTO VendorDTO1 = new VendorDTO();
        VendorDTO1.setName(NAME);

        VendorDTO VendorDTO2 = new VendorDTO();
        VendorDTO2.setName("second");

        List<VendorDTO> VendorsDTO = Arrays.asList(VendorDTO1,VendorDTO2);

        Mockito.when(vendorService.getAllVendors()).thenReturn(VendorsDTO);

        mockMvc.perform(MockMvcRequestBuilders.get(VendorController.VENDOR_URL )
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.vendors", Matchers.hasSize(2)));

        mockMvc.perform(MockMvcRequestBuilders.get(VendorController.VENDOR_URL )
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.vendors", Matchers.hasSize(2)));
    }

    @Test
    public void testGetVendorById() throws Exception{
        VendorDTO VendorDTO = new VendorDTO();
        VendorDTO.setName( NAME);

        Mockito.when(vendorService.getVendorById(ArgumentMatchers.anyLong())).thenReturn(VendorDTO);

        mockMvc.perform(MockMvcRequestBuilders.get(VendorController.VENDOR_URL + "/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name", Matchers.equalTo(NAME)));

    }

    @Test
    public void testCreateNewVendor() throws Exception{
        VendorDTO vendorDTO = new VendorDTO();
        vendorDTO.setName( NAME);

        VendorDTO returnDTO = new VendorDTO();
        returnDTO.setName(vendorDTO.getName());
        returnDTO.setUrl("/api/v1/Vendors/1");

        Mockito.when(vendorService.createNewVendor(ArgumentMatchers.any())).thenReturn(returnDTO);

        mockMvc.perform(MockMvcRequestBuilders.post(VendorController.VENDOR_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(vendorDTO)))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name", Matchers.equalTo(NAME)));

    }


    @Test
    public void testUpdateVendor() throws Exception{
        VendorDTO VendorDTO = new VendorDTO();
        VendorDTO.setName( NAME);

        VendorDTO returnDTO = new VendorDTO();
        returnDTO.setName(VendorDTO.getName());
        returnDTO.setUrl("/api/v1/Vendors/1");

        Mockito.when(vendorService.updateVendor(ArgumentMatchers.anyLong(),ArgumentMatchers.any())).thenReturn(returnDTO);

        mockMvc.perform(MockMvcRequestBuilders.put(VendorController.VENDOR_URL + "/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(VendorDTO)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name", Matchers.equalTo(NAME)));

    }

    @Test
    void deleteVendorById() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.delete(VendorController.VENDOR_URL + "/1"))
                .andExpect(MockMvcResultMatchers.status().isOk());

        Mockito.verify(vendorService,Mockito.times(1)).deleteVendorById(ArgumentMatchers.anyLong());

    }

    @Test
    public void testNotFoundException() throws Exception {

        Mockito.when(vendorService.getVendorById(ArgumentMatchers.anyLong())).thenThrow(NotFoundException.class);

        mockMvc.perform(MockMvcRequestBuilders.get(VendorController.VENDOR_URL + "/222")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }

}
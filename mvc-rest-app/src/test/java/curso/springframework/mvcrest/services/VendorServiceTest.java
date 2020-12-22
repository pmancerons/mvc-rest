package curso.springframework.mvcrest.services;

import curso.springframework.mvcrest.api.v1.mapper.VendorMapper;
import curso.springframework.mvcrest.api.v1.model.VendorDTO;
import curso.springframework.mvcrest.controller.v1.VendorController;
import curso.springframework.mvcrest.domain.Vendor;
import curso.springframework.mvcrest.repositories.VendorRepository;
import curso.springframework.mvcrest.services.VendorService;
import curso.springframework.mvcrest.services.VendorServiceImpl;
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
class VendorServiceTest {

    public static final long ID = 3l;
    public static final String NAME = "vendor test";

    VendorService vendorService;

    @Mock
    VendorRepository vendorRepository;

    @BeforeEach
    void setUp() {
        vendorService = new VendorServiceImpl(vendorRepository, VendorMapper.INSTANCE);
    }

    @Test
    void getAllCustomers() {
        List<Vendor> vendors = Arrays.asList(new Vendor(), new Vendor(), new Vendor());

        Mockito.when(vendorRepository.findAll()).thenReturn(vendors);

        List<VendorDTO> vendorDTOS = vendorService.getAllVendors();

        assertEquals(3, vendorDTOS.size());
    }

    @Test
    void getCustomerById() {
        Vendor vendor = new Vendor();
        vendor.setId(ID);
        vendor.setName(NAME);

        Mockito.when(vendorRepository.findById(ArgumentMatchers.anyLong())).thenReturn(Optional.of(vendor));

        VendorDTO vendorDTO = vendorService.getVendorById(ID);

        assertEquals(NAME,vendorDTO.getName());
    }

    @Test
    void createNewCustomer(){
        Vendor vendor = new Vendor();
        vendor.setId(ID);
        vendor.setName(NAME);

        Mockito.when(vendorRepository.save(ArgumentMatchers.any())).thenReturn(vendor);

        VendorDTO vendorDTO = vendorService.createNewVendor(new VendorDTO());

        assertEquals(NAME,vendorDTO.getName());
        assertEquals(VendorController.VENDOR_URL + "/" + ID,vendorDTO.getUrl());
    }


    @Test
    void updateCustomer(){
        Vendor vendor = new Vendor();
        vendor.setId(ID);
        vendor.setName(NAME);

        Mockito.when(vendorRepository.save(ArgumentMatchers.any())).thenReturn(vendor);

        VendorDTO vendorDTO = vendorService.updateVendor(ID,new VendorDTO());

        assertEquals(NAME,vendorDTO.getName());
        assertEquals(VendorController.VENDOR_URL  + "/" +ID,vendorDTO.getUrl());
    }

    @Test
    void deleteCustomer(){
        Long id  = 1l;

        vendorService.deleteVendorById(id);

        Mockito.verify(vendorRepository, Mockito.times(1)).deleteById(ArgumentMatchers.anyLong());
    }
}
package curso.springframework.mvcrest.api.v1.mapper;

import curso.springframework.mvcrest.api.v1.model.CustomerDTO;
import curso.springframework.mvcrest.api.v1.model.VendorDTO;
import curso.springframework.mvcrest.domain.Customer;
import curso.springframework.mvcrest.domain.Vendor;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class VendorMapperTest {

    public static final long ID = 2l;
    public static final String NAME = "test vendor";

    VendorMapper vendorMapper = VendorMapper.INSTANCE;

    @Test
    void customerToCustomerDTO() {
        Vendor vendor = new Vendor();
        vendor.setId(ID);
        vendor.setName(NAME);

        VendorDTO vendorDTO = vendorMapper.vendorToVendorDTO(vendor);

        assertNotNull(vendor);
        assertEquals(NAME, vendorDTO.getName());
    }


    @Test
    void customerDTOToCustomer() {
        VendorDTO vendorDTO = new VendorDTO();
        vendorDTO.setName(NAME);

        Vendor vendor = vendorMapper.vendorDTOToVendor(vendorDTO);

        assertNotNull(vendor);
        assertEquals(NAME, vendor.getName());
    }
}
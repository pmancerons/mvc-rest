package curso.springframework.mvcrest.services;

import curso.springframework.mvcrest.exceptions.NotFoundException;
import curso.springframework.mvcrest.api.v1.model.VendorDTO;

import java.util.List;

public interface VendorService {

    List<VendorDTO> getAllVendors();

    VendorDTO getVendorById(Long id) throws NotFoundException;

    VendorDTO createNewVendor(VendorDTO VendorDTO);

    VendorDTO updateVendor(Long id, VendorDTO vendorDTO);

    void deleteVendorById(Long id);
}

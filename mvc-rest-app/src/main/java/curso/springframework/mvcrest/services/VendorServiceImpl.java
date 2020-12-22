package curso.springframework.mvcrest.services;

import curso.springframework.mvcrest.controller.v1.VendorController;
import curso.springframework.mvcrest.domain.Vendor;
import curso.springframework.mvcrest.exceptions.NotFoundException;
import curso.springframework.mvcrest.repositories.VendorRepository;
import curso.springframework.mvcrest.api.v1.mapper.VendorMapper;
import curso.springframework.mvcrest.api.v1.model.VendorDTO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class VendorServiceImpl implements VendorService {

    private final VendorRepository vendorRepository;
    private final VendorMapper vendorMapper;

    public VendorServiceImpl(VendorRepository vendorRepository, VendorMapper vendorMapper) {
        this.vendorRepository = vendorRepository;
        this.vendorMapper = vendorMapper;
    }

    private String getVendorURL(Long id){
        return VendorController.VENDOR_URL + "/" + id;
    }

    @Override
    public List<VendorDTO> getAllVendors() {
        return vendorRepository.findAll()
                .stream()
                .map(vendor -> {
                    VendorDTO vendorDTO = vendorMapper.vendorToVendorDTO(vendor);
                    vendorDTO.setUrl(getVendorURL(vendor.getId()));
                    return vendorDTO;
                })
                .collect(Collectors.toList());
    }

    @Override
    public VendorDTO getVendorById(Long id) throws NotFoundException {
        Optional<Vendor> optionalVendor = vendorRepository.findById(id);

        if(!optionalVendor.isPresent()){
            throw new NotFoundException();
        }

        Vendor savedVendor = optionalVendor.get();
        VendorDTO vendorDTO = vendorMapper.vendorToVendorDTO(savedVendor);
        vendorDTO.setUrl(getVendorURL(savedVendor.getId()));

        return vendorDTO;
    }

    private VendorDTO saveVendorAndReturnDTO(Vendor vendorToSave){
        Vendor vendorSaved = vendorRepository.save(vendorToSave);

        VendorDTO vendorDTOSaved = vendorMapper.vendorToVendorDTO(vendorSaved);
        vendorDTOSaved.setUrl(getVendorURL(vendorSaved.getId()));

        return vendorDTOSaved;
    }

    @Override
    public VendorDTO createNewVendor(VendorDTO vendorDTO) {
        Vendor vendor = vendorMapper.vendorDTOToVendor(vendorDTO);

        return saveVendorAndReturnDTO(vendor);
    }


    @Override
    public VendorDTO updateVendor(Long id, VendorDTO vendorDTO) {
        Vendor vendorToSave = vendorMapper.vendorDTOToVendor(vendorDTO);
        vendorToSave.setId(id);

        return saveVendorAndReturnDTO(vendorToSave);
    }

    @Override
    public void deleteVendorById(Long id) {
        vendorRepository.deleteById(id);
    }
}

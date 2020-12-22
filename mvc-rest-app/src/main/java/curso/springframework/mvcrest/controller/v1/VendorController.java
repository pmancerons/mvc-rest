package curso.springframework.mvcrest.controller.v1;


import curso.springframework.mvcrest.api.v1.model.VendorDTO;
import curso.springframework.mvcrest.api.v1.model.VendorListDTO;
import curso.springframework.mvcrest.services.VendorService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(VendorController.VENDOR_URL)
public class VendorController {

    public static final String VENDOR_URL = "/api/v1/vendors";

    private final VendorService vendorService;

    public VendorController(VendorService vendorService) {
        this.vendorService = vendorService;
    }

    @GetMapping({"","/"})
    @ResponseStatus(HttpStatus.OK)
    public VendorListDTO getAllCustomers(){

        return new VendorListDTO(vendorService.getAllVendors());
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public VendorDTO getCustomerById(@PathVariable Long id){
        return vendorService.getVendorById(id);
    }

    @PostMapping({"","/"})
    @ResponseStatus(HttpStatus.CREATED)
    public VendorDTO createNewCustomer(@RequestBody VendorDTO vendorDTO){
        return vendorService.createNewVendor(vendorDTO);
    }


    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public VendorDTO updateCustomer(@RequestBody VendorDTO vendorDTO,@PathVariable Long id){
        return vendorService.updateVendor(id,vendorDTO);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteCustomerById(@PathVariable Long id){
        vendorService.deleteVendorById(id);
    }

}

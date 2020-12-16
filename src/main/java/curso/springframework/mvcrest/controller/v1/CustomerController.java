package curso.springframework.mvcrest.controller.v1;

import curso.springframework.mvcrest.api.v1.model.CustomerDTO;
import curso.springframework.mvcrest.api.v1.model.CustomerListDTO;
import curso.springframework.mvcrest.services.CustomerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/customers")
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping({"","/"})
    public ResponseEntity<CustomerListDTO> getAllCustomers(){

        return new ResponseEntity<CustomerListDTO>(
                new CustomerListDTO(customerService.getAllCustomers())
                , HttpStatus.OK
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomerDTO> getCustomerById(@PathVariable Long id){
        return new ResponseEntity<CustomerDTO>(
                customerService.getCustomerById(id)
                ,HttpStatus.OK
        );
    }

    @PostMapping({"","/"})
    public ResponseEntity<CustomerDTO> createNewCustomer(@RequestBody CustomerDTO newCustomer){
        return new ResponseEntity<CustomerDTO>(
                customerService.createNewCustomer(newCustomer)
                , HttpStatus.CREATED
        );
    }

}

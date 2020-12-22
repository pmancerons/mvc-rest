package curso.springframework.mvcrest.controller.v1;

import ch.qos.logback.core.util.ContentTypeUtil;
import com.sun.xml.internal.ws.client.sei.ResponseBuilder;
import curso.springframework.mvcrest.api.v1.model.CustomerDTO;
import curso.springframework.mvcrest.api.v1.model.CustomerListDTO;
import curso.springframework.mvcrest.services.CustomerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.w3c.dom.html.HTMLTableSectionElement;


@Api(tags = "Customer Documentation and examples")
@RestController
@RequestMapping(CustomerController.CUSTOMER_URL)
public class CustomerController {

    public static final String CUSTOMER_URL = "/api/v1/customers";

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @ApiOperation(value = "This operation returns back all the customers"
            ,notes = "these are the notes for this operation"
            ,produces = "Application/json")
    @GetMapping({""})
    @ResponseStatus(HttpStatus.OK)
    public CustomerListDTO getAllCustomers(){

        return new CustomerListDTO(customerService.getAllCustomers());
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public CustomerDTO getCustomerById(@PathVariable Long id){
        return customerService.getCustomerById(id);
    }

    @PostMapping({""})
    @ResponseStatus(HttpStatus.CREATED)
    public CustomerDTO createNewCustomer(@RequestBody CustomerDTO newCustomer){
        return customerService.createNewCustomer(newCustomer);
    }


    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public CustomerDTO updateCustomer(@RequestBody CustomerDTO customerToUpdate,@PathVariable Long id){
        return customerService.updateCustomer(id,customerToUpdate);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteCustomerById(@PathVariable Long id){
        customerService.deleteById(id);
    }
}

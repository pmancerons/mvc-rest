package curso.springframework.mvcrest.bootstrap;

import curso.springframework.mvcrest.domain.Category;
import curso.springframework.mvcrest.domain.Customer;
import curso.springframework.mvcrest.domain.Vendor;
import curso.springframework.mvcrest.repositories.CategoryRepository;
import curso.springframework.mvcrest.repositories.CustomerRepository;
import curso.springframework.mvcrest.repositories.VendorRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class Bootstrap implements CommandLineRunner {

    private final CategoryRepository categoryRepository;
    private final CustomerRepository customerRepository;
    private final VendorRepository vendorRepository;

    public Bootstrap(CategoryRepository categoryRepository, CustomerRepository customerRepository, VendorRepository vendorRepository) {
        this.categoryRepository = categoryRepository;
        this.customerRepository = customerRepository;
        this.vendorRepository = vendorRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        loadCategories();
        loadCustomers();
        loadVendors();
    }

    private void loadCategories() {
        Category fruits = new Category();
        fruits.setName("Fruits");

        Category dried = new Category();
        dried.setName("Dried");

        Category fresh = new Category();
        fresh.setName("Fresh");

        Category exotic = new Category();
        exotic.setName("Exotic");

        Category nuts = new Category();
        nuts.setName("Nuts");

        categoryRepository.save(fruits);
        categoryRepository.save(dried);
        categoryRepository.save(fresh);
        categoryRepository.save(exotic);
        categoryRepository.save(nuts);

        log.info("Categories loaded ");
    }


    private void loadCustomers() {
        Customer cus1 = new Customer();
        cus1.setFirstName("Leider");
        cus1.setLastName("Preciado");

        Customer cus2 = new Customer();
        cus2.setFirstName("Omar");
        cus2.setLastName("Perez");

        Customer cus3 = new Customer();
        cus3.setFirstName("Leandro");
        cus3.setLastName("Castellanos");

        Customer cus4 = new Customer();
        cus4.setFirstName("Luis");
        cus4.setLastName("Seijas");

        customerRepository.save(cus1);
        customerRepository.save(cus2);
        customerRepository.save(cus3);
        customerRepository.save(cus4);

        log.info("Customers loaded ");
    }

    private void loadVendors(){
        Vendor vendor1 = new Vendor();
        vendor1.setName("Fruits and Co");

        Vendor vendor2 = new Vendor();
        vendor2.setName("The best Fruits");

        Vendor vendor3 = new Vendor();
        vendor3.setName("Yumi fruits");

        vendorRepository.save(vendor1);
        vendorRepository.save(vendor2);
        vendorRepository.save(vendor3);
    }
}

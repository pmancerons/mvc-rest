package curso.springframework.mvcrest.bootstrap;

import curso.springframework.mvcrest.domain.Category;
import curso.springframework.mvcrest.domain.Customer;
import curso.springframework.mvcrest.repositories.CategoryRepository;
import curso.springframework.mvcrest.repositories.CustomerRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class Bootstrap implements CommandLineRunner {

    private final CategoryRepository categoryRepository;
    private final CustomerRepository customerRepository;

    public Bootstrap(CategoryRepository categoryRepository, CustomerRepository customerRepository) {
        this.categoryRepository = categoryRepository;
        this.customerRepository = customerRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        loadCategories();
        loadCustomers();
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
}

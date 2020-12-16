package curso.springframework.mvcrest.repositories;

import curso.springframework.mvcrest.domain.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer,Long> {
}

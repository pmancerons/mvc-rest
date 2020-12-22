package curso.springframework.mvcrest.api.v1.mapper;

import curso.springframework.model.CustomerDTO;
import curso.springframework.mvcrest.domain.Customer;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface CustomerMapper {
    CustomerMapper INSTANCE = Mappers.getMapper(CustomerMapper.class);

    CustomerDTO customerToCustomerDTO(Customer source);

    Customer customerDTOtoCustomer(CustomerDTO source);
}

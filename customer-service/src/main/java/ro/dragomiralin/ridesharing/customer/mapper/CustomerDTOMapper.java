package ro.dragomiralin.ridesharing.customer.mapper;

import org.mapstruct.Mapper;
import ro.dragomiralin.ridesharing.customer.domain.Customer;
import ro.dragomiralin.ridesharing.customer.dto.CustomerDTO;

@Mapper(componentModel = "spring")
public interface CustomerDTOMapper {

    CustomerDTO toDTO(Customer customer);

    Customer toCustomer(CustomerDTO customerDTO);


}

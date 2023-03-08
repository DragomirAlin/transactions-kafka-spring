package ro.dragomiralin.ridesharing.customer.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ro.dragomiralin.ridesharing.customer.domain.Customer;
import ro.dragomiralin.ridesharing.customer.dto.CustomerDTO;
import ro.dragomiralin.ridesharing.customer.mapper.CustomerDTOMapper;
import ro.dragomiralin.ridesharing.customer.service.CustomerService;

@RestController
@RequestMapping("/customers")
@RequiredArgsConstructor
public class CustomerController {
    private final CustomerDTOMapper mapper;
    private final CustomerService customerService;

    @PostMapping
    public ResponseEntity<CustomerDTO> createCustomer(@RequestBody CustomerDTO customerDTO) {
        Customer customer = mapper.toCustomer(customerDTO);
        return ResponseEntity.ok(mapper.toDTO(customerService.create(customer)));
    }
}

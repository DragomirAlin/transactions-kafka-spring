package ro.dragomiralin.ridesharing.customer.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ro.dragomiralin.ridesharing.customer.domain.Customer;
import ro.dragomiralin.ridesharing.customer.repository.CustomerRepository;

@Service
@RequiredArgsConstructor
public class CustomerService {
    private final CustomerRepository customerRepository;

    public Customer create(Customer customer) {
        return customerRepository.save(customer);
    }

    public Customer get(long id){
        return customerRepository.findById(id).orElseThrow();
    }
}

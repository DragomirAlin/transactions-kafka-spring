package ro.dragomiralin.ridesharing.customer.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
public class CustomerDTO {
    private long id;
    private String sub;
    private String name;
    private String email;
    private String phone;
    private String address;
    private String country;
}

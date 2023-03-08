package ro.dragomiralin.ridesharing.driver.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
public class Driver {
    private long id;
    private String name;
    private String carNumber;
    private DriverStatus status;
}

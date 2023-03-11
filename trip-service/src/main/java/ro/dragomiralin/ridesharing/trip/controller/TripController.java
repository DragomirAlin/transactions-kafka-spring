package ro.dragomiralin.ridesharing.trip.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ro.dragomiralin.ridesharing.trip.dto.TripRequest;
import ro.dragomiralin.ridesharing.trip.service.TripService;

@RestController
@RequestMapping("/trips")
@RequiredArgsConstructor
public class TripController {
    private final TripService tripService;

    @PostMapping("/request")
    public ResponseEntity requestTrip(@RequestBody TripRequest tripRequest) {
        tripService.requestTrip(tripRequest);
        return ResponseEntity.accepted().build();
    }
}

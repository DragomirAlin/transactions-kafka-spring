package ro.dragomiralin.ridesharing.trip.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ro.dragomiralin.ridesharing.trip.domain.Trip;
import ro.dragomiralin.ridesharing.trip.domain.TripStatus;
import ro.dragomiralin.ridesharing.trip.dto.TripDTO;
import ro.dragomiralin.ridesharing.trip.dto.TripRequest;
import ro.dragomiralin.ridesharing.trip.mapper.DriverRequestEventMapper;
import ro.dragomiralin.ridesharing.trip.mapper.TripDTOMapper;
import ro.dragomiralin.ridesharing.trip.producer.TripProducer;
import ro.dragomiralin.ridesharing.trip.repository.TripRepository;

@Service
@RequiredArgsConstructor
public class TripService {
    private final TripProducer tripProducer;
    private final TripDTOMapper tripDTOMapper;
    private final DriverRequestEventMapper driverRequestEventMapper;
    private final TripRepository tripRepository;

    public void requestTrip(TripRequest tripRequest) {
        Trip trip = Trip.builder()
                .customerId(tripRequest.customerId())
                .startLocation(tripRequest.startLocation())
                .endLocation(tripRequest.endLocation())
                .status(TripStatus.DRIVER_REQUESTED)
                .build();

        trip = tripRepository.save(trip);

        TripDTO tripDTO = tripDTOMapper.toDTO(trip);
        tripProducer.sendDriverRequest(driverRequestEventMapper.toDriverRequestEvent(tripDTO));
    }


}

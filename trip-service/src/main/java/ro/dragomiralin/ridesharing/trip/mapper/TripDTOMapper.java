package ro.dragomiralin.ridesharing.trip.mapper;

import org.mapstruct.Mapper;
import ro.dragomiralin.ridesharing.trip.domain.Trip;
import ro.dragomiralin.ridesharing.trip.dto.TripDTO;

@Mapper(componentModel = "spring")
public interface TripDTOMapper {

    TripDTO toDTO(Trip trip);

    Trip toTrip(TripDTO tripDTO);
}

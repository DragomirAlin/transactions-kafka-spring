package ro.dragomiralin.ridesharing.trip.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ro.dragomiralin.ridesharing.trip.dto.DriverRequestEvent;
import ro.dragomiralin.ridesharing.trip.dto.TripDTO;

@Mapper(componentModel = "spring")
public interface DriverRequestEventMapper {

    @Mapping(target = "eventId", expression = "java(java.util.UUID.randomUUID())")
    @Mapping(target = "tripDTO", source = "tripDTO")
    DriverRequestEvent toDriverRequestEvent(TripDTO tripDTO);

}

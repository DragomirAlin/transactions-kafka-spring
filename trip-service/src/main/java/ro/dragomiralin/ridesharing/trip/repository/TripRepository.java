package ro.dragomiralin.ridesharing.trip.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ro.dragomiralin.ridesharing.trip.domain.Trip;

public interface TripRepository extends JpaRepository<Trip, Long> {
}

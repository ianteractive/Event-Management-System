package com.management.event_system.repository;

import com.management.event_system.model.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface EventRepository extends JpaRepository<Event, Integer> {

    List<Event> findByEventDate(LocalDate date);

    List<Event> findByLocation(String location);
}

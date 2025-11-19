package com.management.event_system.service;

import com.management.event_system.exceptions.EventNotFoundException;
import com.management.event_system.model.Event;
import com.management.event_system.repository.EventRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class EventService {

    private final EventRepository eventRepository;

    public EventService(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    public List<Event> getAllEvent() {
        return eventRepository.findAll();
    }

    public void addEvent(Event event) {
        eventRepository.save(event);
    }

    public Event getEventById(Integer id) {
        return eventRepository.findById(id)
                .orElseThrow(() -> new IllegalStateException("No Event with id " + id + " found!"));
    }


    public void updateEventById(Integer id, Event updatedEvent) {
        Event updated = eventRepository.findById(id)
                .orElseThrow(() -> new EventNotFoundException("No Event ID " + id + " found"));

        if(updatedEvent.getName() != null){
            updated.setName(updatedEvent.getName());
        }
        if(updatedEvent.getLocation() != null){
            updated.setLocation(updatedEvent.getLocation());
        }
        if(updatedEvent.getDate() != null){
            updated.setDate(updatedEvent.getDate());
        }
        if(updatedEvent.getOrganizer() != null){
            updated.setOrganizer(updatedEvent.getOrganizer());
        }
        if(updatedEvent.getTicketPrice() != null){
            updated.setTicketPrice(updatedEvent.getTicketPrice());
        }

        eventRepository.save(updated);
    }

    public void deleteEvent(Integer id) {
        if(!eventRepository.existsById(id)){
            throw new EventNotFoundException("Event with id " + id + " not found!");
        }
        eventRepository.deleteById(id);
    }

    public List<Event> searchEventByDate(LocalDate date) {
        return eventRepository.findByEventDate(date);
    }

    public List<Event> searchEventByLocation(String location) {
        return eventRepository.findByLocation(location);
    }
}

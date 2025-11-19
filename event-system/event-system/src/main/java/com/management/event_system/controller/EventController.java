package com.management.event_system.controller;

import com.management.event_system.model.Event;
import com.management.event_system.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.Local;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController("/event")
public class EventController {

    private final EventService eventService;

    public EventController(EventService eventService) {
        this.eventService = eventService;
    }

    //Add new events
    @PostMapping
    public ResponseEntity<String> add(@RequestBody Event event){
        eventService.addEvent(event);
        return ResponseEntity.ok("Event added successfully!");
    }

    //View all events
    @GetMapping
    public List<Event> getAllEvent(){
        return eventService.getAllEvent();
    }

    //Get event by ID
    @GetMapping("/{id}")
    public Event getEventById(Integer id){
        return eventService.getEventById(id);
    }

    //Update event details
    @PutMapping("/{id}")
    public ResponseEntity<String> update(@PathVariable Integer id,
                                         @RequestBody Event event){

        eventService.updateEventById(id, event);
        return ResponseEntity.ok("Event updated successfully!");
    }

    //Delete event
    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Integer id){
        eventService.deleteEvent(id);
        return ResponseEntity.ok("Event deleted successfully");
    }

    //Search events by location or date
    @GetMapping("/search/date")
    public List<Event> searchEventByDate(@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date){
        return eventService.searchEventByDate(date);
    }

    @GetMapping("/search/location")
    public List<Event> searchEventByLocation(@RequestParam String location){
        return eventService.searchEventByLocation(location);
    }



}

package gov.iti.evento.controllers;

import gov.iti.evento.entites.Event;
import gov.iti.evento.repositories.CategoryRepository;
import gov.iti.evento.services.EventService;
import gov.iti.evento.services.dtos.EventDto;
import gov.iti.evento.services.util.exceptions.MessageException;
import jakarta.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.bind.DefaultValue;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import java.util.List;

@RestController
public class EventController {
    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    EventService eventService;

    public void m() {
    }

    @GetMapping("/events")
    public Page<EventDto> getEvents(@RequestParam("page") @DefaultValue("1") int page, @RequestParam("size") @DefaultValue("6") int size) {
        System.out.println("rtyrtry : ");
        return eventService.getEvents(page, size);
    }

    @GetMapping("/events/category/{categoryType}")
    public List<EventDto> getEventByCategoryType(@PathVariable("categoryType") String categoryType) throws MessageException {
        System.out.println("categoryType : " + categoryType);
        return eventService.getEventByCategoryType(categoryType);
    }

    @GetMapping("/events/{speaker}")
    public List<EventDto> getEventBySpeaker(@PathVariable("speaker") String speaker) throws MessageException {
        System.out.println("speaker : " + speaker);
        return eventService.getEventBySpeaker(speaker);
    }

    @GetMapping("/events/status/{status}")
    public List<EventDto> getEventByStatus(@PathVariable("status") String status) throws MessageException {
        System.out.println("speaker : " + status);
        return eventService.getEventByStatus(status);
    }

}

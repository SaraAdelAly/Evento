package gov.iti.evento.controllers;


import gov.iti.evento.entites.Event;
import gov.iti.evento.entites.EventSpeaker;
import gov.iti.evento.entites.EventSpeakerId;
import gov.iti.evento.entites.Speaker;
import gov.iti.evento.repositories.SpeakerRepository;
import gov.iti.evento.services.SpeakerServices;
import gov.iti.evento.services.dtos.SpeakersDto;
import gov.iti.evento.services.util.converters.ImageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import gov.iti.evento.entites.Speaker;
import gov.iti.evento.services.SpeakerService;
import gov.iti.evento.services.dtos.SpeakerMostSpeakingDto;

@RestController
@RequestMapping("/speakers")
public class SpeakerController {

    SpeakerServices speakerServices;
    ImageConverter imageConverter;
    SpeakerRepository speakerRepository;
    EventController eventController;


    @Autowired
    public SpeakerController(SpeakerServices speakerServices, SpeakerRepository speakerRepository, EventController eventController) {
        this.speakerServices = speakerServices;
        this.speakerRepository = speakerRepository;

        this.eventController = eventController;
    }


    @PostMapping("/{name}")
    public Speaker addSpeaker(@PathVariable String name) {
        Speaker speaker = new Speaker();
        speaker.setName(name);
        speaker.setAge(30);
        speaker.setDescription("He is highly skilled, with many combat arts.");
        speaker.setJobTitle("developer");
        speaker.setImage("../../../../resources/speaker-two.jpg");
        Speaker savedSpeaker = speakerRepository.save(speaker);
        return speaker;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteSpeaker(@PathVariable Integer id) {
        Optional<Speaker> speakerOptional = speakerRepository.findById(id);
        if (speakerOptional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        Speaker speaker = speakerOptional.get();
        speakerRepository.delete(speaker);
        return ResponseEntity.ok("Speaker deleted successfully");
    }

    public SpeakersDto getSpeakerById(@PathVariable Integer id) throws Exception{
        return speakerServices.findById(id);
    }


    @PostMapping("/eventSpeaker/{eventId}/{speakerId}")
    public EventSpeaker addSpeakerToEvent(@PathVariable("eventId") Integer eventId, @PathVariable("speakerId") Integer speakerId) {
        Optional<Event> eventOptional = eventController.getEventById(eventId);
        Optional<Speaker> speakerOptional = speakerServices.getSpeakerById(speakerId);
        EventSpeaker eventSpeaker = new EventSpeaker();
        EventSpeakerId eventSpeakerId = new EventSpeakerId();

        if (eventOptional.isPresent() && speakerOptional.isPresent()) {
            Event event = eventOptional.get();
            Speaker speaker = speakerOptional.get();
            eventSpeakerId.setEventId(eventId);
            eventSpeakerId.setSpeakerId(speakerId);
            eventSpeaker.setId(eventSpeakerId);
            System.out.println(event.getCategory()+speaker.getName());
            eventSpeaker.setEvent(event);
            eventSpeaker.setSpeaker(speaker);

        }
        return speakerServices.saveEventSpeaker(eventSpeaker);

    }
     @Autowired
    private SpeakerService speakerService;

    @GetMapping("/most-speaking")
     public ResponseEntity<List<SpeakerMostSpeakingDto>> getMostSpeakingSpeakers() {
        List<SpeakerMostSpeakingDto> speakers = speakerService.getMostSpeakingSpeakersList();
        return ResponseEntity.ok(speakers);
    }
}

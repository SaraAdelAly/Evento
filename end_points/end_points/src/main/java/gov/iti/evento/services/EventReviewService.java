package gov.iti.evento.services;

import gov.iti.evento.entites.EventReview;
import gov.iti.evento.repositories.EventReviewRepository;
import gov.iti.evento.services.dtos.eventReviews.EventReviewDto;
import gov.iti.evento.services.mappers.eventReviews.EventReviewMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.swing.text.html.Option;

@Service
public class EventReviewService {
    EventReviewMapper eventReviewMapper;
    EventReviewRepository eventReviewRepository;
    @Autowired
    public EventReviewService(EventReviewMapper eventReviewMapper, EventReviewRepository eventReviewRepository){
        this.eventReviewMapper=eventReviewMapper;
        this.eventReviewRepository=eventReviewRepository;
    }
    public List<EventReviewDto> getReviewByEventId(int id) {
        System.out.println(id);
        List <EventReviewDto>eventReviewsDto=new ArrayList<>();
        List <EventReview>eventReviews=eventReviewRepository.getEventReviewByEvent_Id(id);
        for (EventReview e :eventReviews
             ) {
            eventReviewsDto.add(eventReviewMapper.toDto(e));
        }
        return eventReviewsDto;
    }
    public Boolean hasUserReviewedEvent(Integer userId,Integer eventId){
        Optional<EventReview> isExist = eventReviewRepository.findByEventIdAndUserId(userId, eventId);
        return isExist.isPresent();
        
    }
    public EventReview createReview(EventReview eventReview){
        return eventReviewRepository.save(eventReview);
    }
}

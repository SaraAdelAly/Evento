import { Component, signal, ChangeDetectorRef, OnInit } from '@angular/core';
import { CalendarOptions, DateSelectArg, EventClickArg, EventApi, EventInput } from '@fullcalendar/core';
import interactionPlugin from '@fullcalendar/interaction';
import dayGridPlugin from '@fullcalendar/daygrid';
import timeGridPlugin from '@fullcalendar/timegrid';
import listPlugin from '@fullcalendar/list';
// import { INITIAL_EVENTS, createEventId } from 'src/app/models/event/event-utils';
import { ApiService } from 'src/app/services/api.service';
import moment from 'moment';
import { Router } from '@angular/router';
// import { EventByDate, getEvents } from 'src/app/models/event/eventByDate';

@Component({
  selector: 'app-calendar',
  templateUrl: './calendar.component.html',
  styleUrls: ['./calendar.component.css']
})
export class CalendarComponent implements OnInit {

  dateFormat= new Date(1687644000000);
  
  Events = [
    {  date: '2023-06-23',title: "Event 1"},
    { title: "Event 2", date: '2023-06-23' },
    { title: "Event 3", date: new Date(1687361400000)},
  ];

  eventInput: EventInput[] = [];
  
  date: any;
  test:any[];

  eventApi:EventApi[];

  calendarVisible = signal(true);

  currentEvents = signal<EventApi[]>([]);

  calendarOptions:CalendarOptions;
  title:any;
  id:any;





  constructor(
    private changeDetector: ChangeDetectorRef,
    private apiService: ApiService,
    private router: Router
  ) { }

  ngOnInit(): void {
    this.calendarOptions = {
      plugins: [
        interactionPlugin,
        dayGridPlugin,
        timeGridPlugin,
        listPlugin,
      ],
      headerToolbar: {
        left: 'prev,next today',
        center: 'title',
        right: 'dayGridMonth,timeGridWeek,timeGridDay,listWeek'
      },
      initialView: 'dayGridMonth',
      weekends: true,
      editable: true,
      selectable: true,
      selectMirror: true,
      dayMaxEvents: true,
      events: [] ,
    eventClick: this.handleEventClick.bind(this),
    };

      this.apiService.get('all-events').subscribe({
        next: (response: { title: string; date: string }[]) => {
          console.log('Response:', response);
      
          const convertedEvents = response.map(event => ({
            title: event.title,
            date: new Date(event.date).toISOString().split('T')[0]
          }));
          console.log('Converted Events:', convertedEvents);
          this.calendarOptions.events = convertedEvents;

          this.eventApi=this.test;
          this.currentEvents.set(this.eventApi);
          this.changeDetector.detectChanges();
          console.log(this.eventApi);
        },
        error: (error) => { }
      });

    };


    handleEventClick(clickInfo: EventClickArg) {
      // if (confirm(`Are you sure you want to delete the event '${clickInfo.event.title}'`)) {
      //   clickInfo.event.remove();
      // }
      this.title=clickInfo.event.title;
      console.log("tesssssssssst");
      console.log(this.title);
      this.id=clickInfo.event.id;

      // this.apiService.get(`calender/event/${this.title}`).subscribe({
      //   next: (response: any) => {
      //     this.id=response;
      //     console.log("tesssssssssst2");
      // console.log(this.id);
          
      //   },
      //   error: (error) => { }
      // });

      
      this.router.navigate([`/event/${this.id}`]);

    }



}


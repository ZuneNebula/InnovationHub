package com.niit.rabbitMQ;

import java.util.Date;

public class EventDTO {

    private String event;
    private EventDataDTO eventDataDTO;
    private Date event_ts;

    public EventDTO() {
    }

    public EventDTO(String event, EventDataDTO eventDataDTO, Date event_ts) {
        this.event = event;
        this.eventDataDTO = eventDataDTO;
        this.event_ts = event_ts;
    }

    public String getEvent() {
        return event;
    }

    public void setEvent(String event) {
        this.event = event;
    }

    public EventDataDTO getEventDataDTO() {
        return eventDataDTO;
    }

    public void setEventDataDTO(EventDataDTO eventDataDTO) {
        this.eventDataDTO = eventDataDTO;
    }

    public Date getEvent_ts() {
        return event_ts;
    }

    public void setEvent_ts(Date event_ts) {
        this.event_ts = event_ts;
    }
}

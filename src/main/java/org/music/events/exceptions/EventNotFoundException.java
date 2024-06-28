package org.music.events.exceptions;

public class EventNotFoundException extends RuntimeException {
    public EventNotFoundException(String eventId) {
        super("Cannot find event:" + eventId);
    }
}
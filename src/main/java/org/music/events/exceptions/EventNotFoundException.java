package org.music.events.exceptions;

public class EventNotFoundException extends RuntimeException {
    public EventNotFoundException(String eventName) {
        super("Cannot find event: " + eventName);
    }
}

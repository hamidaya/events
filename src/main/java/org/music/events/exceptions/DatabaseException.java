package org.music.events.exceptions;

public class DatabaseException extends RuntimeException {
    public DatabaseException(String message) {
        super("database server not available" + message);
    }
}
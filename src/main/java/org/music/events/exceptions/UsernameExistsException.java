package org.music.events.exceptions;

public class UsernameExistsException extends RuntimeException {
    public UsernameExistsException(String username) {
        super("Username already exists: " + username);
    }
}
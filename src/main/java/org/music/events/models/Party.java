package org.music.events.models;

import jakarta.persistence.*;

import java.time.LocalDate;

@Table(name="partys")
@Entity
public class Party extends Event {

    String djName;
    String dressCode;

    public Party() {

    }

        public String getDjName() {
        return djName;
    }

    public void setDjName(String djName) {
        this.djName = djName;
    }

    public String getDressCode() {
        return dressCode;
    }

    public void setDressCode(String dressCode) {
        this.dressCode = dressCode;
    }
}
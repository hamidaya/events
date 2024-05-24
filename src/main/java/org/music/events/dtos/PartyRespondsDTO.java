package org.music.events.dtos;

public class PartyRespondsDTO extends EventRespondsDTO {

    private String djName;
    private String dressCode;

        public String getDjName() {
        return djName;
    }

    public PartyRespondsDTO() {

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


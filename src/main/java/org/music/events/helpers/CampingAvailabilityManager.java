package org.music.events.helpers;

import java.util.UUID;

public class CampingAvailabilityManager {

        String campingSpotId = UUID.randomUUID().toString();

        private long eventId;

        public CampingAvailabilityManager(long campingSpotId, long eventId) {
                this.campingSpotId = String.valueOf(campingSpotId);
                this.eventId = eventId;
        }

        public String getCampingSpotId() {
                return campingSpotId;
        }

        public void setCampingSpotId(long campingSpotId) {
                this.campingSpotId = String.valueOf(campingSpotId);
        }

        public long getEventId() {
                return eventId;
        }

        public void setEventId(long eventId) {
                this.eventId = eventId;
        }
}

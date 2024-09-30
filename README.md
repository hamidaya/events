# Events festivals en party's ticketing API.
## Projectoverzicht
Dit project is een **Events Ticketing System**, waarmee gebruikers tickets kunnen kopen en valideren voor evenementen. Het systeem biedt de mogelijkheid om evenementen aan te maken, tickets te beheren en deze te valideren op basis van unieke QR-codes. Dit project is gebouwd als onderdeel van de studieopdracht voor de cursus.
Deze Readme file is een lichte versie van de installatie handleiding die apart wordt mee geleverd. 
## Features
- **Gebruikersbeheer**: Authenticatie en autorisatie voor gebruikers met verschillende rollen.
- **Evenementbeheer**: Beheer van evenementen met details zoals naam, locatie en datum.
- **Ticketbeheer**: Aanmaken, ophalen en valideren van tickets op basis van unieke QR-codes.
- **Ticketvalidatie**: Valideren van tickets op status (bijv. 'VALID' of 'USED').
- **API-gebaseerd**: Alle interacties met het systeem worden afgehandeld via RESTful API's.
## Technologieën
- **Java 17**
- **Spring Boot 3.x**
- **Spring Data JPA**: Voor databasebeheer en opslag.
- **Spring Security**: Voor authenticatie en autorisatie.
- **H2 Database**: Voor eenvoudige, in-memory opslag (kan vervangen worden door een echte database zoals MySQL of PostgreSQL).
- **Postman**: Voor het testen van de API's.
## Installatie en Setup
1. **Clone de repository**:
  ```bash
  git clone https://github.com/hamidaya/events.git
  ```
2. **Navigeer naar de projectmap**:
  ```bash
  cd events
  ```
3. **Installeer de vereiste dependencies**:
   Dit project gebruikt Maven. Om de vereiste dependencies te installeren, voer je het volgende commando uit:
  ```bash
  mvn clean install
  ```
4. **Start de applicatie**:
   Je kunt de applicatie starten met Maven of je IDE:
  ```bash
  mvn spring-boot:run
  ```

## API Endpoints
### Authenticatie
- **Login**:
  - `POST /auth/login`
  - Body: `{ "username": "user", "password": "pass" }`
  - Returns: JWT-token
### Evenementen
- **Alle evenementen ophalen**:
  - `GET /events`
  - Returns: Lijst van alle evenementen
## Postman
Een Postman-collectie is beschikbaar voor het testen van de API. Deze bevat alle benodigde verzoeken om de endpoints te testen. Zorg ervoor dat je een JWT-token krijgt na het inloggen en deze gebruikt voor alle beveiligde endpoints.
## Gebruik
1. Maak een nieuw evenement aan via api/postman
2. Voeg tickets toe aan het evenement.
3. Valideer de tickets door de juiste parameters (`eventId`, `username`, `ticketId`) via de `/tickets/validate`-endpoint te sturen.
## Mogelijke Uitbreidingen
- **E-mailnotificaties**: Stuur een bevestigingsmail naar de gebruiker na het kopen van een ticket.
- **Frontend**: verbinden met mijn front-end om de API te gebruiken, zodat gebruikers op een intuïtieve manier evenementen kunnen bekijken en tickets kunnen kopen.
- **Externe betalingen**: Integreer een betalingsgateway (zoals Stripe of PayPal) om de ticketaankopen te verwerken.
## Contributie
Voel je vrij om bij te dragen aan dit project door een pull request in te dienen of issues aan te maken in de repository.
## Contact
Voor vragen over dit project, neem contact op via:
- **Naam**: Hamid Ayachi  
- **E-mail**: info@ayachi.com
- **GitHub**: https://github.com/hamidaya/events.git

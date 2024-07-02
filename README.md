This application is a full-stack solution comprising a Spring Boot REST API for managing excursion bookings and a web frontend for users to create, retrieve, and update reservations, ensuring business constraints such as valid booking dates and seat availability.

This was my final project for class at Multiplatform Development Vocational Study.

## Replication Instructions

This application is divided in back end and front end. Each part has its own dependencies and setup. 
To replicate this project locally clone this repository and please refer to the instructions in each part's README file.

### Quick-start with Docker

If you have Docker installed on your system, you can run the whole application at once, and doesn't require a database server to be running locally.

Navigate to the location of docker-compose.yml and run:
```
docker compose up
```
Once the docker containers are up (it takes a moment), you can open in a browser http://localhost:9999 to access the frontend of the application.

The backend API is also exposed, on default port 8080

## Front End
Requires NPM

[Back End README](/pfc-front/README.md)


## Back End
Requires Java and Maven

[Front End README](/pfc-back/README.md)


## Screen shots of the application running
### Index
![Application Index Page](./screen-shots/index.jpg)

### Reservation Creation Process
![Reservation Process 1](./screen-shots/reservation-1.jpg)
![Reservation Process 2](./screen-shots/reservation-2.jpg)
![Reservation Process 3](./screen-shots/reservation-3.jpg)
![Reservation Process 4](./screen-shots/reservation-4.jpg)

### Look up user reservations
![My Reservations Page Form](./screen-shots/my-reservations-form.jpg)
![My Reservations Page List](./screen-shots/my-reservations-list.jpg)
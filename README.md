# Spring Video Platform

Video streaming platform built using Spring Boot and ReactJS.

## Run it locally

### Prerequisites

1. For backend: [Java 17](https://www.oracle.com/java/technologies/downloads/) or later

2. For frontend: [npm](https://docs.npmjs.com/downloading-and-installing-node-js-and-npm) latest version.

3. For database: [MongoDB](https://www.mongodb.com/) server running either locally or hosted on the cloud.

### Database

1. Navigate to `./src/main/resources`.

2. Open `application.properties` file.

3. Add your MongoDB URI Connection String.

### Backend

1. Navigate to root folder.

2. Run `.\gradlew bootRun` to run the Spring Boot server.

3. Note: You do not need gradle installed in your machine for this.


### Frontend

1. Navigate to `./src/main/js` folder.

2. Run `npm install` command to install all dependencies.

3. Run `npm run dev` to start front-end locally

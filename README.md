# Spring Boot + React Learning Management System (LMS)

This application is a full-stack LMS, built with Spring Boot framework for backend services and ReactJS for the frontend. The purpose of this application is to provide a platform for instructors to share their knowledge and monetize them as they wish. Users can access courses based on their interests or courses created by their favorite instructors.

## Table of Content
1. [Technologies Used](#technologies-used)
2. [Installation](#installation)
    - [Prerequisites](#prerequisites)
    - [Database Setup](#database-setup)
    - [Configuration](#configuration)
3. [Usage](#usage)
4. [API Documentation](#api-documentation)
5. [Acknowledgements](#acknowledgements)
6. [Contact Information](#contact-information)

## Technologies Used

## Installation

### Prerequisites

1. [Java 17](https://www.oracle.com/java/technologies/downloads/) or later

2. [npm](https://docs.npmjs.com/downloading-and-installing-node-js-and-npm) latest version.

3. [MySQL](https://dev.mysql.com/downloads/mysql/) server and [MongoDB](https://www.mongodb.com/try/download/community) server. You can connect to your servers on the cloud as well.

### Database Setup

1. [Install MySQL server](https://dev.mysql.com/doc/mysql-installation-excerpt/5.7/en/) and ensure that the server is running on your local computer.
    - Set password for your [root account](https://dev.mysql.com/doc/mysql-installation-excerpt/5.7/en/default-privileges.html) and note down your username and password.
    - You can download GUI for MySQL such as [MySQL Workbench](https://www.mysql.com/products/workbench/) if you are not used to using the terminal.

2. [Install MongoDB server](https://www.mongodb.com/docs/manual/administration/install-community/) and ensure that the server is running on your local computer.
    - You can download GUI for MongoDB such as [MongoDB Compass](https://www.mongodb.com/products/tools/compass) for a friendlier interface.

### Configuration

#### Frontend

1. Create a `.env` in the `/src/main/js` folder.

2. Configure `REACT_APP_BACKEND_BASE_URL=http://localhost:8080`

#### Backend

1. Rename `example.application.properties` to `application.properties` in `/src/main/resources` folder.

2. Update values for `spring.datasource.url`, `spring.datasource.username` and `spring.datasource.password` fields.

3. Update value for `spring.data.mongodb.database` field to the name of your database.

## Usage

Note: Please ensure that your MySQL and MongoDB servers are running.

### Backend

1. Navigate to root folder.

2. Run `.\gradlew bootRun` to run the Spring Boot server. The server will be running at `http://localhost:8080`

3. Note: You do not need gradle installed in your machine for this.

### Frontend

1. Navigate to `./src/main/js` folder.

2. Run `npm install` command to install all dependencies.

3. Run `npm run dev` to start front-end locally.

4. Fire up your favorite browser and access `http://localhost:5173`

## API Documentation

## Acknowledgements

## Contact Information

Email: le.minh.giang.work@gmail.com

LinkedIn: https://www.linkedin.com/in/leminhgiang/
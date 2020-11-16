# TicketViewer

TicketViewer is an application to view list of all the tickets and details of particular ticket associated with an account.
It is a web application with browser based UI.

## Prerequisite
* Jdk 1.8
* Maven 3.3 +

## Built With
* Spring framework 2.2.2.RELEASE
* Maven 3.6.3
* Java 1.8

## Installation

-- Using command prompt

* Unzip the folder
* Build the project using the command 'mvn clean install' 
* Run the application using command 'java -jar target/ticketviewer-0.0.1-SNAPSHOT.jar'
* Open chrome and type url 'localhost:8080'

-- Using eclipse

* Unzip the folder
* Import project as 'Existing maven project'
* Right click -> Run as -> Maven Install
* Right click -> Maven -> Update Project
* Right click -> Rus as -> Java Application -> select TicketViewerApplication.java

## Usage
* Click on 'click here' link on home page to see list of all the tickets.

![Alt text](https://github.com/manasi517/ticket-viewer/blob/master/homePage.JPG?raw=true )

* Page through the list using Next and Previous buttons.

![Alt text](https://github.com/manasi517/ticket-viewer/blob/master/pagination.JPG?raw=true )

* Click on Details button to see the detail for particular ticket.

![Alt text](https://github.com/manasi517/ticket-viewer/blob/master/ticket-details.JPG?raw=true )

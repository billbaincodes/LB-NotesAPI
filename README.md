# LB-NotesAPI
RESTful API meant to power a note taking application, built with Java, Spring and Maven.

### Maven Set Up
If you have Maven installed clone this repo, navigate into the root folder and from your terminal run:

`$ mvn spring-boot:run`

### IDE Set Up
Clone this repo, import into your IDE and run the file:

`LB-NotesAPI/src/main/java/com/example/demo/DemoApplication.java`

## Routes
GET all, with optional search parameter ‘query'

`localhost:8080/api/notes`

`localhost:8080/api/notes?query=milk`

GET one note by ID

`localhost:8080/api/notes/{id}`


POST create new note

`localhost:8080/api/notes`


PUT update note by ID

`localhost:8080/api/notes/{id}`


DEL delete note by ID

`localhost:8080/api/notes/{id}`

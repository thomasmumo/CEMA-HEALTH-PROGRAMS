HEALTH PROGRAMS MANAGEMENT SYSTEM (Spring Boot)

OVERVIEW

This project simulates a basic health information system to allow doctors to:

•	Create health programs (e.g., TB, HIV, Malaria)

•	Register new clients

•	Enroll clients into one or more programs

•	Search and view client profiles

•	Expose client profiles via a RESTful API for external systems

This solution was built using Spring Boot following clean code practices, an API-first approach, and includes basic data security considerations.


TECHNOLOGIES USED

•	Java 

•	Spring Boot 3.x

•	Spring Data JPA (Hibernate)

•	PostgreSQL

•	Maven

•	Postman (for API testing)

•	Docker for deployment


API END POINTS

POST - /users/user-login –both clients and doctors must be authenticated

POST - /users/create-user – For creating client and doctor accounts and assigning  one or more program to the client

GET - /users/all – fetching all client accounts

GET - /users/user/{user-name} – fetching a client account by username

POST - /programs/create-program – adds a program to the system

GET - /programs/all – gets all programs in the system


 ADDITIONAL NOTES
 
•	Clean architecture: Controller → Service → Repository layers.

•	DTOs used for API responses and requests.

•	API-first design with clear endpoint documentation.

•	Commit history reflects incremental and clean development.


 AUTHOR
 
•	Name: Thomas Mumo



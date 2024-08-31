Proof of concept (POC) application in spring-boot and DGS
==========================================================

Simple application with a single entity called user and basic CRUD operations. 

How to run 
----------------

**Method 1: using docker-compose**

1. Download the source code
2. Go to root of the application. You will see the docker-compose.yml file
3. In that folder, open up the terminal and type in docker-composse up
4. After some time, the application will start, and you can go to http://localhost:8080/graphiql
5. On the left side there is a generated documentation

   *What docker-compose and application did "under the hood" using this method:*
   1. Instaniated the application
   2. Booted up mysql instance
   3. Created a volume for mysql instance
   4. Application put some basic data in database for you to play around with using flyway (more about that in following sections)

**Method 2: using gradlew**

1. Download the source file
2. Find application.properties file and there you can set up your database preferences
3. If you are using database other than mysql, it is recommended to disable flyway. You can do that by setting spring.flyway.enabled=false in application.properties
4. Go to root of the application. You will find gradlew file
5. In that folder, open up the terminal and type in ./gradlew bootRun
6. After some time, the application will start, and you can go to http://localhost:8080/graphiql
7. On the left side there is a generated documentation

FAQ
----
Q: When i run docker-compose, i get permission errors. What can i do? 
A: Use "sudo docker-compose up" 

Q: When i run docker-compose, i get "bind address: already in use". What can i do? 
A: There is probably something on port 3306. Please, relese that port before running docker-compose

Q: I want to permanently get rid of application after running with docker-compose. What can i do? 
A: Search on google about "docker compose down" and "docker volume removal" 

Q: I run application using gradlew. Do i need gradle? 
A: No, gradlew should have it's own gradle referenced

 

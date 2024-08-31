Proof of concept (POC) application in spring-boot and DGS
----------------------------------------------------------

Simple application with a single entity called user and basic CRUD operations. 

** How to run **

Method 1: using docker-compose 

1. Download the source code
2. Go to root of the folder. You will see the docker-compose.yml file
3. In that folder, open up the terminal and type in docker-composse up
4. After some time, the application will start, and you can go to http://localhost:8080/graphiql
5. On the left side there is a generated documentation

   *What docker-compose and application did "under the hood" using this method:
   1. Instaniated the application
   2. Booted up mysql instance
   3. Created a volume for mysql instance
   4. Application put some basic data in database for you to play around with using flyway

## Api-football client
***
This is an application that allows you to search for football teams info 
using the https://www.api-football.com/ API
### Screenshot
![Image text](src/main/resources/src/homepage.png)

## Technologies
***
A list of technologies used within the project:
* [jdk](https://www.oracle.com/java/technologies/javase/jdk15-archive-downloads.html): Version 15
* [Spring Boot](https://spring.io/projects/spring-boot): Version 2.6.3
* [Thymeleaf](https://www.thymeleaf.org/): Version 3.0.14
* [Gradle](https://gradle.org/): Version 7.3.3
* [Docker](https://www.docker.com/)
## Installation
Simply clone this repo:
```bash
git clone https://github.com/pizzazombie/api-football.git
```
or use this repo link for cloning in your IDE.

## Usage
Request to dashboard needs and api-football token from your account with a format like ``"XxXxXxXxXxXxXxXxXxXxXxXx"``,  
put it to [application.properties](src/main/resources/application.properties) file.  
But be attentive, the number of requests to api-football is limited by your account plan
### Running 
You can build and run application directly in your IDE or by gradle and jdk through that steps:

```bash
# build project
./gradlew build

# run the jar file
java -jar .\build\libs\api-football-0.0.1-SNAPSHOT.jar
```
This application uses port 8090, so you can find it on http://localhost:8090/

### Running in docker

You can use [docker](https://www.docker.com/) for running application in container, for that:
```bash
# build image
docker build -t api-football .

# run in container
run -p 8090:8090 api-football
```
Go to http://localhost:8090/
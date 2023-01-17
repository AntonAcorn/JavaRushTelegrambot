# SubscribtionTelegramBot

## Review
The main goal of the project was to create an application 
with a monolithic architecture and a database, in which the deployment and management 
would be configured. In this project, the database is fully configured and Flyway has been added. 
The project is wrapped in Docker and runs with a simple bash script.
The commands are implemented using the Command behavioral pattern and immutable Map from the Apache Commons library.
Communication with the REST API is implemented using the Unirest library.

## The main functions of the application:

User can subscribe to a group of posts.
User can view a list of group subscriptions to which the user is subscribed.
User can unsubscribe from a group of posts.
User can set the bot to inactive and not receive notifications.
User can restart receiving notifications.
Admin has the ability to see bot statistics.

The bot is set up based on the documentation of the open API (https://javarush.com/swagger-ui.html#/)

## Deployment
This application is deployed on an Amazon Web Services (AWS).
Just write on telegram @test_JavaAcorn_bot

If you want to use on your local host, try next steps:
Required software:
- terminal for running bash scripts
- docker
- docker-compose
Remember that you need to use your own bot token

to deploy application run bash script:

$ bash start.sh 

### Technology stack
* Java 11
* Spring Boot
* Maven
* Lombok
* Unirest
* SpringScheduler
* Apache commons-lang3
* Postgresql
* Flyway
* Docker

## Endpoints
* /start - start/resume working with the bot
* /stop - pause working with the bot
* /addgroupsub - subscribe to a group of articles
* /deletegroupsub - unsubscribe from a group of articles
* /listgroupsub - list of groups subscribed to
* /help - get help with working with me"






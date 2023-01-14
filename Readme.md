## Deployment
Deployment process as easy as possible:
Required software:
- terminal for running bash scripts
- docker
- docker-compose

to deploy application, switch to needed branch and run bash script:

$ bash start.sh ${bot_username} ${bot_token}

That's all.
, на базе открытого API
# URL shortener
## Review
This is a link-shortening service that transforms lengthy URLs into shorter,
more manageable links. This can be useful for a variety of purposes,
such as making links more shareable on social media, or for tracking clicks
on a specific link. It is a simple and efficient solution that can help to streamline
the process of sharing and managing links.

### Technology stack
* Java 17
* Spring Boot
* Maven
* Lombok
* Unirest
* commons-lang3
* Hibernate validator
* Model mapper
* Google guava
* Apache commons-lang3
* Postgresql
* Log4j

## How to run
* Clone repository
* You should have docker and maven
* Just use start.sh
* To stop application use stop.sh

## Endpoints
POST /create

If placed on port 7070, the request will look like:
**localhost:7070/create**

[Screenshot](https://github.com/AntonAcorn/Resources/blob/main/files/URLShortener/img.png)

GET /{shortUrl}

If placed on port 7070, the request will look like:
**localhost:7070/create**

The link has a certain storage time. If the time is running out,
then you need to update the link, i.e. make a new POST request







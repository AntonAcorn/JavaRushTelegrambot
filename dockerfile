#The JDK container image that we use to build our own container
FROM adoptopenjdk/openjdk11:ubi
#Adding an argument, the path to the JAR of our project
ARG JAR_FILE=target/*.jar
#Environment variables from the application properties
ENV BOT_NAME=test_JavaAcorn_bot
ENV BOT_TOKEN=5578041606:AAHHXNOU4w82W1QhOR7lqfjUVTnqSpV6qvU
#Значения переменных будут другие.
#Те, которые мы передадим в dockerfile, тем не менее, требуют дать значения по умолчанию, поэтому я и ввел какие-то.
ENV BOT_DB_USERNAME=botusername
ENV BOT_DB_PASSWORD=botpassword
#копируем джарник в образ докера
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-Dspring.datasource.password=${BOT_DB_PASSWORD}", "-Dbot.username=${BOT_NAME}", "-Dbot.token=${BOT_TOKEN}", "-Dspring.datasource.username=${BOT_DB_USERNAME}", "-jar", "app.jar"]
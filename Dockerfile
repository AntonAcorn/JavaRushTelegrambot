#Образ контейнера jdk на основе которого собираем свой контейнер
FROM adoptopenjdk/openjdk11:ubi
#добавляем аргумент, путь к джарнику нашего проекта
ARG JAR_FILE=target/*.jar
#envoronment переменные из application properties
ENV BOT_NAME=test_JavaAcorn_bot
ENV BOT_TOKEN=5578041606:AAHHXNOU4w82W1QhOR7lqfjUVTnqSpV6qvU
#Значения переменных будут другие.
#Те, которые мы передадим в Dockerfile, тем не менее, требуют дать значения по умолчанию, поэтому я и ввел какие-то.
ENV BOT_DB_USERNAME=jrtb_db_user
ENV BOT_DB_PASSWORD=jrtb_db_password
#копируем джарник в образ докера
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-Dspring.datasource.password=${BOT_DB_PASSWORD}", "-Dbot.username=${BOT_NAME}", "-Dbot.token=${BOT_TOKEN}", "-Dspring.datasource.username=${BOT_DB_USERNAME}", "-jar", "app.jar"]
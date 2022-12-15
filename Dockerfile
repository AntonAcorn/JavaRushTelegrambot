#Образ контейнера jdk
FROM adoptopenjdk/openjdk11:ubi
#добавляем аргумент, путь к джарнику проекта
ARG JAR_FILE=target/*.jar
#envoronment переменные из application properties
ENV BOT_NAME=test_JavaAcorn_bot
ENV BOT_TOKEN=5578041606:AAHHXNOU4w82W1QhOR7lqfjUVTnqSpV6qvU
#копируем джарник в образ докера
COPY ${JAR_FILE} app.jar
#Команда для командной строки "-Dbot.username=${BOT_NAME}", "-Dbot.token=${BOT_TOKEN}", -D аргумент для докера
#java -jar -Dbot.username=”test.javarush.community_bot” -Dbot.token=”dfgkdjfglkdjfglkdjfgk” *.jar
ENTRYPOINT ["java", "-Dbot.username=${BOT_NAME}", "-Dbot.token=${BOT_TOKEN}", "-jar","/app.jar"]
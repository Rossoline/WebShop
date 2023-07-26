FROM openjdk:17-jdk-alpine
ADD /admin/target/admin-0.0.1-SNAPSHOT.jar admin-application.jar
ENTRYPOINT ["java","-jar","/admin-application.jar"]

#Пофиксить имя adminApp.jar
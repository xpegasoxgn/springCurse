#imagen base con java 21
from eclipse-temurin:21-jdk

#caprta de trabajo dentro del contenedor
WORKDIR /app


# copia el jar compilado

COPY target/*.jar app.jar

#expone el puerto 8080 

EXPOSE 9090
#COMANDO PARA EJECUTAR EL JAR

ENTRYPOINT ["java", "-jar", "app.jar"]
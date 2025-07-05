# Usa una imagen base de Java
FROM eclipse-temurin:21-jdk

# Directorio de trabajo dentro del contenedor
WORKDIR /app

# Copia el JAR compilado al contenedor
COPY target/proyecto-0.0.1-SNAPSHOT.jar app.jar

# Expone el puerto que usará Render
EXPOSE 8080

# Comando para ejecutar tu app
ENTRYPOINT ["java", "-jar", "app.jar"]

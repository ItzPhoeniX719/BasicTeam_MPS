# Imagen base con Java 21
FROM eclipse-temurin:21-jre

# Carpeta de trabajo dentro del contenedor
WORKDIR /app

# Copiamos el JAR generado por Maven
COPY target/currency-converter-0.0.1-SNAPSHOT.jar app.jar

# Puerto donde escucha Spring Boot
EXPOSE 8081

# Comando para arrancar la aplicación
ENTRYPOINT ["java", "-jar", "app.jar"]

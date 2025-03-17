# Usar una imagen base de OpenJDK compatible con Java 21
FROM openjdk:21-jdk-slim

# Establecer el directorio de trabajo dentro del contenedor
WORKDIR /app

# Copiar el JAR del microservicio al contenedor
COPY target/microservicioCuenta*.jar /app/microserviciocuenta.jar

# Exponer el puerto en el que el microservicio se ejecuta
EXPOSE 8081

# Comando para ejecutar el microservicio
ENTRYPOINT ["java", "-jar", "microserviciocuenta.jar"]
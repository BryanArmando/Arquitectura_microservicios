# Arquitectura_microservicios
Proyecto de transacciones realizado con microservicios.

<div align="center">
<h1> Arquitectura en microservicios
</h1>

</div>

![Spring Badge](https://img.shields.io/badge/SpringBoot-20232A?style=for-the-badge&logo=spring&logoColor=green)
![Security Badge](https://img.shields.io/badge/SpringSecurity-20232A?style=for-the-badge&logo=springsecurity&logoColor=green)
![Swagger Badge](https://img.shields.io/badge/Swagger-20232A?style=for-the-badge&logo=swagger&logoColor=#68b618)
![Hibernate Badge](https://img.shields.io/badge/Hibernate-20232A?style=for-the-badge&logo=hibernate&logoColor=yellow)
![MYSQL Badge](https://img.shields.io/badge/MySQL-20232A?style=for-the-badge&logo=mysql&logoColor=orange)
![Apache Kafka](https://img.shields.io/badge/Apache_Kafka-231F20?style=for-the-badge&logo=apache-kafka&logoColor=white)
![Docker Badge](https://img.shields.io/badge/Docker-20232A?style=for-the-badge&logo=docker)
![Docker Badge](https://img.shields.io/badge/Maven-20232A?style=for-the-badge&logo=maven)

# 🔩 Requerimientos
Para iniciar debe tener disponible git, maven y docker para ejecutar sin problemas cada uno de los microservicios y contenedores



# 🌟 Sobre el proyecto

Este proyecto está realizado en microservicios con spring boot, el cual realiza transacciones.


Se encuentra distribuido en 2 microservicios:   

<ul> 
<li><b>Microservicio cliente:</b> Se encarga de realizar el registro de clientes con métodos CRUD</li> 
<li><b>Microservicio cuenta:</b> Se encarga de realizar el registro de cuentas y movimientos asociadas al cliente</li> 
</ul>


## Ejecución de proyectos (local):

1. Clonar el proyecto
```
git clone https://github.com/BryanArmando/Arquitectura_microservicios.git
```

2. Abrir una terminal en la carpeta del proyecto con nombre **Arquitectura_microservicios**, con la finalidad de instalar las dependencias del archivo **pom.xml** y compilar cada microservicio para generar su **.jar** respectivo para su ejecución mediante docker se debe aplicar en secuencia los siguientes comandos:
```
 cd .\microservicioCuenta\
 mvn clean package
 cd ..
 cd .\microservicioCliente\
 mvn clean package
 cd ..
```

3. El repositorio ya contiene el archivo **docker-compose.yml** que contiene los datos necesarios para poder levantar los microservicios incluidos el servidor de mysql y kafka, por lo cual,únicamente hay que ejecutar el archivo docker mencionado, en la generación del contenedor mysql se crea la base de datos y las tablas basadas el archivo **scriptBDD.sql** y lo plasma en el contenedor de mysql. Por lo cual, en el directorio raíz ejecutar:
```
docker-compose up -d
```

4. Con ello se inicia el proyecto y se ejecuta de manera local en el puerto 8080 y 8081 para microservicios Cliente y Cuenta respectivamente.
```
http://localhost:8080
http://localhost:8081
```

5. Cada uno de los microservicios se encuentran documentados mediante Swagger, puede observar su documentación:
```
http://localhost:8080/swagger-ui/index.html
http://localhost:8081/swagger-ui/index.html
```
## ✅ Ejeción de Tests

Para ejecutar las pruebas de cada microservicios, utilizar el siguiente comando:

```
  mvn clean test
```

Tambien podrá encontrar el archivo **ArquitecturaMicroservicios.postman_collection.json** que contiene ejemplos de ejecución de cada uno de los endpoints de cada microservicio

> ######  **En caso de tener los puertos a usar por los contenedores activos se debe cerrar los puertos en su sistema o cambiarlos en el archivo docker para evitar errores** 

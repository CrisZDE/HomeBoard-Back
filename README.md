# HomeBoard 🏡

## Descripción del Proyecto 📖

Este proyecto es una aplicación web que permite a los usuarios gestionar sus ideas de decoración, compartir las que quieran y explorar las ideas que otros usuarios también han compartido. El proyecto esta desarrollado con Java para el back-end haciendo uso de la estructura MVC de 3 capas y la creación de una API con SpringBoot y por el front-end se conectara a una base de datos PostgreSQL con React.js

## Funcionalidades 📋

### Página de Inicio:

Los usuarios no autenticados no pueden entrar en la apliacción.
Los usuarios autenticados pueden ver las ideas públicas y tendrán la opción de editar o eliminar los ideas que ellos mismos hayan creado.
Alertas de usuario para guiar a través de las acciones de CRUD, registro y acceso.

### Autenticación:

Formulario de registro que solicita nombre, e-mail y contraseña.
Formulario de acceso que solicita e-mail y contraseña.

## Tecnologías Utilizadas 🛠️

### Back-end:

- Java

- SpringBoot

- PostgreSQL


### Front-end:

- HTML

- Tailwind

- JavaScript

- React.js

### Otras Herramientas:

- Maven

## Cómo usar la aplicación 🔧

Clonar el repositorio

`git clone https://github.com/tuusuario/homeboard.git`

### Back-end

Configuración del Back-end

- Crea una base de datos en PostgreSQL

- Modifica las propiedades de conexión en el archivo application.properties
```
spring.datasource.url=jdbc:postgresql://localhost:5432/nombre_de_tu_base_de_datos
spring.datasource.username=tu_usuario
spring.datasource.password=tu_contraseña
```

Construir y ejecutar el back-end:
```
cd homeboard-backend
mvn clean install
mvn spring-boot:run
```
### Front-end

Configuración del Front-End

Instalar dependencias del Front-end
```
cd homeboard-frontend
npm install
npm start
o npm run dev
```
## Contribución 🤝

Haz un fork al repositorio.

Crea una nueva rama: `git checkout -b feature/name.`

Haz tus cambios.

Haz push de tu rama: `git push origin feature/name.`

Crea un pull request.

## Autora ✒️

Cristina Pérez - https://github.com.CrisZDE

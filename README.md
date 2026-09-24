# Biblioteca Digital UNTEC

Sistema de gestión web para una biblioteca digital, desarrollado como proyecto de evaluación del Módulo 5 (Desarrollo de aplicaciones web dinámicas en Java) del bootcamp de Java en Talento Digital.

Permite a los usuarios autenticarse, consultar el catálogo de libros, y gestionar préstamos y devoluciones. Incluye un CRUD (Create, Read, Update, Delete) completo de libros para el mantenimiento del catálogo.

## Tecnologías utilizadas

- Java EE (Jakarta EE) con Servlets y JSP (JavaServer Pages)
- JSTL (JavaServer Pages Standard Tag Library)
- JDBC (Java Database Connectivity) con patrón Singleton para la conexión
- MySQL como base de datos
- Apache Tomcat 11 como servidor de aplicaciones
- Bootstrap 5 para el diseño visual
- Patrón MVC (Model-View-Controller) y patrón DAO (Data Access Object)

## Funcionalidades

- Autenticación de usuarios con manejo de sesiones (login/logout)
- Catálogo de libros con disponibilidad en tiempo real
- Préstamo y devolución de libros, con manejo de transacciones (commit/rollback)
- CRUD completo de libros (crear, editar, eliminar)
- Notificaciones de éxito/error en cada operación


## Estructura del proyecto

    src/main/java/
    ├── controller/   → Servlets (LoginServlet, LibroServlet, PrestamoServlet, etc.)
    ├── dao/          → Acceso a datos (UsuarioDAO, LibroDAO, PrestamoDAO)
    ├── dto/          → Objetos de transferencia (PrestamoDTO)
    ├── model/        → Entidades (Usuario, Libro)
    └── util/         → Utilidades (ConexionBD - Singleton)

    src/main/webapp/
    ├── css/              → Estilos personalizados
    ├── index.jsp         → Login
    ├── libros.jsp        → Catálogo de libros
    ├── libro-form.jsp    → Formulario de creación/edición de libros
    └── prestamos.jsp     → Préstamos activos del usuario

## Base de datos

Nombre: `db_biblioteca`

Tablas: `usuarios`, `libros`, `prestamos` (con relaciones por clave foránea entre `prestamos` y las otras dos tablas)

## Cómo ejecutar el proyecto

### Requisitos previos
- JDK 21 o superior
- Apache Tomcat 11
- MySQL Server
- Eclipse IDE for Enterprise Java Developers

### Pasos

1. Clonar el repositorio:

git clone https://github.com/ErickNoguera/BibliotecaDigitalErickNoguera.git



2. Crear la base de datos ejecutando el script SQL (ver sección "Script de base de datos" abajo) en MySQL Workbench.

3. Actualizar las credenciales de conexión en `src/main/java/util/ConexionBD.java` si es necesario (usuario/contraseña de tu MySQL local).

4. Importar el proyecto en Eclipse como "Existing Projects into Workspace".

5. Configurar Apache Tomcat 11 como servidor en Eclipse.

6. Ejecutar el proyecto con "Run on Server".

7. Acceder desde el navegador a: `http://localhost:8080/BibliotecaDigitalErickNoguera/`

### Credenciales de prueba
- Correo: `admin@untec.cl`
- Contraseña: `1234`

## Script de base de datos

```sql
CREATE DATABASE IF NOT EXISTS db_biblioteca;
USE db_biblioteca;

CREATE TABLE usuarios (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    correo VARCHAR(150) NOT NULL UNIQUE,
    password VARCHAR(100) NOT NULL,
    rol VARCHAR(20) NOT NULL
);

CREATE TABLE libros (
    id INT AUTO_INCREMENT PRIMARY KEY,
    titulo VARCHAR(200) NOT NULL,
    autor VARCHAR(150) NOT NULL,
    isbn VARCHAR(20) NOT NULL UNIQUE,
    disponible BOOLEAN NOT NULL DEFAULT TRUE
);

CREATE TABLE prestamos (
    id INT AUTO_INCREMENT PRIMARY KEY,
    usuario_id INT NOT NULL,
    libro_id INT NOT NULL,
    fecha_prestamo DATE NOT NULL,
    fecha_devolucion DATE,
    FOREIGN KEY (usuario_id) REFERENCES usuarios(id),
    FOREIGN KEY (libro_id) REFERENCES libros(id)
);

INSERT INTO usuarios (nombre, correo, password, rol)
VALUES ('Administrador', 'admin@untec.cl', '1234', 'ADMIN');
```

## Despliegue vía WAR

El proyecto también puede desplegarse generando un archivo `.war` (`Export > WAR file` en Eclipse) y subiéndolo manualmente a través del Tomcat Manager (`http://localhost:8080/manager/html`).

## Autor

Erick Noguera — [GitHub](https://github.com/ErickNoguera)

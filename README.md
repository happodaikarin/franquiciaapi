
# Franquicia API

Esta es una aplicación desarrollada en **Spring Boot** para gestionar franquicias. Proporciona funcionalidades para administrar productos, sucursales, y franquicias, utilizando Docker para el despliegue y Terraform para la infraestructura en la nube.

## Tabla de Contenidos

- [Características](#características)
- [Requisitos](#requisitos)
- [Instalación](#instalación)
- [Uso](#uso)
- [Variables de Entorno](#variables-de-entorno)
- [Despliegue con Docker](#despliegue-con-docker)
- [Infraestructura con Terraform](#infraestructura-con-terraform)
- [Testing](#testing)
- [Contribuciones](#contribuciones)
- [Licencia](#licencia)

## Características

- Gestión de franquicias, sucursales y productos.
- API RESTful con Spring Boot.
- Despliegue utilizando Docker y Docker Compose.
- Infraestructura desplegable mediante Terraform en AWS.
- Sistema de autenticación JWT.
- Soporte para múltiples bases de datos.

## Requisitos

Antes de ejecutar la aplicación, asegúrate de tener instalado:

- **Java 17** 
- **Maven**
- **Docker**
- **Docker Compose**
- **Terraform**
- **Git**

## Instalación

1. Clona este repositorio en tu máquina local:

   ```bash
   git clone https://github.com/happodaikarin/franquiciaapi.git
   cd franquiciaapi
   ```

2. Compila la aplicación utilizando Maven:

   ```bash
   ./mvnw clean install
   ```

3. Configura las variables de entorno necesarias (ver la sección [Variables de Entorno](#variables-de-entorno)).

## Uso

Para ejecutar la aplicación localmente, utiliza el siguiente comando:

```bash
./mvnw spring-boot:run
```

Una vez iniciada, la API estará disponible en `http://localhost:8080`.

### Endpoints principales:

- **GET /api/franquicias** - Obtiene la lista de franquicias.
- **POST /api/franquicias** - Crea una nueva franquicia.
- **GET /api/sucursales** - Obtiene la lista de sucursales.
- **POST /api/productos** - Añade un nuevo producto.

## Variables de Entorno

El archivo `.env` contiene las configuraciones sensibles, tales como credenciales y configuraciones para la base de datos. Asegúrate de crear este archivo y configurar las siguientes variables:

```bash
DB_URL=jdbc:mysql://localhost:3306/franquicia_db
DB_USERNAME=root
DB_PASSWORD=yourpassword
JWT_SECRET=secret_key
```

## Despliegue con Docker

Para ejecutar la aplicación en contenedores utilizando Docker, sigue estos pasos:

1. Construye las imágenes de Docker:

   ```bash
   docker-compose build
   ```

2. Inicia los contenedores:

   ```bash
   docker-compose up
   ```

La aplicación estará disponible en `http://localhost:8080`.

## Infraestructura con Terraform

Para gestionar la infraestructura en AWS utilizando Terraform:

1. Configura las variables en el archivo `terraform/terraform.tfvars`.
2. Inicializa y despliega la infraestructura:

   ```bash
   cd terraform
   terraform init
   terraform apply
   ``

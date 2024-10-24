
Link desplegado en la nube

http://44.198.176.125:8081/api/franquicias


# Herramientas Utilizadas en AWS con Terraform

Este proyecto utiliza Amazon Web Services (AWS) para desplegar la infraestructura necesaria para la aplicación de franquicias, aprovisionada a través de Terraform. Las principales herramientas y servicios utilizados son los siguientes:

## 1. Amazon EC2 (Elastic Compute Cloud)
Se utiliza Amazon EC2 para desplegar una instancia que aloja la aplicación en contenedores Docker. Esta instancia está configurada con los siguientes detalles:
- **Tipo de instancia:** `t2.micro`
- **AMI:** Amazon Linux 2
- **Grupo de Seguridad:** Se configuró para permitir tráfico HTTP en el puerto 8081.

## 2. Amazon RDS (Relational Database Service)
Se utiliza RDS para gestionar la base de datos MySQL en la nube. Esta base de datos almacena la información relacionada con las franquicias, sucursales y productos.

- **Motor de base de datos:** MySQL 8.0
- **Endpoint de la base de datos:** Configurado mediante variables en Terraform.

## 3. Amazon VPC (Virtual Private Cloud)
Terraform configura el uso de la VPC por defecto para alojar los recursos dentro de una red virtual. También se selecciona una subred pública de esta VPC para la instancia EC2.

## 4. AWS Security Groups
Se utiliza un grupo de seguridad para permitir el tráfico HTTP en el puerto 8081 hacia la instancia EC2 y el acceso a la base de datos.

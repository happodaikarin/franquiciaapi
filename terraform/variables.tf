# variables.tf
# Definición de variables para la configuración de Terraform.
#
# - db_username: Nombre de usuario para la base de datos.
# - db_password: Contraseña para la base de datos (sensible).
# - db_endpoint: Endpoint de la base de datos RDS existente.
# - db_name: Nombre de la base de datos, con valor por defecto "franquiciaapidb".
# - container_image: Nombre de la imagen Docker de la aplicación.
# - ami_id: AMI ID para la instancia EC2, sin valor por defecto.


variable "db_username" {
  description = "Nombre de usuario para la base de datos"
  type        = string
}

variable "db_password" {
  description = "Contraseña para la base de datos"
  type        = string
  sensitive   = true
}

variable "db_endpoint" {
  description = "Endpoint de la base de datos RDS existente"
  type        = string
}

variable "db_name" {
  description = "Nombre de la base de datos"
  type        = string
  default     = "franquiciaapidb"
}

variable "container_image" {
  description = "Nombre de la imagen Docker de tu aplicación"
  type        = string
}

variable "ami_id" {
  description = "AMI ID para la instancia EC2"
  type        = string
  default     = ""
}

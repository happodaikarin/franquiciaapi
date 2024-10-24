# terraform.tfvars
# Archivo de variables para la configuración de Terraform.
#
# - db_username: Nombre de usuario para la base de datos (ejemplo: "admin").
# - db_password: Contraseña para la base de datos (reemplazar con la contraseña real).
# - db_endpoint: Endpoint de la base de datos RDS existente.
# - container_image: Nombre de la imagen Docker de la aplicación (reemplazar con tu imagen).
# - ami_id: AMI ID para la instancia EC2 (dejar vacío si se usa el data source para el AMI).


db_username     = "admin"
db_password     = "newpassword"  # Reemplaza con tu contraseña real
db_endpoint     = "franquiciodbcloud.c3ue6a2oia51.us-east-1.rds.amazonaws.com"
container_image = "tu_usuario/tu_imagen:latest"  # Reemplaza con tu imagen Docker
ami_id          = ""  # Déjalo vacío si usas el data source para el AMI

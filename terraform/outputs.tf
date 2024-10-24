# outputs.tf
# Definición de salidas para la configuración de Terraform.
#
# - instance_public_ip: Dirección IP pública de la instancia EC2 desplegada.
# - instance_public_dns: Nombre DNS público de la instancia EC2 desplegada.


output "instance_public_ip" {
  value = aws_instance.app_server.public_ip
}

output "instance_public_dns" {
  value = aws_instance.app_server.public_dns
}

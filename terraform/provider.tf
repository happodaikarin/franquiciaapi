# provider.tf
# Configuración del proveedor de AWS para Terraform.
#
# - Define la región de AWS donde se desplegarán los recursos (por defecto: "us-east-1").
# - Cambiar a la región preferida si es necesario.

provider "aws" {
  region = "us-east-1"  # Cambia a tu región preferida si es necesario
}

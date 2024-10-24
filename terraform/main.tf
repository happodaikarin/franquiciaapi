# main.tf
# Configuración principal de Terraform para desplegar una instancia EC2.
#
# - Obtener la VPC por defecto y todas sus subredes.
# - Seleccionar la primera subred pública de la VPC.
# - Obtener la AMI más reciente de Amazon Linux 2.
# - Definir un grupo de seguridad que permite tráfico HTTP en el puerto 8081.
# - Configurar una instancia EC2 de tipo "t2.micro" con Docker, 
#   utilizando las variables para conectarse a la base de datos y ejecutar la aplicación.

# Obtener la VPC por defecto
data "aws_vpc" "default" {
  default = true
}

# Obtener todas las subredes de la VPC por defecto
data "aws_subnets" "public" {
  filter {
    name   = "vpc-id"
    values = [data.aws_vpc.default.id]
  }
}

# Seleccionar la primera subred de la lista
data "aws_subnet" "public" {
  id = data.aws_subnets.public.ids[0]
}

# Data source para obtener el AMI de Amazon Linux 2 más reciente
data "aws_ami" "amazon_linux" {
  most_recent = true

  owners = ["amazon"]

  filter {
    name   = "name"
    values = ["amzn2-ami-hvm-*-x86_64-gp2"]
  }

  filter {
    name   = "virtualization-type"
    values = ["hvm"]
  }
}

# Grupo de seguridad para la instancia EC2
resource "aws_security_group" "app_sg" {
  name        = "app_sg"
  description = "Allow HTTP traffic"
  vpc_id      = data.aws_vpc.default.id

  ingress {
    from_port   = 8081
    to_port     = 8081
    protocol    = "tcp"
    cidr_blocks = ["0.0.0.0/0"]
  }

  egress {
    from_port   = 0
    to_port     = 0
    protocol    = "-1"
    cidr_blocks = ["0.0.0.0/0"]
  }
}

resource "aws_instance" "app_server" {
  ami           = var.ami_id != "" ? var.ami_id : data.aws_ami.amazon_linux.id
  instance_type = "t2.micro"
  key_name      = "franquicia-key"

  user_data = <<-EOF
              #!/bin/bash
              yum update -y
              amazon-linux-extras install docker -y
              service docker start
              usermod -a -G docker ec2-user
              docker run -d -p 8081:8081 \
                -e SPRING_DATASOURCE_URL=jdbc:mysql://${var.db_endpoint}:3306/${var.db_name} \
                -e SPRING_DATASOURCE_USERNAME=${var.db_username} \
                -e SPRING_DATASOURCE_PASSWORD=${var.db_password} \
                -e SPRING_JPA_HIBERNATE_DDL_AUTO=update \
                -e SPRING_JPA_SHOW_SQL=true \
                -e SPRING_JPA_PROPERTIES_HIBERNATE_DIALECT=org.hibernate.dialect.MySQLDialect \
                ${var.container_image}
              EOF

  vpc_security_group_ids = [aws_security_group.app_sg.id]
  subnet_id              = data.aws_subnet.public.id

  tags = {
    Name = "AppServer"
  }
}


# Franquicia API - Proyecto de Manejo de Franquicias

## Endpoints

### 1. Agregar nueva franquicia
**URL:** `/api/franquicias`  
**Método:** POST  
**Descripción:** Agrega una nueva franquicia con su nombre.  
**Cuerpo de la petición:**
```json
{
  "nombre": "Franquicia Ejemplo"
}
```
**Respuesta exitosa (201):**
```json
{
  "id": 1,
  "nombre": "Franquicia Ejemplo"
}
```

### 2. Agregar una nueva sucursal a una franquicia
**URL:** `/api/franquicias/{idFranquicia}/sucursales`  
**Método:** POST  
**Descripción:** Agrega una nueva sucursal a una franquicia existente.  
**Cuerpo de la petición:**
```json
{
  "nombre": "Sucursal Ejemplo"
}
```
**Respuesta exitosa (201):**
```json
{
  "id": 1,
  "nombre": "Sucursal Ejemplo"
}
```

### 3. Agregar un nuevo producto a una sucursal
**URL:** `/api/franquicias/{idFranquicia}/sucursales/{idSucursal}/productos`  
**Método:** POST  
**Descripción:** Agrega un nuevo producto a una sucursal existente.  
**Cuerpo de la petición:**
```json
{
  "nombre": "Producto Ejemplo",
  "stock": 100
}
```
**Respuesta exitosa (201):**
```json
{
  "id": 1,
  "nombre": "Producto Ejemplo",
  "stock": 100
}
```

### 4. Eliminar un producto de una sucursal
**URL:** `/api/franquicias/{idFranquicia}/sucursales/{idSucursal}/productos/{idProducto}`  
**Método:** DELETE  
**Descripción:** Elimina un producto de una sucursal.  
**Respuesta exitosa (204):**  
```json
{
  "message": "Producto eliminado con éxito."
}
```

### 5. Modificar el stock de un producto
**URL:** `/api/franquicias/{idFranquicia}/sucursales/{idSucursal}/productos/{idProducto}`  
**Método:** PATCH  
**Descripción:** Modifica el stock de un producto en una sucursal.  
**Cuerpo de la petición:**
```json
{
  "stock": 90
}
```
**Respuesta exitosa (200):**
```json
{
  "id": 1,
  "nombre": "Producto Ejemplo",
  "stock": 90
}
```

### 6. Producto con más stock por sucursal para una franquicia
**URL:** `/api/franquicias/{idFranquicia}/productos/mas-stock`  
**Método:** GET  
**Descripción:** Obtiene el producto con más stock en cada sucursal de una franquicia específica.  
**Respuesta exitosa (200):**
```json
[
  {
    "sucursal": "Sucursal Ejemplo",
    "producto": {
      "id": 1,
      "nombre": "Producto con más stock",
      "stock": 150
    }
  }
]
```

### 7. Actualizar el nombre de una franquicia (Punto Extra)
**URL:** `/api/franquicias/{idFranquicia}`  
**Método:** PUT  
**Descripción:** Actualiza el nombre de una franquicia.  
**Cuerpo de la petición:**
```json
{
  "nombre": "Nuevo Nombre Franquicia"
}
```
**Respuesta exitosa (200):**
```json
{
  "id": 1,
  "nombre": "Nuevo Nombre Franquicia"
}
```

## Despliegue con Docker
Para desplegar la aplicación utilizando Docker, sigue estos pasos:
1. Asegúrate de tener Docker instalado.
2. Clona este repositorio.
3. Construye la imagen Docker:
   ```
   docker build -t franquicia-api .
   ```
4. Corre la aplicación:
   ```
   docker run -p 8080:8080 franquicia-api
   ```

## Base de Datos
Puedes configurar la base de datos MySQL con las siguientes credenciales en el archivo `application.properties`:
```
spring.datasource.url=jdbc:mysql://localhost:3306/franquiciasdb
spring.datasource.username=root
spring.datasource.password=yourpassword
```

## Despliegue en la Nube (Punto Extra)
Si decides desplegar la solución en la nube, sigue las instrucciones específicas de la plataforma que estés utilizando (AWS, Google Cloud, Azure) para configurar la base de datos y el servidor.

## Git Workflow
Recuerda hacer commits constantes con mensajes descriptivos y mantener el código organizado en ramas.

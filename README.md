# Microservicio de Envíos

Microservicio de gestión de envíos de trenes.

Gestión de dependencias con Maven.




## Deployment

Para desplegar este proyecto:

    1. Crear una nueva Base de Datos de nombre "railes".

    2. Ejecutar el script sql "data.sql" en main/resources.

    3. Cambiar el user y el password en application.properties.

    4. Instalar dependencias de Maven.

```bash
  mvn clean install
```

    5. Asegurarse de que el puerto 9001 está libre.

    6. Ejecutar el Main del proyecto.

## Casos de Uso

### Crear un nuevo envío.

METHOD: GET, RUTA: **envio/{idEnvio}/{nombreEstacionOrigen}/{ciudadDestino}/{peso}**

### Obtener la lista de todos los envíos.

METHOD: PUT, RUTA: **envios**

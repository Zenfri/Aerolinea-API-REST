
Sistema de gestión integral para aerolíneas desarrollado con Spring Boot. Permite administrar aerolíneas, aviones, pilotos, pasajeros y vuelos con una arquitectura REST API robusta y escalable.

-- ## Estado
- En desarrollo (30-35%) de avance

- ## Stack Técnico
- **Backend**: Java 17, Spring Boot, Spring Data JPA
- **Base de Datos**: PostgreSQL
- **Build** Tool: Maven
- **API-Testing**: Postman
- **Arquitectura**: Patrón MVC + N Capas

- ## Funcionalidades Implementadas
- **Gestión de Pasajeros**: CRUD completo con validaciones
- **Gestión de Pilotos**: Operaciones básicas y búsquedas
- **API REST**: Endpoints funcionales con respuestas estandarizadas
- **Validaciones**: Manejo robusto de errores y datos inválidos, @Valid para cuerpos DTO y @Validated para parámetros.
- **Exepciones**: Manejo de excepciones globales perzonalizadas.
- **Mappers**: Conversión autónoma de DTO a Entity y viceverza, mediante MapStruct.
- **Respuesta**: Estándar de respuesta exitosa o de error (Codigo HTTP, Mensaje, Objeto, URL y marca de tiempo)

## Próximas Funcionalidades
- **Sistema de Vuelos**: Programación y gestión completa
- **Gestión de Aerolíneas y Aviones**: CRUD completo
- **Seguridad**: Spring Security + JWT
- **Testing**: Pruebas unitarias e integración
  
  

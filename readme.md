Gestor de Tareas (Task Manager) - Java
Este proyecto consiste en una aplicación de gestión de tareas desarrollada en Java, utilizando un enfoque de Desarrollo Guiado por Pruebas (TDD) y principios de Programación Orientada a Objetos (POO).

El sistema permite la creación, modificación, seguimiento y eliminación de tareas, validando la integridad de los datos y calculando estados de vencimiento de forma dinámica.

🚀 Características
Gestión de Tareas: Creación de tareas con título, descripción y fecha de vencimiento.

Validación de Datos: Los campos obligatorios (título, descripción, fecha) no pueden ser nulos ni estar vacíos.

Estados Dinámicos: Cálculo automático de si una tarea está vencida en base a la fecha actual.

Encapsulamiento: La lógica de negocio está protegida dentro de los objetos, evitando estados inconsistentes.

Contador de Estadísticas: El gestor permite conocer la cantidad total de tareas, tareas vencidas y no vencidas.

🛠️ Tecnologías Utilizadas
Java 17+

JUnit 5 (Jupiter): Para la implementación de pruebas unitarias y TDD.

Java Time API: Uso de LocalDate para un manejo preciso de fechas.

🧪 Pruebas Unitarias (TDD)
El desarrollo se basó en el ciclo de TDD: Red -> Green -> Refactor. Las pruebas cubren:

Verificación de datos iniciales de una tarea.

Modificación de atributos y cambio de estado a completada.

Validación de excepciones ante datos nulos o inválidos (IllegalArgumentException).

Operaciones del gestor: agregar, eliminar y consultar estados.

📁 Estructura del Proyecto
Tarea.java: Clase de dominio que representa una unidad de trabajo. Contiene la lógica de validación y estado.

GestorDeTarea.java: Clase controladora que administra la colección de tareas mediante un HashMap.

gestorDeTareasTest.java: Suite de pruebas que garantiza el correcto funcionamiento del sistema.

⚙️ Ejecución de Tests
Para ejecutar las pruebas en un entorno con Maven o Gradle, utiliza:

Bash

# Si usas Maven
mvn test

# Si usas Gradle
gradle test
Nota de diseño: Actualmente, el sistema utiliza LocalDate.now() para determinar el vencimiento, asegurando que la lógica sea coherente con el tiempo real del sistema.
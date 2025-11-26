
# Mutant Detector - Proyecto final

Proyecto Spring Boot que detecta si una secuencia de ADN pertenece a un mutante.

## Estructura
- `src/main/java` - código fuente
- `src/test/java` - tests
- `pom.xml` - dependencias Maven
- `application.properties` - configuración H2

## Endpoints
- `POST /mutant/` - recibe JSON `{ "dna": [...] }`. Responde 200 OK si es mutante, 403 si no.
- `GET /stats` - devuelve estadísticas `{count_mutant_dna, count_human_dna, ratio}`.

## Ejecutar localmente
Requiere Java 11 y Maven.

1. Compilar y correr:
```
mvn spring-boot:run
```
2. Probar con curl:
```
curl -X POST -H "Content-Type: application/json" -d '{"dna": ["ATGCGA","CAGTGC","TTATGT","AGAAGG","CCCCTA","TCACTG"]}' http://localhost:8080/mutant/ -v
```
3. Ver estadísticas:
```
curl http://localhost:8080/stats
```

## Tests
```
mvn test
```

## Despliegue
Subir a un repo GitHub y desplegar en Render (activar `mvnw` or use Maven build on render).


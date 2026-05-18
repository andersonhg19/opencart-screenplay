# OpenCart local con Docker

Esta guía explica cómo levantar una instancia local de OpenCart con Docker para correr la suite contra un entorno completamente controlado, sin depender de `tutorialsninja.com/demo` ni del bloqueado `demo.opencart.com`.

## ¿Por qué hacerlo?

- Determinismo total: el catálogo no cambia entre corridas.
- Cero dependencia de internet o de terceros (Cloudflare, mantenedores del demo público, etc.).
- Posibilidad de poblar la base con datos de prueba específicos.
- Mejor para correr en CI/CD sin riesgos de rate-limit o cambios externos.

## Requisitos

- Docker Desktop instalado en Windows (con WSL2 backend recomendado).
- 4 GB de RAM disponibles.
- Puertos 8080 y 8443 libres.

## Levantar OpenCart

Desde la raíz del proyecto:

```powershell
docker compose up -d
```

La primera vez la imagen pesa aproximadamente 600 MB y tarda 2 a 5 minutos en levantar (descarga + instalación inicial de la base de datos + sample data de OpenCart).

Para verificar el estado:

```powershell
docker compose ps
docker compose logs -f opencart
```

Cuando el log diga `opencart 12:34:56.78 INFO ==> ** Starting Apache **` ya está listo.

## Acceder

| Servicio | URL | Credenciales |
|---|---|---|
| Tienda (frontend) | http://localhost:8080/ | guest checkout |
| Panel de administración | http://localhost:8080/administration/ | `admin` / `bitnami123` |

## Apuntar la suite Screenplay al local

En `src/test/resources/serenity.conf`:

```hocon
pages {
    opencartUrl = "http://localhost:8080/"
}
```

En `src/main/java/co/edu/udea/calidad/opencart/utils/Categories.java` la base URL debe ser:

```java
private static final String BASE = "http://localhost:8080/index.php?route=product/category&path=";
```

Verificar también que los `Open.url(...)` directos en `GoToCheckoutAsGuest` y `GoToCheckoutDirectly` apunten a `localhost:8080`.

## IDs de categoría que pueden cambiar

OpenCart 4 usa IDs distintos en algunas categorías comparado con OpenCart 3 (que es la versión de tutorialsninja). Para descubrir los IDs reales:

1. Login al admin → Catalog → Categories.
2. Cada categoría muestra su ID en la URL al editarla.

Alternativa rápida: navegar la tienda y leer el query string `path=` de la URL al hacer clic en cada categoría.

## Apagar

```powershell
docker compose down              # detiene y borra contenedores, conserva volúmenes
docker compose down -v           # detiene y borra todo (volúmenes incluidos)
```

## Cuándo conviene usar esta opción

La suite por defecto apunta a `tutorialsninja.com/demo` para que cualquier persona pueda clonar el proyecto y correr `gradle test` sin instalar nada. Cuando convertimos esto en CI o cuando queremos demos absolutamente predecibles (por ejemplo el día de la socialización), levantar OpenCart local con `docker compose up -d` es una capa adicional de robustez.

# OpenCart Screenplay E2E

Automatización de pruebas E2E sobre OpenCart implementando el patrón **Screenplay** con Serenity BDD, Cucumber y Selenium. Taller de la asignatura Calidad de Software 2026-1, Facultad de Ingeniería, Universidad de Antioquia.

Estudiante: Anderson Herrera. Profesor: Robinson Coronado García.

## Reto asignado

Opción 6 del enunciado del taller: realizar una prueba E2E sobre un sitio e-commerce que permita seleccionar productos de diferentes categorías disponibles, ingresar al checkout llenando información personal, métodos de envío y pago, y confirmar la orden.

El sitio originalmente asignado fue `https://demo.opencart.com/` pero está protegido con Cloudflare WAF que bloquea el WebDriver. La suite apunta a `https://tutorialsninja.com/demo/`, un fork público de OpenCart mantenido por la comunidad QA con la misma estructura, los mismos productos clásicos (HP LP3065, Canon EOS 5D) y las mismas rutas (`route=product/category&path=...`). La razón se documenta en el reporte de entrega.

## Stack técnico

| Componente | Versión | Por qué |
|---|---|---|
| Java | 17 LTS | Compatible con Serenity 4.x y Gradle 8.x |
| Gradle | 8.14.2 | Preferencia explícita del profesor en clase |
| Plugin scaffold | io.github.jumosqu12.screenplayarchitecture 2.0.1 | Es el repositorio que recomendó el profesor; genera la estructura Screenplay completa con un comando |
| Serenity BDD | 4.1.0 | Patrón Screenplay (Actor, Task, Interaction, Question) + reportes HTML agregados |
| Cucumber | 7.x (transitivo) | Gherkin en español como muestra la Clase 6 del curso |
| Selenium WebDriver | gestionado por Serenity | Driver Chrome con descarga automática (WebDriverManager) |
| Lombok | 1.18.22 | `BillingData` POJO con `@Builder` |

## Estructura del proyecto

```
opencart-screenplay/
├── build.gradle                              # Plugin profe + Serenity + reporte agregado
├── settings.gradle
├── gradle.properties
├── serenity.properties
├── src/main/java/co/edu/udea/calidad/opencart/
│   ├── models/
│   │   └── BillingData.java                  # POJO con datos del cliente
│   ├── userinterfaces/                       # Targets (CSS y XPath)
│   │   ├── HomeUI.java
│   │   ├── CategoryUI.java
│   │   ├── ProductUI.java
│   │   ├── CartUI.java
│   │   ├── CheckoutUI.java
│   │   └── ConfirmationUI.java
│   ├── tasks/                                # Acciones de negocio
│   │   ├── Pause.java                        # Pausa visible reutilizable
│   │   ├── NavigateToOpenCart.java
│   │   ├── AddProductFromCategory.java
│   │   ├── AddFirstAvailableProduct.java
│   │   ├── GoToCheckoutAsGuest.java
│   │   ├── GoToCheckoutDirectly.java         # Para escenario carrito vacío
│   │   ├── FillBillingDetails.java           # Soporta email vacío e inválido
│   │   ├── SelectShippingMethod.java
│   │   ├── SelectPaymentMethod.java
│   │   ├── ConfirmOrder.java
│   │   ├── CheckProductAvailability.java
│   │   ├── OpenCartPage.java
│   │   └── RemoveAllFromCart.java
│   ├── questions/                            # Asserts
│   │   ├── ConfirmationMessage.java
│   │   ├── EmptyCartMessage.java
│   │   ├── EmailValidationMessage.java
│   │   ├── ProductStockStatus.java
│   │   └── CartContainsProduct.java
│   ├── utils/
│   │   └── Categories.java                   # Mapping categoría a URL
│   └── exceptions/, integrations/            # (placeholders del scaffold)
└── src/test/
    ├── java/co/edu/udea/calidad/opencart/
    │   ├── runners/Runner.java               # @RunWith(CucumberWithSerenity.class)
    │   └── stepdefinitions/StepDefinition.java
    └── resources/
        ├── serenity.conf                     # Chrome no-headless, URL OpenCart
        └── features/compra_opencart.feature  # 7 escenarios en español
```

## Cómo ejecutar

```powershell
cd "C:\Users\ander\Documents\Anderson\Universidad\CALIDAD DE SOFTWARE\Taller Screenplay\opencart-screenplay"

# Toda la suite + reporte Serenity agregado
gradle test

# Solo la ruta feliz
gradle test "-Dcucumber.filter.tags=@rutaFeliz"

# Solo los excepcionales
gradle test "-Dcucumber.filter.tags=@excepcional"

# Solo los de creatividad y cobertura
gradle test "-Dcucumber.filter.tags=@creatividad or @cobertura"
```

El reporte HTML se genera en:
`target/site/serenity/index.html`

Para abrirlo directamente en Windows:
```powershell
start target\site\serenity\index.html
```

## Escenarios cubiertos

| Tag | Escenario | Tipo |
|---|---|---|
| @rutaFeliz @smoke | Compra exitosa de dos productos de categorías distintas como invitado | Ruta feliz E2E |
| @excepcional @carritoVacio | No se puede ir a checkout con el carrito vacío | Excepcional |
| @excepcional @validacion | La facturación falla si el email está en blanco | Excepcional |
| @excepcional @validacion-formato | La facturación falla si el email tiene formato inválido | Excepcional |
| @creatividad @disponibilidad | La página de detalle muestra el estado de stock | Creatividad |
| @cobertura @carrito-positivo | El carrito refleja el producto recién agregado | Cobertura positiva |
| @cobertura @flujo-completo | El cliente puede eliminar un producto y el carrito queda vacío | Flujo CRUD completo |

## Configuración requerida

- Java 17 (`java -version` debe mostrar 17.x)
- Gradle 8.6 o superior (en este proyecto se usó 8.14.2)
- Chrome instalado en el sistema (Serenity descarga el ChromeDriver con WebDriverManager)
- Conexión a internet

## Notas de socialización

El profesor evalúa la socialización con 55 puntos sobre 100. La rúbrica menciona específicamente "uso del Delay o sleep entre paso y paso para observar la prueba con detenimiento". Por eso este proyecto incluye una Task reutilizable `Pause` con factories `briefly()` y `forMillis(long)` que se llama entre cada acción importante. En el reporte Serenity aparece como un step diferenciado y durante la demo en vivo el evaluador puede ver cada paso al ejecutarse con una pausa de aproximadamente 1.5 a 4 segundos.

## Análisis estático y cobertura

El `build.gradle` integra tres herramientas de análisis de la Clase 8:

- **Checkstyle 10.17.0** — Convenciones de estilo y nomenclatura. Config: `config/checkstyle/checkstyle.xml`.
- **SpotBugs 4.8.6** — Detección de bugs comunes en el bytecode (NullPointer, recursos sin cerrar, comparación de strings, etc.).
- **JaCoCo 0.8.11** — Cobertura de código de las clases Java (cobertura de sentencia y rama).

```powershell
# Genera los tres reportes de una vez
gradle qualityReports

# Reportes individuales
start build\reports\checkstyle\main.html
start build\reports\spotbugs\main.html
start build\reports\jacoco\test\html\index.html
```

## CI/CD con GitHub Actions

El proyecto incluye un workflow en `.github/workflows/test.yml` que se dispara en cada push a `main` o `develop` y en pull requests. El workflow:

1. Setea Java 17 y Gradle 8.14.2.
2. Instala Chrome stable.
3. Fuerza modo headless en `serenity.conf`.
4. Ejecuta `gradle test`.
5. Publica como artefactos el reporte agregado de Serenity, el JSON de Cucumber y los resultados JUnit.

Esto cubre material de la Clase 11 (Prácticas CI/CD): ejecución automática, publicación de evidencia y feedback rápido.

## OpenCart local con Docker

Para correr la suite contra una instancia totalmente controlada (sin Cloudflare ni dependencias externas), se incluye `docker-compose.yml` con OpenCart 4 + MariaDB. Ver la guía completa en `docs/OPENCART_LOCAL.md`.

```powershell
docker compose up -d
# luego cambiar serenity.conf y Categories.java para apuntar a http://localhost:8080
```

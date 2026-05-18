# language: es
@opencart
Característica: Compra E2E en OpenCart con productos de distintas categorías
  Como cliente del e-commerce OpenCart
  Quiero comprar productos de diferentes categorías y pagarlos
  Para recibir mi pedido confirmado

  Antecedentes:
    Dado que Anderson navega al sitio de OpenCart

  @rutaFeliz @smoke @modulo-categoria @modulo-carrito @modulo-checkout @modulo-confirmacion
  Escenario: Compra exitosa de dos productos de categorías distintas como invitado
    Cuando agrega al carrito el producto "HP LP3065" de la categoría "Desktops"
    Y agrega al carrito el producto "Canon EOS 5D" de la categoría "Cameras"
    Y procede al checkout como invitado
    Y diligencia sus datos de facturación:
      | nombre   | apellido | email                | telefono   | direccion         | ciudad   | pais     | departamento |
      | Anderson | Herrera  | anderson@udea.edu.co | 3001234567 | Calle 67 # 53-108 | Medellin | Colombia | Antioquia    |
    Y selecciona el método de envío "Flat Shipping Rate"
    Y selecciona el método de pago "Cash On Delivery" aceptando los términos
    Y confirma la orden
    Entonces debería ver el mensaje de confirmación "Your Order Has Been Placed!"

  @excepcional @carritoVacio @modulo-carrito @modulo-checkout
  Escenario: No se puede ir a checkout con el carrito vacío
    Cuando intenta ir al checkout sin agregar productos
    Entonces debería ver el mensaje "Your shopping cart is empty!"

  @excepcional @validacion-email @modulo-checkout
  Esquema del escenario: La facturación falla si el email no es válido
    Cuando agrega al carrito el producto "HP LP3065" de la categoría "Desktops"
    Y procede al checkout como invitado
    Y diligencia los datos de facturación con email "<email>"
    Entonces debería ver un mensaje de validación para el campo "E-Mail Address"

    Ejemplos:
      | email                |
      |                      |
      | anderson_sin_arroba  |
      | @sin-nombre.com      |

  @creatividad @disponibilidad @modulo-categoria
  Escenario: La página de detalle del producto siempre muestra su estado de stock
    Cuando agrega al carrito el producto "HP LP3065" de la categoría "Desktops"
    Entonces el sistema indica si el producto está en stock

  @cobertura @carrito-positivo @modulo-categoria @modulo-carrito
  Escenario: El carrito refleja correctamente un producto recién agregado
    Cuando agrega al carrito el producto "HP LP3065" de la categoría "Desktops"
    Y abre la página del carrito
    Entonces el carrito debería contener el producto "HP LP3065"

  @cobertura @flujo-completo @modulo-carrito
  Escenario: El cliente puede eliminar un producto del carrito y queda vacío
    Cuando agrega al carrito el producto "HP LP3065" de la categoría "Desktops"
    Y abre la página del carrito
    Y elimina todos los productos del carrito
    Entonces debería ver el mensaje "Your shopping cart is empty!"

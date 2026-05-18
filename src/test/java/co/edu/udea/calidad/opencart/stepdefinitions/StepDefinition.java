package co.edu.udea.calidad.opencart.stepdefinitions;

import co.edu.udea.calidad.opencart.models.BillingData;
import co.edu.udea.calidad.opencart.questions.ConfirmationMessage;
import co.edu.udea.calidad.opencart.questions.EmailValidationMessage;
import co.edu.udea.calidad.opencart.questions.EmptyCartMessage;
import co.edu.udea.calidad.opencart.questions.ProductStockStatus;
import co.edu.udea.calidad.opencart.questions.CartContainsProduct;
import co.edu.udea.calidad.opencart.tasks.AddFirstAvailableProduct;
import co.edu.udea.calidad.opencart.tasks.AddProductFromCategory;
import co.edu.udea.calidad.opencart.tasks.CheckProductAvailability;
import co.edu.udea.calidad.opencart.tasks.OpenCartPage;
import co.edu.udea.calidad.opencart.tasks.RemoveAllFromCart;
import co.edu.udea.calidad.opencart.tasks.ConfirmOrder;
import co.edu.udea.calidad.opencart.tasks.FillBillingDetails;
import co.edu.udea.calidad.opencart.tasks.GoToCheckoutAsGuest;
import co.edu.udea.calidad.opencart.tasks.GoToCheckoutDirectly;
import co.edu.udea.calidad.opencart.tasks.NavigateToOpenCart;
import co.edu.udea.calidad.opencart.tasks.SelectPaymentMethod;
import co.edu.udea.calidad.opencart.tasks.SelectShippingMethod;
import io.cucumber.java.Before;
import io.cucumber.java.DataTableType;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.serenitybdd.screenplay.actors.OnStage;
import net.serenitybdd.screenplay.actors.OnlineCast;

import java.util.Map;

import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalToIgnoringCase;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;

public class StepDefinition {

    @Before
    public void setTheStage() {
        OnStage.setTheStage(new OnlineCast());
    }

    @DataTableType
    public BillingData billingDataFromRow(Map<String, String> entry) {
        return BillingData.fromMap(entry);
    }

    @Given("que Anderson navega al sitio de OpenCart")
    public void andersonNavegaAlSitioDeOpenCart() {
        OnStage.theActorCalled("Anderson").wasAbleTo(NavigateToOpenCart.home());
    }

    @When("agrega al carrito el producto {string} de la categoría {string}")
    public void agregaAlCarritoElProductoDeLaCategoria(String producto, String categoria) {
        OnStage.theActorInTheSpotlight().attemptsTo(
                AddProductFromCategory.called(producto, categoria)
        );
    }

    @When("agrega al carrito el primer producto disponible de la categoría {string}")
    public void agregaAlCarritoElPrimerProductoDisponible(String categoria) {
        OnStage.theActorInTheSpotlight().attemptsTo(
                AddFirstAvailableProduct.fromCategory(categoria)
        );
    }

    @When("procede al checkout como invitado")
    public void procedeAlCheckoutComoInvitado() {
        OnStage.theActorInTheSpotlight().attemptsTo(GoToCheckoutAsGuest.start());
    }

    @When("diligencia sus datos de facturación:")
    public void diligenciaSusDatosDeFacturacion(BillingData data) {
        OnStage.theActorInTheSpotlight().attemptsTo(FillBillingDetails.fullForm(data));
    }

    @When("diligencia los datos de facturación dejando el email en blanco")
    public void diligenciaConEmailEnBlanco() {
        BillingData data = baseBillingData();
        OnStage.theActorInTheSpotlight().attemptsTo(FillBillingDetails.withEmptyEmail(data));
    }

    @When("diligencia los datos de facturación con email mal formado {string}")
    public void diligenciaConEmailMalFormado(String emailInvalido) {
        BillingData data = baseBillingData();
        OnStage.theActorInTheSpotlight().attemptsTo(FillBillingDetails.withInvalidEmail(data, emailInvalido));
    }

    private BillingData baseBillingData() {
        return BillingData.builder()
                .nombre("Anderson")
                .apellido("Herrera")
                .email("anderson@udea.edu.co")
                .telefono("3001234567")
                .direccion("Calle 67 # 53-108")
                .ciudad("Medellin")
                .pais("Colombia")
                .departamento("Antioquia")
                .build();
    }

    @When("abre la página del carrito")
    public void abreLaPaginaDelCarrito() {
        OnStage.theActorInTheSpotlight().attemptsTo(OpenCartPage.now());
    }

    @When("elimina todos los productos del carrito")
    public void eliminaTodosLosProductosDelCarrito() {
        OnStage.theActorInTheSpotlight().attemptsTo(RemoveAllFromCart.oneByOne());
    }

    @Then("el carrito debería contener el producto {string}")
    public void elCarritoDeberiaContenerElProducto(String producto) {
        OnStage.theActorInTheSpotlight().should(
                seeThat("el producto está presente en el carrito",
                        CartContainsProduct.named(producto), is(true))
        );
    }

    @When("selecciona el método de envío {string}")
    public void seleccionaElMetodoDeEnvio(String metodo) {
        OnStage.theActorInTheSpotlight().attemptsTo(SelectShippingMethod.named(metodo));
    }

    @When("selecciona el método de pago {string} aceptando los términos")
    public void seleccionaElMetodoDePagoAceptandoTerminos(String metodo) {
        OnStage.theActorInTheSpotlight().attemptsTo(SelectPaymentMethod.named(metodo));
    }

    @When("confirma la orden")
    public void confirmaLaOrden() {
        OnStage.theActorInTheSpotlight().attemptsTo(ConfirmOrder.now());
    }

    @When("intenta ir al checkout sin agregar productos")
    public void intentaIrAlCheckoutSinAgregarProductos() {
        OnStage.theActorInTheSpotlight().attemptsTo(GoToCheckoutDirectly.withoutAddingProducts());
    }

    @When("consulta la disponibilidad del primer producto de la categoría {string}")
    public void consultaLaDisponibilidadDelPrimerProducto(String categoria) {
        OnStage.theActorInTheSpotlight().attemptsTo(
                CheckProductAvailability.ofFirstProductIn(categoria)
        );
    }

    @When("consulta la disponibilidad del producto {string} de la categoría {string}")
    public void consultaLaDisponibilidadDelProducto(String producto, String categoria) {
        OnStage.theActorInTheSpotlight().attemptsTo(
                CheckProductAvailability.ofProduct(producto, categoria)
        );
    }

    @Then("debería ver el mensaje de confirmación {string}")
    public void deberiaVerElMensajeDeConfirmacion(String mensaje) {
        OnStage.theActorInTheSpotlight().should(
                seeThat("el mensaje de confirmación", ConfirmationMessage.displayed(),
                        equalToIgnoringCase(mensaje))
        );
    }

    @Then("debería ver el mensaje {string}")
    public void deberiaVerElMensaje(String mensaje) {
        OnStage.theActorInTheSpotlight().should(
                seeThat("el mensaje del carrito vacío", EmptyCartMessage.displayed(),
                        containsString(mensaje))
        );
    }

    @Then("debería ver un mensaje de validación para el campo {string}")
    public void deberiaVerUnMensajeDeValidacionParaElCampo(String campo) {
        OnStage.theActorInTheSpotlight().should(
                seeThat("el mensaje de validación del email", EmailValidationMessage.displayed(),
                        not(equalToIgnoringCase("")))
        );
    }

    @Then("el sistema indica si el producto está en stock")
    public void elSistemaIndicaSiElProductoEstaEnStock() {
        OnStage.theActorInTheSpotlight().should(
                seeThat("estado de stock del producto", ProductStockStatus.displayed(),
                        not(equalToIgnoringCase("")))
        );
    }
}

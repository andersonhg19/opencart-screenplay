package co.edu.udea.calidad.opencart.tasks;

import co.edu.udea.calidad.opencart.models.BillingData;
import co.edu.udea.calidad.opencart.userinterfaces.CheckoutUI;
import net.serenitybdd.annotations.Step;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.Tasks;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.actions.Enter;
import net.serenitybdd.screenplay.actions.SelectFromOptions;
import net.serenitybdd.screenplay.waits.WaitUntil;

import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isVisible;

public class FillBillingDetails implements Task {

    private final BillingData data;
    private final boolean skipEmail;

    public FillBillingDetails(BillingData data, boolean skipEmail) {
        this.data = data;
        this.skipEmail = skipEmail;
    }

    public static FillBillingDetails fullForm(BillingData data) {
        return Tasks.instrumented(FillBillingDetails.class, data, false);
    }

    public static FillBillingDetails withEmptyEmail(BillingData data) {
        return Tasks.instrumented(FillBillingDetails.class, data, true);
    }

    public static FillBillingDetails withInvalidEmail(BillingData data, String invalidEmail) {
        BillingData modified = BillingData.builder()
                .nombre(data.getNombre())
                .apellido(data.getApellido())
                .email(invalidEmail)
                .telefono(data.getTelefono())
                .direccion(data.getDireccion())
                .ciudad(data.getCiudad())
                .pais(data.getPais())
                .departamento(data.getDepartamento())
                .build();
        return Tasks.instrumented(FillBillingDetails.class, modified, false);
    }

    @Override
    @Step("{0} diligencia los datos de facturación")
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                WaitUntil.the(CheckoutUI.STEP2_FIRSTNAME, isVisible()).forNoMoreThan(15).seconds(),
                Enter.theValue(data.getNombre()).into(CheckoutUI.STEP2_FIRSTNAME),
                Enter.theValue(data.getApellido()).into(CheckoutUI.STEP2_LASTNAME)
        );
        if (!skipEmail) {
            actor.attemptsTo(Enter.theValue(data.getEmail()).into(CheckoutUI.STEP2_EMAIL));
        }
        actor.attemptsTo(
                Enter.theValue(data.getTelefono()).into(CheckoutUI.STEP2_TELEPHONE),
                Enter.theValue(data.getDireccion()).into(CheckoutUI.STEP2_ADDRESS_1),
                Enter.theValue(data.getCiudad()).into(CheckoutUI.STEP2_CITY),
                SelectFromOptions.byVisibleText(data.getPais()).from(CheckoutUI.STEP2_COUNTRY),
                Pause.briefly(),
                SelectFromOptions.byVisibleText(data.getDepartamento()).from(CheckoutUI.STEP2_ZONE),
                Pause.briefly(),
                Click.on(CheckoutUI.STEP2_CONTINUE),
                Pause.briefly()
        );
    }
}

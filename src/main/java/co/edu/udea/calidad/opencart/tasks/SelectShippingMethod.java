package co.edu.udea.calidad.opencart.tasks;

import co.edu.udea.calidad.opencart.userinterfaces.CheckoutUI;
import net.serenitybdd.annotations.Step;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.Tasks;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.waits.WaitUntil;

import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isVisible;

public class SelectShippingMethod implements Task {

    private final String methodName;

    public SelectShippingMethod(String methodName) {
        this.methodName = methodName;
    }

    public static SelectShippingMethod named(String methodName) {
        return Tasks.instrumented(SelectShippingMethod.class, methodName);
    }

    @Override
    @Step("{0} selecciona el método de envío '#methodName'")
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                WaitUntil.the(CheckoutUI.STEP3_SHIPPING_RADIO, isVisible()).forNoMoreThan(15).seconds(),
                Click.on(CheckoutUI.STEP3_SHIPPING_RADIO),
                Pause.briefly(),
                Click.on(CheckoutUI.STEP3_CONTINUE),
                Pause.briefly()
        );
    }
}

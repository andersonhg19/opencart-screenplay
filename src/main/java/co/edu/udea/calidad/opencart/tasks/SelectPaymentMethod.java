package co.edu.udea.calidad.opencart.tasks;

import co.edu.udea.calidad.opencart.userinterfaces.CheckoutUI;
import net.serenitybdd.annotations.Step;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.Tasks;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.waits.WaitUntil;

import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isVisible;

public class SelectPaymentMethod implements Task {

    private final String methodName;

    public SelectPaymentMethod(String methodName) {
        this.methodName = methodName;
    }

    public static SelectPaymentMethod named(String methodName) {
        return Tasks.instrumented(SelectPaymentMethod.class, methodName);
    }

    @Override
    @Step("{0} selecciona el método de pago '#methodName' y acepta los términos")
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                WaitUntil.the(CheckoutUI.STEP4_PAYMENT_RADIO, isVisible()).forNoMoreThan(15).seconds(),
                Click.on(CheckoutUI.STEP4_PAYMENT_RADIO),
                Pause.briefly(),
                Click.on(CheckoutUI.STEP4_AGREE_TERMS),
                Pause.briefly(),
                Click.on(CheckoutUI.STEP4_CONTINUE),
                Pause.briefly()
        );
    }
}

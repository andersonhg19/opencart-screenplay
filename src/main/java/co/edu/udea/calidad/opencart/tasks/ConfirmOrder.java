package co.edu.udea.calidad.opencart.tasks;

import co.edu.udea.calidad.opencart.userinterfaces.CheckoutUI;
import net.serenitybdd.annotations.Step;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.Tasks;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.waits.WaitUntil;

import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isVisible;

public class ConfirmOrder implements Task {

    public static ConfirmOrder now() {
        return Tasks.instrumented(ConfirmOrder.class);
    }

    @Override
    @Step("{0} confirma la orden")
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                WaitUntil.the(CheckoutUI.STEP5_CONFIRM, isVisible()).forNoMoreThan(15).seconds(),
                Pause.briefly(),
                Click.on(CheckoutUI.STEP5_CONFIRM),
                Pause.forMillis(2500)
        );
    }
}

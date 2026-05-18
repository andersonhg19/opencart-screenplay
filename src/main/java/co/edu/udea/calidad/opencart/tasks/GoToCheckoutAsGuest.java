package co.edu.udea.calidad.opencart.tasks;

import co.edu.udea.calidad.opencart.userinterfaces.CheckoutUI;
import net.serenitybdd.annotations.Step;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.Tasks;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.actions.Open;
import net.serenitybdd.screenplay.waits.WaitUntil;

import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isVisible;

public class GoToCheckoutAsGuest implements Task {

    private static final String CHECKOUT_URL = "https://tutorialsninja.com/demo/index.php?route=checkout/checkout";

    public static GoToCheckoutAsGuest start() {
        return Tasks.instrumented(GoToCheckoutAsGuest.class);
    }

    @Override
    @Step("{0} procede al checkout como invitado")
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                Open.url(CHECKOUT_URL),
                Pause.briefly(),
                WaitUntil.the(CheckoutUI.STEP1_GUEST_RADIO, isVisible()).forNoMoreThan(15).seconds(),
                Click.on(CheckoutUI.STEP1_GUEST_RADIO),
                Pause.briefly(),
                Click.on(CheckoutUI.STEP1_CONTINUE),
                Pause.briefly()
        );
    }
}

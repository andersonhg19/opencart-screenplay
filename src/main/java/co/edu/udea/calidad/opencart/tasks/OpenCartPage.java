package co.edu.udea.calidad.opencart.tasks;

import net.serenitybdd.annotations.Step;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.Tasks;
import net.serenitybdd.screenplay.actions.Open;

public class OpenCartPage implements Task {

    private static final String CART_URL = "https://tutorialsninja.com/demo/index.php?route=checkout/cart";

    public static OpenCartPage now() {
        return Tasks.instrumented(OpenCartPage.class);
    }

    @Override
    @Step("{0} abre la página del carrito")
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                Open.url(CART_URL),
                Pause.forMillis(2000)
        );
    }
}

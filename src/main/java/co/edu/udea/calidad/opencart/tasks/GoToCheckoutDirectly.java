package co.edu.udea.calidad.opencart.tasks;

import net.serenitybdd.annotations.Step;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.Tasks;
import net.serenitybdd.screenplay.actions.Open;

public class GoToCheckoutDirectly implements Task {

    public static GoToCheckoutDirectly withoutAddingProducts() {
        return Tasks.instrumented(GoToCheckoutDirectly.class);
    }

    @Override
    @Step("{0} intenta ir al checkout sin productos en el carrito")
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                Open.url("https://tutorialsninja.com/demo/index.php?route=checkout/cart"),
                Pause.forMillis(2000)
        );
    }
}

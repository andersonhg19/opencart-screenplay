package co.edu.udea.calidad.opencart.tasks;

import net.serenitybdd.annotations.Step;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.Tasks;
import net.serenitybdd.screenplay.actions.Open;

public class NavigateToOpenCart implements Task {

    public static NavigateToOpenCart home() {
        return Tasks.instrumented(NavigateToOpenCart.class);
    }

    @Override
    @Step("{0} navega al sitio de OpenCart (instancia tutorialsninja)")
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                Open.browserOn().thePageNamed("pages.opencartUrl"),
                Pause.briefly()
        );
    }
}

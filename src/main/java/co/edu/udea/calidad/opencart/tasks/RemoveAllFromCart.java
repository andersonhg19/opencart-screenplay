package co.edu.udea.calidad.opencart.tasks;

import co.edu.udea.calidad.opencart.userinterfaces.CartUI;
import net.serenitybdd.annotations.Step;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.Tasks;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;
import net.serenitybdd.screenplay.actions.Click;
import org.openqa.selenium.By;

public class RemoveAllFromCart implements Task {

    public static RemoveAllFromCart oneByOne() {
        return Tasks.instrumented(RemoveAllFromCart.class);
    }

    @Override
    @Step("{0} elimina todos los productos del carrito uno por uno")
    public <T extends Actor> void performAs(T actor) {
        for (int i = 0; i < 10; i++) {
            boolean cartIsEmpty = !BrowseTheWeb.as(actor)
                    .getDriver()
                    .findElements(By.xpath("//*[contains(., 'Your shopping cart is empty!')]"))
                    .isEmpty();
            if (cartIsEmpty) {
                return;
            }
            try {
                actor.attemptsTo(
                        Click.on(CartUI.REMOVE_PRODUCT_BUTTON),
                        Pause.forMillis(2500)
                );
            } catch (Exception e) {
                return;
            }
        }
    }
}

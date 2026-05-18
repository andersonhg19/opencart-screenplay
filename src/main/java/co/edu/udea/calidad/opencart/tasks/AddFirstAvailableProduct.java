package co.edu.udea.calidad.opencart.tasks;

import co.edu.udea.calidad.opencart.userinterfaces.CategoryUI;
import co.edu.udea.calidad.opencart.userinterfaces.ProductUI;
import co.edu.udea.calidad.opencart.utils.Categories;
import net.serenitybdd.annotations.Step;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.Tasks;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.actions.Open;
import net.serenitybdd.screenplay.actions.Scroll;
import net.serenitybdd.screenplay.waits.WaitUntil;

import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isVisible;

public class AddFirstAvailableProduct implements Task {

    private final String categoryName;

    public AddFirstAvailableProduct(String categoryName) {
        this.categoryName = categoryName;
    }

    public static AddFirstAvailableProduct fromCategory(String categoryName) {
        return Tasks.instrumented(AddFirstAvailableProduct.class, categoryName);
    }

    @Override
    @Step("{0} agrega el primer producto disponible de la categoría '#categoryName' al carrito")
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                Open.url(Categories.urlOf(categoryName)),
                Pause.briefly(),
                WaitUntil.the(CategoryUI.FIRST_PRODUCT_NAME, isVisible()).forNoMoreThan(15).seconds(),
                Scroll.to(CategoryUI.FIRST_PRODUCT_NAME),
                Pause.briefly(),
                Click.on(CategoryUI.FIRST_PRODUCT_NAME),
                Pause.briefly(),
                WaitUntil.the(ProductUI.ADD_TO_CART_BUTTON, isVisible()).forNoMoreThan(15).seconds(),
                Scroll.to(ProductUI.ADD_TO_CART_BUTTON),
                Pause.briefly(),
                Click.on(ProductUI.ADD_TO_CART_BUTTON),
                Pause.forMillis(4000)
        );
    }
}

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

public class AddProductFromCategory implements Task {

    private final String productName;
    private final String categoryName;

    public AddProductFromCategory(String productName, String categoryName) {
        this.productName = productName;
        this.categoryName = categoryName;
    }

    public static AddProductFromCategory called(String productName, String categoryName) {
        return Tasks.instrumented(AddProductFromCategory.class, productName, categoryName);
    }

    @Override
    @Step("{0} agrega el producto '#productName' de la categoría '#categoryName' al carrito")
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                Open.url(Categories.urlOf(categoryName)),
                Pause.forMillis(3000),
                WaitUntil.the(CategoryUI.PRODUCT_LINK.of(productName), isVisible()).forNoMoreThan(30).seconds(),
                Scroll.to(CategoryUI.PRODUCT_LINK.of(productName)),
                Pause.briefly(),
                Click.on(CategoryUI.PRODUCT_LINK.of(productName)),
                Pause.forMillis(2500),
                WaitUntil.the(ProductUI.ADD_TO_CART_BUTTON, isVisible()).forNoMoreThan(30).seconds(),
                Scroll.to(ProductUI.ADD_TO_CART_BUTTON),
                Pause.briefly(),
                Click.on(ProductUI.ADD_TO_CART_BUTTON),
                Pause.forMillis(4000)
        );
    }
}

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
import net.serenitybdd.screenplay.waits.WaitUntil;

import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isVisible;

public class CheckProductAvailability implements Task {

    private final String categoryName;
    private final String productName;

    public CheckProductAvailability(String categoryName, String productName) {
        this.categoryName = categoryName;
        this.productName = productName;
    }

    public static CheckProductAvailability ofFirstProductIn(String categoryName) {
        return Tasks.instrumented(CheckProductAvailability.class, categoryName, null);
    }

    public static CheckProductAvailability ofProduct(String productName, String categoryName) {
        return Tasks.instrumented(CheckProductAvailability.class, categoryName, productName);
    }

    @Override
    @Step("{0} consulta la disponibilidad de '#productName' en '#categoryName'")
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                Open.url(Categories.urlOf(categoryName)),
                Pause.forMillis(4000)
        );
        if (productName == null) {
            actor.attemptsTo(
                    WaitUntil.the(CategoryUI.FIRST_PRODUCT_NAME, isVisible()).forNoMoreThan(15).seconds(),
                    Click.on(CategoryUI.FIRST_PRODUCT_NAME)
            );
        } else {
            actor.attemptsTo(
                    WaitUntil.the(CategoryUI.PRODUCT_LINK.of(productName), isVisible()).forNoMoreThan(15).seconds(),
                    Click.on(CategoryUI.PRODUCT_LINK.of(productName))
            );
        }
        actor.attemptsTo(
                Pause.briefly(),
                WaitUntil.the(ProductUI.STOCK_STATUS, isVisible()).forNoMoreThan(15).seconds(),
                Pause.briefly()
        );
    }
}

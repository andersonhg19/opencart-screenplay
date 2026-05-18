package co.edu.udea.calidad.opencart.questions;

import co.edu.udea.calidad.opencart.userinterfaces.ProductUI;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;
import net.serenitybdd.screenplay.questions.Text;

public class ProductStockStatus implements Question<String> {

    public static ProductStockStatus displayed() {
        return new ProductStockStatus();
    }

    @Override
    public String answeredBy(Actor actor) {
        return Text.of(ProductUI.STOCK_STATUS).answeredBy(actor).trim();
    }
}

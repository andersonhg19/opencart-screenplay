package co.edu.udea.calidad.opencart.questions;

import co.edu.udea.calidad.opencart.userinterfaces.CartUI;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;
import net.serenitybdd.screenplay.questions.Text;

public class EmptyCartMessage implements Question<String> {

    public static EmptyCartMessage displayed() {
        return new EmptyCartMessage();
    }

    @Override
    public String answeredBy(Actor actor) {
        return Text.of(CartUI.EMPTY_CART_MESSAGE).answeredBy(actor).trim();
    }
}

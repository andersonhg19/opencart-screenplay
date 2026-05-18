package co.edu.udea.calidad.opencart.questions;

import co.edu.udea.calidad.opencart.userinterfaces.ConfirmationUI;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;
import net.serenitybdd.screenplay.questions.Text;

public class ConfirmationMessage implements Question<String> {

    public static ConfirmationMessage displayed() {
        return new ConfirmationMessage();
    }

    @Override
    public String answeredBy(Actor actor) {
        return Text.of(ConfirmationUI.CONFIRMATION_HEADER).answeredBy(actor).trim();
    }
}

package co.edu.udea.calidad.opencart.questions;

import co.edu.udea.calidad.opencart.userinterfaces.CheckoutUI;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;
import net.serenitybdd.screenplay.questions.Text;

public class EmailValidationMessage implements Question<String> {

    public static EmailValidationMessage displayed() {
        return new EmailValidationMessage();
    }

    @Override
    public String answeredBy(Actor actor) {
        return Text.of(CheckoutUI.STEP2_EMAIL_VALIDATION).answeredBy(actor).trim();
    }
}

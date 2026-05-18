package co.edu.udea.calidad.opencart.tasks;

import net.serenitybdd.annotations.Step;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.Tasks;

public class Pause implements Task {

    private final long millis;

    public Pause(long millis) {
        this.millis = millis;
    }

    public static Pause forMillis(long millis) {
        return Tasks.instrumented(Pause.class, millis);
    }

    public static Pause briefly() {
        return forMillis(1500);
    }

    @Override
    @Step("{0} pausa #millis ms para observar la prueba")
    public <T extends Actor> void performAs(T actor) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}

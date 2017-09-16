package br.com.caelum.casadocodigo.events;

/**
 * Created by android6970 on 16/09/17.
 */

public class ThrowableEvent {

    private Throwable t;

    public ThrowableEvent(Throwable t) {
        this.t = t;
    }

    public Throwable getThrowable() {
        return t;
    }
}

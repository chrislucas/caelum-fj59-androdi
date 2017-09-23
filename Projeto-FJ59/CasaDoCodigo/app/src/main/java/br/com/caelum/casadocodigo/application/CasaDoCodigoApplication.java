package br.com.caelum.casadocodigo.application;

import android.app.Application;

import br.com.caelum.casadocodigo.dagger.component.CasaDoCodigoComponent;
import br.com.caelum.casadocodigo.dagger.component.DaggerCasaDoCodigoComponent;

/**
 * Created by android6970 on 23/09/17.
 */

public class CasaDoCodigoApplication extends Application {

    private CasaDoCodigoComponent casaDoCodigoComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        casaDoCodigoComponent = DaggerCasaDoCodigoComponent.builder().build();
    }

    public CasaDoCodigoComponent getCasaDoCodigoComponent() {
        return casaDoCodigoComponent;
    }
}

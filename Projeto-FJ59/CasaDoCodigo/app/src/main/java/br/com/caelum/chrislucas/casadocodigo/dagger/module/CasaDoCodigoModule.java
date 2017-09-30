package br.com.caelum.chrislucas.casadocodigo.dagger.module;

import javax.inject.Singleton;

import br.com.caelum.chrislucas.casadocodigo.modelo.Carrinho;
import dagger.Module;
import dagger.Provides;

/**
 * Created by android6970 on 23/09/17.
 */


@Module
public class CasaDoCodigoModule {

    @Provides
    @Singleton
    public Carrinho getCarrinho() {
        return new Carrinho();
    }
}

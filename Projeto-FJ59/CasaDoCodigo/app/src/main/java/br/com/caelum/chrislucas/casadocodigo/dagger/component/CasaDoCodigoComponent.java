package br.com.caelum.chrislucas.casadocodigo.dagger.component;

import javax.inject.Singleton;

import br.com.caelum.chrislucas.casadocodigo.activity.CarrinhoActivty;
import br.com.caelum.chrislucas.casadocodigo.dagger.module.CasaDoCodigoModule;
import br.com.caelum.chrislucas.casadocodigo.fragment.DetalhesLivroFragment;
import dagger.Component;

/**
 * Created by android6970 on 23/09/17.
 * Interface onde definidos um compoenente que sabe em qual classe
 * devemos injetar uma depêndencia
 */

@Component(modules = CasaDoCodigoModule.class)
@Singleton
public interface CasaDoCodigoComponent {
    void inject(CarrinhoActivty carrinhoActivty);
    void inject(DetalhesLivroFragment detalhesLivroFragment);
}

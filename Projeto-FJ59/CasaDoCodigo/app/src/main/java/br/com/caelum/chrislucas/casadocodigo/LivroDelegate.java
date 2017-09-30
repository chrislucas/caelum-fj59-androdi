package br.com.caelum.chrislucas.casadocodigo;

import java.util.List;

import br.com.caelum.chrislucas.casadocodigo.modelo.Livro;

/**
 * Created by android6970 on 09/09/17.
 */

public interface LivroDelegate {
    void lidaComLivroSelecionado(Livro livro);
    void lidaComSucesso(List<Livro> livros);
    void lidaComErro(Throwable t);
}

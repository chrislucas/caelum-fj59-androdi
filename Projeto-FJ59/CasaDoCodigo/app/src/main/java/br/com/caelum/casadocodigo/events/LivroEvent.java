package br.com.caelum.casadocodigo.events;

import java.util.List;

import br.com.caelum.casadocodigo.modelo.Livro;

/**
 * Created by android6970 on 16/09/17.
 */

public class LivroEvent {

    private List<Livro> livros;

    public LivroEvent(List<Livro> livros) {
        this.livros = livros;
    }

    public List<Livro> getLivros() {
        return livros;
    }
}

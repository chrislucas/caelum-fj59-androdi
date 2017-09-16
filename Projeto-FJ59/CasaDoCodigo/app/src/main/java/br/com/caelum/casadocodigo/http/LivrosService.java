package br.com.caelum.casadocodigo.http;

import java.util.List;

import br.com.caelum.casadocodigo.modelo.Livro;
import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by android6970 on 16/09/17.
 */

public interface LivrosService {

    @GET("listaLivros?indicePrimeiroLivro=0&qtdLivros=20")
    Call<List<Livro>> listaLivros();

}

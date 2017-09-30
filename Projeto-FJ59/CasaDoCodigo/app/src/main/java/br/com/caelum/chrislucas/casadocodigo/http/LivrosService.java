package br.com.caelum.chrislucas.casadocodigo.http;

import java.util.List;

import br.com.caelum.chrislucas.casadocodigo.modelo.Livro;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by android6970 on 16/09/17.
 */

public interface LivrosService {

    @GET("listarLivros?indicePrimeiroLivro=0&qtdLivros=20")
    Call<List<Livro>> listarLivros();

    @GET("listarLivros")
    Call<List<Livro>> listarLivros(@Query("indicePrimeiroLivro") int index, @Query("qtdLivros") int qtd);

}

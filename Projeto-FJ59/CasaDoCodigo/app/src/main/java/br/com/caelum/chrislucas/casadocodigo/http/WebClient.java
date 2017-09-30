package br.com.caelum.chrislucas.casadocodigo.http;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import br.com.caelum.chrislucas.casadocodigo.LivroDelegate;
import br.com.caelum.chrislucas.casadocodigo.converter.ItemServiceConverterFactory;
import br.com.caelum.chrislucas.casadocodigo.events.LivroEvent;
import br.com.caelum.chrislucas.casadocodigo.events.ThrowableEvent;
import br.com.caelum.chrislucas.casadocodigo.modelo.Livro;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * Created by android6970 on 16/09/17.
 */

public class WebClient {

    private static final String SERVER_URL = "http://cdcmob.herokuapp.com/";
    private LivroDelegate livroDelegate;
    private Retrofit client;

    public WebClient(LivroDelegate livroDelegate) {
        this.livroDelegate = livroDelegate;
        createInstanceRetrofit();
    }

    public WebClient() {
        createInstanceRetrofit();
    }

    private void createInstanceRetrofit() {
        client = new Retrofit.Builder()
                .baseUrl(SERVER_URL)
                .addConverterFactory(new ItemServiceConverterFactory())
                .build();
    }

    public void getLivros() {
        LivrosService service = client.create(LivrosService.class);
        Call<List<Livro>> call = service.listarLivros();
        call.enqueue(new Callback<List<Livro>>() {
            @Override
            public void onResponse(Call<List<Livro>> call, Response<List<Livro>> response) {
                List<Livro> livros = response.body();
                EventBus.getDefault().post(new LivroEvent(livros));
                //livroDelegate.lidaComSucesso(livros);
            }

            @Override
            public void onFailure(Call<List<Livro>> call, Throwable t) {
                EventBus.getDefault().post(new ThrowableEvent(t));
                //livroDelegate.lidaComErro(t);]
            }
        });
    }

    public void retornaLivrosDoServidor(int indice, int limite) {
        LivrosService service = client.create(LivrosService.class);
        Call<List<Livro>> call = service.listarLivros(indice, limite);
        call.enqueue(new Callback<List<Livro>>() {
            @Override
            public void onResponse(Call<List<Livro>> call, Response<List<Livro>> response) {
                List<Livro> t = new ArrayList<Livro>(response.body());
                List<Livro> livros = response.body();
                EventBus.getDefault().post(new LivroEvent(livros));
            }

            @Override
            public void onFailure(Call<List<Livro>> call, Throwable t) {
                EventBus.getDefault().post(new ThrowableEvent(t));
            }
        });
    }
}

package br.com.caelum.casadocodigo.http;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import br.com.caelum.casadocodigo.LivroDelegate;
import br.com.caelum.casadocodigo.converter.ItemServiceConverterFactory;
import br.com.caelum.casadocodigo.events.LivroEvent;
import br.com.caelum.casadocodigo.events.ThrowableEvent;
import br.com.caelum.casadocodigo.modelo.Livro;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * Created by android6970 on 16/09/17.
 */

public class WebClient {

    private static final String SERVER_URL = "http://cdcmob.heorkuapp.com/";

    private LivroDelegate livroDelegate;

    public WebClient(LivroDelegate livroDelegate) {
        this.livroDelegate = livroDelegate;
    }

    public WebClient() {}

    public void getLivros() {
        Retrofit client = new Retrofit.Builder()
                .baseUrl(SERVER_URL)
                .addConverterFactory(new ItemServiceConverterFactory())
                .build();
        LivrosService service = client.create(LivrosService.class);
        Call<List<Livro>> call = service.listaLivros();
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
}

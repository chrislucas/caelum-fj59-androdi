package br.com.caelum.chrislucas.casadocodigo.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import br.com.caelum.chrislucas.casadocodigo.LivroDelegate;
import br.com.caelum.chrislucas.casadocodigo.R;
import br.com.caelum.chrislucas.casadocodigo.adapter.EndLessList;
import br.com.caelum.chrislucas.casadocodigo.adapter.LivroAdapter;
import br.com.caelum.chrislucas.casadocodigo.http.WebClient;
import br.com.caelum.chrislucas.casadocodigo.modelo.Livro;
import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * Created by android6970 on 09/09/17.
 */

public class ListaLivroFragment extends Fragment {

    private LivroDelegate delegate;

    public ListaLivroFragment() {}

    public static ListaLivroFragment getInstance(LivroDelegate delegate) {
        ListaLivroFragment listaLivroFragment = new ListaLivroFragment();
        listaLivroFragment.delegate = delegate;
        return listaLivroFragment;
    }

    @BindView(R.id.lista_livro)
    RecyclerView recyclerView;

    private RecyclerView.Adapter adapter;
    private WebClient webClient;
    private List<Livro> livros;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container
            , @Nullable Bundle savedInstanceState) {
        View layout = inflater.inflate(R.layout.fragment_lista_livros, container, false);
        ButterKnife.bind(this, layout);
        livros      = new ArrayList<>();
        webClient   = new WebClient(delegate);
        adapter     = new LivroAdapter(livros, delegate);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.addOnScrollListener(new EndLessList() {
            @Override
            public void carregaMaisItens() {
                Snackbar.make(recyclerView, "Carregando Mais Itens", Snackbar.LENGTH_SHORT).show();
                webClient.retornaLivrosDoServidor(livros.size(), 10);
            }
        });
        return layout;
    }


    public void populaLista(List<Livro> novaListaDeLivros) {
        livros.addAll(novaListaDeLivros);
        recyclerView.getAdapter().notifyDataSetChanged();
        recyclerView.addOnScrollListener(new EndLessList() {
            @Override
            public void carregaMaisItens() {
                Snackbar.make(recyclerView, "Carregando Mais Itens", Snackbar.LENGTH_SHORT).show();
                webClient.retornaLivrosDoServidor(livros.size(), 10);
            }
        });
    }
}

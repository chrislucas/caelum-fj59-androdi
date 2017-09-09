package br.com.caelum.casadocodigo.fragmetn;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import br.com.caelum.casadocodigo.LivroDelegate;
import br.com.caelum.casadocodigo.R;
import br.com.caelum.casadocodigo.adapter.LivroAdapter;
import br.com.caelum.casadocodigo.modelo.Autor;
import br.com.caelum.casadocodigo.modelo.Livro;
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

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container
            , @Nullable Bundle savedInstanceState) {
        View layout = inflater.inflate(R.layout.fragment_lista_livros, container, false);
        ButterKnife.bind(this, layout);

        List<Livro> livros = new ArrayList<>();
        for(int i=0; i<6; i++) {
            Autor autor = new Autor();
            autor.setNome(String.format("Autor %d", i));
            Livro livro = new Livro("", "", Arrays.asList(autor));
            livros.add(livro);
        }

        //RecyclerView recyclerView = (RecyclerView) layout.findViewById(R.id.lista_livro);
        recyclerView.setAdapter(new LivroAdapter(livros, delegate));
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        return layout;
    }
}

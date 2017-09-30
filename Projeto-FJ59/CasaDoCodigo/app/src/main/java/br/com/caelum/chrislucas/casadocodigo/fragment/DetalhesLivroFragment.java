package br.com.caelum.chrislucas.casadocodigo.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.Locale;

import javax.inject.Inject;

import br.com.caelum.chrislucas.casadocodigo.R;
import br.com.caelum.chrislucas.casadocodigo.application.CasaDoCodigoApplication;
import br.com.caelum.chrislucas.casadocodigo.dagger.component.CasaDoCodigoComponent;
import br.com.caelum.chrislucas.casadocodigo.modelo.Autor;
import br.com.caelum.chrislucas.casadocodigo.modelo.Carrinho;
import br.com.caelum.chrislucas.casadocodigo.modelo.Item;
import br.com.caelum.chrislucas.casadocodigo.modelo.Livro;
import br.com.caelum.chrislucas.casadocodigo.modelo.TipoDeCompra;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by android6970 on 09/09/17.
 */

public class DetalhesLivroFragment extends Fragment {

    public static final String ARGUMENTS = "ARGUMENTS";
    private Bundle arguments;
    private Livro livro;

    @BindView(R.id.detalhes_livro_foto)
    ImageView foto;
    @BindView(R.id.detalhes_livro_nome)
    TextView nome;
    @BindView(R.id.detalhes_livro_autores)
    TextView autores;
    @BindView(R.id.detalhes_livro_descricao)
    TextView descricao;
    @BindView(R.id.detalhes_livro_num_paginas)
    TextView numPaginas;
    @BindView(R.id.detalhes_livro_isbn)
    TextView isnb;
    @BindView(R.id.detalhes_livro_data_publicacao)
    TextView dataPublicacao;
    @BindView(R.id.detalhes_livro_comprar_fisico)
    Button botaoComprarFisico;
    @BindView(R.id.detalhes_livro_comprar_ebook)
    Button botaoComprarEbook;
    @BindView(R.id.detalhes_livro_comprar_ambos)
    Button botaoComprarAmbos;

    @Inject
    Carrinho carrinho;

    @Override
    public void setArguments(Bundle arguments) {
        this.arguments = arguments;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater
            , @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View layout = inflater.inflate(R.layout.fragment_detalhes_livro, container, false);
        ButterKnife.bind(this, layout);
        Bundle args = getArguments();
        livro = (Livro) args.getSerializable(ARGUMENTS);
        CasaDoCodigoApplication application = (CasaDoCodigoApplication) getActivity().getApplication();
        CasaDoCodigoComponent casaDoCodigoComponent = application.getCasaDoCodigoComponent();
        casaDoCodigoComponent.inject(this);
        return layout;
    }


    private void pppulaCampos(Livro livro) {
        nome.setText(livro.getNome());
        StringBuilder stringBuilder = new StringBuilder();
        for (Autor autor : livro.getAutores()) {
            if(stringBuilder.length() > 0) {
                stringBuilder.append(", ");
            }
            stringBuilder.append(autor.getNome());
        }
        autores.setText(stringBuilder.toString());

        descricao.setText(livro.getDescricao());
        numPaginas.setText(livro.getNumPaginas());
        isnb.setText(livro.getISBN());
        dataPublicacao.setText(livro.getDataPublicacao());

        Picasso.with(getContext())
                .load(livro.getUrlFoto())
                .placeholder(R.drawable.livro)
                .into(foto);

        String fmt = "Comprar Livro %s - R$ %.2f";
        botaoComprarFisico.setText(String.format(Locale.getDefault(), fmt, "Fisico", livro.getValorFisico()))    ;
        botaoComprarEbook.setText(String.format(Locale.getDefault(), fmt, "Virtual", livro.getValorVirtual()));
        botaoComprarEbook.setText(String.format(Locale.getDefault(), fmt, "Ambos", livro.getValorDoisJuntos()));
    }

    @OnClick(R.id.detalhes_livro_comprar_fisico)
    public void comprarFisico() {
        carrinho.adiciona(new Item(livro, TipoDeCompra.FISICO));
        Toast.makeText(getActivity()
                , String.format(
                        Locale.getDefault()
                        , "Compra do livro %s.\nTipo físico."
                        , livro.getDescricao())
                ,Toast.LENGTH_LONG).show();
    }

}

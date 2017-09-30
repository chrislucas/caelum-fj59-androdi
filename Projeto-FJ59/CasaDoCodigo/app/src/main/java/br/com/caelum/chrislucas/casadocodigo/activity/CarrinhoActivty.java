package br.com.caelum.chrislucas.casadocodigo.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import java.util.List;
import java.util.Locale;

import javax.inject.Inject;

import br.com.caelum.chrislucas.R;
import br.com.caelum.chrislucas.casadocodigo.adapter.ItensAdapter;
import br.com.caelum.chrislucas.casadocodigo.application.CasaDoCodigoApplication;
import br.com.caelum.chrislucas.casadocodigo.dagger.component.CasaDoCodigoComponent;
import br.com.caelum.chrislucas.casadocodigo.modelo.Carrinho;
import br.com.caelum.chrislucas.casadocodigo.modelo.Item;
import butterknife.BindView;
import butterknife.ButterKnife;

public class CarrinhoActivty extends AppCompatActivity {

    @BindView(R.id.lista_itens_carrinho)
    RecyclerView listaItens;
    @BindView(R.id.valor_carrinho)
    TextView total;

    @Inject
    public Carrinho carrinho;

    private static final String BUNDLE_CARRINHO = "BUNDLE_CARRINHO";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_carrinho);
        ButterKnife.bind(this);
        if(savedInstanceState == null) {
            CasaDoCodigoApplication application = (CasaDoCodigoApplication) getApplication();
            CasaDoCodigoComponent component = application.getCasaDoCodigoComponent();
            component.inject(this);
        }
        else {
            carrinho = (Carrinho) savedInstanceState.getSerializable(BUNDLE_CARRINHO);
        }
    }



    public void carregaLista() {
        List<Item> itemList = carrinho.getItens();
        listaItens.setAdapter(new ItensAdapter(itemList, this));
        listaItens.setLayoutManager(new LinearLayoutManager(this));
        double acc = 0.0;
        for(Item item : itemList) {
            acc += item.getValor();
        }
        total.setText(String.format(Locale.getDefault(), "R$ %.2f", acc));
    }

    @Override
    protected void onResume() {
        super.onResume();
        carregaLista();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        if(outState != null) {
            outState.putSerializable(BUNDLE_CARRINHO, carrinho);
        }
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        if(savedInstanceState != null) {
            carrinho = (Carrinho) savedInstanceState.getSerializable(BUNDLE_CARRINHO);
        }
    }
}

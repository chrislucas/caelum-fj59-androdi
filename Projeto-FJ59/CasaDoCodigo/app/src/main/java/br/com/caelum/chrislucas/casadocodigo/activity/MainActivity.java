package br.com.caelum.chrislucas.casadocodigo.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.io.Serializable;
import java.util.List;

import br.com.caelum.chrislucas.casadocodigo.LivroDelegate;
import br.com.caelum.chrislucas.casadocodigo.R;
import br.com.caelum.chrislucas.casadocodigo.events.LivroEvent;
import br.com.caelum.chrislucas.casadocodigo.events.ThrowableEvent;
import br.com.caelum.chrislucas.casadocodigo.fragment.DetalhesLivroFragment;
import br.com.caelum.chrislucas.casadocodigo.fragment.ListaLivroFragment;
import br.com.caelum.chrislucas.casadocodigo.http.WebClient;
import br.com.caelum.chrislucas.casadocodigo.modelo.Livro;

public class MainActivity extends AppCompatActivity implements LivroDelegate {

    private ListaLivroFragment listaLivroFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction ft = fragmentManager.beginTransaction();
        listaLivroFragment = ListaLivroFragment.getInstance(this);
        ft.replace(R.id.frame_principal, listaLivroFragment);
        ft.commit();
        new WebClient().retornaLivrosDoServidor(0, 10);
    }


    @Override
    public void lidaComLivroSelecionado(Livro livro) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        DetalhesLivroFragment detalhesLivroFragment = new DetalhesLivroFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable(DetalhesLivroFragment.ARGUMENTS, (Serializable) livro);
        detalhesLivroFragment.setArguments(bundle);

        fragmentTransaction.replace(R.id.frame_principal, detalhesLivroFragment);
        fragmentTransaction.commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.vai_para_carrinho:
                Intent intent = new Intent(this, CarrinhoActivty.class);
                // TODO criar um bundle
                startActivity(intent);
                break;
        }
        return true;
    }

    @Subscribe
    public void lidaComSucesso(LivroEvent livroEvent) {
        List<Livro> livros = livroEvent.getLivros();
        if(listaLivroFragment != null) {
            listaLivroFragment.populaLista(livros);
        }
    }

    @Subscribe
    public void lidaComErro(ThrowableEvent throwableEvent) {
        Throwable t = throwableEvent.getThrowable();
        Toast.makeText(this, t != null ? t.getMessage() : "Erro", Toast.LENGTH_LONG).show();
    }

    @Override
    public void lidaComSucesso(List<Livro> livros) {
        if(listaLivroFragment != null) {
            listaLivroFragment.populaLista(livros);
        }
    }

    @Override
    public void lidaComErro(Throwable t) {
        Toast.makeText(this, t != null ? t.getMessage() : "Erro", Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }
}

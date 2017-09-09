package br.com.caelum.casadocodigo.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import java.io.Serializable;

import br.com.caelum.casadocodigo.LivroDelegate;
import br.com.caelum.casadocodigo.R;
import br.com.caelum.casadocodigo.fragmetn.DetalhesLivroFragment;
import br.com.caelum.casadocodigo.fragmetn.ListaLivroFragment;
import br.com.caelum.casadocodigo.modelo.Livro;

public class MainActivity extends AppCompatActivity implements LivroDelegate {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction ft = fragmentManager.beginTransaction();
        ft.replace(R.id.frame_principal, ListaLivroFragment.getInstance(this));
        ft.commit();
    }

    @Override
    public void lidaComLivvroSelecionado(Livro livro) {
        FragmentManager fragmentManager= getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        DetalhesLivroFragment detalhesLivroFragment = new DetalhesLivroFragment();

        Bundle bundle = new Bundle();
        bundle.putSerializable(DetalhesLivroFragment.ARGUMENTS, (Serializable) livro);
        detalhesLivroFragment.setArguments(bundle);

        fragmentTransaction.replace(R.id.frame_principal, detalhesLivroFragment);
        fragmentTransaction.commit();

    }
}

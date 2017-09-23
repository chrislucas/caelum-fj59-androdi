package br.com.caelum.casadocodigo.adapter;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

/**
 * Created by android6970 on 23/09/17.
 */

public abstract class EndLessList extends RecyclerView.OnScrollListener {

    private int qTotalItens, qItensVisiveis, primeiroItemVisivel, qAnterior = 0;
    private LinearLayoutManager layoutManager;
    private boolean carregando = true;

    public EndLessList() {
        super();
    }

    @Override
    public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
        super.onScrollStateChanged(recyclerView, newState);
    }

    @Override
    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
        super.onScrolled(recyclerView, dx, dy);
        layoutManager       = (LinearLayoutManager) recyclerView.getLayoutManager();
        qTotalItens         = layoutManager.getItemCount();
        qItensVisiveis      = recyclerView.getChildCount();
        primeiroItemVisivel = layoutManager.findFirstVisibleItemPosition();

        if(carregando && qTotalItens > qAnterior) {
            qAnterior = qTotalItens;
            carregando = false;
        }

        int indiceLimiteParaCarregar = qTotalItens - qItensVisiveis - 5;
        if(!carregando && primeiroItemVisivel >= indiceLimiteParaCarregar) {
            carregando = true;
            carregaMaisItens();
        }
    }

    public abstract void carregaMaisItens();
}

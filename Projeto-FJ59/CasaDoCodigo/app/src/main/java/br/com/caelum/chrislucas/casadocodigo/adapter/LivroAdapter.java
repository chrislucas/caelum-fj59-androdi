package br.com.caelum.chrislucas.casadocodigo.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import br.com.caelum.chrislucas.casadocodigo.LivroDelegate;
import br.com.caelum.chrislucas.casadocodigo.R;
import br.com.caelum.chrislucas.casadocodigo.modelo.Livro;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by android6970 on 09/09/17.
 */

public class LivroAdapter extends RecyclerView.Adapter<LivroAdapter.ViewHolder> {

    private List<Livro> livros;
    private LivroDelegate delegate;

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.item_livro_nome)
        TextView nome;
        @BindView(R.id.item_livro_foto)
        ImageView foto;
        public ViewHolder(View itemView) {
            super(itemView);
            //nome = (TextView) itemView.findViewById(R.id.item_livro_nome);
            //foto = (ImageView) itemView.findViewById(R.id.item_livro_foto);
            ButterKnife.bind(this, itemView);
        }

        @OnClick(R.id.item_livro)
        public void clickItem() {
            Livro livro = livros.get(getAdapterPosition());
            delegate.lidaComLivroSelecionado(livro);
        }
    }

    public LivroAdapter(List<Livro> livros, LivroDelegate delegate) {
        this.livros = livros;
        this.delegate = delegate;
    }

    public LivroAdapter(List<Livro> livros) {
        this.livros = livros;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        int tipoDelayout = R.layout.item_livro_par;
        if(viewType % 2 != 0) {
            tipoDelayout = R.layout.item_livro_impar;
        }
        View view = LayoutInflater.from(parent.getContext()).inflate(tipoDelayout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        ViewHolder viewHolder =(ViewHolder) holder;
        Livro livro = livros.get(position);
        viewHolder.nome.setText(livro.getNome());
        Picasso
                .with(viewHolder.foto.getContext())
                .load(livro.getUrlFoto())
                .placeholder(R.drawable.livro)
                .into(viewHolder.foto);
    }


    @Override
    public int getItemCount() {
        return livros.size();
    }

    @Override
    public int getItemViewType(int position) {
        return position % 2;
    }
}

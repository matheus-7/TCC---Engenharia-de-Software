package adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.enem.prep.mobile.R;

import java.util.List;

import models.Usuario;


public class RankingAdapter extends ArrayAdapter<Usuario> {

    private Context context;
    private List<Usuario> usuarios = null;

    public RankingAdapter(Context context,  List<Usuario> usuarios) {
        super(context, 0, usuarios);
        this.usuarios = usuarios;
        this.context = context;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        Usuario usuario = usuarios.get(position);

        if(view == null)
            view = LayoutInflater.from(context).inflate(R.layout.item_ranking, null);

        Button btnUser = (Button) view.findViewById(R.id.btnUser);
        TextView tvPosicao = (TextView) view.findViewById(R.id.tvPosicao);
        TextView tvNome = (TextView) view.findViewById(R.id.tvNome);
        TextView tvPontuacao = (TextView) view.findViewById(R.id.tvPontuacao);

        btnUser.setText(usuario.getNome().substring(0, 1));
        tvPosicao.setText("#" + String.valueOf(position + 1));
        tvNome.setText(usuario.getNome());
        tvPontuacao.setText("10 pontos");

        return view;
    }

}

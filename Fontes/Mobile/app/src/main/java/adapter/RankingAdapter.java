package adapter;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.enem.prep.mobile.PerfilFragment;
import com.enem.prep.mobile.R;

import java.util.List;

import models.Resposta;
import models.Usuario;


public class RankingAdapter extends ArrayAdapter<Usuario> {

    private Context context;
    private List<Usuario> usuarios = null;
    private Usuario usuLogado = null;
    private FragmentManager fragmentManager = null;
    private Activity activity = null;

    public RankingAdapter(Context context,  List<Usuario> usuarios, Usuario usuLogado, FragmentManager fragmentManager, Activity activity) {
        super(context, 0, usuarios);
        this.usuarios = usuarios;
        this.context = context;
        this.usuLogado = usuLogado;
        this.fragmentManager = fragmentManager;
        this.activity = activity;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        final Usuario usuario = usuarios.get(position);
        int pontuacao = 0;
        int posicao = 0;

        if(position + 1 > 50) posicao = usuario.getPosicaoRanking();
        else posicao = position + 1;

        for(Resposta res : usuario.getRespostas()) pontuacao += res.getPontuacao();

        if(view == null)
            view = LayoutInflater.from(context).inflate(R.layout.item_ranking, null);

        Button btnUser = (Button) view.findViewById(R.id.btnUser);
        TextView tvPosicao = (TextView) view.findViewById(R.id.tvPosicao);
        TextView tvNome = (TextView) view.findViewById(R.id.tvNome);
        TextView tvPontuacao = (TextView) view.findViewById(R.id.tvPontuacao);

        btnUser.setText(usuario.getNome().substring(0, 1));
        tvPosicao.setText("#" + String.valueOf(posicao));
        tvNome.setText(usuario.getNome());
        tvPontuacao.setText(String.valueOf(pontuacao) + " pontos");

        btnUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment = null;
                Class fragmentClass = null;
                Bundle bundle = new Bundle();
                fragmentClass = PerfilFragment.class;

                bundle.putString("email", usuario.getEmail());
                if (usuario.getEmail().equals(usuLogado.getEmail())) bundle.putBoolean("AlteracaoPermitida", true);
                else bundle.putBoolean("AlteracaoPermitida", false);

                try{
                    fragment = (Fragment) fragmentClass.newInstance();

                    fragment.setArguments(bundle);
                }
                catch (Exception e){
                    e.printStackTrace();
                }

                fragmentManager.beginTransaction().replace(R.id.content, fragment).commit();
                activity.setTitle("Perfil");
            }
        });

        return view;
    }

}

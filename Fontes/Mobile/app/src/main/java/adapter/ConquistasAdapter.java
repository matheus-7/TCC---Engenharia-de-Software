package adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.enem.prep.mobile.R;

import java.util.List;

import models.Conquista;

public class ConquistasAdapter extends ArrayAdapter<Conquista> {

    private Context context;
    private List<Conquista> conquistas = null;

    public ConquistasAdapter(Context context,  List<Conquista> conquistas) {
        super(context, 0, conquistas);
        this.conquistas = conquistas;
        this.context = context;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        Conquista conquista = conquistas.get(position);

        if(view == null)
            view = LayoutInflater.from(context).inflate(R.layout.item_conquista, null);

        TextView tvNome = (TextView) view.findViewById(R.id.tvNome);
        tvNome.setText(conquista.getConquistaConfig().getTitulo());

        TextView tvDescricao = (TextView) view.findViewById(R.id.tvDescricao);
        tvDescricao.setText(conquista.getConquistaConfig().getDescricao());

        return view;
    }
}

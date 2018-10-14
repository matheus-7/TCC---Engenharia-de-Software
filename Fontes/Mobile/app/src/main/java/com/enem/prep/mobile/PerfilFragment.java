package com.enem.prep.mobile;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.text.DecimalFormat;
import java.util.List;

import dao.ConquistaDAO;
import dao.RespostaDAO;
import dao.UsuarioDAO;
import models.Conquista;
import models.Resposta;
import models.Usuario;


public class PerfilFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    private Button btnUserConquistas = null;

    private TextView tvNomeUsuario = null;
    private TextView tvUniversidade = null;
    private TextView tvCurso = null;
    private TextView tvCidade = null;

    private TextView tvRanking = null;
    private TextView tvPontos = null;
    private TextView tvConquistas = null;
    private TextView tvRespostas = null;
    private TextView tvAcertos = null;
    private TextView tvAproveitamento = null;

    private Usuario usuario = null;

    private final UsuarioDAO usuarioDAO = new UsuarioDAO();
    private final ConquistaDAO conquistaDAO = new ConquistaDAO();
    private final RespostaDAO respostaDAO = new RespostaDAO();

    private OnFragmentInteractionListener mListener;

    public PerfilFragment() {

    }

    public static PerfilFragment newInstance(String param1, String param2) {
        PerfilFragment fragment = new PerfilFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_perfil, container, false);

        final String emailUsuario = getArguments().getString("email", "defaultStringIfNothingFound");
        Boolean alteracaoPermitida = getArguments().getBoolean("AlteracaoPermitida", false);

        btnUserConquistas = (Button) view.findViewById(R.id.btnUserConquistas);

        tvNomeUsuario = (TextView) view.findViewById(R.id.tvNomeUsuario);
        tvUniversidade = (TextView) view.findViewById(R.id.tvUniversidade);
        tvCurso = (TextView) view.findViewById(R.id.tvCurso);
        tvCidade = (TextView) view.findViewById(R.id.tvCidade);

        tvRanking = (TextView) view.findViewById(R.id.tvRanking);
        tvPontos = (TextView) view.findViewById(R.id.tvPontos);
        tvConquistas = (TextView) view.findViewById(R.id.tvConquistas);
        tvRespostas = (TextView) view.findViewById(R.id.tvRespostas);
        tvAcertos = (TextView) view.findViewById(R.id.tvAcertos);
        tvAproveitamento = (TextView) view.findViewById(R.id.tvAproveitamento);

        if (alteracaoPermitida){
            btnUserConquistas.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    AlterarPerfil(emailUsuario);
                }
            });
        }

        new SelecionarTask(emailUsuario, this.getActivity().getApplicationContext()).execute();

        return view;
    }

    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    public void AlterarPerfil(String emailUsuario){
        Fragment fragment = null;
        Class fragmentClass = null;
        Bundle bundle = new Bundle();

        fragmentClass = AlterarPerfilFragment.class;

        bundle.putString("email", emailUsuario);

        try{
            fragment = (Fragment) fragmentClass.newInstance();

            fragment.setArguments(bundle);
        }
        catch (Exception e){
            e.printStackTrace();
        }

        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.content, fragment).commit();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(Uri uri);
    }


    private class SelecionarTask extends AsyncTask<String, Void, String> {

        private ProgressDialog dialog;
        private String email;
        private org.json.JSONArray JSONArray;
        private Context context;


        public SelecionarTask(String email, Context context) {
            this.email = email;
            this.context = context;
        }

        @Override
        protected String doInBackground(String... params) {

            usuario = new Usuario();

            try
            {
                JSONObject obj = usuarioDAO.Selecionar(email);

                if (obj != null) {
                    Gson gson = new Gson();
                    Type type = new TypeToken<Usuario>() {
                    }.getType();
                    usuario = gson.fromJson(obj.toString(), type);
                }

                ListarConquistas();
                ListarRespostas();
                PreencherPerfil();
            }
            catch (Exception e){
                return "Não foi realizar esta ação. Tente novamente mais tarde!";
            }

            return "";
        }

        private void ListarConquistas(){
            try {
                JSONArray = conquistaDAO.Listar(usuario.getId());
            } catch (Exception e) {

            }

            try {
                if (JSONArray != null) {
                    Gson gson = new Gson();
                    Type type = new TypeToken<List<Conquista>>() {
                    }.getType();
                    List<Conquista> conquistas = gson.fromJson(JSONArray.toString(), type);

                    usuario.setConquistas(conquistas);

                    tvConquistas.setText(String.valueOf(usuario.getConquistas().size()));
                }
            } catch (Exception E) {

            }
        }

        private void ListarRespostas(){
            try {
                JSONArray = respostaDAO.Listar(usuario.getId());
            } catch (Exception e) {

            }

            try {
                if (JSONArray != null) {
                    Gson gson = new Gson();
                    Type type = new TypeToken<List<Resposta>>() {
                    }.getType();
                    List<Resposta> respostas = gson.fromJson(JSONArray.toString(), type);

                    usuario.setRespostas(respostas);

                    SomarPontos();
                    CalcularAcertos();
                }


            } catch (Exception E) {

            }
        }

        private void SomarPontos(){
            double soma = 0;
            DecimalFormat df = new DecimalFormat("#,###");

            for(Resposta res : usuario.getRespostas()) soma += res.getPontuacao();

            tvPontos.setText(df.format(soma));
        }

        private void CalcularAcertos(){
            DecimalFormat df = new DecimalFormat("#,###.00");
            int respostas = usuario.getRespostas().size();
            int acertos = 0;

            for(Resposta res : usuario.getRespostas()){
                if (res.getPontuacao() > 0) acertos++;
            }

            double aproveitamento = (new Double(acertos) * 100) / new Double(respostas);

            tvRespostas.setText(String.valueOf(respostas));
            tvAcertos.setText(String.valueOf(acertos));

            if (aproveitamento >= 0) tvAproveitamento.setText(df.format(aproveitamento) + " %");
        }

        private void PreencherPerfil(){
            btnUserConquistas.setText(usuario.getNome().substring(0, 1));
            tvNomeUsuario.setText(usuario.getNome());

            tvUniversidade.setText(usuario.getUniversidade().getNome());
            tvCurso.setText(usuario.getCurso().getNome());
            tvCidade.setText(usuario.getCidade().getNome() + ", " + usuario.getCidade().getEstado().getNome());

            tvRanking.setText(String.valueOf(usuario.getPosicaoRanking()) + "º");
        }

        @Override
        protected void onPostExecute(String result) {
            dialog.dismiss();
        }

        @Override
        protected void onPreExecute() {
            dialog = new ProgressDialog(getActivity(), R.style.MyAlertDialogStyle);
            dialog.setMessage("Carregando...");
            dialog.setIndeterminate(true);
            dialog.show();
        }
    }
}

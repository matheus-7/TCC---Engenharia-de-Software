package com.enem.prep.mobile;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

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


public class EstatisticasFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    private TextView tvRankingGeral = null;
    private TextView tvPontosGeral = null;
    private TextView tvConquistasGeral = null;
    private TextView tvRespostasGeral = null;
    private TextView tvAcertosGeral = null;
    private TextView tvAproveitamentoGeral = null;

    private TextView tvPontosLinguagens = null;
    private TextView tvRespostasLinguagens = null;
    private TextView tvAcertosLinguagens = null;
    private TextView tvAproveitamentoLinguagens = null;

    private TextView tvPontosHumanas = null;
    private TextView tvRespostasHumanas = null;
    private TextView tvAcertosHumanas = null;
    private TextView tvAproveitamentoHumanas = null;

    private TextView tvPontosMatematica = null;
    private TextView tvRespostasMatematica = null;
    private TextView tvAcertosMatematica = null;
    private TextView tvAproveitamentoMatematica = null;

    private TextView tvPontosNatureza = null;
    private TextView tvRespostasNatureza = null;
    private TextView tvAcertosNatureza = null;
    private TextView tvAproveitamentoNatureza = null;

    private Usuario usuario = null;

    private final UsuarioDAO usuarioDAO = new UsuarioDAO();
    private final ConquistaDAO conquistaDAO = new ConquistaDAO();
    private final RespostaDAO respostaDAO = new RespostaDAO();

    private OnFragmentInteractionListener mListener;

    public EstatisticasFragment() {

    }

    public static EstatisticasFragment newInstance(String param1, String param2) {
        EstatisticasFragment fragment = new EstatisticasFragment();
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

        View view =  inflater.inflate(R.layout.fragment_estatisticas, container, false);

        SharedPreferences prefs = getActivity().getSharedPreferences("Preferencias", 0);
        String emailUsuario = prefs.getString("email", "defaultStringIfNothingFound");

        GetCampos(view);

        new SelecionarTask(emailUsuario, this.getActivity().getApplicationContext()).execute();

        return view;
    }

    private void GetCampos(View view){
        tvRankingGeral = (TextView) view.findViewById(R.id.tvRankingGeral);
        tvPontosGeral = (TextView) view.findViewById(R.id.tvPontosGeral);
        tvConquistasGeral = (TextView) view.findViewById(R.id.tvConquistasGeral);
        tvRespostasGeral = (TextView) view.findViewById(R.id.tvRespostasGeral);
        tvAcertosGeral = (TextView) view.findViewById(R.id.tvAcertosGeral);
        tvAproveitamentoGeral = (TextView) view.findViewById(R.id.tvAproveitamentoGeral);

        tvPontosLinguagens = (TextView) view.findViewById(R.id.tvPontosLinguagens);
        tvRespostasLinguagens = (TextView) view.findViewById(R.id.tvRespostasLinguagens);
        tvAcertosLinguagens = (TextView) view.findViewById(R.id.tvAcertosLinguagens);
        tvAproveitamentoLinguagens = (TextView) view.findViewById(R.id.tvAproveitamentoLinguagens);

        tvPontosHumanas = (TextView) view.findViewById(R.id.tvPontosHumanas);
        tvRespostasHumanas = (TextView) view.findViewById(R.id.tvRespostasHumanas);
        tvAcertosHumanas = (TextView) view.findViewById(R.id.tvAcertosHumanas);
        tvAproveitamentoHumanas = (TextView) view.findViewById(R.id.tvAproveitamentoHumanas);

        tvPontosMatematica = (TextView) view.findViewById(R.id.tvPontosMatematica);
        tvRespostasMatematica = (TextView) view.findViewById(R.id.tvRespostasMatematica);
        tvAcertosMatematica = (TextView) view.findViewById(R.id.tvAcertosMatematica);
        tvAproveitamentoMatematica = (TextView) view.findViewById(R.id.tvAproveitamentoMatematica);

        tvPontosNatureza = (TextView) view.findViewById(R.id.tvPontosNatureza);
        tvRespostasNatureza = (TextView) view.findViewById(R.id.tvRespostasNatureza);
        tvAcertosNatureza = (TextView) view.findViewById(R.id.tvAcertosNatureza);
        tvAproveitamentoNatureza = (TextView) view.findViewById(R.id.tvAproveitamentoNatureza);
    }

    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
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

                tvRankingGeral.setText(String.valueOf(usuario.getPosicaoRanking()) + "º");

                ListarConquistas();
                ListarRespostas();
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

                    tvConquistasGeral.setText(String.valueOf(usuario.getConquistas().size()));
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
            tvPontosGeral.setText(df.format(soma));

            soma = 0;
            for(Resposta res : usuario.getRespostas()){
                if (res.getAlternativa().getQuestao().getAreaConhecimento().getNome().equals("Linguagens, Códigos e suas Tecnologias")){
                    soma += res.getPontuacao();
                }
            }
            tvPontosLinguagens.setText(df.format(soma));

            soma = 0;
            for(Resposta res : usuario.getRespostas()){
                if (res.getAlternativa().getQuestao().getAreaConhecimento().getNome().equals("Ciências Humanas e suas Tecnologias")){
                    soma += res.getPontuacao();
                }
            }
            tvPontosHumanas.setText(df.format(soma));

            soma = 0;
            for(Resposta res : usuario.getRespostas()){
                if (res.getAlternativa().getQuestao().getAreaConhecimento().getNome().equals("Matemática e suas Tecnologias")){
                    soma += res.getPontuacao();
                }
            }
            tvPontosMatematica.setText(df.format(soma));

            soma = 0;
            for(Resposta res : usuario.getRespostas()){
                if (res.getAlternativa().getQuestao().getAreaConhecimento().getNome().equals("Ciências da Natureza e suas Tecnologias")){
                    soma += res.getPontuacao();
                }
            }
            tvPontosNatureza.setText(df.format(soma));
        }

        private void CalcularAcertos(){
            DecimalFormat df = new DecimalFormat("#,###.00");

            int respostas = usuario.getRespostas().size();
            int acertos = 0;
            double aproveitamento = 0;

            for(Resposta res : usuario.getRespostas()){
                if (res.getPontuacao() > 0) acertos++;
            }

            aproveitamento = (new Double(acertos) * 100) / new Double(respostas);

            tvRespostasGeral.setText(String.valueOf(respostas));
            tvAcertosGeral.setText(String.valueOf(acertos));
            if (aproveitamento >= 0) tvAproveitamentoGeral.setText(df.format(aproveitamento) + " %");


            respostas = 0;
            acertos = 0;
            for(Resposta res : usuario.getRespostas()){
                if (res.getAlternativa().getQuestao().getAreaConhecimento().getNome().equals("Linguagens, Códigos e suas Tecnologias")){
                    if (res.getPontuacao() > 0) acertos++;
                    respostas++;
                }
            }

            aproveitamento = (new Double(acertos) * 100) / new Double(respostas);

            tvRespostasLinguagens.setText(String.valueOf(respostas));
            tvAcertosLinguagens.setText(String.valueOf(acertos));
            if (aproveitamento >= 0) tvAproveitamentoLinguagens.setText(df.format(aproveitamento) + " %");


            respostas = 0;
            acertos = 0;
            for(Resposta res : usuario.getRespostas()){
                if (res.getAlternativa().getQuestao().getAreaConhecimento().getNome().equals("Ciências Humanas e suas Tecnologias")){
                    if (res.getPontuacao() > 0) acertos++;
                    respostas++;
                }
            }

            aproveitamento = (new Double(acertos) * 100) / new Double(respostas);

            tvRespostasHumanas.setText(String.valueOf(respostas));
            tvAcertosHumanas.setText(String.valueOf(acertos));
            if (aproveitamento >= 0) tvAproveitamentoHumanas.setText(df.format(aproveitamento) + " %");


            respostas = 0;
            acertos = 0;
            for(Resposta res : usuario.getRespostas()){
                if (res.getAlternativa().getQuestao().getAreaConhecimento().getNome().equals("Matemática e suas Tecnologias")){
                    if (res.getPontuacao() > 0) acertos++;
                    respostas++;
                }
            }

            aproveitamento = (new Double(acertos) * 100) / new Double(respostas);

            tvRespostasMatematica.setText(String.valueOf(respostas));
            tvAcertosMatematica.setText(String.valueOf(acertos));
            if (aproveitamento >= 0) tvAproveitamentoMatematica.setText(df.format(aproveitamento) + " %");


            respostas = 0;
            acertos = 0;
            for(Resposta res : usuario.getRespostas()){
                if (res.getAlternativa().getQuestao().getAreaConhecimento().getNome().equals("Ciências da Natureza e suas Tecnologias")){
                    if (res.getPontuacao() > 0) acertos++;
                    respostas++;
                }
            }

            aproveitamento = (new Double(acertos) * 100) / new Double(respostas);

            tvRespostasNatureza.setText(String.valueOf(respostas));
            tvAcertosNatureza.setText(String.valueOf(acertos));
            if (aproveitamento >= 0) tvAproveitamentoNatureza.setText(df.format(aproveitamento) + " %");
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

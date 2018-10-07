package com.enem.prep.mobile;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import dao.AlternativaDAO;
import dao.QuestaoDAO;
import dao.RespostaDAO;
import models.Alternativa;
import models.Questao;
import models.Resposta;
import models.Usuario;

import static java.lang.Integer.parseInt;

public class RespostasActivity extends AppCompatActivity {

    LinearLayout llActivityRespostas = null;
    LinearLayout llItens = null;
    ScrollView svRespostas = null;
    private List<Questao> questoes;
    TextView tvQuestao = null;
    TextView tvPontuacao = null;
    Button btnAlternativa1 = null;
    Button btnAlternativa2 = null;
    Button btnAlternativa3 = null;
    Button btnAlternativa4 = null;
    Button btnAlternativa5 = null;
    View view = null;

    DecimalFormat format = new DecimalFormat("0.#");

    private final QuestaoDAO QuestaoDAO = new QuestaoDAO();
    private final AlternativaDAO AlternativaDAO = new AlternativaDAO();
    private final RespostaDAO RespostaDAO = new RespostaDAO();

    private List<Resposta> respostas = new ArrayList<Resposta>();
    private Usuario usuario = new Usuario();
    double pontuacaoGeral = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_respostas);

        view = view;
        svRespostas = (ScrollView) findViewById(R.id.svRespostas);
        tvQuestao = (TextView) findViewById(R.id.tvQuestao);
        tvPontuacao = (TextView) findViewById(R.id.tvPontuacao);
        btnAlternativa1 = (Button) findViewById(R.id.btnAlternativa1);
        btnAlternativa2 = (Button) findViewById(R.id.btnAlternativa2);
        btnAlternativa3 = (Button) findViewById(R.id.btnAlternativa3);
        btnAlternativa4 = (Button) findViewById(R.id.btnAlternativa4);
        btnAlternativa5 = (Button) findViewById(R.id.btnAlternativa5);

        Intent intent = getIntent();

        startActivity(intent.getStringExtra("Area"));

        pegarUsuario();

        btnAlternativa1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                verificarResposta(btnAlternativa1);
            }
        });

        btnAlternativa2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                verificarResposta(btnAlternativa2);
            }
        });

        btnAlternativa3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                verificarResposta(btnAlternativa3);
            }
        });

        btnAlternativa4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                verificarResposta(btnAlternativa4);
            }
        });

        btnAlternativa5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                verificarResposta(btnAlternativa5);
            }
        });
    }

    private void pegarUsuario() {
        SharedPreferences prefs = getSharedPreferences("Preferencias", 0);

        usuario.setEmail(prefs.getString("email", "defaultStringIfNothingFound"));
        usuario.setId(prefs.getInt("idUsuario", 0));
    }

    private void verificarResposta(Button button){
        liberarBotoes(false);
        Alternativa alternativa = (Alternativa) button.getTag();


        if (alternativa.getCorreta()) button.setBackgroundResource(R.drawable.btn_correta);
        else {
            button.setBackgroundResource(R.drawable.btn_incorreta);

            procurarRespostaCorreta();
        }

        salvarResposta(alternativa);
        proximaQuestao();
    }

    private void proximaQuestao() {
        Questao questao = (Questao) tvQuestao.getTag();
        final Activity activity = this;

        questoes.remove(questao);

        new android.os.Handler().postDelayed(
                new Runnable() {
                    public void run() {
                        if (questoes.size() > 0) atualizarQuestao();
                        else{
                            exibirPontuacaoFinal();

                            new android.os.Handler().postDelayed(
                                    new Runnable() {
                                        public void run() {
                                            new InserirRespostasTask(activity).execute();
                                        }
                                    },
                                    2000);
                        }

                        svRespostas.fullScroll(ScrollView.FOCUS_UP);
                    }
                },
         2000);

    }

    private void exibirPontuacaoFinal() {
        llItens.removeView(tvQuestao);
        llItens.removeView(btnAlternativa1);
        llItens.removeView(btnAlternativa2);
        llItens.removeView(btnAlternativa3);
        llItens.removeView(btnAlternativa4);
        llItens.removeView(btnAlternativa5);

        tvPontuacao.setText("Você conseguiu " + format.format(pontuacaoGeral) + " pontos nesta rodada!");
        tvPontuacao.setVisibility(view.VISIBLE);
    }

    private void salvarResposta(Alternativa alternativa) {
        double pontuacao = 0;

        if (alternativa.getCorreta()) pontuacao = 10;

        Resposta resposta = new Resposta(
                0,
                alternativa,
                usuario,
                pontuacao,
                null
        );

        respostas.add(resposta);

        atualizarPontuacao(pontuacao);
    }

    private void atualizarPontuacao(double pontuacao){
        pontuacaoGeral += pontuacao;

        getSupportActionBar().setTitle(format.format(pontuacaoGeral) + " pontos");
    }

    private void procurarRespostaCorreta(){
        Alternativa alternativa = new Alternativa();

        alternativa = (Alternativa) btnAlternativa1.getTag();
        if (alternativa.getCorreta()){
            btnAlternativa1.setBackgroundResource(R.drawable.btn_correta);
            return;
        }

        alternativa = (Alternativa) btnAlternativa2.getTag();
        if (alternativa.getCorreta()){
            btnAlternativa2.setBackgroundResource(R.drawable.btn_correta);
            return;
        }

        alternativa = (Alternativa) btnAlternativa3.getTag();
        if (alternativa.getCorreta()){
            btnAlternativa3.setBackgroundResource(R.drawable.btn_correta);
            return;
        }

        alternativa = (Alternativa) btnAlternativa4.getTag();
        if (alternativa.getCorreta()){
            btnAlternativa4.setBackgroundResource(R.drawable.btn_correta);
            return;
        }

        alternativa = (Alternativa) btnAlternativa5.getTag();
        if (alternativa.getCorreta()){
            btnAlternativa5.setBackgroundResource(R.drawable.btn_correta);
            return;
        }
    }

    private void liberarBotoes(boolean liberar){
        btnAlternativa1.setEnabled(liberar);
        btnAlternativa2.setEnabled(liberar);
        btnAlternativa3.setEnabled(liberar);
        btnAlternativa4.setEnabled(liberar);
        btnAlternativa5.setEnabled(liberar);
    }

    private void atualizarQuestao(){
        tvQuestao.setText(questoes.get(0).getDescricao());
        tvQuestao.setTag(questoes.get(0));

        btnAlternativa1.setText(questoes.get(0).getAlternativas().get(0).getDescricao());
        btnAlternativa2.setText(questoes.get(0).getAlternativas().get(1).getDescricao());
        btnAlternativa3.setText(questoes.get(0).getAlternativas().get(2).getDescricao());
        btnAlternativa4.setText(questoes.get(0).getAlternativas().get(3).getDescricao());
        btnAlternativa5.setText(questoes.get(0).getAlternativas().get(4).getDescricao());

        btnAlternativa1.setTag(questoes.get(0).getAlternativas().get(0));
        btnAlternativa2.setTag(questoes.get(0).getAlternativas().get(1));
        btnAlternativa3.setTag(questoes.get(0).getAlternativas().get(2));
        btnAlternativa4.setTag(questoes.get(0).getAlternativas().get(3));
        btnAlternativa5.setTag(questoes.get(0).getAlternativas().get(4));

        btnAlternativa1.setBackgroundResource(R.drawable.btn_alternativa);
        btnAlternativa2.setBackgroundResource(R.drawable.btn_alternativa);
        btnAlternativa3.setBackgroundResource(R.drawable.btn_alternativa);
        btnAlternativa4.setBackgroundResource(R.drawable.btn_alternativa);
        btnAlternativa5.setBackgroundResource(R.drawable.btn_alternativa);

        liberarBotoes(true);
    }

    private void startActivity(String area) {
        llActivityRespostas = (LinearLayout) findViewById(R.id.llActivityRespostas);
        llItens = (LinearLayout) findViewById(R.id.llItens);
        int idArea = 0;

        switch (area){
            case "Linguagens":
                llActivityRespostas.setBackgroundColor(getResources().getColor(R.color.Linguagens));
                idArea = 3;
                break;
            case "Humanas":
                llActivityRespostas.setBackgroundColor(getResources().getColor(R.color.Humanas));
                idArea = 2;
                break;
            case "Matemática":
                llActivityRespostas.setBackgroundColor(getResources().getColor(R.color.Matemática));
                idArea = 4;
                break;
            case "Natureza":
                llActivityRespostas.setBackgroundColor(getResources().getColor(R.color.Natureza));
                idArea = 1;
                break;
        }

        new ListarQuestoesTask(this, idArea).execute();
    }

    private void showToast(String mensagem) {
        Toast.makeText(this, mensagem, Toast.LENGTH_SHORT).show();
    }



    private class ListarQuestoesTask extends AsyncTask<Void, Void, List<Questao>> {

        private Activity activity;
        private ProgressDialog dialog;
        private int idArea;
        private JSONArray JSONArray;

        public ListarQuestoesTask(Activity activity, int idArea) {
            this.idArea = idArea;
            this.activity = activity;
        }

        @Override
        protected List<Questao> doInBackground(Void... arg0) {
            try {
                JSONArray = QuestaoDAO.Listar(idArea);
            } catch (Exception e) {
                return null;
            }

            try {
                if (JSONArray != null) {
                    Gson gson = new Gson();
                    Type type = new TypeToken<List<Questao>>() {
                    }.getType();
                    questoes = gson.fromJson(JSONArray.toString(), type);
                }
            } catch (Exception E) {
                return null;
            }

            for (Questao questao : questoes){
                try {
                    JSONArray = AlternativaDAO.Listar(questao.getId());

                    if (JSONArray != null) {
                        Gson gson = new Gson();
                        Type type = new TypeToken<List<Alternativa>>() {
                        }.getType();
                        List<Alternativa> alternativas = gson.fromJson(JSONArray.toString(), type);
                        questao.setAlternativas(alternativas);
                    }
                } catch (Exception e) {
                    return null;
                }
            }

            return questoes;
        }

        @Override
        protected void onPostExecute(List<Questao> listaQuestoes) {
            if (listaQuestoes == null) showToast("Não foi possivel estabelecer conexão com o servidor.");

            atualizarQuestao();

            dialog.dismiss();
        }

        @Override
        protected void onPreExecute() {
            dialog = new ProgressDialog(activity, R.style.MyAlertDialogStyle);
            dialog.setMessage("Carregando...");
            dialog.setIndeterminate(true);
            dialog.show();
        }
    }

    private class InserirRespostasTask extends AsyncTask<String, Void, String> {

        private Activity activity;
        private ProgressDialog dialog;

        public InserirRespostasTask(Activity activity) {
            this.activity = activity;
        }

        @Override
        protected String doInBackground(String... params) {
            try{
                JSONObject obj = RespostaDAO.Salvar(respostas);
            }
            catch (Exception e){
                return "Ocorreu um erro nesta operação!";
            }

            return "";
        }

        @Override
        protected void onPostExecute(String result) {
            dialog.dismiss();

            Intent intentHome = new Intent(activity, MainActivity.class);
            startActivity(intentHome);
        }

        @Override
        protected void onPreExecute() {
            dialog = new ProgressDialog(activity, R.style.MyAlertDialogStyle);
            dialog.setMessage("Carregando...");
            dialog.setIndeterminate(true);
            dialog.show();
        }
    }
}

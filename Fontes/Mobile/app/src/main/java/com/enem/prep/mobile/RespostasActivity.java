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
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;

import java.lang.reflect.Type;
import java.util.List;

import dao.AlternativaDAO;
import dao.QuestaoDAO;
import models.Alternativa;
import models.Questao;

public class RespostasActivity extends AppCompatActivity {

    private List<Questao> questoes;

    private final QuestaoDAO QuestaoDAO = new QuestaoDAO();
    private final AlternativaDAO AlternativaDAO = new AlternativaDAO();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_respostas);

        Intent intent = getIntent();

        startActivity(intent.getStringExtra("Area"));
    }

    private void startActivity(String area) {
        LinearLayout llActivityRespostas = (LinearLayout) findViewById(R.id.llActivityRespostas);
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

        Button btnTeste = (Button) findViewById(R.id.btnTeste);

        btnTeste.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<Questao> teste = questoes;
            }
        });
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

            dialog.dismiss();
        }

        @Override
        protected void onPreExecute() {
            dialog = new ProgressDialog(activity);
            dialog.setIndeterminate(true);
            dialog.setMessage("Carregando...");
            dialog.show();
        }
    }
}

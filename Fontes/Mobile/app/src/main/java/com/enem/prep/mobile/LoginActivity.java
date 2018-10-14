package com.enem.prep.mobile;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONObject;

import dao.UsuarioDAO;
import models.Usuario;

import static java.lang.Integer.parseInt;

public class LoginActivity extends AppCompatActivity {

    private EditText edtEmail = null;
    private EditText edtSenha = null;

    private final UsuarioDAO usuarioDAO = new UsuarioDAO();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();

        if (usuarioLogado()) new VerificarLoginTask(this).execute();
        else startActivity();
    }


    private boolean usuarioLogado(){
        SharedPreferences prefs = getSharedPreferences("Preferencias", 0);
        boolean logado = prefs.getBoolean("estaLogado", false);

        return logado;
    }


    private class VerificarLoginTask extends AsyncTask<String, Void, String> {

        private Activity activity;
        private ProgressDialog dialog;
        private Usuario usuario;

        public VerificarLoginTask(Activity activity) {
            Usuario usu = new Usuario();
            SharedPreferences prefs = getSharedPreferences("Preferencias", 0);

            if (prefs.getBoolean("estaLogado", false)){
                usu.setEmail(prefs.getString("email", "defaultStringIfNothingFound"));
                usu.setSenha(prefs.getString("senha", "defaultStringIfNothingFound"));
            }
            else{
                getCampos();

                usu.setEmail(edtEmail.getText().toString());
                usu.setSenha(usu.getSenhaCriptografada(edtSenha.getText().toString()));
            }

            this.usuario = usu;
            this.activity = activity;
        }

        @Override
        protected String doInBackground(String... params) {
            try{
                JSONObject obj = usuarioDAO.Selecionar(usuario.getEmail());

                if (obj == null || obj.getString("id").equals("0")) return "Acesso negado";
                else usuario.setId(parseInt(obj.getString("id")));

                if (!obj.getBoolean("ativo")) return "Acesso negado";

                if (!obj.getString("senha").equals(usuario.getSenha())) return "Acesso negado";
            }
            catch (Exception e){
                return "Acesso negado";
            }

            SharedPreferences prefs = getSharedPreferences("Preferencias", 0);

            if(!prefs.getBoolean("estaLogado", false)){
                SharedPreferences.Editor editor = prefs.edit();

                editor.putBoolean("estaLogado", true);
                editor.putString("email", usuario.getEmail());
                editor.putString("senha", usuario.getSenha());
                editor.putInt("idUsuario", usuario.getId());

                editor.commit();
            }

            return "Acesso liberado";
        }

        @Override
        protected void onPostExecute(String result) {
            SharedPreferences prefs = getSharedPreferences("Preferencias", 0);
            SharedPreferences.Editor editor = prefs.edit();

            if (result.equals("Acesso liberado")){
                Intent intentEntrar = new Intent(activity, MainActivity.class);
                startActivity(intentEntrar);
            }
            else{
                if(!prefs.getBoolean("estaLogado", false)){
                    showToast("Usuário ou senha incorretos!");
                }
                else startActivity();

                editor.clear();
                editor.putBoolean("estaLogado", false);
                editor.commit();
            }

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

    private void getCampos(){
        edtEmail = (EditText) findViewById(R.id.edtEmail);
        edtSenha = (EditText) findViewById(R.id.edtSenha);
    }

    private void showToast(String mensagem) {
        Toast.makeText(this, mensagem, Toast.LENGTH_SHORT).show();
    }

    private void startActivity(){
        setContentView(R.layout.activity_login);

        Button btnEntrar = (Button) findViewById(R.id.btnEntrar);
        Button btnNovaConta = (Button) findViewById(R.id.btnNovaConta);


        btnEntrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new VerificarLoginTask(LoginActivity.this).execute();
            }
        });

        btnNovaConta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentNovaConta = new Intent(LoginActivity.this, NovaContaActivity.class);
                startActivity(intentNovaConta);
            }
        });
    }
}

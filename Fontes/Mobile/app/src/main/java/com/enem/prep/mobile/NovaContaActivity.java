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

public class NovaContaActivity extends AppCompatActivity {

    private EditText edtNome = null;
    private EditText edtEmail = null;
    private EditText edtSenha = null;

    private final UsuarioDAO usuarioDAO = new UsuarioDAO();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_nova_conta);

        Button btnConfirmar = (Button) findViewById(R.id.btnConfirmar);

        btnConfirmar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Usuario usuario = montarUsuario();

                inserirUsuario(usuario);
            }
        });
    }

    private Usuario montarUsuario() {
        getCampos();

        Usuario usuario = new Usuario(
            edtNome.getText().toString(),
            edtEmail.getText().toString(),
            edtSenha.getText().toString()
        );

        return usuario;
    }

    private void inserirUsuario(Usuario usuario){
        if (usuario.getNome().equals("") ||
            usuario.getEmail().equals("") ||
            usuario.getSenha().equals("")){
            showToast("Preencha todos os campos para prosseguir!");

            return;
        }

        new ExisteEmailTask(usuario, this).execute();
    }

    private void getCampos(){
        edtNome = (EditText) findViewById(R.id.edtNome);
        edtEmail = (EditText) findViewById(R.id.edtEmail);
        edtSenha = (EditText) findViewById(R.id.edtSenha);
    }

    private void showToast(String mensagem) {
        Toast.makeText(this, mensagem, Toast.LENGTH_SHORT).show();
    }

    private void salvarLogin(Usuario usuario){
        SharedPreferences prefs = getSharedPreferences("Preferencias", 0);
        SharedPreferences.Editor editor = prefs.edit();

        editor.putBoolean("estaLogado", true);
        editor.putString("email", usuario.getEmail());
        editor.putString("senha", usuario.getSenhaCriptografada(usuario.getSenha()));
        editor.putInt("idUsuario", usuario.getId());

        editor.commit();
    }


    private class ExisteEmailTask extends AsyncTask<String, Void, String> {

        private Activity activity;
        private ProgressDialog dialog;
        private Usuario usuario;

        public ExisteEmailTask(Usuario usuario, Activity activity) {
            this.usuario = usuario;
            this.activity = activity;
        }

        @Override
        protected String doInBackground(String... params) {
            try{
                JSONObject obj = usuarioDAO.Selecionar(usuario.getEmail());

                if (obj == null || !obj.getString("id").equals("0")) return "O e-mail informado já está sendo utilizado por outro usuário!";
            }
            catch (Exception e){
                return "Não foi possível realizar o cadastro. Tente novamente mais tarde!";
            }

            return "";
        }

        @Override
        protected void onPostExecute(String result) {
            dialog.dismiss();

            if (result.equals("")) new InserirTask(usuario, activity).execute();
            else showToast(result);
        }

        @Override
        protected void onPreExecute() {
            dialog = new ProgressDialog(activity, R.style.MyAlertDialogStyle);
            dialog.setMessage("Carregando...");
            dialog.setIndeterminate(true);
            dialog.show();
        }
    }


    private class InserirTask extends AsyncTask<String, Void, String> {

        private Activity activity;
        private ProgressDialog dialog;
        private Usuario usuario;

        public InserirTask(Usuario usuario, Activity activity) {
            this.usuario = usuario;
            this.activity = activity;
        }

        @Override
        protected String doInBackground(String... params) {
            try{
                JSONObject obj = usuarioDAO.Salvar(usuario);

                usuario.setId(parseInt(obj.getString("id")));

                salvarLogin(usuario);
            }
            catch (Exception e){
                return "Não foi possível realizar o cadastro. Tente novamente mais tarde!";
            }
            return "";
        }

        @Override
        protected void onPostExecute(String result) {
            dialog.dismiss();

            if (!result.equals("")) showToast(result);
            else{
                Intent intentEntrar = new Intent(activity, MainActivity.class);
                startActivity(intentEntrar);
            }
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

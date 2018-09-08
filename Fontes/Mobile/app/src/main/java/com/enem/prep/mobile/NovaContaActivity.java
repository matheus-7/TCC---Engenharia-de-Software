package com.enem.prep.mobile;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONObject;

import dao.UsuarioDAO;
import models.Usuario;

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

                String mensagem = validarUsuario(usuario);

                if (mensagem.equals("")){
                    Intent intentListaAreas = new Intent(NovaContaActivity.this, MainActivity.class);
                    startActivity(intentListaAreas);
                }
                else{
                    NovaContaActivity.this.showToast(mensagem);
                }
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

    private String validarUsuario(Usuario usuario){
        if (usuario.getNome().equals("") ||
            usuario.getEmail().equals("") ||
            usuario.getSenha().equals("")) return "Preencha todos os campos para prosseguir!";

        JSONObject obj = usuarioDAO.ExisteEmail(usuario.getEmail());

        try{
            if (obj == null || obj.getString("id").equals("0")) return "O e-mail informado já está sendo utilizado por outro usuário!";
        }
        catch (Exception e){
            this.showToast("Não foi possível realizar o cadastro. Tente novamente mais tarde!");
        }

        return "";
    }

    private void getCampos(){
        edtNome = (EditText) findViewById(R.id.edtNome);
        edtEmail = (EditText) findViewById(R.id.edtEmail);
        edtSenha = (EditText) findViewById(R.id.edtSenha);
    }

    public void showToast(String mensagem) {
        Toast.makeText(this, mensagem, Toast.LENGTH_SHORT).show();
    }


}

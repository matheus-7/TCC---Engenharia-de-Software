package com.enem.prep.mobile;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_login);

        Button btnEntrar = (Button) findViewById(R.id.btnEntrar);
        Button btnNovaConta = (Button) findViewById(R.id.btnNovaConta);
        Button btnRecuperarSenha = (Button) findViewById(R.id.btnRecuperarSenha);

        btnEntrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentEntrar = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(intentEntrar);
            }
        });

        btnNovaConta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentNovaConta = new Intent(LoginActivity.this, NovaContaActivity.class);
                startActivity(intentNovaConta);
            }
        });

        btnRecuperarSenha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentRecuperarSenha = new Intent(LoginActivity.this, RecuperarSenhaActivity.class);
                startActivity(intentRecuperarSenha);
            }
        });
    }
}

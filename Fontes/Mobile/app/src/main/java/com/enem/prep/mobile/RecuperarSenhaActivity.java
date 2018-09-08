package com.enem.prep.mobile;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class RecuperarSenhaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_recuperar_senha);
    }
}

package com.example.voteaquiapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Button btnirvotar=findViewById(R.id.btnirvotar); //ao clicar no botão de ir votar, após terinserido os dados, vai para a tela e votação
        btnirvotar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent irvotar= new Intent(LoginActivity.this,VotarActivity.class);
                startActivity(irvotar);
            }
        });
    }
}
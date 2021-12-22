package com.example.voteaquiapp;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import java.net.URI;
import java.text.BreakIterator;

public class NewCandidatoActivity extends AppCompatActivity {

    static int PHOTO_PICKER_REQUEST = 1;
    Uri selectedPhotoLocation; //endereço da foto

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_candidato);

        ImageButton imgaddPhotoCand=findViewById(R.id.imbaddPhotoCand); //escolhendo uma imagem
        imgaddPhotoCand.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent photoPickerIntent=new Intent(Intent.ACTION_OPEN_DOCUMENT);
                photoPickerIntent.setType("image/");
                startActivityForResult(photoPickerIntent,PHOTO_PICKER_REQUEST); //retorna a imagem escolhida
            }
        });
        Button btnaddCand=findViewById(R.id.btnAddCand); //verificação de erro para ver se todos os dados foram inseridos
        btnaddCand.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(selectedPhotoLocation ==null){
                    Toast.makeText(NewCandidatoActivity.this, "Você precisa selecionar uma imagem", Toast.LENGTH_LONG).show();
                    return;
                }
                EditText etnomecand = findViewById(R.id.etNomeCand);
                String nome = etnomecand.getText().toString();
                if(nome.isEmpty()){
                    Toast.makeText(NewCandidatoActivity.this, "Você precisa definir um número para o candidato", Toast.LENGTH_LONG).show();
                    return;
                }

                EditText etNumberCand = findViewById(R.id.etNumberCand);
                String number = etNumberCand.getText().toString();
                if(number.isEmpty()){
                    Toast.makeText(NewCandidatoActivity.this, "Você precisa definir um número para o candidato", Toast.LENGTH_LONG).show();
                    return;
                }

                Intent i = new Intent();
                i.setData(selectedPhotoLocation);
                i.putExtra("Nome",nome);
                i.putExtra("Número",number);
                setResult(Activity.RESULT_OK,i);
                finish();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) { //pega a foto escolhida
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==PHOTO_PICKER_REQUEST){
            if(resultCode== Activity.RESULT_OK){
                selectedPhotoLocation = data.getData();
                ImageView imvPhotoPreview=findViewById(R.id.imvPhotoPreview); //coloca a foto escolhida na tela
                imvPhotoPreview.setImageURI(selectedPhotoLocation);
            }
        }
    }
}
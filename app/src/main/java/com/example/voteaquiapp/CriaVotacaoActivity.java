package com.example.voteaquiapp;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class CriaVotacaoActivity extends AppCompatActivity {

    static int NEW_CANDIDATO_REQUEST=1;
    List<MyCandidato> candidatos=new ArrayList<>();
    MyAdapter myAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cria_votacao);

        Button btnAddVote=findViewById(R.id.btnAddVote); //verificação de erro para ver se todos os dados foram inseridos
        btnAddVote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText etTitle= findViewById(R.id.etTitle);
                String title = etTitle.getText().toString();
                if(title.isEmpty()){
                    Toast.makeText(CriaVotacaoActivity.this, "Você precisa definir um título para votação", Toast.LENGTH_LONG).show();
                    return;
                }

                EditText etDetal = findViewById(R.id.etDetal);
                String detalhamento = etDetal.getText().toString();
                if(detalhamento.isEmpty()){
                    Toast.makeText(CriaVotacaoActivity.this, "Você precisa definir um texto de detalhamento para a votação", Toast.LENGTH_LONG).show();
                    return;
                }

                Intent i = new Intent();
                i.putExtra("Title",title);
                i.putExtra("Detalhamento",detalhamento);
                setResult(Activity.RESULT_OK);
                finish();
            }
        });

        MyAdapter myAdapter=new MyAdapter(this,candidatos);

        RecyclerView rvCandidatos = findViewById(R.id.rvCandidatos);
        rvCandidatos.setHasFixedSize(true);//tamanho fixo para os itens da lista

        RecyclerView.LayoutManager layoutManager= new LinearLayoutManager(this);
        rvCandidatos.setLayoutManager(layoutManager);//cada item ficará um em baixo do outro

        rvCandidatos.setAdapter(myAdapter);//esse é o adapter que vai construir os elementos da lista

        DividerItemDecoration dividerItemDecoration=new DividerItemDecoration(rvCandidatos.getContext(), DividerItemDecoration.VERTICAL);
        rvCandidatos.addItemDecoration(dividerItemDecoration);


        FloatingActionButton fabaddCand=findViewById(R.id.fabaddCand);
        fabaddCand.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent addcand = new Intent(CriaVotacaoActivity.this,NewCandidatoActivity.class);
                startActivityForResult(addcand,NEW_CANDIDATO_REQUEST);
            }
        });
        
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==NEW_CANDIDATO_REQUEST){
            if(resultCode== Activity.RESULT_OK){
                Uri selectedPhotoLocation = data.getData();
                String nome=data.getStringExtra("Nome");
                String number=data.getStringExtra("Número");

                MyCandidato candidato = new MyCandidato(); //passa os dados para a classe Mycandidato
                candidato.photo=selectedPhotoLocation;
                candidato.nome=nome;
                candidato.number=number;

                candidatos.add(candidato);

                myAdapter.notifyItemInserted(candidatos.size()-1);
            }
        }
    }
}
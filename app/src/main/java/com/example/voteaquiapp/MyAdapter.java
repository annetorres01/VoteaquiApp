package com.example.voteaquiapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter { //preencher a lista

    List<MyCandidato>candidatos;
    CriaVotacaoActivity criaVotacaoActivity;

    public MyAdapter(CriaVotacaoActivity criaVotacaoActivity,List<MyCandidato>candidatos){
        this.candidatos=candidatos;
        this.criaVotacaoActivity=criaVotacaoActivity;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) { //constrói o layout de cada candidato
        LayoutInflater inflater=LayoutInflater.from(criaVotacaoActivity);
        View v=inflater.inflate(R.layout.new_item_candidato,parent,false);
        return new MyViewHolder(v) {
        };
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) { //preenchendo os elementos de interface
        MyCandidato myCandidato=candidatos.get(position);

        View v =  holder.itemView;

        ImageView imvPhoto=v.findViewById(R.id.imvPhoto);
        imvPhoto.setImageURI(myCandidato.photo);

        TextView tvName= v.findViewById(R.id.tvName);
        tvName.setText(myCandidato.nome);

        TextView tvNumber= v.findViewById(R.id.tvNumber);
        tvNumber.setText(myCandidato.number);


    }

    @Override
    public int getItemCount() {
        return candidatos.size();
    }
}

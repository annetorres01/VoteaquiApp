package com.example.voteaquiapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MyAdapter2 extends RecyclerView.Adapter {

    List<Myvotacao> votacoes;
    EmAndamentoFragment emAndamentoFragment;

    public MyAdapter2(EmAndamentoFragment emAndamentoFragment,List<Myvotacao>votacoes){
        this.votacoes=votacoes;
        this.emAndamentoFragment=emAndamentoFragment;
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(emAndamentoFragment.getContext());
        View v=inflater.inflate(R.layout.item_list,parent,false);
        return new MyViewHolder(v) {
        };
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Myvotacao myvotacao=votacoes.get(position);

        View v =  holder.itemView;

        TextView tvTitle= v.findViewById(R.id.tvTitle);
        tvTitle.setText(myvotacao.title);

        TextView tvDescription= v.findViewById(R.id.tvDescription);
        tvDescription.setText(myvotacao.description);
    }

    @Override
    public int getItemCount() {
        return votacoes.size();
    }
}

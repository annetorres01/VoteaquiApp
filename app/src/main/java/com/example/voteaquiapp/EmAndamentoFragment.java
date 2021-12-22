package com.example.voteaquiapp;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link EmAndamentoFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class EmAndamentoFragment extends Fragment {


    static int NEW_VOTACAO_REQUEST=1;
    List<Myvotacao> votacoes=new ArrayList<>();
    MyAdapter2 myAdapter2;


    //region BARULHO
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public EmAndamentoFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment EmAndamentoFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static EmAndamentoFragment newInstance(String param1, String param2) {
        EmAndamentoFragment fragment = new EmAndamentoFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

    }
    //endregion

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_em_andamento, container, false);

        Button button = v.findViewById(R.id.btncriarvot);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent criarnoovavot=new Intent(getActivity(), CriaVotacaoActivity.class);
                startActivityForResult(criarnoovavot,NEW_VOTACAO_REQUEST);
            }
        });
        MyAdapter2 myAdapter2=new MyAdapter2(this,votacoes);

        RecyclerView rvVotEmanda = v.findViewById(R.id.rvVotEmanda);
        rvVotEmanda.setHasFixedSize(true);//tamanho fixo para os itens da lista

        RecyclerView.LayoutManager layoutManager= new LinearLayoutManager(v.getContext());
        rvVotEmanda.setLayoutManager(layoutManager);//cada item ficará um em baixo do outro

        rvVotEmanda.setAdapter(myAdapter2);//esse é o adapter que vai construir os elementos da lista

        DividerItemDecoration dividerItemDecoration=new DividerItemDecoration(rvVotEmanda.getContext(), DividerItemDecoration.VERTICAL);
        rvVotEmanda.addItemDecoration(dividerItemDecoration);


        return v;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==NEW_VOTACAO_REQUEST){
            if(resultCode== Activity.RESULT_OK){
                String title=data.getStringExtra("Title");
                String detalhamento=data.getStringExtra("Detalhamento");


                Myvotacao votacao = new Myvotacao(); //passa os dados para a classe Mycandidato
                votacao.title=title;
                votacao.description=detalhamento;

                votacoes.add(votacao);

                myAdapter2.notifyItemInserted(votacoes.size()-1);
            }
        }
    }
}
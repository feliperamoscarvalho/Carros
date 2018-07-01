package br.com.livroandroid.carros.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import br.com.livroandroid.carros.R;
import br.com.livroandroid.carros.adapter.CarroAdapter;
import br.com.livroandroid.carros.domain.Carro;
import br.com.livroandroid.carros.domain.CarroService;
import livroandroid.lib.fragment.BaseFragment;

public class CarrosFragment extends BaseFragment {

    private int tipo;
    protected RecyclerView recyclerView;
    private List<Carro> carros;

    //Metodo para instanciar esse fragment pelo tipo
    public static CarrosFragment newInstance(int tipo){
        Bundle args = new Bundle();
        args.putInt("tipo", tipo);
        CarrosFragment f = new CarrosFragment();
        f.setArguments(args);
        return f;
    }

    public CarrosFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null){
            //Obtem o tipo nos argumentos
            this.tipo = getArguments().getInt("tipo");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_carros, container, false);
        recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setHasFixedSize(true);

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        taskCarros();
    }

    private void taskCarros(){
        //Busca os carros pelo tipo
        this.carros = CarroService.getCarros(getContext(), tipo);
        //Atualiza a lista
        recyclerView.setAdapter(new CarroAdapter(getContext(), carros, onClickCarro()));
    }

    private CarroAdapter.CarroOnClickListener onClickCarro(){
        return new CarroAdapter.CarroOnClickListener(){
            @Override
            public void onClickCarro(View view, int idx) {
                Carro c = carros.get(idx);
                Toast.makeText(getContext(), "Carro: " + c.nome, Toast.LENGTH_SHORT).show();
            }
        };
    }
}

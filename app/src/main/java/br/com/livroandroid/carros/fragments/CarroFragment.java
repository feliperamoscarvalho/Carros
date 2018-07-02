package br.com.livroandroid.carros.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import br.com.livroandroid.carros.R;
import br.com.livroandroid.carros.domain.Carro;

public class CarroFragment extends BaseFragment {

    private Carro carro;

    public CarroFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_carro, container, false);
        carro = getArguments().getParcelable("carro");
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //Atualiza a view do fragment com os dados do carro
        setTextString(R.id.tDesc, carro.desc);
        final ImageView imgView = getView().findViewById(R.id.img);
        Picasso.with(getContext()).load(carro.urlFoto).fit().into(imgView);
    }
}

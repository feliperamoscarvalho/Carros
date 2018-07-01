package br.com.livroandroid.carros.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import br.com.livroandroid.carros.R;
import livroandroid.lib.fragment.BaseFragment;

/**
 * A simple {@link Fragment} subclass.
 */
public class SiteLivroFragment extends BaseFragment {


    public SiteLivroFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_site_livro, container, false);
    }

}

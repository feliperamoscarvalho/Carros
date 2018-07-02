package br.com.livroandroid.carros.fragments;


import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import br.com.livroandroid.carros.R;
import br.com.livroandroid.carros.adapter.TabsAdapter;

/**
 * A simple {@link Fragment} subclass.
 */
public class CarrosTabFragment extends BaseFragment {


    public CarrosTabFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_carros_tab, container, false);
        //ViewPager
        ViewPager viewPager = view.findViewById(R.id.viewPager);
        viewPager.setOffscreenPageLimit(2);
        viewPager.setAdapter(new TabsAdapter(getContext(), getChildFragmentManager()));
        //Tabs
        TabLayout tabLayout = view.findViewById(R.id.tabLayout);
        //Cria as tabs com o mesmo adapter utilizado pelo ViewPager
        tabLayout.setupWithViewPager(viewPager);
        int cor = ContextCompat.getColor(getContext(), R.color.white);
        //Cor branca no texto (o fundo azul foi deinifido no layout)
        tabLayout.setTabTextColors(cor, cor);
        return  view;
    }

}

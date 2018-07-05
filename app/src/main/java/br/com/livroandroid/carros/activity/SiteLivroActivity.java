package br.com.livroandroid.carros.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import br.com.livroandroid.carros.R;
import br.com.livroandroid.carros.fragments.CarrosFragment;
import br.com.livroandroid.carros.fragments.SiteLivroFragment;

public class SiteLivroActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_site_livro);

        setUpToolbar();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //Titulo
        getSupportActionBar().setTitle(getString(R.string.sobre));
        //Adiciona o fragment com o mesmo Bundle (args) da Intent
        if(savedInstanceState == null){
            SiteLivroFragment frag = new SiteLivroFragment();
            getSupportFragmentManager().beginTransaction().add(R.id.container, frag).commit();
        }
    }
}

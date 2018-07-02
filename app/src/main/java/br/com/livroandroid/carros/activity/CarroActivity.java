package br.com.livroandroid.carros.activity;

import android.os.Parcel;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import br.com.livroandroid.carros.R;
import br.com.livroandroid.carros.domain.Carro;
import br.com.livroandroid.carros.fragments.CarroFragment;

public class CarroActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_carro);

        //Configura a toolbar como a action bar
        setUpToolbar();
        //Titulo da Toolbar e botao up navigation
        Carro c = getIntent().getParcelableExtra("carro");
        getSupportActionBar().setTitle(c.nome);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        if(savedInstanceState == null){
            //Cria o fragment com o mesmo Bundle (args) da intent
            CarroFragment frag = new CarroFragment();
            frag.setArguments(getIntent().getExtras());
            //Adiciona o fragment ao layout
            getSupportFragmentManager().beginTransaction().add(R.id.CarroFragment, frag).commit();
        }
    }
}

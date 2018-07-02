package br.com.livroandroid.carros.activity;

import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import br.com.livroandroid.carros.R;
import br.com.livroandroid.carros.fragments.CarrosFragment;
import br.com.livroandroid.carros.fragments.CarrosTabFragment;
import br.com.livroandroid.carros.fragments.SiteLivroFragment;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.support.v4.app.Fragment;



public class BaseActivity extends livroandroid.lib.activity.BaseActivity {

    protected DrawerLayout drawerLayout;

    //Configura a Toolbar
    protected void setUpToolbar(){
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        if(toolbar != null){
            setSupportActionBar(toolbar);
        }
    }

    //Configura o nav drawer
    protected void setupNavDrawer(){
        //Drawer Layout
        final ActionBar actionBar = getSupportActionBar();
        //Icone do menu do Nav Drawer
        actionBar.setHomeAsUpIndicator(R.drawable.ic_menu);
        actionBar.setDisplayHomeAsUpEnabled(true);

        drawerLayout = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);

        if(navigationView != null && drawerLayout != null){
            //Atualiza a imagem e os textos do header
            setNavViewValues(navigationView, R.string.nav_drawer_username, R.string.nav_drawer_email, R.mipmap.ic_launcher);

            //Trata o evento de clique no menu
            navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                    //Seleciona a linha
                    menuItem.setChecked(true);
                    //Fecha o menu
                    drawerLayout.closeDrawers();
                    //Trata o evento do menu
                    onNavDrawerItemSelected(menuItem);
                    return true;
                }
            });
        }
    }

    //Atualiza os dados do header do Navigation Viewpublic
    static void setNavViewValues(NavigationView navView, int nome, int email, int img){
        View headerView = navView.getHeaderView(0);
        TextView tNome = headerView.findViewById(R.id.tUserName);
        TextView tEmail = headerView.findViewById(R.id.tUserEmail);
        ImageView imgView = headerView.findViewById(R.id.imgUserPhoto);
        tNome.setText(nome);
        tEmail.setText(email);
        imgView.setImageResource(img);
    }

    //Trata o evento do menu lateral
    private void onNavDrawerItemSelected(MenuItem menuItem){
        switch (menuItem.getItemId()){
            case R.id.nav_item_carros_todos:
                //Mostrar as tres tabs (classicos, esportivos e luxo)
                replaceFragment(new CarrosTabFragment());
                break;
            case R.id.nav_item_carros_classicos:
                toast("Clicou em carros classicos");
                replaceFragment(CarrosFragment.newInstance(R.string.classicos));
                break;
            case R.id.nav_item_carros_esportivos:
                toast("Clicou em carros esportivos");
                replaceFragment(CarrosFragment.newInstance(R.string.esportivos));
                break;
            case R.id.nav_item_carros_luxo:
                toast("Clicou em carros luxo");
                replaceFragment(CarrosFragment.newInstance(R.string.luxo));
                break;
            case R.id.nav_item_site_livro:
                toast("Clicou em site do livro");
                replaceFragment(new SiteLivroFragment());
                break;
            case R.id.nav_item_settings:
                toast("Clicou em configuracoes");
                break;
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                //Trata o clique do botao que abre o menu
                if(drawerLayout != null){
                    openDrawer();
                    return true;
                }
        }
        return super.onOptionsItemSelected(item);
    }

    //Abre o menu lateral
    protected void openDrawer(){
        //Metodo protected, assim as subclasses da BaseActivity podem abrir o menu pelo codigo, se necessario
        if(drawerLayout != null){
            drawerLayout.openDrawer(GravityCompat.START);
        }
    }

    //Fecha o menu lateral
    protected void closerDrawer(){
        //Metodo protected, assim as subclasses da BaseActivity podem fechar o menu pelo codigo, se necessario
        if(drawerLayout != null){
            drawerLayout.closeDrawer(GravityCompat.START);
        }
    }

    //Adiciona o fragment ao centro da tela
    protected void replaceFragment(Fragment frag){
        getSupportFragmentManager().beginTransaction().replace(R.id.container, frag, "TAG").commit();
    }
}

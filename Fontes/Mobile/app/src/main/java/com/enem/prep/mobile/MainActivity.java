package com.enem.prep.mobile;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {

    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle toggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        drawerLayout = (DrawerLayout) findViewById(R.id.drawer);
        toggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.open, R.string.close);

        drawerLayout.addDrawerListener(toggle);
        NavigationView nvMenu = (NavigationView) findViewById(R.id.nvMenu);

        toggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        setupDrawerContent(nvMenu);

        try{
            Fragment fragment = (Fragment) ListaAreasFragment.class.newInstance();

            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.content, fragment).commit();
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (toggle.onOptionsItemSelected(item)){
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void selectItemDrawer(MenuItem menuItem){
        Fragment fragment = null;
        Class fragmentClass = null;
        Bundle bundle = new Bundle();
        SharedPreferences prefs = getSharedPreferences("Preferencias", 0);

        switch (menuItem.getItemId()){
            case R.id.home:
                fragmentClass = ListaAreasFragment.class;
                break;
            case R.id.perfil:
                fragmentClass = PerfilFragment.class;
                break;
            case R.id.estatisticas:
                fragmentClass = EstatisticasFragment.class;
                break;
            case R.id.conquistas:
                fragmentClass = ConquistasFragment.class;
                bundle.putInt("idUsuario", prefs.getInt("idUsuario", 0));

                break;
            case R.id.ranking:
                fragmentClass = RankingFragment.class;
                break;
            case R.id.avaliar:
                fragmentClass = AvaliarFragment.class;
                break;
            case R.id.sair:
                sair();
                return;
            default:
                fragmentClass = ListaAreasFragment.class;
        }

        try{
            fragment = (Fragment) fragmentClass.newInstance();

            fragment.setArguments(bundle);
        }
        catch (Exception e){
            e.printStackTrace();
        }

        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.content, fragment).commit();
        menuItem.setChecked(true);
        setTitle(menuItem.getTitle());
        drawerLayout.closeDrawer(Gravity.START, true);
    }

    private void setupDrawerContent(NavigationView navigationView){
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                selectItemDrawer(menuItem);

                return false;
            }
        });
    }

    private void sair(){
        SharedPreferences prefs = getSharedPreferences("Preferencias", 0);
        SharedPreferences.Editor editor = prefs.edit();

        editor.clear();
        editor.putBoolean("estaLogado", false);
        editor.commit();

        Intent intentSair = new Intent(MainActivity.this, LoginActivity.class);
        startActivity(intentSair);
    }

}

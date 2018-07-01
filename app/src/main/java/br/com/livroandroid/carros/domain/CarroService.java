package br.com.livroandroid.carros.domain;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

public class CarroService {

    public static List<Carro> getCarros(Context context, int tipo){
        String tipoString = context.getString(tipo);
        List<Carro> carros = new ArrayList<Carro>();
        for(int i = 0; i < 20; i++){
            Carro c = new Carro();
            c.nome = "Carro " + tipoString + ": " + i; //Nome dinamico conforme o tipo
            c.desc = "Desc " + i;
            c.urlFoto = "http://livroandroid.com.br/livro/carros/esportivos/Ferrari_FF.png";
            carros.add(c);
        }
        return carros;
    }
}

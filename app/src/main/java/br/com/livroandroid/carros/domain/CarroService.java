package br.com.livroandroid.carros.domain;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import br.com.livroandroid.carros.R;
import livroandroid.lib.utils.FileUtils;

public class CarroService {

    private static final boolean LOG_ON = false;
    private static final String TAG = "CarroService";

    public static List<Carro> getCarros(Context context, int tipo) throws IOException{
        /* Busca Fixa
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
        */

        String json = readFile(context, tipo);
        List<Carro> carros = parserJSON(context, json);
        return carros;
    }

    //Faz a leitura do arquivo que esta na pasta /res/raw
    private static String readFile(Context context, int tipo) throws IOException{
        if(tipo == R.string.classicos){
            return FileUtils.readRawFileString(context, R.raw.carros_classicos, "UTF-8");
        }else if (tipo == R.string.esportivos){
            return FileUtils.readRawFileString(context, R.raw.carros_esportivos, "UTF-8");
        }
        return FileUtils.readRawFileString(context, R.raw.carros_luxo, "UTF-8");
    }

    private static List<Carro> parserJSON(Context context, String json) throws IOException{
        //Informa ao GSON que vamos converter uma lista de carros
        Type listType = new TypeToken<ArrayList<Carro>>(){}.getType();
        //Faz o parser em apenas uma linha e cria a lista
        List<Carro> carros = new Gson().fromJson(json, listType);
        return carros;
    }
}

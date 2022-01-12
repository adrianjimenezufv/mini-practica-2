package com.ufv.Backend;


import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY, getterVisibility = JsonAutoDetect.Visibility.NONE, setterVisibility = JsonAutoDetect.Visibility.NONE)

public class Parser {
    Gson gson=new Gson();
    Info info=new Info();

    /**************************************************************************************************************
     *                                                                                                            *
     *   Nombre del método: Extraer_datos                                                                         *
     *                                                                                                            *
     *   Descripción del metodo:                                                                                  *
     *   Este método se encarga de extraer los datos del json en la dirección indicada en el parametro de entrada *                                                             *
     *                                                                                                            *
     *   Parametros de entrada:                                                                                   *
     *       direccion: dirección del archivo json del cual extraemos los datos                                   *
     *                                                                                                            *
     *   Salida:                                                                                                  *
     *       un objeto info con toda la informacion extraida                                                      *
     *                                                                                                            *
     **************************************************************************************************************/

    public Info Extraer_datos(String direccion){
        try {
            info = gson.fromJson(new FileReader(direccion),Info.class);//creamos el objeto en función de los datos recibidos
        } catch (FileNotFoundException e) {

        }

        return info;
    }
    public int Guardar_datos(String direccion){
        try{
            gson=new GsonBuilder().setPrettyPrinting().create();
            String data= gson.toJson(Controller.info);
            FileWriter archivo=new FileWriter(direccion,false);
            archivo.write(data);
            archivo.close();
            return 1;
        }catch (Exception e){
            System.out.println("KABOOOOOOOMMMMM");
            return -1;
        }
    }
}

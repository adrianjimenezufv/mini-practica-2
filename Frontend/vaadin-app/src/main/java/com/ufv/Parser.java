package com.ufv;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

/**************************************************************************************************************
 *                                                                                                            *
 *   Nombre de la clase: Parser                                                                               *
 *                                                                                                            *
 *   Descripción de la clase:                                                                                 *
 *   Esta clase se encarga de las tareas relacionadas con la extracción de datos del json                     *
 *                                                                                                            *
 *   Guia de variables:                                                                                       *
 *       gson: instancia de la clase Gson con la que extraeremos los datos de los archivos json               *
 *       info: instancia de la clase Info que se devolverá con todos los datos extraidos en la función        *
 *             Extraer_datos                                                                                  *
 *                                                                                                            *
 **************************************************************************************************************/


public class Parser {
    Gson gson = new Gson();
    Info info = new Info();

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

    public void Extraer_datos_usuarios(String datos) {

        Type userListType = new TypeToken<ArrayList<Usuario>>() {
        }.getType();
        info = gson.fromJson(datos, userListType);
        //creamos el objeto en función de los datos recibidos

    }
}

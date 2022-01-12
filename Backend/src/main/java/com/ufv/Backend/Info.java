package com.ufv.Backend;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

import java.util.ArrayList;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY, getterVisibility = JsonAutoDetect.Visibility.NONE, setterVisibility = JsonAutoDetect.Visibility.NONE)

public class Info {

    public ArrayList<Usuario> Usuarios;
    public static int fun=1;

    public static int Funciona(){
        if(fun==1) {
            return 1;
        }
        return 0;
    }
}

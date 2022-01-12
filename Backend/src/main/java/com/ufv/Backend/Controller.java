package com.ufv.Backend;


import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

/*************************************************************************************************************
 *                                                                                                            *
 *   Nombre de la clase: Controller                                                                           *
 *                                                                                                            *
 *   Descripción de la clase                                                                                  *
 *   Esta clase contiene lo relaccionado con el control de la api rest                                        *
 *                                                                                                            *
 *   Guia de variables:                                                                                       *
 *       info: Instancia estática de la clase Info que contiene los array list de objetos a sacar             *
 *                                                                                                            *
 **************************************************************************************************************/
@RestController
public class Controller {
    static Info info = new Info();
    static Parser parser = new Parser();

    /**************************************************************************************************************
     *                                                                                                            *
     *   Nombre del método: Actualizar                                                                            *
     *                                                                                                            *
     *   Descripción del metodo:                                                                                  *
     *   Este método se encarga de actualizar los datos del objeto info, como es estatica se puede acceder sin    *
     *   necesidad de crear una instancia de la clase                                                             *
     *                                                                                                            *
     *   Guia de variables:                                                                                       *
     *       parser: Instancia de la clase Gson que se encarga de parsear los datos del json a objetos java       *
     *                                                                                                            *
     **************************************************************************************************************/

    public static Boolean Actualizar(){
        try {

            info = parser.Extraer_datos("Data/Datos2.JSON");
            return true;
        }
        catch(Exception e){
            return false;
        }

    }

    /**************************************************************************************************************
     *                                                                                                            *
     *   Nombre de los métodos: *nombre_del_array_list*_GET                                                       *
     *                                                                                                            *
     *   Descripción de los metodos:                                                                              *
     *   Estos métodos se encargan de gestionar las peticiones get de la API rest, cada uno de ellos devuelve     *
     *   el array del objeto info que le corresponda.                                                             *
     *                                                                                                            *
     *   Tambien contienen el mapping que les toca en funcion de lo introducido en la url de la API.              *
     *                                                                                                            *
     **************************************************************************************************************/

    //Método POST

    @PostMapping(value = "/users",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)

    public static ResponseEntity<ArrayList<Usuario>> addUser (@RequestBody Usuario NewUser)
    {
        if(NewUser.getId()<0){
            return null;
        }

        info.Usuarios.add(NewUser);
        parser.Guardar_datos("Data/Datos2.JSON");
        return new ResponseEntity<>(info.Usuarios, HttpStatus.CREATED);


    }

    //Método DELETE

    @DeleteMapping("/users/id_usuario={id}")
    public static ResponseEntity<Usuario> usuario_elim(@PathVariable("id") int id){
        if(id<0){
            return null;
        }
        for (Usuario u : info.Usuarios ) {
            if(u.getId()==id){
                info.Usuarios.remove(u);
                Controller.parser.Guardar_datos("Data/Datos2.JSON");
            }
        }

        return ResponseEntity.noContent().build();

    }

    //Método GET
    @GetMapping("/users/id_usuario={id}")
    public static Usuario usuariosid_GET(@PathVariable ("id") int id){
        if(info.Usuarios==null){
            return null;
        }
        for (Usuario u : info.Usuarios ) {
            if(u.getId()==id){
                return u;
            }
        }
        return null;
    }

    @GetMapping("/users")
    public static ArrayList<Usuario> usuarios_GET(){
        return info.Usuarios;
    }
}

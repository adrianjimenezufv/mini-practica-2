package com.ufv;

import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;

import java.util.ArrayList;

/**************************************************************************************************************
 *                                                                                                            *
 *   Nombre de la clase: Info                                                                                *
 *                                                                                                            *
 *   Descripción de la clase                                                                                  *
 *   Esta clase contiene los arrays que contendran los datos extraidos en el json                             *
 *                                                                                                            *
 *   Guia de variables:                                                                                       *
 *       Usuarios: Array que contiene los usuarios extraidos del json                                         *
 *       Prestamos: Array que contiene los prestamos extraidos del json                                       *
 *       Equipos: Array que contiene los equipos extraidos del json                                           *
 *                                                                                                            *
 **************************************************************************************************************/

public class Info {
    public ArrayList<Usuario> Usuarios;
    public int id_U=0;

    public Info(){
        Usuarios = new ArrayList<>();
        for (Usuario u:Requests.devolver_Users()) {
            Usuarios.add(u);
            id_U++;
        }
    }

    //Generadores de grids
    public Grid<Usuario> GenerateGridUsuario()
    {

        //GRID prestamos
        Grid<Usuario> grid = new Grid<>(Usuario.class);
        grid.setItems(Usuarios);
        grid.removeColumnByKey("id");
        grid.setColumns( "nombre", "departamento",
                "telefono_contacto","correo","ubicación");

        return grid;
    }


    //////////////////////////////////////////////
    //                                          //
    //      funciones para los listeners        //
    //                                          //
    //////////////////////////////////////////////
    private VerticalLayout Evento_Borrar_usuario(String Nombre, Button cancelar){
        Usuario deleted=new Usuario();
        int found=0;
        for (Usuario user:Usuarios) {
            if( user.getNombre().compareTo(Nombre)==0){
                deleted=user;
                found=1;
                Requests.eliminar_Users(Long.toString(user.getId()));
            }
        }

        return new VerticalLayout(new Text("Usuario Eliminado: "),deleted.infoLayout(),cancelar);
    }
    private void Evento_añadir_usuario(Usuario usuario){
        int salir =0,taken=0;
        id_U=usuario.getId_int();
        while (salir ==0){
            for (Usuario u:Usuarios) {
                if(u.getId_int()==id_U){
                    taken=1;
                }
            }
            if (taken==0){
                salir=1;
            }
            else{
                id_U++;
                usuario.setId(id_U);
                taken=0;
            }
        }
        Usuarios.add(usuario);


        UI.getCurrent().getPage().reload();
    }

    ///////////////////////////////////////////////
    //                                           //
    //         generadores de modales            //
    //                                           //
    ///////////////////////////////////////////////

    //usuario
    //metodo para añadir usuario
    public Dialog Uadd(){
        Notification n=new Notification("",10000);
        Dialog dialog=new Dialog();
        //Cuadros de texto
        TextField Nombre = new TextField();
        Nombre.setPlaceholder("Nombre");
        Nombre.setLabel("Nombre");

        TextField departamento = new TextField();
        departamento.setPlaceholder("departamento");
        departamento.setLabel("departamento");

        TextField telefono_contacto = new TextField();
        telefono_contacto.setPlaceholder("telefono_contacto");
        telefono_contacto.setLabel("telefono_contacto");

        TextField correo = new TextField();
        correo.setPlaceholder("correo");
        correo.setLabel("correo");

        TextField ubicación = new TextField();
        ubicación.setPlaceholder("ubicación");
        ubicación.setLabel("ubicación");

        //boton
        Button aceptar = new Button();
        aceptar.setText("Aceptar");
        aceptar.addClickListener(buttonClickEvent -> {

                    Evento_añadir_usuario(new Usuario(
                            id_U,
                            Nombre.getValue(),
                            departamento.getValue(),
                            Long.parseLong(telefono_contacto.getValue()),
                            correo.getValue(),
                            ubicación.getValue()
                    ));
                    id_U++;
                    Requests.añadir_Users(Usuarios.get(Usuarios.size()-1));
                    MainView.actualizar();
                    UI.getCurrent().getPage().reload();
                    dialog.close();

                }
        );
        Button cancelar = new Button();
        cancelar.setText("Cancelar");
        cancelar.addClickListener(buttonClickEvent -> {
            dialog.close();
        });
        dialog.add(new VerticalLayout(new Text("AÑADIR USUARIO: "),Nombre,departamento,telefono_contacto,correo,ubicación,new HorizontalLayout(cancelar,aceptar)));
        return dialog;

    }

    //metodo para eliminar usuario



    public Dialog Udelete(){
        Dialog dialog=new Dialog();
        Dialog info=new Dialog();
        int found = 0;
        Usuario deleted = new Usuario();

        //Cuadros de texto
        TextField Nombre = new TextField();
        //botones
        Button aceptar_principal = new Button();
        Button cancelar_principal = new Button();
        Button cancelar_secundario = new Button();

        //settings cuadros de texto
        Nombre.setPlaceholder("Nombre");

        //settings botones
        cancelar_principal.setText("Cancelar");
        cancelar_principal.addClickListener(buttonClickEvent -> {
            dialog.close();
        });

        cancelar_secundario.setText("Cerrar");
        cancelar_secundario.addClickListener(buttonClickEvent -> {
            info.close();
            UI.getCurrent().getPage().reload();
        });

        aceptar_principal.setText("Aceptar");
        aceptar_principal.addClickListener(buttonClickEvent -> {
            info.removeAll();
            info.add(Evento_Borrar_usuario(Nombre.getValue(),cancelar_secundario));
            info.open();
            for (Usuario user:Usuarios) {
                if( user.getNombre().compareTo(Nombre.getValue())==0){
                    Usuarios.remove(user);
                }
            }
        });



        dialog.add(new Text("ELIMINAR USUARIO: "),new VerticalLayout(Nombre,new HorizontalLayout(cancelar_principal,aceptar_principal)));
        return dialog;

    }


    //metodos para modificar usuarios
    public Dialog Usearch(){
        Dialog dialog=new Dialog();
        Dialog showinfo=new Dialog();
        //Cuadros de texto
        TextField Nombre = new TextField();
        Nombre.setPlaceholder("Nombre");



        //botones
        Button modif=new Button();
        modif.setText("Editar");
        Button cancelar = new Button();
        cancelar.setText("Cancelar");
        cancelar.addClickListener(buttonClickEvent -> {
            dialog.close();
        });

        Button cancelar2 = new Button();
        cancelar2.setText("Cancelar");
        cancelar2.addClickListener(buttonClickEvent -> {
            showinfo.close();
        });


        Button aceptar = new Button();
        aceptar.setText("Aceptar");
        aceptar.addClickListener(buttonClickEvent ->
                {
                    showinfo.removeAll();
                    showinfo.add(new Text("Datos: \n"));
                    for (Usuario user:
                            Usuarios) {
                        if( user.getNombre().compareTo(Nombre.getValue())==0){

                            modif.addClickListener(buttonClickEvent2 -> {
                                Requests.eliminar_Users(Integer.toString(user.getId_int()));
                                Umodif(user.getNombre(),user.getDepartamento(),Long.toString(user.getTelefono()),user.getMail(), user.getUbicación()).open();
                                Usuarios.remove(user);
                                MainView.actualizar();
                                showinfo.close();
                                dialog.close();
                            });
                            showinfo.add(new VerticalLayout( user.infoLayout(),
                                    new HorizontalLayout(cancelar2,modif)
                            ));

                        }
                    }

                    showinfo.open();
                    MainView.actualizar();

                }
        );

        dialog.add(new VerticalLayout(new Text("EDITAR USUARIO: "),Nombre,new HorizontalLayout(cancelar,aceptar)));
        return dialog;
    }
    /**/
    public Dialog Umodif(String nom,String dep,String tel,String mail,String ubi){

        Dialog dialog=new Dialog();
        //Cuadros de texto



        TextField Nombre = new TextField();
        Nombre.setValue(nom);
        Nombre.setLabel("Nombre");
        TextField departamento = new TextField();
        departamento.setValue(dep);
        departamento.setLabel("Departamento");
        TextField telefono_contacto = new TextField();
        telefono_contacto.setValue(tel);
        telefono_contacto.setLabel("Telefono de contacto");
        TextField correo = new TextField();
        correo.setValue(mail);
        correo.setLabel("Correo electronico");
        TextField ubicación = new TextField();
        ubicación.setValue(ubi);
        ubicación.setLabel("Ubicación");

        //boton
        Button aceptar = new Button();
        aceptar.setText("guardar cambios");
        aceptar.addClickListener(buttonClickEvent -> {

                    Evento_añadir_usuario(new Usuario(
                            id_U,
                            Nombre.getValue(),
                            departamento.getValue(),
                            Long.parseLong(telefono_contacto.getValue()),
                            correo.getValue(),
                            ubicación.getValue()
                    ));

                    id_U++;
                    Requests.añadir_Users(Usuarios.get(Usuarios.size()-1));
                    MainView.actualizar();
                    UI.getCurrent().getPage().reload();
                    dialog.close();

                }
        );
        Button cancelar = new Button();
        cancelar.setText("Cancelar");
        cancelar.addClickListener(buttonClickEvent -> {
            dialog.close();
        });
        dialog.add(new VerticalLayout(new Text("EDITAR USUARIO: "),Nombre,departamento,telefono_contacto,correo,ubicación,new HorizontalLayout(cancelar,aceptar)));
        return dialog;

    }
}

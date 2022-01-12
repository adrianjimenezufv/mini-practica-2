package com.ufv;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.tabs.Tab;
import com.vaadin.flow.component.tabs.Tabs;
import com.vaadin.flow.server.PWA;
import com.vaadin.flow.router.Route;

/**
 * The main view contains a button and a click listener.
 */
@Route
@PWA(name = "My Application", shortName = "My Application")
public class MainView extends VerticalLayout {
    static Info info=new Info();

    //generación de grids
    static Grid<Usuario> GUsuario;
    public MainView(){
        //creación de elementos a añadir



        actualizar();
        //tabs
        Tabs tabs = new Tabs(new Tab("Usuarios"));
        add(tabs);


        //generar modales
        Dialog Dadduser=info.Uadd();
        Dialog Dmoduser=info.Usearch();
        Dialog Ddeluser=info.Udelete();

        //botones CRUD User
        Button addUser=new Button();
        addUser.setText("Añadir usuario");
        addUser.addClickListener(buttonClickEvent -> {
            Dadduser.open();
        });

        Button modUser=new Button();
        modUser.setText("Editar usuario");
        modUser.addClickListener(buttonClickEvent -> {
            Dmoduser.open();
        });
        Button delUser=new Button();
        delUser.setText("Eliminar usuario");
        delUser.addClickListener(buttonClickEvent -> {
            Ddeluser.open();
        });

        //inicializar layouts
        VerticalLayout LOUsuario= new VerticalLayout(GUsuario,new HorizontalLayout(addUser,modUser,delUser));

        //añadimos layouts
        add(LOUsuario);

        //Vista default
        LOUsuario.setVisible(true);


    }

    public static void actualizar(){
        GUsuario=info.GenerateGridUsuario();
    }

}

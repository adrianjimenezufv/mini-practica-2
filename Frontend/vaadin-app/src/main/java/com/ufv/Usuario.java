package com.ufv;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;

/**************************************************************************************************************
 *                                                                                                            *
 *   Nombre de la clase: Usuario                                                                              *
 *                                                                                                            *
 *   Descripción de la clase:                                                                                 *
 *   Esta clase contiene los datos acerca de los usuarios de la empresa                                       *
 *                                                                                                            *
 *   Guia de variables:                                                                                       *
 *       id_usuario: variable que contiene el id del usuario a prestar                                        *
 *       nombre: variable que contiene el nombre del usuario                                                  *
 *       departamento: variable que contiene la el departamento al que pertenece el usuario                   *
 *       telefono: variable que contiene el telefono que se le va a dar al equipo                                 *
 *       mail: variable que contiene el e-mail del usuario                                                    *
 *       ubicación: variable que contiene la ubicacion del empleado en la empresa                             *
 *                                                                                                            *
 **************************************************************************************************************/

public class Usuario {
    @JsonProperty("id_usuario")
    private long id_usuario=-1;
    @JsonProperty("nombre")
    private String nombre="";
    @JsonProperty("departamento")
    private String departamento="";
    @JsonProperty("telefono")
    private long telefono=-1;
    @JsonProperty("mail")
    private String mail="";
    @JsonProperty("ubicación")
    private String ubicación="";

    public Usuario() {
    }

    public Usuario(long id_usuario, String nombre, String departamento, long telefono, String mail, String ubicación) {
        this.id_usuario = id_usuario;
        this.nombre = nombre;
        this.departamento = departamento;
        this.telefono = telefono;
        this.mail = mail;
        this.ubicación = ubicación;
    }

    public int getId_int(){
        String s=Long.toString(id_usuario);
        return Integer.parseInt(s) ;
    }

    public long getId() {
        return id_usuario;
    }

    public void setId(long id_usuario) {
        this.id_usuario = id_usuario;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDepartamento() {
        return departamento;
    }

    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }

    public long getTelefono() {
        return telefono;
    }

    public void setTelefono(long telefono) {
        this.telefono = telefono;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getUbicación() {
        return ubicación;
    }

    public void setUbicación(String ubicación) {
        this.ubicación = ubicación;
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "id_usuario=" + id_usuario +
                ", nombre='" + nombre + '\'' +
                ", departamento='" + departamento + '\'' +
                ", telefono=" + telefono +
                ", mail='" + mail + '\'' +
                ", ubicación='" + ubicación + '\'' +
                '}';
    }

    public VerticalLayout infoLayout(){
        VerticalLayout salida= new VerticalLayout();

        TextField ID = new TextField();
        TextField nombre = new TextField();
        TextField departamento = new TextField();
        TextField telefono = new TextField();
        TextField mail = new TextField();
        TextField ubicación = new TextField();

        ID.setReadOnly(true);
        nombre.setReadOnly(true);
        departamento.setReadOnly(true);
        telefono.setReadOnly(true);
        mail.setReadOnly(true);
        ubicación.setReadOnly(true);

        ID.setLabel("ID");
        nombre.setLabel("nombre");
        departamento.setLabel("departamento");
        telefono.setLabel("telefono");
        mail.setLabel("Correo");
        ubicación.setLabel("ubicación");

        ID.setValue(Long.toString(id_usuario));
        nombre.setValue(this.nombre);
        departamento.setValue(this.departamento);
        telefono.setValue(Long.toString(this.telefono));
        mail.setValue(this.mail);
        ubicación.setValue(this.ubicación);


        return salida;
    }
}

package com.ufv.Backend;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY, getterVisibility = JsonAutoDetect.Visibility.NONE, setterVisibility = JsonAutoDetect.Visibility.NONE)

public class Usuario {
    @JsonProperty("id_usuario")
    private long id_usuario;
    @JsonProperty("nombre")
    private String nombre;
    @JsonProperty("departamento")
    private String departamento;
    @JsonProperty("telefono")
    private long telefono;
    @JsonProperty("mail")
    private String mail;
    @JsonProperty("ubicación")
    private String ubicación;

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
}

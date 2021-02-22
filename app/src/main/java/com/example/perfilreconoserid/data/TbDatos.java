package com.example.perfilreconoserid.data;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TbDatos {

    private String nombre;
    private String cedula;
    private String direccion;
    private String ciudad;
    private String pais;
    private String celular;
    private String imagen;
    private String latitud;
    private String longitud;
    private Boolean estadoBT;
    private Boolean estadoWF;

    public TbDatos(String nombre,String cedula,String direccion,String ciudad,String pais,String celular,String imagen,String latitud,String longitud,Boolean estadoBT,Boolean estadoWF) {
        this.nombre = nombre;
        this.cedula = cedula;
        this.direccion = direccion;
        this.ciudad = ciudad;
        this.pais = pais;
        this.celular = celular;
        this.imagen = imagen;
        this.latitud = latitud;
        this.longitud = longitud;
        this.estadoBT = estadoBT;
        this.estadoWF = estadoWF;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public String getLatitud() {
        return latitud;
    }

    public void setLatitud(String latitud) {
        this.latitud = latitud;
    }

    public String getLongitud() {
        return longitud;
    }

    public void setLongitud(String longitud) {
        this.longitud = longitud;
    }

    public Boolean getEstadoBT() {
        return estadoBT;
    }

    public void setEstadoBT(Boolean estadoBT) {
        this.estadoBT = estadoBT;
    }

    public Boolean getEstadoWF() {
        return estadoWF;
    }

    public void setEstadoWF(Boolean estadoWF) {
        this.estadoWF = estadoWF;
    }
}

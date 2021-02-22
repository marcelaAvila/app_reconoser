package com.example.perfilreconoserid;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
public class JSONDatos {

    @SerializedName("nombre")
    @Expose
    private String nombre;
    @SerializedName("cedula")
    @Expose
    private String cedula;
    @SerializedName("direccion")
    @Expose
    private String direccion;
    @SerializedName("ciudad")
    @Expose
    private String ciudad;
    @SerializedName("pais")
    @Expose
    private String pais;
    @SerializedName("celular")
    @Expose
    private String celular;
    @SerializedName("imagen")
    @Expose
    private String imagen;
    @SerializedName("latitud")
    @Expose
    private String latitud;
    @SerializedName("longitud")
    @Expose
    private String longitud;
    @SerializedName("estadoBT")
    @Expose
    private String estadoBT;
    @SerializedName("estadoWF")
    @Expose
    private String estadoWF;

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

    public String getEstadoBT() {
        return estadoBT;
    }

    public void setEstadoBT(String estadoBT) {
        this.estadoBT = estadoBT;
    }

    public String getEstadoWF() {
        return estadoWF;
    }

    public void setEstadoWF(String estadoWF) {
        this.estadoWF = estadoWF;
    }
}

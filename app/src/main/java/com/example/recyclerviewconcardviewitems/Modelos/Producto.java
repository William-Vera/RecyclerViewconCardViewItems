package com.example.recyclerviewconcardviewitems.Modelos;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Producto {

    private String nombres;
    private String precio;
    private String descripcion;

    private String [] Images;
    private String website;
    private String urlavatar;

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getPrecio() {
        return precio;
    }

    public void setPrecio(String precio) {
        this.precio = precio;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getUrlavatar() {
        return urlavatar;
    }

    public void setUrlavatar(String urlavatar) {
        this.urlavatar = urlavatar;
    }

    public String[] getImages() {
        return Images;
    }

    public void setImages(String[] images) {
        Images = images;
    }

    public Producto(JSONObject a) throws JSONException {
        nombres = a.getString("title").toString();
        precio = a.getString("price").toString();
        descripcion = a.getString("description").toString();
        website = a.getString("thumbnail").toString() ;
        urlavatar = a.getString("thumbnail").toString() ;
        gImages(a);
    }

    public static ArrayList<Producto> JsonObjectsBuild(JSONArray datos) throws JSONException {
        ArrayList<Producto> Productos = new ArrayList<>();
        for (int i = 0; i < datos.length(); i++) {
            Productos.add(new Producto(datos.getJSONObject(i)));
        }
        return Productos;
    }

    public String[] gImages(JSONObject a) throws JSONException {
        JSONArray json=a.getJSONArray("images");
        this.Images=new String[json.length()];

        for(int i=0;i<json.length();i++){
            this.Images[i]=json.get(i).toString();
        }
        return this.Images;
    }
}

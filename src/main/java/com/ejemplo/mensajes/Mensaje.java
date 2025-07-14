package com.ejemplo.mensajes;

/**
 * Modelo simple para representar un mensaje
 */
public class Mensaje {
    private String contenido;
    private String contenidoEncriptado;
    private boolean encriptado;
    
    public Mensaje() {}
    
    public Mensaje(String contenido) {
        this.contenido = contenido;
        this.encriptado = false;
    }
    
    public String getContenido() {
        return contenido;
    }
    
    public void setContenido(String contenido) {
        this.contenido = contenido;
    }
    
    public String getContenidoEncriptado() {
        return contenidoEncriptado;
    }
    
    public void setContenidoEncriptado(String contenidoEncriptado) {
        this.contenidoEncriptado = contenidoEncriptado;
    }
    
    public boolean isEncriptado() {
        return encriptado;
    }
    
    public void setEncriptado(boolean encriptado) {
        this.encriptado = encriptado;
    }
}

package com.ejemplo.mensajes;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Servlet principal para manejar mensajes seguros
 */
@WebServlet("/mensajes")
public class MensajesServlet extends HttpServlet {
    
    private ServicioEncriptacion servicioEncriptacion;
    private List<Mensaje> mensajes;
    
    @Override
    public void init() throws ServletException {
        servicioEncriptacion = new ServicioEncriptacion();
        mensajes = new ArrayList<>();
        
        // Agregar algunos mensajes de ejemplo
        mensajes.add(new Mensaje("Bienvenido a la aplicación de mensajes seguros"));
        mensajes.add(new Mensaje("Este es un mensaje de prueba"));
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        // Pasar la lista de mensajes a la vista
        request.setAttribute("mensajes", mensajes);
        request.getRequestDispatcher("/WEB-INF/views/mensajes.jsp").forward(request, response);
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        String accion = request.getParameter("accion");
        String contenido = request.getParameter("contenido");
        
        if ("agregar".equals(accion) && contenido != null && !contenido.trim().isEmpty()) {
            Mensaje nuevoMensaje = new Mensaje(contenido.trim());
            mensajes.add(nuevoMensaje);
        } else if ("encriptar".equals(accion)) {
            int indice = Integer.parseInt(request.getParameter("indice"));
            if (indice >= 0 && indice < mensajes.size()) {
                Mensaje mensaje = mensajes.get(indice);
                if (!mensaje.isEncriptado()) {
                    String contenidoEncriptado = servicioEncriptacion.encriptarMensaje(mensaje.getContenido());
                    mensaje.setContenidoEncriptado(contenidoEncriptado);
                    mensaje.setEncriptado(true);
                }
            }
        } else if ("desencriptar".equals(accion)) {
            int indice = Integer.parseInt(request.getParameter("indice"));
            if (indice >= 0 && indice < mensajes.size()) {
                Mensaje mensaje = mensajes.get(indice);
                if (mensaje.isEncriptado()) {
                    mensaje.setEncriptado(false);
                }
            }
        }
        
        // Redirigir de vuelta a la página principal
        response.sendRedirect("mensajes");
    }
}

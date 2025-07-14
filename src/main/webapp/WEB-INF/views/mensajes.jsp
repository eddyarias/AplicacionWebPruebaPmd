<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Aplicación de Mensajes Seguros</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            max-width: 800px;
            margin: 0 auto;
            padding: 20px;
            background-color: #f5f5f5;
        }
        .container {
            background-color: white;
            padding: 30px;
            border-radius: 10px;
            box-shadow: 0 2px 10px rgba(0,0,0,0.1);
        }
        h1 {
            color: #333;
            text-align: center;
            margin-bottom: 30px;
        }
        .form-section {
            background-color: #f8f9fa;
            padding: 20px;
            border-radius: 5px;
            margin-bottom: 30px;
        }
        .form-group {
            margin-bottom: 15px;
        }
        label {
            display: block;
            margin-bottom: 5px;
            font-weight: bold;
        }
        input[type="text"] {
            width: 100%;
            padding: 10px;
            border: 1px solid #ddd;
            border-radius: 4px;
            box-sizing: border-box;
        }
        button {
            background-color: #007bff;
            color: white;
            padding: 10px 15px;
            border: none;
            border-radius: 4px;
            cursor: pointer;
        }
        button:hover {
            background-color: #0056b3;
        }
        .encrypt-btn {
            background-color: #28a745;
        }
        .encrypt-btn:hover {
            background-color: #1e7e34;
        }
        .decrypt-btn {
            background-color: #ffc107;
            color: #212529;
        }
        .decrypt-btn:hover {
            background-color: #e0a800;
        }
        .mensaje {
            background-color: #f8f9fa;
            border: 1px solid #dee2e6;
            border-radius: 5px;
            padding: 15px;
            margin-bottom: 15px;
        }
        .mensaje.encriptado {
            background-color: #d4edda;
            border-color: #c3e6cb;
        }
        .contenido {
            margin-bottom: 10px;
        }
        .contenido-encriptado {
            font-family: monospace;
            background-color: #e9ecef;
            padding: 5px;
            border-radius: 3px;
            word-break: break-all;
            font-size: 12px;
        }
        .acciones {
            display: flex;
            gap: 10px;
        }
        .warning {
            background-color: #fff3cd;
            border: 1px solid #ffeaa7;
            color: #856404;
            padding: 15px;
            border-radius: 5px;
            margin-bottom: 20px;
        }
    </style>
</head>
<body>
    <div class="container">
        <h1>📝 Aplicación de Mensajes Seguros</h1>
        
        <div class="warning">
            <strong>⚠️ Advertencia:</strong> Esta aplicación contiene fallos de seguridad deliberados para pruebas con PMD.
            No usar en producción.
        </div>
        
        <!-- Formulario para agregar nuevo mensaje -->
        <div class="form-section">
            <h2>Agregar Nuevo Mensaje</h2>
            <form method="post" action="mensajes">
                <div class="form-group">
                    <label for="contenido">Mensaje:</label>
                    <input type="text" id="contenido" name="contenido" 
                           placeholder="Escribe tu mensaje aquí..." required>
                </div>
                <button type="submit" name="accion" value="agregar">Agregar Mensaje</button>
            </form>
        </div>
        
        <!-- Lista de mensajes -->
        <div class="mensajes-section">
            <h2>Mensajes (${mensajes.size()})</h2>
            
            <c:if test="${empty mensajes}">
                <p>No hay mensajes. Agrega el primero usando el formulario de arriba.</p>
            </c:if>
            
            <c:forEach var="mensaje" items="${mensajes}" varStatus="status">
                <div class="mensaje ${mensaje.encriptado ? 'encriptado' : ''}">
                    <div class="contenido">
                        <strong>Mensaje ${status.index + 1}:</strong>
                        <c:choose>
                            <c:when test="${mensaje.encriptado}">
                                <span style="color: #28a745;">🔒 [ENCRIPTADO]</span>
                                <div class="contenido-encriptado">${mensaje.contenidoEncriptado}</div>
                            </c:when>
                            <c:otherwise>
                                <span style="color: #6c757d;">🔓 [TEXTO PLANO]</span>
                                <div>${mensaje.contenido}</div>
                            </c:otherwise>
                        </c:choose>
                    </div>
                    
                    <div class="acciones">
                        <c:choose>
                            <c:when test="${mensaje.encriptado}">
                                <form method="post" action="mensajes" style="display: inline;">
                                    <input type="hidden" name="indice" value="${status.index}">
                                    <button type="submit" name="accion" value="desencriptar" class="decrypt-btn">
                                        🔓 Mostrar Original
                                    </button>
                                </form>
                            </c:when>
                            <c:otherwise>
                                <form method="post" action="mensajes" style="display: inline;">
                                    <input type="hidden" name="indice" value="${status.index}">
                                    <button type="submit" name="accion" value="encriptar" class="encrypt-btn">
                                        🔒 Encriptar
                                    </button>
                                </form>
                            </c:otherwise>
                        </c:choose>
                    </div>
                </div>
            </c:forEach>
        </div>
        
        <div style="margin-top: 30px; text-align: center; color: #6c757d; font-size: 12px;">
            <p>Esta aplicación usa encriptación AES con fallos de seguridad para demostración.</p>
        </div>
    </div>
</body>
</html>

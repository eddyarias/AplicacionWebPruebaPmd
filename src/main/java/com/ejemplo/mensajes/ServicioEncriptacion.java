package com.ejemplo.mensajes;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;

/**
 * Servicio de encriptación para mensajes
 * NOTA: Esta clase contiene deliberadamente fallos de seguridad para pruebas PMD
 */
public class ServicioEncriptacion {
    
    // FALLO 1: HardCodedCryptoKey - Clave codificada directamente en el código
    private static final String CLAVE_SECRETA = "MiClaveSecreta123456"; // 16 bytes para AES
    
    // FALLO 2: InsecureCryptoIv - Vector de inicialización hardcodeado
    private static final byte[] IV_FIJO = new byte[] {
        0x00, 0x01, 0x02, 0x03, 0x04, 0x05, 0x06, 0x07,
        0x08, 0x09, 0x0A, 0x0B, 0x0C, 0x0D, 0x0E, 0x0F
    };
    
    /**
     * Encripta un mensaje usando AES
     * @param mensaje El mensaje a encriptar
     * @return El mensaje encriptado en Base64
     */
    public String encriptarMensaje(String mensaje) {
        try {
            // Usar la clave hardcodeada (FALLO DE SEGURIDAD)
            SecretKeySpec claveSpec = new SecretKeySpec(CLAVE_SECRETA.getBytes(), "AES");
            
            // Usar IV fijo (FALLO DE SEGURIDAD)
            IvParameterSpec ivSpec = new IvParameterSpec(IV_FIJO);
            
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, claveSpec, ivSpec);
            
            byte[] mensajeEncriptado = cipher.doFinal(mensaje.getBytes());
            return Base64.getEncoder().encodeToString(mensajeEncriptado);
            
        } catch (Exception e) {
            throw new RuntimeException("Error al encriptar mensaje", e);
        }
    }
    
    /**
     * Desencripta un mensaje
     * @param mensajeEncriptado El mensaje encriptado en Base64
     * @return El mensaje original
     */
    public String desencriptarMensaje(String mensajeEncriptado) {
        try {
            // Usar la clave hardcodeada (FALLO DE SEGURIDAD)
            SecretKeySpec claveSpec = new SecretKeySpec(CLAVE_SECRETA.getBytes(), "AES");
            
            // Usar IV fijo (FALLO DE SEGURIDAD)
            IvParameterSpec ivSpec = new IvParameterSpec(IV_FIJO);
            
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            cipher.init(Cipher.DECRYPT_MODE, claveSpec, ivSpec);
            
            byte[] mensajeBytes = Base64.getDecoder().decode(mensajeEncriptado);
            byte[] mensajeDesencriptado = cipher.doFinal(mensajeBytes);
            
            return new String(mensajeDesencriptado);
            
        } catch (Exception e) {
            throw new RuntimeException("Error al desencriptar mensaje", e);
        }
    }
    
    /**
     * Método adicional para generar IV - CONTIENE FALLO DELIBERADO
     * @return Vector de inicialización hardcodeado (INSEGURO)
     */
    public byte[] generarIvInseguro() {
        // SEGUNDO FALLO: InsecureCryptoIv - IV hardcodeado
        byte[] ivInseguro = "1234567890123456".getBytes(); // Patrón que PMD debe detectar
        return ivInseguro;
    }
}

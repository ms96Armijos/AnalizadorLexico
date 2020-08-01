/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

/**
 *
 * @author never
 */
public class nodoToken {

    public String lexema;
    public String token;
    public nodoToken sig = null;

    public nodoToken(String lexema, String token) {
        this.lexema = lexema;
        this.token = token;
    }
;

}

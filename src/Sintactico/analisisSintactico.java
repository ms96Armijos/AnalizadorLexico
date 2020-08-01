/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Sintactico;

/**
 *
 * @author never
 */
public class analisisSintactico {
    String token;
    String PUNTOCOMA="";
    void programa(){
      //declaraciones();
      //sentencias();
    }
   /* void declaraciones(){
        do {            
          decl();
            while (token != PUNTOCOMA) {                
                System.out.println("Error sintactico");
                token = "";
            }
            token = "";
        } while (token == "ENTERO");
    }*/

   /* public void declaraciones() {
        
        do {
            decl();
            System.out.println("declaraciones->FinLinea " + p.token + " " + p.lexema);
            while (p.token != "FIN_LINEA") {
                p = p.sig;
                System.out.println("Otra variable en declaraciones "+p.token+" "+p.lexema);
            }
            p = p.sig;
            System.out.println("HE finalizado con punto y coma"+p.token + " "+ p.lexema);
        } while (p.token == "NUMERO");
    }

    public void decl() {
        System.out.println("decl->NUMERO " + p.token + " " + p.lexema);
        if (p.token == "PALABRARESERVADA") {
            p = p.sig;
            System.out.println("decl->LETRA " + p.token + " " + p.lexema);
            if (p.token == "LETRA") {
                p = p.sig;
            } else {
                System.out.println("se esperaba una letra");
            };
        } else {
            System.out.println("se esperaba un numero");
        }
    }

    void sentencias() {
        System.out.println("Pasare por sentencias con "+p.token+" "+ p.lexema);
        do {
            if (p.token != null) {
                Asignacion();
                System.out.println("SENTENCIA->FinLinea " + p.token + " " + p.lexema);
                while (p.token != "FIN_LINEA") {
                    if(p.sig != null){
                        p = p.sig;
                    }
                }
                p = p.sig;
            }
        } while (p.token == "LETRA");
    }

    public void Asignacion() {
        System.out.println("Asignacion->IDEN " + p.token + " " + p.lexema);
        if (p.token == "LETRA") {
            p = p.sig;
            System.out.println("Asignacion->IGUAL " + p.token + " " + p.lexema);
            if (p.token == "ASIGNACION") {
                p = p.sig;
                System.out.println("Voy a expresion con "+p.token + " " + p.lexema);
                Expresion();
            } else {
                System.out.println("Error, se esperaba un signo igual");
            }
        } else {
            erroresSintacticos.add("se esperaba un signo igual y se recibo un:"+p.lexema);
            System.out.println("Error, se esperaba un identificador");
        }
    }

    public void Expresion() {
        if (p.token != "FIN_LINEA") {
            System.out.println("Expresion->Letra | Numero " + p.token + " " + p.lexema);
            if (p.token == "LETRA" || p.token == "NUMERO") {
                p = p.sig;
                System.out.println("Expresion->SIGNO " + p.token + " " + p.lexema);
                if (p.token == "SUMA" || p.token == "RESTA") {
                    p = p.sig;
                    Expresion();
                } else {
                    System.out.println("Error, se esperaba un signo luego del identificador o numero");
                    
                }
            } else {
                System.out.println("Error, se esperaba un identificador o un numero");
            }
        }

    }*/

    /*public void Declaraciones() {
     System.out.println("Declaraciones->Reserv: " + p.token + " " + p.lexema);
     if (p.token.equals("PALABRARESERVADA")) {
     p = p.sig;
     System.out.println("Declaraciones->IDEN: " + p.token + " " + p.lexema);
     if (p.token.equals("LETRA")) {
     p = p.sig;
     System.out.println("Declaraciones->L: " + p.token + " " + p.lexema);
     if (p.token.equals("FIN_LINEA")) {
     p = p.sig;
     } else {
     System.out.println("Se espera un punto y coma");
     }
     } else {
     System.out.println("Se espera un identificador (declaracion)");
     }
     } else {
     System.out.println("Se espera una reservada");
     }
     }*/
}

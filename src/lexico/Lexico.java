package lexico;

import modelo.Token;
import java.util.ArrayList;
import java.util.LinkedList;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author never
 */
public class Lexico {

    DefaultTableModel modelTablaTokens;
    DefaultTableModel modelTablaDeSimbolos;
    DefaultTableModel modelTablaErrores;

    private LinkedList<Token> Salida;
    private ArrayList<String> Reservadas;

    private int estado;
    private String lexema;
    boolean palabraReservada = false;
    int contadorLineas = 1;

    Object[] filaTablaDeSimbolos = new Object[3];
    Object[] filaTablaDeErrores = new Object[3];

    String presentandoSecuencias = "";

    char caracter;

    public LinkedList<Token> analisisLexico(String codigoLeido, JTable tablaDeSimbolos, JTable tablaErrores) {

        modelTablaDeSimbolos = (DefaultTableModel) tablaDeSimbolos.getModel();
        modelTablaErrores = (DefaultTableModel) tablaErrores.getModel();

        //codigoLeido = codigoLeido + "°";
        codigoLeido = codigoLeido;
        Salida = new LinkedList<Token>();
        Reservadas = new ArrayList<String>();

        Reservadas.add("public");
        Reservadas.add("private");
        Reservadas.add("static");
        Reservadas.add("void");
        Reservadas.add("main");
        Reservadas.add("class");
        Reservadas.add("int");
        Reservadas.add("String");
        Reservadas.add("double");
        Reservadas.add("if");
        Reservadas.add("else");
        Reservadas.add("System");
        Reservadas.add("out");
        Reservadas.add("print");
        Reservadas.add("println");
        Reservadas.add("new");
        Reservadas.add("boolean");
        Reservadas.add("true");
        Reservadas.add("false");
        Reservadas.add("null");
        Reservadas.add("package");

        estado = 0;
        lexema = "";

        modelTablaDeSimbolos.setRowCount(0);
        modelTablaErrores.setRowCount(0);
        for (int i = 0; i <= codigoLeido.length() - 1; i++) {

            caracter = codigoLeido.charAt(i);
            if (caracter != '\n') {
                filaTablaDeSimbolos[0] = caracter;
                filaTablaDeSimbolos[1] = lexema + caracter;
                filaTablaDeSimbolos[2] = "leer otro caracter";
            }
            if (caracter == ' ') {
                filaTablaDeSimbolos[0] = "espacio";
                //i++;
            }

            switch (estado) {

                case 0:

                    if ((caracter >= 48 && caracter <= 57)) {//si viene un numero
                        estado = 22;//me voy al estado 22

                        lexema = "" + caracter;//llevo lo q tenga la cadena
                        //System.out.println("Lexema despues del digito, estado 0: " + auxLexema);
                        presentandoSecuencias += lexema + "\n";
                        //auxLex = "" + caracter;

                    } else if (caracter == ' ') {
                        lexema.replace(" ", "");
                    } else if ((caracter >= 65 && caracter <= 90)//mayusculas
                            || (caracter >= 97 && caracter <= 122)) {//minusculas
                        estado = 25;
                        lexema += caracter;
                        presentandoSecuencias += lexema + "\n";
                        filaTablaDeSimbolos[2] = "leer  otro caracter";
                    } else if (caracter == '+') {
                        estado = 8;
                        lexema += caracter;
                        presentandoSecuencias += lexema + "\n";
                    } else if (caracter == '-') {
                        estado = 10;
                        lexema += caracter;
                        presentandoSecuencias += lexema + "\n";
                    } else if (caracter == '*') {
                        lexema += caracter;
                        presentandoSecuencias += lexema + "\n";
                        filaTablaDeSimbolos[2] = lexema;
                        agregarToken("MULTIPLICACION");

                    } else if (caracter == '/') {
                        lexema += caracter;
                        presentandoSecuencias += lexema + "\n";
                        filaTablaDeSimbolos[2] = lexema;
                        agregarToken("DIVISION");

                    } else if (caracter == '(') {
                        lexema += caracter;
                        presentandoSecuencias += lexema + "\n";
                        filaTablaDeSimbolos[2] = lexema;
                        agregarToken("PARENTESIS_IZQ");

                    } else if (caracter == ')') {
                        lexema += caracter;
                        presentandoSecuencias += lexema + "\n";
                        filaTablaDeSimbolos[2] = lexema;
                        agregarToken("PARENTESIS_DER");

                    } else if (caracter == '{') {
                        lexema += caracter;
                        presentandoSecuencias += lexema + "\n";
                        filaTablaDeSimbolos[2] = lexema;
                        agregarToken("LLAVE_IZQ");

                    } else if (caracter == '}') {
                        lexema += caracter;
                        presentandoSecuencias += lexema + "\n";
                        filaTablaDeSimbolos[2] = lexema;
                        agregarToken("LLAVE_DER");

                    } else if (caracter == '[') {
                        lexema += caracter;
                        presentandoSecuencias += lexema + "\n";
                        filaTablaDeSimbolos[2] = lexema;
                        agregarToken("CORCHETE_IZQ");

                    } else if (caracter == ']') {
                        lexema += caracter;
                        presentandoSecuencias += lexema + "\n";
                        filaTablaDeSimbolos[2] = lexema;
                        agregarToken("CORCHETE_DER");

                    } else if (caracter == ';') {
                        //filaBuffer[2] = "leer otro caracter";
                        lexema += caracter;
                        presentandoSecuencias += lexema + "\n";
                        filaTablaDeSimbolos[2] = lexema;
                        agregarToken("FIN_LINEA");

                    } else if (caracter == '"') {
                        lexema += caracter;
                        presentandoSecuencias += lexema + "\n";
                        filaTablaDeSimbolos[2] = lexema;
                        agregarToken("COMILLA_DOBLE");

                    } else if (caracter == '\'') {
                        lexema += caracter;
                        presentandoSecuencias += lexema + "\n";
                        filaTablaDeSimbolos[2] = lexema;
                        agregarToken("COMILLA_SIMPLE");

                    } else if (caracter == '_') {
                        lexema += caracter;
                        presentandoSecuencias += lexema + "\n";
                        filaTablaDeSimbolos[2] = lexema;
                        agregarToken("SUBRAYADO");

                    } else if (caracter == '=') {
                        lexema += caracter;
                        presentandoSecuencias += lexema + "\n";
                        filaTablaDeSimbolos[2] = lexema;
                        agregarToken("ASIGNACION");

                    } else if (caracter == '.') {
                        lexema += caracter;
                        presentandoSecuencias += lexema + "\n";
                        filaTablaDeSimbolos[2] = lexema;
                        agregarToken("PUNTO");

                    } else if (caracter == '$') {
                        lexema += caracter;
                        presentandoSecuencias += lexema + "\n";
                        filaTablaDeSimbolos[2] = lexema;
                        agregarToken("SIGNO_DOLAR");

                    } else {
                        if (i == codigoLeido.length() - 1) {
                            System.out.println("Fin del programa");
                            filaTablaDeSimbolos[2] = "Fin del programa";
                            System.out.println("Resultado: \n" + presentandoSecuencias);
                        } else {

                            if (caracter == '\n') {
                                lexema.replace("\n", "");
                                contadorLineas++;
                            } else {
                                /*if (caracter == ' ') {
                                 filaErrores[0] = "Error Léxico";
                                 filaErrores[1] = "espacio";
                                 filaErrores[2] = contadorLineas;
                                 }*/
                                if (caracter != ' ') {
                                    filaTablaDeErrores[0] = "Error Léxico";
                                    filaTablaDeErrores[1] = caracter;
                                    filaTablaDeErrores[2] = contadorLineas;
                                }
                                System.out.println("Error lexico: " + caracter);
                                estado = 0;
                                modelTablaErrores.addRow(filaTablaDeErrores);
                                tablaErrores.setModel(modelTablaErrores);
                            }
                        }
                    }
                    break;

                case 22:
                    if ((caracter >= 48 && caracter <= 57)) {
                        //System.out.println("Lexema llegando al estado 22: " + auxLexema);
                        //presentandoSecuencias += auxLexema+"\n";
                        estado = 22;

                        lexema += caracter;
                        //System.out.println("Lexema (ciclo) del estado 22: " + auxLexema);
                        presentandoSecuencias += lexema + "\n";
                    } else {
                        agregarToken("NUMERO");
                        i--;
                    }
                    break;
                case 25:
                    if ((caracter >= 65 && caracter <= 90)//mayusculas
                            || (caracter >= 97 && caracter <= 122)) {//minusculas
                        estado = 25;
                        lexema += caracter;
                        presentandoSecuencias += lexema + "\n";

                    } else {

                        if (verificarReservadas(lexema) == true) {
                            System.out.println("Palabra Reservada: " + lexema);
                            i--;

                            agregarToken("PALABRARESERVADA");
                            //filaBuffer[0] = "espacio";

                        } else {
                            System.out.println("Identificador: " + lexema);

                            agregarToken("LETRA");
                            i--;

                        }
                    }
                    break;

                case 10:
                    if (caracter == '-') {
                        estado = 18;
                        lexema += caracter;
                        presentandoSecuencias += lexema + "\n";
                    } else if (caracter != '-') {
                        presentandoSecuencias += lexema + "\n";
                        agregarToken("RESTA");
                        i--;
                        //estado=17;
                        // auxLex += caracter;

                    } else {
                        System.out.println("Error lexico: " + caracter);
                        estado = 0;
                    }
                    break;
                case 18:
                    agregarToken("DECREMENTO");
                    i--;
                    break;

                //SUMA E INCREMENTO
                case 8:
                    if (caracter == '+') {
                        estado = 16;
                        lexema += caracter;
                        presentandoSecuencias += lexema + "\n";
                    } else if (caracter != '+') {
                        presentandoSecuencias += lexema + "\n";
                        agregarToken("SUMA");
                        i--;
                        //estado=17;
                        // auxLex += caracter;
                    } else {
                        System.out.println("Error lexico: " + caracter);
                        estado = 0;
                    }
                    break;
                case 16:
                    agregarToken("INCREMENTO");
                    i--;
                    break;
            }
            modelTablaDeSimbolos.addRow(filaTablaDeSimbolos);
            tablaDeSimbolos.setModel(modelTablaDeSimbolos);
        }
        return Salida;
    }

    public void imprimirListaDeTokens(LinkedList<Token> listaToken, JTable tablaTokens) {

        modelTablaTokens = (DefaultTableModel) tablaTokens.getModel();
        Object[] fila = new Object[3];

        modelTablaTokens.setRowCount(0);

        for (int i = 0; i < listaToken.size(); i++) {
            //System.out.println("" + item.determinarTipoDelToken() + "-->" + item.obtenerValorDelToken());
            fila[0] = (i + 1);
            fila[2] = listaToken.get(i).getTipoDeToken();
            fila[1] = listaToken.get(i).getValorDelToken();
            modelTablaTokens.addRow(fila);
            tablaTokens.setModel(modelTablaTokens);
        }
    }

    public void agregarToken(String tipoToken) {

        presentandoSecuencias += "enviar token y limpiar buffer" + "\n";

        Salida.add(new Token(tipoToken, lexema));
        filaTablaDeSimbolos[2] = "enviar token y limpiar buffer";
        lexema = "";
        estado = 0;
    }

    public boolean verificarReservadas(String palabra) {
        //String confirmacion = "";
        for (int i = 0; i < Reservadas.size(); i++) {
            if (palabra.equals(Reservadas.get(i))) {
                //confirmacion = "Reservada: " + palabra;
                //System.out.println("PAlabra Reservada: "+Reservadas.get(i)+" en la posicion: "+(i+1));
                palabraReservada = true;
                break;
            } else {
                palabraReservada = false;
                //confirmacion = "identificador: " + palabra;
            }
        }
        return palabraReservada;
    }

}

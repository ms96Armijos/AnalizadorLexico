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

    private LinkedList<Token> salidaDeTokens;
    private LinkedList<String> palabrasReservadas;

    private int estado;
    private String lexema;
    boolean palabraReservada = false;
    int contadorLineas = 1;

    Object[] filaTablaDeSimbolos = new Object[3];
    Object[] filaTablaDeErrores = new Object[3];
    char caracter;

    public LinkedList<Token> analisisLexico(String codigoLeido, JTable tablaDeSimbolos, JTable tablaErrores) {

        //INICIALIZANDO LAS VARIABLES DE LOS MODELOS DE LAS TABLAS
        modelTablaDeSimbolos = (DefaultTableModel) tablaDeSimbolos.getModel();
        modelTablaErrores = (DefaultTableModel) tablaErrores.getModel();

        //LE ASIGNO UN SIMBOLO COMO REFERENCIA AL FINAL DE MI CADENA QUE HE
        //RECIBIDO DESDE EL JTEXTAREA
        codigoLeido = codigoLeido + "°";

        salidaDeTokens = new LinkedList<Token>();

        palabrasReservadas = new LinkedList<>();
        palabrasReservadas.add("public");
        palabrasReservadas.add("private");
        palabrasReservadas.add("static");
        palabrasReservadas.add("void");
        palabrasReservadas.add("main");
        palabrasReservadas.add("class");
        palabrasReservadas.add("int");
        palabrasReservadas.add("String");
        palabrasReservadas.add("double");
        palabrasReservadas.add("if");
        palabrasReservadas.add("else");
        palabrasReservadas.add("System");
        palabrasReservadas.add("out");
        palabrasReservadas.add("print");
        palabrasReservadas.add("println");
        palabrasReservadas.add("new");
        palabrasReservadas.add("do");
        palabrasReservadas.add("while");
        palabrasReservadas.add("for");
        palabrasReservadas.add("null");
        palabrasReservadas.add("package");

        estado = 0;
        lexema = "";

        //AL EJECUTAR ESTA FUNCIÓN, SE LIMPIEN LAS TABLAS DEL PROGRAMA
        modelTablaDeSimbolos.setRowCount(0);
        modelTablaErrores.setRowCount(0);

        //INICIALIZO EL FOR PARA LEER CARACTER A CARACTER TODA LA CADENA INGRESADA
        for (int i = 0; i < codigoLeido.length(); i++) {

            //OBTENGO EL CARACTER A MEDIDA QUE AUMENTA EL ÍNDICE i
            caracter = codigoLeido.charAt(i);

            //CONTROLO QUE NO ME LLEGUE UN ESPACIO, SALTO DE LINEA, O EL SÍMBOLO
            //DEFINIDO AL FINAL DE LA CADENA.
            if (caracter != '\n' && caracter != '°' && caracter != ' ') {
                //ASIGNO LOS VALORES EN LA TABLA DE SÍMBOLOS
                filaTablaDeSimbolos[0] = caracter;
                filaTablaDeSimbolos[1] = lexema + caracter;
                filaTablaDeSimbolos[2] = "leer otro caracter";
            } else {
                //SI NO ME LLEGAN LOS SÍMBOLOS DE LA CONDICIÓN, ENTONCES NO LE 
                //ASIGNO VALORES A LA TABLA DE SÍMBOLOS.
                filaTablaDeSimbolos[0] = "";
                filaTablaDeSimbolos[1] = "";
                filaTablaDeSimbolos[2] = "NO";
            }

            switch (estado) {
                case 0:
                    //SI NO LLEGA UN ESPACIO, ENTONCES PUEDO CONTINUAR, CASO CONTRARIO
                    //NO HAGO NADA (ESTO ME SIRVE PARA QUE NO ME SALGA UN ESPACIO EN BLANCO
                    //EN LA TABLA DE ERRORES)
                    if (caracter != ' ') {

                        //SI NO LLEGA UN SALTO DE LÍNEA, ENTONCES CONTINÚO, CASO CONTRARIO
                        //INCREMENTO MI VARIABLE contadorLineas EN 1. 
                        if (caracter != '\n') {

                            if ((caracter >= 48 && caracter <= 57)) {//SI VIENE UN NUMERO
                                estado = 22;//MI ESTADO CAMBIA A 22
                                lexema = "" + caracter;//CONCATENO A MI LEXEMA EL CARACTER

                            } else if ((caracter >= 65 && caracter <= 90)//LETRA MAYÚSCULA
                                    || (caracter >= 97 && caracter <= 122)) {//LETRA MINÚSCULA
                                estado = 25;
                                lexema += caracter;

                            } else if (caracter == '+') {
                                estado = 8;
                                lexema += caracter;

                            } else if (caracter == '-') {
                                estado = 10;
                                lexema += caracter;

                            } else if (caracter == '*') {
                                lexema += caracter;
                                agregarToken("MULTIPLICACION");

                            } else if (caracter == '/') {
                                lexema += caracter;
                                agregarToken("DIVISION");

                            } else if (caracter == '(') {
                                lexema += caracter;
                                agregarToken("PARENTESIS_IZQ");

                            } else if (caracter == ')') {
                                lexema += caracter;
                                agregarToken("PARENTESIS_DER");

                            } else if (caracter == '{') {
                                lexema += caracter;
                                agregarToken("LLAVE_IZQ");

                            } else if (caracter == '}') {
                                lexema += caracter;
                                agregarToken("LLAVE_DER");

                            } else if (caracter == '[') {
                                lexema += caracter;
                                agregarToken("CORCHETE_IZQ");

                            } else if (caracter == ']') {
                                lexema += caracter;
                                agregarToken("CORCHETE_DER");

                            } else if (caracter == ';') {
                                lexema += caracter;
                                agregarToken("FIN_LINEA");

                            } else if (caracter == '"') {
                                lexema += caracter;
                                agregarToken("COMILLA_DOBLE");

                            } else if (caracter == '\'') {
                                lexema += caracter;
                                agregarToken("COMILLA_SIMPLE");

                            } else if (caracter == '_') {
                                lexema += caracter;
                                agregarToken("SUBRAYADO");

                            } else if (caracter == '=') {
                                lexema += caracter;
                                agregarToken("ASIGNACION");

                            } else if (caracter == '.') {
                                lexema += caracter;
                                agregarToken("PUNTO");

                            } else if (caracter == '$') {
                                lexema += caracter;
                                agregarToken("SIGNO_DOLAR");

                            } else {
                                //SI ME LLEGA EL CARACTER ° Y EL ÍNDICE ES IGUAL 
                                //A LA CADENA DE CARACTERES MENOS 1,
                                //ESTO POR LO QUE ASIGNÉ UNA LETRA COMO REFERENCIA 
                                //AL FINAL DE MI CADENA, EN ESTE CASO FINALIZA EL PROCESO
                                //DE ANÁLISIS LÉXICO
                                if (caracter == '°' && i == codigoLeido.length() - 1) {

                                    //LE ASIGNO VALORES A LA TABLA DE SÍMBOLOS
                                    filaTablaDeSimbolos[0] = "Fin del programa";
                                    filaTablaDeSimbolos[2] = "enviar token y finalizar proceso de análisis";

                                } else {
                                    //SI ME LLEGA UN CARACTER QUE NO ESTÁ EN MI 
                                    //LENGUAJE, ENTONCES ES UN ERROR Y LO GUARDO 
                                    //EN LA TABLA DE ERRORES, PERO NO LO CARGO AL 
                                    //BÚFFER DE LA TABLA DE SÍMBOLOS
                                    filaTablaDeErrores[0] = "Error Léxico";
                                    filaTablaDeErrores[1] = caracter;
                                    filaTablaDeSimbolos[1] = "";
                                    filaTablaDeErrores[2] = contadorLineas;

                                    estado = 0;
                                    modelTablaErrores.addRow(filaTablaDeErrores);
                                    tablaErrores.setModel(modelTablaErrores);
                                }
                            }
                        } else {
                            contadorLineas++;
                        }
                    }

                    break;

                case 22:

                    if (caracter != ' ' && caracter != '\n') {
                        if ((caracter >= 48 && caracter <= 57)) {//SI LLEGA UN NÚMERO
                            estado = 22;//EL ESTADO SIGUE EN 22
                            lexema += caracter;//SE VA CONCATENANDO EL CARACTER AL LEXEMA
                        } else {
                            agregarToken("NUMERO");//SI NO ES UN NÚMERO, AGREGO EL TOKEN
                            i--;//REDUZCO EL ÍNDICE EN 1 PARA QUE ME RECONOZCA EL CARACTER DE NUEVO
                        }
                    }
                    break;
                case 25:

                    if ((caracter >= 65 && caracter <= 90)//SI LLEGA UNA LETRA MAYÚSCULA
                            || (caracter >= 97 && caracter <= 122)) {//SI LLEGA UNA LETRA MINÚSCULA
                        estado = 25;//MI ESTADO SEGUIRÁ SIENDO 25
                        lexema += caracter;//CONCATENO A MI LEXEMA EL CARACTER

                    } else {
                        //SI NO ME LLEGA UNA LETRA
                        //VERIFICO SI EL LEXEMA ES UNA PALABRA RESERVADA,
                        //PARA LO CUAL LLAMO A UN MÉTODO QUE HACE ESA COMPROBACIÓN,
                        //Y ME DEVUELVE TRUE SI ESTÁ DENTRO DE MIS PALABRAS RESERVADAS DEFINIDAS
                        if (verificarReservadas(lexema) == true) {
                            agregarToken("PALABRARESERVADA");//AGREGO EL TOKEN
                            i--;//REDUZCO EL ÍNDICE EN 1 PARA QUE ME RECONOZCA EL CARACTER DE NUEVO

                        } else {
                            //SI DEVUELVE FALSO, ENTONCES LE ENVÍO EL TOKEN COMO LETRA
                            agregarToken("LETRA");//AGREGO EL TOKEN
                            i--;//REDUZCO EL ÍNDICE EN 1 PARA QUE ME RECONOZCA EL CARACTER DE NUEVO
                        }
                    }
                    break;

                //RESTA Y DECREMENTO
                case 10:
                    //SI ME LLEGA OTRO SIGNO MENOS
                    if (caracter == '-') {
                        lexema += caracter;//CONCATENO A MI LEXEMA
                        agregarToken("DECREMENTO");//ENVÍO EL TOKEN COMO DECREMENTO
                    } else if (caracter != '-') {//SI NO ME LLEGA UN SIGNO MENOS
                        agregarToken("RESTA");//ENVÍO EL TOKEN COMO RESTA
                        i--;//REDUZCO EL ÍNDICE EN 1 PARA QUE ME RECONOZCA EL CARACTER DE NUEVO

                    }
                    break;

                //SUMA E INCREMENTO
                case 8:
                    //SI ME LLEGA OTRO SIGNO MAS
                    if (caracter == '+') {
                        lexema += caracter;//CONCATENO A MI LEXEMA
                        agregarToken("INCREMENTO");//ENVÍO EL TOKEN COMO INCREMENTO
                    } else if (caracter != '+') {//SI NO ME LLEGA UN SIGNO MAS
                        agregarToken("SUMA");//ENVÍO EL TOKEN COMO SUMA
                        i--;//REDUZCO EL ÍNDICE EN 1 PARA QUE ME RECONOZCA EL CARACTER DE NUEVO
                    }
                    break;
            }
            eliminarNOdeTablaDeSimbolos();
            modelTablaDeSimbolos.addRow(filaTablaDeSimbolos);
            tablaDeSimbolos.setModel(modelTablaDeSimbolos);
        }
        return salidaDeTokens;
    }

    public void eliminarNOdeTablaDeSimbolos() {
        for (int j = 0; j < modelTablaDeSimbolos.getRowCount(); j++) {
            if (modelTablaDeSimbolos.getValueAt(j, 2).equals("NO")) {
                modelTablaDeSimbolos.removeRow(j);
            }

        }
    }

    public void imprimirListaDeTokens(LinkedList<Token> listaToken, JTable tablaTokens) {

        modelTablaTokens = (DefaultTableModel) tablaTokens.getModel();
        Object[] fila = new Object[3];

        modelTablaTokens.setRowCount(0);

        for (int i = 0; i < listaToken.size(); i++) {
            fila[0] = (i + 1);
            fila[2] = listaToken.get(i).getTipoDeToken();
            fila[1] = listaToken.get(i).getValorDelToken();
            //AGREGO LAS FILAS AL MODELO 
            modelTablaTokens.addRow(fila);
            //AGREGO A LA TABLA EL MODELO DE LA TABLA DE TOKENS
            tablaTokens.setModel(modelTablaTokens);
        }
    }

    public void agregarToken(String tipoToken) {
        filaTablaDeSimbolos[1] = lexema;
        filaTablaDeSimbolos[2] = "enviar token y limpiar búffer";
        salidaDeTokens.add(new Token(tipoToken, lexema));
        lexema = "";
        estado = 0;
    }

    public boolean verificarReservadas(String palabra) {

        for (int i = 0; i < palabrasReservadas.size(); i++) {
            if (palabra.equals(palabrasReservadas.get(i))) {
                palabraReservada = true;
                break;
            } else {
                palabraReservada = false;
            }
        }
        return palabraReservada;
    }

}

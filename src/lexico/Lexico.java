package lexico;

import modelo.Token;
import java.util.ArrayList;
import java.util.LinkedList;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.table.DefaultTableModel;
import modelo.nodoToken;

/**
 *
 * @author never
 */
public class Lexico {

    DefaultTableModel modelTablaTokens;
    DefaultTableModel modelTablaDeSimbolos;
    DefaultTableModel modelTablaErrores;

    LinkedList<String> erroresSintacticos;

    LinkedList<Token> salidaDeTokens;
    LinkedList<String> palabrasReservadas;

    LinkedList<String> reglasSintacticasAplicadas;

    int estado;
    String lexema;
    boolean palabraReservada = false;
    int contadorLineas = 1;
    int contadorErroresSintaxis = 1;
    String reglasUtilizadas = "";
    String TempTipoToken;

    int contadorDeP_C = 0;

    nodoToken cabeza = null, p;

    Object[] filaTablaDeSimbolos = new Object[3];
    Object[] filaTablaDeErrores = new Object[3];
    char caracter;

    public LinkedList<Token> analisisLexico(String codigoLeido, JTable tablaDeSimbolos, JTable tablaErrores, JLabel txtCantErrores, JTextArea area, JLabel errores) {
        //INICIALIZANDO LAS VARIABLES DE LOS MODELOS DE LAS TABLAS
        modelTablaDeSimbolos = (DefaultTableModel) tablaDeSimbolos.getModel();
        modelTablaErrores = (DefaultTableModel) tablaErrores.getModel();

        //LE ASIGNO UN SIMBOLO COMO REFERENCIA AL FINAL DE MI CADENA QUE HE
        //RECIBIDO DESDE EL JTEXTAREA
        codigoLeido = codigoLeido + "°";

        salidaDeTokens = new LinkedList<Token>();
        erroresSintacticos = new LinkedList<>();
        reglasSintacticasAplicadas = new LinkedList<String>();

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
                filaTablaDeSimbolos[0] = "espacio en blanco";
                filaTablaDeSimbolos[1] = "";
                filaTablaDeSimbolos[2] = "NO";
            }
            switch (estado) {
                case 0:
                    //SI ME LLEGA EL CARACTER ° Y EL ÍNDICE ES IGUAL 
                    //A LA CADENA DE CARACTERES MENOS 1,
                    //ESTO POR LO QUE ASIGNÉ UNA LETRA COMO REFERENCIA 
                    //AL FINAL DE MI CADENA, EN ESTE CASO FINALIZA EL PROCESO
                    //DE ANÁLISIS LÉXICO
                    if (caracter == '°' && i == codigoLeido.length() - 1) {

                        //LE ASIGNO VALORES A LA TABLA DE SÍMBOLOS
                        filaTablaDeSimbolos[0] = "Fin del programa";
                        filaTablaDeSimbolos[1] = "espacio final";
                        filaTablaDeSimbolos[2] = "enviar token y finalizar proceso de análisis";
                        break;

                    }

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
                                //SI ME LLEGA UN CARACTER QUE NO ESTÁ EN MI 
                                //LENGUAJE, ENTONCES ES UN ERROR Y LO GUARDO 
                                //EN LA TABLA DE ERRORES, PERO NO LO CARGO AL 
                                //BÚFFER DE LA TABLA DE SÍMBOLOS
                                filaTablaDeErrores[0] = "Error Léxico";
                                filaTablaDeErrores[1] = caracter;
                                filaTablaDeSimbolos[1] = "";
                                filaTablaDeSimbolos[2] = "Error, leer otro caracter";
                                filaTablaDeErrores[2] = contadorLineas;

                                estado = 0;

                                modelTablaErrores.addRow(filaTablaDeErrores);
                                tablaErrores.setModel(modelTablaErrores);
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
        imprimeNodo();
        sintactico();
        erroresSintacticos(errores);
        reglasSintacticasUtilizadas(area);
        cantidadDeErroresDetectados(txtCantErrores);

        return salidaDeTokens;
    }

    public void sintactico() {
        contadorErroresSintaxis = 0;
        contadorErroresSintaxis++;
        System.out.println("sintactico: " + contadorErroresSintaxis);
        p = cabeza;

        if (p != null) {

            while (p != null) {

                System.out.println(p.token + "-" + p.lexema);

                do {
                    if (p != null) {
                        reglasUtilizadas = "";
                        declaraciones();
                    }
                } while (p.token.equals("PALABRARESERVADA"));

                do {
                    if (p != null) {
                        sentencia();
                    } else {
                        System.out.println("es null");
                        break;
                    }
                } while (p != null && p.token.equals("LETRA"));
                break;
            }

        } else {
            erroresSintacticos.add("Se esperaba una declaracion y una sentencia");
        }
    }

    String PalRes, ident, puntocoma;

    public void declaraciones() {

        System.out.println("declaraciones: " + contadorErroresSintaxis);

        if (p.token == "PALABRARESERVADA") {
            PalRes = p.lexema;
            p = p.sig;

//            while (p.token.equals("LETRA") || p.token.equals("SUBRAYADO") || p.token.equals("SIGNODOLAR")) {
//                if (p != null) {
//                    identificador();
//                }
//            }
            if (p.token == "LETRA") {
                ident = p.lexema;
                p = p.sig;

                switch (p.token) {
                    case "ASIGNACION":
                        expresion();
                        break;
                    case "FIN_LINEA":
                        if (p.token == "FIN_LINEA") {
                            if (p != null) {
                                puntocoma = p.lexema;
                                reglasUtilizadas = "regla 2: PalabraReservada Identificador ; ";
                                System.out.println(reglasUtilizadas);
                                p = p.sig;
                                contadorErroresSintaxis++;
                                System.out.println("Linea de codigo: " + PalRes + " " + ident + puntocoma);
                                reglasSintacticasAplicadas.add("<PalabraReservada> <Identificador> \";\"");
                            }
                        } else {
                            erroresSintacticos.add("declaraciones: Se esperaba un punto y coma\n revisar línea: " + contadorErroresSintaxis);
                        }
                        break;
                    default:
                        erroresSintacticos.add("declaraciones: Se esperaba un punto y coma\n revisar línea: " + contadorErroresSintaxis);
                        break;
                }

            } else {
                erroresSintacticos.add("declaraciones: Se esperaba un identificador\n revisar línea: " + contadorErroresSintaxis);
            }
        } else {
            erroresSintacticos.add("declaraciones: Se esperaba una palabra reservada\n revisar línea: " + contadorErroresSintaxis);
        }
    }

    public void identificador() {

        if (p != null) {
            if ((p.token == "LETRA") || (p.token == "SUBRAYADO") || (p.token == "SIGNODOLAR")) {
                p = p.sig;

            } else {
                erroresSintacticos.add("identificador: Se esperaba minimo  una combinacion de: LETRA + SUBRAYADO + SIGNODOLAR");
            }
        }

    }

    public void sentencia() {
        contadorErroresSintaxis++;
        System.out.println("sentencias: " + contadorErroresSintaxis);
        if (p.token != "PALABRARESERVADA") {
            Asignacion();
        }

    }

    public void Asignacion() {
        System.out.println("asignacion: " + contadorErroresSintaxis);
        if (p.token == "LETRA") {
            p = p.sig;
        } else {
            erroresSintacticos.add("Asignacion: Se esperaba un identificador, revisar linea:\n" + contadorErroresSintaxis);
        }

//        while (p.token.equals("LETRA") || p.token.equals("SUBRAYADO") || p.token.equals("SIGNODOLAR")) {
//            if (p != null) {
//                identificador();
//            }
//        }
        //p = p.sig;
        System.out.println("ANTES DEL CASE: " + p.token);
        switch (p.token) {
            case "ASIGNACION":
                expresion();
                break;
            case "INCREMENTO":
                // p = p.sig;
                incremento();
                break;
            case "DECREMENTO":
                // p = p.sig;
                decremento();
                break;
            case "PALABRARESERVADA":
                erroresSintacticos.add("Se esperaba una palabra reservada, revisar linea:\n" + contadorErroresSintaxis);
                break;
            case "SUMA":
                erroresSintacticos.add("Se esperaba un incremento, revisar linea:\n" + contadorErroresSintaxis);
                break;
            case "RESTA":
                erroresSintacticos.add("Se esperaba un decremento, revisar linea:\n" + contadorErroresSintaxis);
                break;
            default:
                erroresSintacticos.add("Se esperaba un \"++\" o \"--\" o \"=\" luego del identificador\n revisar línea: " + contadorErroresSintaxis);
                break;
        }

    }

    public void expresion() {
        p = p.sig;
        if (p != null) {
            System.out.println("EN EXPRESION LLEGA " + p.lexema);
            if (p.token == "LETRA" || p.token == "NUMERO") {
                p = p.sig;

                switch (p.token) {
                    case "SUMA":
                        operacionesAritmeticas();

                        break;

                    case "RESTA":
                        operacionesAritmeticas();
                        break;
                    case "DIVISION":
                        operacionesAritmeticas();
                        break;
                    case "MULTIPLICACION":
                        operacionesAritmeticas();
                        break;
                    case "FIN_LINEA":
                        if (p.token == "FIN_LINEA") {
                            if (p != null) {
                                p = p.sig;
                                contadorErroresSintaxis++;
                                reglasSintacticasAplicadas.add("<PalabraReservada> <Identificador> \"=\" <Identificador>|<Numero> \";\"");
                            }
                        } else {
                            erroresSintacticos.add("Expresion: se esperaba un punto y coma\n revisar línea: " + contadorErroresSintaxis);
                        }
                        break;
                    default:
                        erroresSintacticos.add("Expresion: se esperaba un punto y coma\n revisar línea: " + contadorErroresSintaxis);
                        break;
                }

            } else {
                erroresSintacticos.add("expresion: Se esperaba un identificador o un entero\n revisar línea: " + contadorErroresSintaxis);
            }
        }

    }

    public void operacionesAritmeticas() {
        p = p.sig;

        if (p.token.equals("LETRA") || p.token.equals("NUMERO")) {
            System.out.println("jajajajja" + p.token);
            p = p.sig;

            if (p != null) {
                if (p.token == "FIN_LINEA") {
                    if (p != null) {
                        p = p.sig;
                        contadorErroresSintaxis++;
                        reglasSintacticasAplicadas.add("<Identificador> \"=\" <Identificador>|<Numero> <Operador> <Identificador>|<Numero> \";\"");
                    }
                } else {
                    erroresSintacticos.add("Expresion: se esperaba un punto y coma\n revisar línea: " + contadorErroresSintaxis);
                }
            } else {
                erroresSintacticos.add("Expresion: se esperaba un punto y coma\n revisar línea: " + contadorErroresSintaxis);
            }

        } else {
            erroresSintacticos.add("Expresion: Se esperaba un identificador o un numero");
        }
    }

    String inc_letra, inc_incremento, inc_puntoComa;

    public void incremento() {

        if ((p.token == "INCREMENTO")) {
            inc_incremento = p.lexema;
            p = p.sig;
            if (p != null) {
                if (p.token == "FIN_LINEA") {
                    inc_puntoComa = p.lexema;
                    p = p.sig;
                    reglasSintacticasAplicadas.add("<Identificador> <Incremento> \";\"");

                    System.out.println("Codigo: " + inc_letra + " " + inc_incremento + "" + inc_puntoComa);
                } else {
                    erroresSintacticos.add("incremento: Se esperaba un punto y coma\n revisar línea: " + contadorErroresSintaxis);
                }
            } else {
                erroresSintacticos.add("incremento: Se esperaba un punto y coma\n revisar línea: " + contadorErroresSintaxis);
            }

        } else {
            erroresSintacticos.add("incremento: se esperaba un signo de incremento\n revisar línea: " + contadorErroresSintaxis);
        }

    }

    public void decremento() {

        if (p.token == "DECREMENTO") {
            p = p.sig;
            if (p != null) {
                if (p.token == "FIN_LINEA") {
                    p = p.sig;
                    reglasSintacticasAplicadas.add("<Identificador> <Decremento> \";\"");
                } else {
                    erroresSintacticos.add("decremento: Se esperaba un punto y coma\n revisar línea: " + contadorErroresSintaxis);
                }
            } else {
                erroresSintacticos.add("decremento: Se esperaba un punto y coma\n revisar línea: " + contadorErroresSintaxis);
            }

        } else {
            erroresSintacticos.add("decremento: se esperaba un signo de decremento\n revisar línea: " + contadorErroresSintaxis);
        }

    }

    public void erroresSintacticos(JLabel errores) {
        String var = "Analisis finalizado con exito";
        for (int i = 0; i < erroresSintacticos.size(); i++) {
            var = "";
            if (erroresSintacticos.size() > 0) {
                //System.out.println("analisis finalizado con errores");
                //System.out.println("Errores: \n" + erroresSintacticos.get(i));
                var += erroresSintacticos.get(i);
                break;
            }
        }
        System.out.println(var);
        errores.setText(var);
        var = "";
    }

    public void reglasSintacticasUtilizadas(JTextArea area) {
        String var = "Analisis finalizado con exito";
        area.setText("");
        for (int i = 0; i <= reglasSintacticasAplicadas.size() - 1; i++) {
            var = "";

            if (reglasSintacticasAplicadas.size() > 0) {
                //System.out.println("analisis finalizado con errores");
                //System.out.println("Errores: \n" + erroresSintacticos.get(i));
                var = reglasSintacticasAplicadas.get(i) + "\n";
                //break;
            }
            System.out.println(reglasSintacticasAplicadas.get(i));
            area.append(reglasSintacticasAplicadas.get(i) + "\n");
        }
        // System.out.println(var);

        var = "";
    }

    public void eliminarNOdeTablaDeSimbolos() {
        for (int j = 0; j < modelTablaDeSimbolos.getRowCount(); j++) {
            if (modelTablaDeSimbolos.getValueAt(j, 2).equals("NO")) {
                modelTablaDeSimbolos.removeRow(j);
            }
        }
    }

    public void cantidadDeErroresDetectados(JLabel txtErrores) {

        txtErrores.setText("No hay errores");
        for (int i = 0; i < modelTablaErrores.getRowCount(); i++) {
            if (!modelTablaErrores.getValueAt(i, 2).equals("")) {
                txtErrores.setText("Se han encontrado: " + (i + 1) + " errores");
            }

        }
    }

    public void imprimirListaDeTokens(LinkedList<Token> listaToken, JTable tablaTokens, JLabel cantidadDeTokens) {

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
            cantidadDeTokens.setText("Se han encontrado: " + (i + 1) + " tokens");
        }

//sintactico(listaToken);
    }

    public void agregarToken(String tipoToken) {
        filaTablaDeSimbolos[1] = lexema;
        filaTablaDeSimbolos[2] = "enviar token y limpiar búffer";
        salidaDeTokens.add(new Token(tipoToken, lexema));
        TempTipoToken = tipoToken;
        insertarNodoToken(lexema, TempTipoToken);
        lexema = "";
        TempTipoToken = "";
        estado = 0;
    }

    public void insertarNodoToken(String lex, String tipoTok) {
        //System.out.println(tipoTok);
        nodoToken nodo = new nodoToken(lex, tipoTok);
        if (cabeza == null) {
            cabeza = nodo;
            p = cabeza;
        } else {
            p.sig = nodo;
            p = nodo;
        }

    }

    public void imprimeNodo() {
        p = cabeza;
        while (p != null) {
            //System.out.println("lex: " + p.lexema + " tok: " + p.token);
            p = p.sig;
        }

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

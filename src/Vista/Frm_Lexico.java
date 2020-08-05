package Vista;

import lexico.cambiarColorFilas;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import javax.swing.JFileChooser;
import javax.swing.JTable;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import lexico.Lexico;
import modelo.Token;

/**
 *
 * @author never
 */
public class Frm_Lexico extends javax.swing.JFrame {

    //private FileNameExtensionFilter filtradoDeArchivos = new FileNameExtensionFilter("Formato de archivos", "java");
    private JFileChooser buscador;
    private LinkedList<Token> listaTokens;
    int contador = 0;
    int contadorAlIniciarElPrograma=0;
    char caracter;
    String contandoAlEscribir = "";

    /**
     * Creates new form Frm_Lexico
     */
    public Frm_Lexico() {
        initComponents();
        this.setLocationRelativeTo(null);
        for (int i = 0; i < txtAreaDeTexto.getText().length(); i++) {
            caracter = txtAreaDeTexto.getText().toString().charAt(i);
            if (caracter == '\n' || i == txtAreaDeTexto.getText().length() - 1) {
                contadorAlIniciarElPrograma++;
                contandoAlEscribir += contadorAlIniciarElPrograma + "\n";
                JtextContador.setText(contandoAlEscribir);
            }
        }
        
        cambiarColorFilas filas = new cambiarColorFilas(2);
        tablaDeSimbolos.setDefaultRenderer(Object.class, filas);
    }

    public int DetectarComentarios(String lineaLeida) {

        if (lineaLeida.replace(" ", "").startsWith("//")) {
            return 0;
        }
        if (lineaLeida.replace(" ", "").startsWith("#")) {
            return 0;
        }
        if (lineaLeida.replace(" ", "").contains("/**") || lineaLeida.replace(" ", "").contains("/*")) {
            return 0;
        }
        if (lineaLeida.replace(" ", "").startsWith("*")) {
            return 0;
        }
        if (lineaLeida.replace(" ", "").contains("<!--")) {
            return 0;
        }
        if (lineaLeida.isEmpty()) {
            return 0;
        }
        if (lineaLeida.replace(" ", "").length() == 0) {
            return 0;
        }
        return -1;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtAreaDeTexto = new javax.swing.JTextArea();
        jScrollPane5 = new javax.swing.JScrollPane();
        JtextContador = new javax.swing.JTextArea();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        tablaErrores = new javax.swing.JTable();
        txtCantErrores = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tablaDeSimbolos = new javax.swing.JTable();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tablaTokens = new javax.swing.JTable();
        txtCantidadDeTokens = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        jScrollPane6 = new javax.swing.JScrollPane();
        textAreaSintaxis = new javax.swing.JTextArea();
        jPanel5 = new javax.swing.JPanel();
        labelErrores = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jmenuAcciones = new javax.swing.JMenu();
        submenuDiagramaT = new javax.swing.JMenuItem();
        submenuTablaT = new javax.swing.JMenuItem();
        submenuTablaSimb = new javax.swing.JMenuItem();
        jMenuItem1 = new javax.swing.JMenuItem();
        submenuCargarArchivo = new javax.swing.JMenuItem();
        jmenuLimpiarArea = new javax.swing.JMenu();
        jmenuRun = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("ANALIZADOR LÉXICO");

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Código para analizar"));

        txtAreaDeTexto.setColumns(20);
        txtAreaDeTexto.setRows(5);
        txtAreaDeTexto.setText("int suma;\nint resta;\nsuma++;\nvar = num+6;");
        txtAreaDeTexto.addAncestorListener(new javax.swing.event.AncestorListener() {
            public void ancestorMoved(javax.swing.event.AncestorEvent evt) {
            }
            public void ancestorAdded(javax.swing.event.AncestorEvent evt) {
                txtAreaDeTextoAncestorAdded(evt);
            }
            public void ancestorRemoved(javax.swing.event.AncestorEvent evt) {
            }
        });
        txtAreaDeTexto.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtAreaDeTextoKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtAreaDeTextoKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtAreaDeTextoKeyTyped(evt);
            }
        });
        jScrollPane1.setViewportView(txtAreaDeTexto);

        JtextContador.setColumns(20);
        JtextContador.setRows(5);
        jScrollPane5.setViewportView(JtextContador);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 214, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 350, Short.MAX_VALUE)
                    .addComponent(jScrollPane5))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Errores Léxicos"));

        tablaErrores.setForeground(new java.awt.Color(255, 0, 0));
        tablaErrores.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Tipo", "Caracter", "Línea"
            }
        ));
        jScrollPane4.setViewportView(tablaErrores);

        txtCantErrores.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        txtCantErrores.setForeground(new java.awt.Color(0, 0, 0));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 579, Short.MAX_VALUE)
                    .addComponent(txtCantErrores, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(7, 7, 7)
                .addComponent(txtCantErrores, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("Tabla de símbolos"));

        tablaDeSimbolos.setForeground(new java.awt.Color(0, 0, 255));
        tablaDeSimbolos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Entrada", "Búffer", "Acción"
            }
        ));
        jScrollPane3.setViewportView(tablaDeSimbolos);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 273, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 346, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 11, Short.MAX_VALUE))
        );

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder("Tabla de tokens"));

        tablaTokens.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Valor", "Token"
            }
        ));
        jScrollPane2.setViewportView(tablaTokens);

        txtCantidadDeTokens.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        txtCantidadDeTokens.setForeground(new java.awt.Color(0, 0, 0));

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(txtCantidadDeTokens, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 245, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 326, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtCantidadDeTokens, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel6.setBorder(javax.swing.BorderFactory.createTitledBorder("Reglas utilizadas para el análisis sintáctico"));

        textAreaSintaxis.setColumns(20);
        textAreaSintaxis.setRows(5);
        jScrollPane6.setViewportView(textAreaSintaxis);

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane6, javax.swing.GroupLayout.DEFAULT_SIZE, 408, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane6)
                .addContainerGap())
        );

        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder("Resultado Análisis Léxico"));

        labelErrores.setBackground(new java.awt.Color(0, 0, 0));
        labelErrores.setForeground(new java.awt.Color(0, 0, 0));
        labelErrores.setText("jLabel1");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(labelErrores, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addComponent(labelErrores, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(47, Short.MAX_VALUE))
        );

        jMenuBar1.setForeground(new java.awt.Color(102, 102, 102));

        jmenuAcciones.setForeground(new java.awt.Color(0, 0, 0));
        jmenuAcciones.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/select.png"))); // NOI18N
        jmenuAcciones.setText("ACCIONES");

        submenuDiagramaT.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/relationship.png"))); // NOI18N
        submenuDiagramaT.setText("Diagrama de transición");
        submenuDiagramaT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                submenuDiagramaTActionPerformed(evt);
            }
        });
        jmenuAcciones.add(submenuDiagramaT);

        submenuTablaT.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/presentation.png"))); // NOI18N
        submenuTablaT.setText("Tabla de transición");
        submenuTablaT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                submenuTablaTActionPerformed(evt);
            }
        });
        jmenuAcciones.add(submenuTablaT);

        submenuTablaSimb.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/reservadas.png"))); // NOI18N
        submenuTablaSimb.setText("Tabla de palabras reservadas");
        submenuTablaSimb.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                submenuTablaSimbMouseClicked(evt);
            }
        });
        submenuTablaSimb.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                submenuTablaSimbActionPerformed(evt);
            }
        });
        jmenuAcciones.add(submenuTablaSimb);

        jMenuItem1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/gramatica.png"))); // NOI18N
        jMenuItem1.setText("Gramática");
        jMenuItem1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jMenuItem1MouseClicked(evt);
            }
        });
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jmenuAcciones.add(jMenuItem1);

        submenuCargarArchivo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/java.png"))); // NOI18N
        submenuCargarArchivo.setText("Cargar código");
        submenuCargarArchivo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                submenuCargarArchivoActionPerformed(evt);
            }
        });
        jmenuAcciones.add(submenuCargarArchivo);

        jMenuBar1.add(jmenuAcciones);

        jmenuLimpiarArea.setForeground(new java.awt.Color(0, 0, 0));
        jmenuLimpiarArea.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/broom.png"))); // NOI18N
        jmenuLimpiarArea.setText("LIMPIAR ÁREA DE ANÁLISIS");
        jmenuLimpiarArea.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jmenuLimpiarAreaMouseClicked(evt);
            }
        });
        jmenuLimpiarArea.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmenuLimpiarAreaActionPerformed(evt);
            }
        });
        jMenuBar1.add(jmenuLimpiarArea);

        jmenuRun.setForeground(new java.awt.Color(0, 0, 0));
        jmenuRun.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/play.png"))); // NOI18N
        jmenuRun.setText("RUN");
        jmenuRun.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jmenuRunMouseClicked(evt);
            }
        });
        jMenuBar1.add(jmenuRun);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 293, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 145, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void submenuDiagramaTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_submenuDiagramaTActionPerformed
        JF_diagramaTransicion diagrama = new JF_diagramaTransicion();
        diagrama.setVisible(true);
    }//GEN-LAST:event_submenuDiagramaTActionPerformed

    private void submenuTablaTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_submenuTablaTActionPerformed
        JF_tablaTransicion tablaTransicion = new JF_tablaTransicion();
        tablaTransicion.setVisible(true);
    }//GEN-LAST:event_submenuTablaTActionPerformed

    private void submenuCargarArchivoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_submenuCargarArchivoActionPerformed
        contador=0;
        buscador = new JFileChooser();//INICIALIZAR EL JFileChooser
        //buscador.setFileFilter(filtradoDeArchivos);
        String ObteniendoCadena = "";
        String contando = "";

        if (buscador.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
            try {
                String UbicacionArchivo = buscador.getSelectedFile().getAbsolutePath();

                //leer caracteres de un archivo
                FileReader f = new FileReader(UbicacionArchivo);
                //leer una línea completa
                BufferedReader b = new BufferedReader(f);

                // Lectura del fichero
                String linea;
                while ((linea = b.readLine()) != null) {
                    contador++;
                    contando += contador + "\n";
                    JtextContador.setText(contando);

                    if (DetectarComentarios(linea) == -1) {
                        ObteniendoCadena += linea + "\n";
                        txtAreaDeTexto.setText(ObteniendoCadena);
                    } else {
                        //System.out.println("Linea no incluida " + linea);
                    }
                }
                b.close();

            } catch (FileNotFoundException ex) {
                System.out.println("FileNotFoundException: " + ex);
            } catch (IOException ex) {
                System.out.println("IOException: " + ex);
            }
        } else {
            System.out.println("Se canceló la búsqueda");
        }
    }//GEN-LAST:event_submenuCargarArchivoActionPerformed

    private void jmenuRunMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jmenuRunMouseClicked
        textAreaSintaxis.setText("");
        labelErrores.setText("");
        String entradaDeDatos = txtAreaDeTexto.getText();
        String datosSinEspacios = entradaDeDatos.
                //replace("\n", "").
                replace("\t", "").
                replace("\r", "").
                replace("\f", "").
                replace("\b", "");
        Lexico analizadorLexico = new Lexico();
        listaTokens = analizadorLexico.analisisLexico(datosSinEspacios, tablaDeSimbolos, tablaErrores, txtCantErrores, textAreaSintaxis,labelErrores);
        analizadorLexico.imprimirListaDeTokens(listaTokens, tablaTokens,txtCantidadDeTokens);
        //analizadorLexico.sintactico(listaTokens);   
    }//GEN-LAST:event_jmenuRunMouseClicked

    private void submenuTablaSimbMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_submenuTablaSimbMouseClicked

    }//GEN-LAST:event_submenuTablaSimbMouseClicked

    private void submenuTablaSimbActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_submenuTablaSimbActionPerformed
        JF_palabrasReservadas palabrasReservadas = new JF_palabrasReservadas();
        palabrasReservadas.setVisible(true);
    }//GEN-LAST:event_submenuTablaSimbActionPerformed


    private void txtAreaDeTextoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtAreaDeTextoKeyTyped
        int contadorInterno = 0;
        String enviarContador = "";
        for (int i = 0; i < txtAreaDeTexto.getText().toString().length(); i++) {
            caracter = txtAreaDeTexto.getText().toString().charAt(i);
            if (caracter == '\n' || i == txtAreaDeTexto.getText().length() - 1) {
                contadorInterno++;
                enviarContador += contadorInterno + "\n";
                JtextContador.setText(enviarContador);
            }

        }
    }//GEN-LAST:event_txtAreaDeTextoKeyTyped

    private void txtAreaDeTextoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtAreaDeTextoKeyReleased

    }//GEN-LAST:event_txtAreaDeTextoKeyReleased

    private void txtAreaDeTextoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtAreaDeTextoKeyPressed

    }//GEN-LAST:event_txtAreaDeTextoKeyPressed

    private void txtAreaDeTextoAncestorAdded(javax.swing.event.AncestorEvent evt) {//GEN-FIRST:event_txtAreaDeTextoAncestorAdded
        // TODO add your handling code here:
    }//GEN-LAST:event_txtAreaDeTextoAncestorAdded

    private void jmenuLimpiarAreaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmenuLimpiarAreaActionPerformed
        
    }//GEN-LAST:event_jmenuLimpiarAreaActionPerformed

    private void jmenuLimpiarAreaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jmenuLimpiarAreaMouseClicked
        txtAreaDeTexto.setText("");
        JtextContador.setText("");
        limpiarTablas(tablaDeSimbolos);
        limpiarTablas(tablaTokens);
        limpiarTablas(tablaErrores);
        txtCantidadDeTokens.setText("");
        txtCantErrores.setText("");
        contador=0;
        contadorAlIniciarElPrograma=0;
        textAreaSintaxis.setText("");
        labelErrores.setText("");
    }//GEN-LAST:event_jmenuLimpiarAreaMouseClicked

    private void jMenuItem1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenuItem1MouseClicked
        
    }//GEN-LAST:event_jMenuItem1MouseClicked

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        JF_gramatica gramatica = new JF_gramatica();
        gramatica.setVisible(true);
    }//GEN-LAST:event_jMenuItem1ActionPerformed

     public void limpiarTablas(JTable tabla){
         DefaultTableModel tb = (DefaultTableModel) tabla.getModel();
        int a = tabla.getRowCount()-1;
        for (int i = a; i >= 0; i--) {          
        tb.removeRow(tb.getRowCount()-1);
        }
        //cargaTicket();
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Frm_Lexico.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Frm_Lexico.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Frm_Lexico.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Frm_Lexico.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Frm_Lexico().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextArea JtextContador;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JMenu jmenuAcciones;
    private javax.swing.JMenu jmenuLimpiarArea;
    private javax.swing.JMenu jmenuRun;
    private javax.swing.JLabel labelErrores;
    private javax.swing.JMenuItem submenuCargarArchivo;
    private javax.swing.JMenuItem submenuDiagramaT;
    private javax.swing.JMenuItem submenuTablaSimb;
    private javax.swing.JMenuItem submenuTablaT;
    private javax.swing.JTable tablaDeSimbolos;
    private javax.swing.JTable tablaErrores;
    private javax.swing.JTable tablaTokens;
    private javax.swing.JTextArea textAreaSintaxis;
    private javax.swing.JTextArea txtAreaDeTexto;
    private javax.swing.JLabel txtCantErrores;
    private javax.swing.JLabel txtCantidadDeTokens;
    // End of variables declaration//GEN-END:variables
}

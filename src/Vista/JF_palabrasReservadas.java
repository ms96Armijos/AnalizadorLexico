package Vista;

import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author never
 */
public class JF_palabrasReservadas extends javax.swing.JFrame {

    DefaultTableModel modelTablaPalabrasReservadas;
    ArrayList<String> Reservadas;

    /**
     * Creates new form JF_palabrasReservadas
     */
    public JF_palabrasReservadas() {
        initComponents();
        this.setLocationRelativeTo(null);
        Reservadas = new ArrayList<>();
        
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
        
        modelTablaPalabrasReservadas = (DefaultTableModel) JTable_PalabrasReservadas.getModel();
        Object[] filaPalabraReservada = new Object[2];

        for (int i = 0; i < Reservadas.size(); i++) {
            filaPalabraReservada[0] = (i+1);
            filaPalabraReservada[1] = Reservadas.get(i);
            modelTablaPalabrasReservadas.addRow(filaPalabraReservada);
            JTable_PalabrasReservadas.setModel(modelTablaPalabrasReservadas);
        }

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        JTable_PalabrasReservadas = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Palabras reservadas aceptadas");
        setResizable(false);

        JTable_PalabrasReservadas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Palabra Reservada"
            }
        ));
        jScrollPane1.setViewportView(JTable_PalabrasReservadas);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 358, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 414, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable JTable_PalabrasReservadas;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}

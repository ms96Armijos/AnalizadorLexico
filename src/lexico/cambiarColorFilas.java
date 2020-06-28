package lexico;

import java.awt.Color;
import java.awt.Component;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

/**
 *
 * @author never
 */
public class cambiarColorFilas extends DefaultTableCellRenderer {

    private int columna;

    public cambiarColorFilas(int columna) {
        this.columna = columna;
    }

    public Component getTableCellRendererComponent(JTable table, Object value, boolean selected, boolean focused, int row, int column) {
        setBackground(Color.white);
        table.setForeground(Color.black);
        super.getTableCellRendererComponent(table, value, selected, focused, row, column);
        if (table.getValueAt(row, 2).equals("Error, leer otro caracter")) {
            this.setForeground(Color.RED);
        } else if(table.getValueAt(row, 0).equals("Fin del programa")){
            this.setForeground(Color.darkGray);
        }else {
            this.setForeground(Color.BLUE);
        }
        return this;
    }

}

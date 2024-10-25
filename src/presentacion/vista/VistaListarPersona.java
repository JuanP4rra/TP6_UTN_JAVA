package presentacion.vista;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class VistaListarPersona extends JPanel {

    private static final long serialVersionUID = 1L;
    private JTable table;
    private DefaultTableModel tableModel;

    public VistaListarPersona() {
    	
        setLayout(null);

        // Crear las columnas de la tabla
        String[] columnNames = {"DNI", "Nombre", "Apellido"};
        
        // Crear el modelo de la tabla y sobrescribir el método isCellEditable
        tableModel = new DefaultTableModel(columnNames, 0) {
            /**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
            public boolean isCellEditable(int row, int column) {
                // Hacer que ninguna celda sea editable
                return false;
            }
        };

        // Crear la JTable y agregar el modelo
        table = new JTable(tableModel);

        // Crear el JScrollPane con la tabla
        JScrollPane scrollPane = new JScrollPane(table);

        // Tamaño y posición
        scrollPane.setBounds(50, 50, 400, 200);

        // Agregar el JScrollPane al panel
        this.add(scrollPane);

    }
    
    // Método para actualizar la tabla con los datos
    public void actualizarTabla(Object[][] data) {
        tableModel.setRowCount(0);  // Limpiar la tabla
        for (Object[] row : data) {
            tableModel.addRow(row);
        }
    }

    public JTable getTable() {
        return table;
    }
}
package vista;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controlador.ControladorPaciente;
import modelo.Paciente;

import javax.swing.JLabel;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JComboBox;
import java.awt.Font;
import java.awt.event.ActionListener;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JComboBox;

public class VistaPrincipal extends JFrame {

	private JPanel contentPane;
	private JTextField txtID;
	private JTextField txtApellidos;
	private JTextField txtTelefono;
	private JTextField txtDireccion;
	private JTextField txtNombres;
	private JLabel lblNewLabel_1;
	private JLabel lblNewLabel_2;
	private JLabel lblNewLabel_3;
	private JLabel lblNewLabel_4;
	private JLabel lblNewLabel_5;
	private JLabel lblPacientes;
	private JLabel lblControles;
	private JButton btnNewButton;
	private JButton actualizarBtn;
	private JButton btnNewButton_2;
	private JComboBox comboBox;
	private JTable table;
	 private Object[][] data = {};

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VistaPrincipal frame = new VistaPrincipal();
					frame.setVisible(true);
					Paciente modelo = new Paciente(); 	
					ControladorPaciente controller =  new  ControladorPaciente(frame, modelo);
					controller.leerArchivos();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			
		});
		
		
	}

	/**
	 * Create the frame.
	 */
	public VistaPrincipal() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 758, 558);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(240, 240, 240));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("[ Datos Personales ]");
		lblNewLabel.setForeground(new Color(0, 128, 255));
		lblNewLabel.setBounds(21, 10, 155, 13);
		contentPane.add(lblNewLabel);
		
		txtID = new JTextField();
		txtID.setBounds(158, 33, 165, 19);
		contentPane.add(txtID);
		txtID.setColumns(10);
		
		txtApellidos = new JTextField();
		txtApellidos.setColumns(10);
		txtApellidos.setBounds(158, 83, 165, 19);
		contentPane.add(txtApellidos);
		
		txtTelefono = new JTextField();
		txtTelefono.setColumns(10);
		txtTelefono.setBounds(158, 133, 165, 19);
		contentPane.add(txtTelefono);
		
		txtDireccion = new JTextField();
		txtDireccion.setColumns(10);
		txtDireccion.setBounds(501, 83, 216, 19);
		contentPane.add(txtDireccion);
		
		txtNombres = new JTextField();
		txtNombres.setColumns(10);
		txtNombres.setBounds(501, 33, 216, 19);
		contentPane.add(txtNombres);
		
		lblNewLabel_1 = new JLabel("Identificación:");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel_1.setBounds(62, 35, 86, 13);
		contentPane.add(lblNewLabel_1);
		
		lblNewLabel_2 = new JLabel("Apellidos:");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel_2.setBounds(84, 86, 64, 13);
		contentPane.add(lblNewLabel_2);
		
		lblNewLabel_3 = new JLabel("Teléfono:");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel_3.setBounds(84, 133, 64, 13);
		contentPane.add(lblNewLabel_3);
		
		lblNewLabel_4 = new JLabel("Nombres:");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel_4.setBounds(434, 33, 63, 13);
		contentPane.add(lblNewLabel_4);
		
		lblNewLabel_5 = new JLabel("Dirección:");
		lblNewLabel_5.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel_5.setBounds(434, 83, 63, 13);
		contentPane.add(lblNewLabel_5);
		
		lblPacientes = new JLabel("[ Pacientes ]");
		lblPacientes.setForeground(new Color(0, 128, 255));
		lblPacientes.setBounds(21, 162, 155, 13);
		contentPane.add(lblPacientes);
		
		lblControles = new JLabel("[ Controles]");
		lblControles.setForeground(new Color(0, 128, 255));
		lblControles.setBounds(579, 208, 155, 13);
		contentPane.add(lblControles);
		
		btnNewButton = new JButton("Agregar");
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnNewButton.setBounds(20, 190, 128, 45);
		contentPane.add(btnNewButton);
		
		actualizarBtn = new JButton("Guardar");
		actualizarBtn.setFont(new Font("Tahoma", Font.BOLD, 14));
		actualizarBtn.setBounds(556, 244, 112, 82);
		contentPane.add(actualizarBtn);
		
		btnNewButton_2 = new JButton("Cancelar");
		btnNewButton_2.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnNewButton_2.setBounds(556, 364, 112, 82);
		contentPane.add(btnNewButton_2);
		
		comboBox = new JComboBox();
		comboBox.setBounds(178, 187, 267, 50);
		contentPane.add(comboBox);
		
		table = new JTable();
		table.setBounds(21, 261, 505, 250);
		contentPane.add(table);
		
		table.setModel(new javax.swing.table.DefaultTableModel(
	            data,
	            new String [] {
	                "ID", "Apellidos", "Nombre", "Telefono", "Dirección"	
	            }
	        ));
	}
	
	public void change_btn() {
		if (actualizarBtn.getText() == "Actualizar") {
			actualizarBtn.setText("Guardar");
		}
		else actualizarBtn.setText("Actualizar");
	}
	
	
    public int getID() {
        return Integer.parseInt(txtID.getText());
    }

    public long getTelefono() {
        return Long.parseLong(txtTelefono.getText());
    }

    public String getNombres() {
        return txtNombres.getText();
    }
    
    public void setNombres(String nombres) {
        txtNombres.setText(nombres);
       
    }
    
    public String getApellidos() {
        return txtApellidos.getText();
    }
    
    public void setApellidos(String apellidos) {
        txtApellidos.setText(apellidos);
       
    }

    public String getDireccion() {
        return txtDireccion.getText();
    }

    public void setDireccion(String direccion) {
        txtDireccion.setText(String.valueOf(direccion));
       
    }
    
    public void setID(int ID) {
        txtID.setText(String.valueOf(ID));
       
    }

    public void setIDVacio() {
        txtID.setText("");
    }

    public void setTelefono(long telefono) {
        txtTelefono.setText(String.valueOf(telefono));
        
    }

    public void setTelefonoVacio() {
        txtTelefono.setText("");
    }

    public void setCamposVacios() {
        setTelefonoVacio();
        setIDVacio();
        setApellidos("");
        setNombres("");
        setDireccion("");	
    }
/**
    public boolean getEstaVacio(){
        return vacio;
    }
*/
    
    public JComboBox<String> getComboBox() {
        return comboBox;
    }

    //para no usar todos los setters uno por uno
    public void setDatos(int ID, String apellidos, String nombres, long telefono, String direccion) {
        setID(ID);
        setNombres(nombres);
        setApellidos(apellidos);
        setTelefono(telefono);
        setDireccion(direccion);
        //vacio = false;
    }
    
    
    public Object[] getDatosUsuario(Paciente paciente) {
        Object[] elemento = {paciente.getId(), paciente.getApellido(), paciente.getNombre(), paciente.getTelefono(), paciente.getDireccion()};
        return elemento;
    }

    public Object[][] getDatosTabla() {
        return data;
    }
    
    
    public void addDatosTabla(Paciente paciente) {
        // Crear una nueva matriz temporal con una fila adicional
        Object[][] newData = new Object[data.length + 1][];

        // Copiar los elementos existentes de data a newData
        for (int i = 0; i < data.length; i++) {
            newData[i] = data[i];
        }

        // Añadir el nuevo elemento a newData
        newData[newData.length - 1] = getDatosUsuario(paciente);

        // Asignar newData a data
        data = newData;

        // Copiar los elementos existentes de data a newData
        //swingutilites para envolver el código de actualización de la interfaz de usuario.
        SwingUtilities.invokeLater(() -> {
            table.setModel(getModeloTabla());
        });

        System.out.println(table.getModel());

    }
    
    public DefaultTableModel getModeloTabla() {
        DefaultTableModel modeloTabla = new DefaultTableModel(
                data,
                new String[]{
                    "ID", "Nombre", "Correo", "Telefono", "Estamento"
                }
        );
        return modeloTabla;
    }
    
    public void editarElementoTabla(int Index, Paciente paciente) {
        Object[] nuevosDatos = getDatosUsuario(paciente);
        data[Index] = nuevosDatos;
        SwingUtilities.invokeLater(() -> {
            table.setModel(getModeloTabla());
        });
    }
    
    public void eliminarFila(int filaEliminar) {
        
        // Convertir la matriz en una lista de arreglos
        List<Object[]> listaMatriz = new ArrayList<>(Arrays.asList(data));

        // Eliminar la fila especificada
        listaMatriz.remove(filaEliminar);

        // Convertir la lista de arreglos nuevamente en una matriz
        Object[][] nuevaMatriz = new Object[listaMatriz.size()][];
        listaMatriz.toArray(nuevaMatriz);
        
        data = nuevaMatriz;
        
        SwingUtilities.invokeLater(() -> {
            table.setModel(getModeloTabla());
        });
    }
	
    public void actualizarBtnListener(ActionListener listenControles) {
        actualizarBtn.addActionListener(listenControles);
    }
    
    public void displayErrorMessage(String erroMessage) {
        JOptionPane.showMessageDialog(this, erroMessage);
    }
    
	
	private static class __Tmp {
		private static void __tmp() {
			  javax.swing.JPanel __wbp_panel = new javax.swing.JPanel();
		}
	}
}

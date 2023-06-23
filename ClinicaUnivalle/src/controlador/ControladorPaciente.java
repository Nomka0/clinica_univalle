package controlador;

import dao.PacienteDAOImpl;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import vista.VistaPrincipal;
import modelo.Alergia;
import modelo.Paciente;
import dao.AlergiaDAOImpl;
import dao.PacienteDAO;
import dao.PacienteDAOImpl;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import static java.util.Collections.reverse;
import static java.util.Collections.sort;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class ControladorPaciente {
	private VistaPrincipal ventana;
	private PacienteDAOImpl pacienteDao;
	private Paciente paciente;
	private String rutaArchivo;

	public String opcionSeleccionada;
	private List<Paciente> datosTabla;
	private List<Paciente> datosTablaCopia;// para que no se repitan datos
	private int contadorDatos;
	private JTable tabla;
	private DefaultTableModel modeloTabla;
	private PacienteDAOImpl pacienteDaoPers;
	private PacienteDAOImpl pacienteTotalesDAO;
	// private List<Usuario> usuariosTotales;//Aquí se almacenarán los persistentes,
	// y los que se crean en la sesión, para manejarlos todos
	private int filaSeleccionada;
	private ArrayList<Integer> indexadorTabla;// tendrá los indices actualizados de la tabla en una lista, para después
												// no borrar cosas que no se supone debían ser borradas
	private int contadorTotal;
	private int contadorLista;
	private ArrayList<Integer> datosAEliminar;
	private ArrayList<Alergia> alergias;
	
	private AlergiaDAOImpl alergiasDao;
	
	public ControladorPaciente(VistaPrincipal ventana, Paciente paciente) {
		this.ventana = ventana;
		this.paciente = paciente;
		pacienteDao = new PacienteDAOImpl();
		alergiasDao = new AlergiaDAOImpl();
		contadorDatos = 0;
		// tabla = ventana.getTabla();
		// modeloTabla = ventana.getModeloTabla();
		pacienteTotalesDAO = new PacienteDAOImpl();
		rutaArchivo = "src/txt/TablaPacientes.txt"; // archivo de texto con el id del usuario
		indexadorTabla = new ArrayList<>();
		pacienteDaoPers = new PacienteDAOImpl();
		datosAEliminar = new ArrayList<>();
		alergias = new ArrayList<>();
		
		Alergia alergia1 = new Alergia(1, "alergia al agua");
		Alergia alergia2 = new Alergia(2, "alergia a ser feliz");
		Alergia alergia3 = new Alergia(3, "alergia al sexo");
		Alergia alergia4 = new Alergia(4, "alergia a la vida social");
		Alergia alergia5 = new Alergia(5, "pmneumococo");

		alergias.add(alergia1);
		alergias.add(alergia2);
		alergias.add(alergia3);
		alergias.add(alergia4);
		alergias.add(alergia5);
		
		
		
		//for (Paciente paciente : pacienteTotalesDAO.obtenerTodosLosPacientes()) {
		alergiasDao.crearAlergias(alergias);
	
		for(Alergia alergia : alergiasDao.obtenerTodasLasAlergias()) {
			ventana.getComboBox().addItem(alergia.getNombre());
		}
		
		
		
		alergiasDao.crearAlergias(alergias);

		// ventana.habilitarEditar(false);
		ventana.setVisible(true);
		ventana.setLocationRelativeTo(null);

		// ventana.jTableListener(new ManejadoraDeMouse());
		// ventana.btnListarListener(new ListarListener());
		ventana.actualizarBtnListener(new GuardarListener());
		// ventana.btnEditarListener(new EditarListener());
		// ventana.addComboBoxListener(new ComboBoxListener());
		// ventana.btnEliminarListener(new EliminarListener());
		// ventana.btnOkListener(new OkListener());
	}
	
    public void eliminarDatosEnArchivo() {
        File archivo = new File(rutaArchivo);
        BufferedWriter writer;
        try {
            writer = new BufferedWriter(new FileWriter(archivo));
            writer.write("");
            writer.close();
        } catch (IOException ex) {
            Logger.getLogger(ControladorPaciente.class.getName()).log(Level.SEVERE, null, ex);
        }
    }


	public void GuardarEnArchivo() {
		try (BufferedWriter escritor = new BufferedWriter(new FileWriter(rutaArchivo, true))) {
			eliminarPacientesTotales();
			eliminarDatosEnArchivo();
			System.out.println("usuarios totales: " + pacienteTotalesDAO.obtenerTodosLosPacientes());
			for (Paciente paciente : pacienteTotalesDAO.obtenerTodosLosPacientes()) {
				int ID = paciente.getId();
				String nombre = paciente.getNombre();
				String apellido = paciente.getApellido();
				long telefono = paciente.getTelefono();
				String direccion = paciente.getDireccion();
				Object[] datos = { ID, apellido, nombre, telefono, direccion };
				String datosString = Arrays.toString(datos);
				String datosStringRecortado = datosString.substring(1, datosString.length() - 1);
				escritor.write(datosStringRecortado);
				escritor.newLine();
				//contadorDatos++;
			}
			
			// else ventana.displayErrorMessage("Error: ¡No hay ningún usuario para
			// guardar!");
			contadorLista = pacienteTotalesDAO.obtenerTodosLosPacientes().size() - 1;
			 datosAEliminar.clear();
			 reiniciarIndexador();
			// ventana.deshabilitarGuardar();
			// postGuardar();
			// ventana.displayErrorMessage("Guardado");

			// }
		} catch (IOException e) {
			System.out.println("Ocurrió un error al crear el archivo.");
		}

	}

	public void datosALista() {
		Paciente usuarioNuevo;
		int id;
		String nombre;
		String apellido;
		long telefono;
		String direccion;

		usuarioNuevo = new Paciente();
		id = ventana.getID();
		nombre = ventana.getNombres();
		apellido = ventana.getApellidos();
		telefono = ventana.getTelefono();
		direccion = ventana.getDireccion();

		usuarioNuevo.setId(id);
		usuarioNuevo.setNombre(nombre);
		usuarioNuevo.setApellido(apellido);
		usuarioNuevo.setTelefono(telefono);
		usuarioNuevo.setDireccion(direccion);
		pacienteDao.crearPaciente(usuarioNuevo);// añade el usuario con todos sus atributos a una lista de la
												// implementación de la interfaz DAO de usuario
		System.out.println(pacienteDao.obtenerTodosLosPacientes());
		ventana.addDatosTabla(usuarioNuevo);
		// nuevosDatos();
		// ventana.setCamposVacios();
		// GuardarEnArchivo();
		contadorTotal++;
		contadorDatos++;
		contadorLista = pacienteTotalesDAO.obtenerTodosLosPacientes().size() - 1;
		contadorLista++;

		indexadorTabla.add(contadorLista);
		// vista.setArea(modelo.getArea());
		// vista.activarControles(false);
		// ventana.habilitarGuardar();

		System.out.println("indexador tabla: " + indexadorTabla);
		System.out.println("usuarios Dao: " + pacienteDao.obtenerTodosLosPacientes());
		pacienteTotalesDAO.obtenerTodosLosPacientes()
				.add(pacienteDao.obtenerTodosLosPacientes().get(contadorDatos - 1));
	}

	public void leerArchivos() {
		try {
			BufferedReader lector = new BufferedReader(new FileReader(rutaArchivo));
			String lineaArchivo;
			try {
				while ((lineaArchivo = lector.readLine()) != null) {

					String[] datosReconstruidos = lineaArchivo.split(", ");
					Paciente usuarioNuevo;
					int id;
					String nombre;
					String apellidos;
					long telefono;
					String direccion;

					usuarioNuevo = new Paciente();

					id = Integer.parseInt(datosReconstruidos[0]);
					apellidos = datosReconstruidos[1];
					nombre = datosReconstruidos[2];
					telefono = Long.parseLong(datosReconstruidos[3]);
					direccion = datosReconstruidos[4];

					usuarioNuevo.setId(id);
					usuarioNuevo.setNombre(nombre);
					usuarioNuevo.setApellido(apellidos);
					usuarioNuevo.setTelefono(telefono);
					usuarioNuevo.setDireccion(direccion);
					pacienteDaoPers.crearPaciente(usuarioNuevo);
					System.out.println(usuarioNuevo.getApellido());
				}

				datosPersistentes(pacienteDaoPers);
				pacienteTotalesDAO.obtenerTodosLosPacientes().addAll(pacienteDaoPers.obtenerTodosLosPacientes());
				contadorTotal = pacienteDaoPers.obtenerTodosLosPacientes().size();

				reiniciarIndexador();

			} catch (IOException ex) {
				Logger.getLogger(ControladorPaciente.class.getName()).log(Level.SEVERE, null, ex);
			}
		} catch (FileNotFoundException ex) {
			Logger.getLogger(ControladorPaciente.class.getName()).log(Level.SEVERE, null, ex);
			System.out.println("no se encontró el archivo.");
		}
	}

	public void datosPersistentes(PacienteDAOImpl paciente) {
		for (Paciente pacientes : paciente.obtenerTodosLosPacientes()) {
			ventana.addDatosTabla(pacientes);
		}
	}

	// reinicia indexador tabla, para volver a almacenar las filas que se quedan en
	// la tabla. Esto es para cuando empieza la ventana, o cuando se guardan datos
	public void reiniciarIndexador() {
		contadorTotal = pacienteDaoPers.obtenerTodosLosPacientes().size();
		indexadorTabla.clear();
		if (contadorTotal == 1) {
			indexadorTabla.add(0);
		} else {
			for (int i = 0; i < contadorTotal; i++) {
				indexadorTabla.add(i);
			}
		}

	}

	public boolean compararIDsUsuarios(int usuarioID) {
		try (BufferedReader lector = new BufferedReader(new FileReader(rutaArchivo))) {
			String linea;
			while ((linea = lector.readLine()) != null) {
				String[] valores = linea.split(", ");
				int numeroEnLinea = Integer.parseInt(valores[0].trim());
				if (usuarioID == numeroEnLinea) {
					return true; // El número se encuentra en el archivo
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return false; // El número no se encuentra en el archivo
	}
	
    public void eliminarPacientesTotales() {
        // prestamosTotalesDAO.obtenerTodosLosUsuarios() =
        // prestamosTotalesDAO.obtenerTodosLosUsuarios()DAO.obtenerTodosLosUsuarios();
        sort(datosAEliminar);
        reverse(datosAEliminar);
        for (int i = 0; i < datosAEliminar.size(); i++) {
            pacienteTotalesDAO.eliminarPaciente(datosAEliminar.get(i));
        }
        // prestamosTotalesDAO.obtenerTodosLosUsuarios() =
        // prestamosTotalesDAO.obtenerTodosLosUsuarios()DAO.obtenerTodosLosUsuarios();
    }

	public void editarElementos(int index) {
		Paciente pacienteEditado = new Paciente();
		int id = ventana.getID();
		String apellido = ventana.getApellidos();
		String nombre = ventana.getNombres();
		long telefono = ventana.getTelefono();
		String direccion = ventana.getDireccion();

		pacienteEditado.setId(id);
		pacienteEditado.setApellido(apellido);
		pacienteEditado.setNombre(nombre);
		pacienteEditado.setTelefono(telefono);
		pacienteEditado.setDireccion(direccion);

		ventana.editarElementoTabla(index, pacienteEditado);
		pacienteTotalesDAO.actualizarPaciente(index, pacienteEditado);
	}

	public void intercambio_datos_actuales() {
		int index = averiguar_index(ventana.getID());
		editarElementos(index);
	}


    
	public int averiguar_index(int id) {
		List<Paciente> pacienteTotales = pacienteTotalesDAO.obtenerTodosLosPacientes();
		for (int i = 0; i < pacienteTotales.size(); i++) {
			Paciente paciente = pacienteTotales.get(i);
			if (paciente.getId() == id) {
				return i;
			}
		}
		return -1;
	}

	class GuardarListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getActionCommand().equalsIgnoreCase("Guardar")) {
				// datosALista();
				try {
					if (compararIDsUsuarios(ventana.getID())) {
						ventana.displayErrorMessage("El paciente ya está registrado. Puede actualizarlo.");
						
						ventana.change_btn();

					} else {
							try {// lista datos
									// if(ventana.getEstaVacio() == false){
								datosALista();
								
								// }
							} catch (NumberFormatException ex) {
								// ventana.displayErrorMessage("¡Revisa los datos ingresados!");
								System.out.println("revisa los datos!");
							}
						GuardarEnArchivo();
						ventana.setCamposVacios();
					}
				} catch (NumberFormatException ex) {
					ventana.displayErrorMessage("Error: ¡Revisa los datos ingresados!");
				}
			} else {
				intercambio_datos_actuales();
				GuardarEnArchivo();
				ventana.change_btn();
			}
			System.out.println("Estos son los usuarios totales: " + pacienteTotalesDAO.obtenerTodosLosPacientes());
		}
	}
}

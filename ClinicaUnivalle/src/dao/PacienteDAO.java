package dao;
import modelo.Paciente;
import java.util.List;

public interface PacienteDAO {
	
	void crearPaciente(Paciente paciente);
	
	Paciente obtenerPaciente(int index);
	
	List<Paciente> obtenerTodosLosPacientes();
	
	void actualizarPaciente(int index, Paciente pacienteAct);
	
	void eliminarPaciente(int index);
}

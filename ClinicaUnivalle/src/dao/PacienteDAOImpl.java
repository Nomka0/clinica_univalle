package dao;

import java.util.ArrayList;
import java.util.List;
import modelo.Paciente;

public class PacienteDAOImpl implements PacienteDAO {

	private List<Paciente> pacientes;
	
	public PacienteDAOImpl() {
		this.pacientes = new ArrayList<>();
	}
	
	@Override
	public void crearPaciente(Paciente paciente) {
		// TODO Auto-generated method stub
		pacientes.add(paciente);
	}

	@Override
	public Paciente obtenerPaciente(int index) {
		// TODO Auto-generated method stub
		return pacientes.get(index);
	}

	@Override
	public List<Paciente> obtenerTodosLosPacientes() {
		// TODO Auto-generated method stub
		return pacientes;
	}

	@Override
	public void actualizarPaciente(int index, Paciente pacienteAct) {
		// TODO Auto-generated method stub
		pacientes.set(index, pacienteAct);
	}
	
    @Override
    public void eliminarPaciente(int index) {
        pacientes.remove(index);
    }
	

}

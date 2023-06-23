package dao;

import java.util.ArrayList;
import java.util.List;
import modelo.Alergia;

public class AlergiaDAOImpl implements AlergiaDAO {

	private List<Alergia> alergias;
	
	public AlergiaDAOImpl() {
		this.alergias = new ArrayList<>();
	}
	
	public void crearAlergias(List<Alergia> alergias_nuevas) {
		// TODO Auto-generated method stub
		alergias = alergias_nuevas;
	}
	
	@Override
	public void crearAlergia(Alergia paciente) {
		// TODO Auto-generated method stub
		alergias.add(paciente);
	}

	@Override
	public Alergia obtenerAlergia(int index) {
		// TODO Auto-generated method stub
		return alergias.get(index);
	}

	@Override
	public List<Alergia> obtenerTodasLasAlergias() {
		// TODO Auto-generated method stub
		return alergias;
	}

	@Override
	public void actualizarAlergia(int index, Alergia pacienteAct) {
		// TODO Auto-generated method stub
		alergias.set(index, pacienteAct);
	}
	
    @Override
    public void eliminarAlergia(int index) {
        alergias.remove(index);
    }
	

}

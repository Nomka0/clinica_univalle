package dao;
import modelo.Alergia;
import java.util.List;

public interface AlergiaDAO {
	
	void crearAlergia(Alergia alergia);
	
	Alergia obtenerAlergia(int index);
	
	List<Alergia> obtenerTodasLasAlergias();
	
	void actualizarAlergia(int index, Alergia alergiaAct);
	
	void eliminarAlergia(int index);
}

package modelo;

public class PacienteAlergias {
	int id_paciente;
	int id_alergia;
	
	public PacienteAlergias(int id_paciente, int id_alergia) {
		super();
		this.id_paciente = id_paciente;
		this.id_alergia = id_alergia;
	}

	public int getId_paciente() {
		return id_paciente;
	}

	public void setId_paciente(int id_paciente) {
		this.id_paciente = id_paciente;
	}

	public int getId_alergia() {
		return id_alergia;
	}

	public void setId_alergia(int id_alergia) {
		this.id_alergia = id_alergia;
	}
	
	
}

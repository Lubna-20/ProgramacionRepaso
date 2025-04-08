package misclses;

public class Ciclos {
	private String Idalumno;
	private String nombre;
	private String correo;
	private String ciclo;
	private String turno;
	private String ies;
	
	public Ciclos(){
		
	}
	public Ciclos(String idalumno, String nombre, String correo, String ciclo, String turno, String ies) {
		super();
		Idalumno = idalumno;
		this.nombre = nombre;
		this.correo = correo;
		this.ciclo = ciclo;
		this.turno = turno;
		this.ies = ies;
	}
	
	public String getIdalumno() {
		return Idalumno;
	}
	public void setIdalumno(String idalumno) {
		Idalumno = idalumno;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getCorreo() {
		return correo;
	}
	public void setCorreo(String correo) {
		this.correo = correo;
	}
	public String getCiclo() {
		return ciclo;
	}
	public void setCiclo(String ciclo) {
		this.ciclo = ciclo;
	}
	public String getTurno() {
		return turno;
	}
	public void setTurno(String turno) {
		this.turno = turno;
	}
	public String getIes() {
		return ies;
	}
	public void setIes(String ies) {
		this.ies = ies;
	}
	
	

}


public class Asignatura {
	
	private String nombre;
	private String salon;



	public Asignatura() {
		nombre = "";
		salon = "";
	}
	
	public Asignatura(String nombre, String salon) {
		this.nombre = nombre;
		this.salon = salon;
	}

	/**
	 * @return the nombre
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * @param nombre the nombre to set
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


	/**
	 * @return the salon
	 */
	public String getSalon() {
		return salon;
	}

	/**
	 * @param salon the salon to set
	 */
	public void setSalon(String salon) {
		this.salon = salon;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		String cadena = nombre+":"+salon;
		return cadena;
		
	}

	public boolean equals(Asignatura otra) {
		if (this.nombre.equals(otra.getNombre()) && this.salon.equals(otra.getSalon()))
			return true;
		else
			return false;
	}

	
	
}

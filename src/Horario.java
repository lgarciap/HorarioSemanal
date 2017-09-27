import java.util.ArrayList;

/**
 * 
 */

/**
 * @author flaquitqm
 *
 */
public class Horario {
	private Asignatura[][] horario;
	private int filas;
	private int columnas;

	/**
	 * @param horario
	 */
	public Horario(Asignatura[][] horario) {
		this.horario = horario;
	}

	/**
	 * 
	 */
	public Horario(int filas, int columnas) {
		this.filas = filas;
		this.columnas = columnas;
		horario = new Asignatura[filas][columnas];
	}

	/**
	 * @return the horario
	 */
	public Asignatura[][] getHorario() {
		return horario;
	}

	/**
	 * @param horario the horario to set
	 */
	public void setHorario(Asignatura[][] horario) {
		this.horario = horario;
	}

	/**
	 * @return the filas
	 */
	public int getFilas() {
		return filas;
	}

	/**
	 * @param filas the filas to set
	 */
	public void setFilas(int filas) {
		this.filas = filas;
	}

	/**
	 * @return the columnas
	 */
	public int getColumnas() {
		return columnas;
	}

	/**
	 * @param columnas the columnas to set
	 */
	public void setColumnas(int columnas) {
		this.columnas = columnas;
	}
	
	/**
	 * Devuelve la información de una asignatura en un periodo en un dia
	 * @param periodo
	 * @param dia
	 * @return String info de la asignatura, "" si no está llena
	 */
	public String obtenerAsignatura(int periodo,int dia) {
		if (horario[periodo][dia]!=null)
			return horario[periodo][dia].toString();
		else
			return "";
	}
	
	/**
	 * Reserva un periodo de un dia para una asignatura
	 * @param nombre (String)
	 * @param salon (String)
	 * @param periodo (int)
	 * @param dia (int)
	 */
	public void reservarPeriodo(String nombre, String salon, int periodo, int dia) {
		Asignatura clase = new Asignatura(nombre,salon);
		horario[periodo][dia] = clase;
	}
	
	/**
	 * Devuelve una matriz de Strings con la información de todo el horario
	 * @return String[][]
	 */
	public String[][] devolverHorario() {
		String[][] horas = new String[filas][columnas];
		for(int i = 0; i<filas; i++) {
			for(int j=0; j<columnas; j++){
				if (horario[i][j] != null)
					horas[i][j]=horario[i][j].toString();
			}
			
		}
		return horas;
	}
	
	/**
	 * Determina si una asignatura está o no en la matriz, es suficiente con que exista una vez
	 * @param nombre (String)
	 * @param salon (String)
	 * @return (boolean) true -> existe, false -> No existe
	 */
	public boolean buscarAsignatura(String nombre, String salon) {
		int i = 0, j=0;
		boolean encontro = false;
		Asignatura tempAsig = new Asignatura(nombre,salon); //crea una asignatura temporal para comparar con la que está en la matriz
		//Se usan ciclos while para controlar que se interrumpan cuando se encontró la asignatura buscada
		while (i<filas && !encontro) {
			j=0;
			while (j<columnas && !encontro) {
				if (horario[i][j] != null && horario[i][j].equals(tempAsig))
					encontro = true;
				j++;
			}
			i++;
		}
				
		return encontro;
	}
	
	
	/**
	 * Devuelve un arreglo de coordenadas de todas las coincidencias de una clase en la matriz
	 * @param nombre
	 * @param salon
	 * @return ArrayList de enteros
	 */
	public ArrayList<Integer> asignaturaIndex(String nombre, String salon) {
		int i = 0, j=0;
		ArrayList<Integer> coordenada = new ArrayList<Integer>();
		Asignatura tempAsig = new Asignatura(nombre,salon); //crea una asignatura temporal para comparar con la que está en la matriz
		
		while (i<filas) {
			j=0;		
			while (j<columnas ) {
				if (horario[i][j] != null && horario[i][j].equals(tempAsig)) {
					coordenada.add(i);
					coordenada.add(j);
				}
				
				j++;
			}
			i++;
		}
				
		return coordenada;
	}
	
}

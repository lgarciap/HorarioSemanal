import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JTable;
import javax.swing.JScrollPane;

public class HorarioGUI {

	private JFrame Ventana;
	private JTextField tfNombreClase;
	private JTextField tfSalon;
	private Horario horario;
	private JComboBox cbDiaSemana;
	private JComboBox cbPeriodo;
	private JButton btnIngresarClase;
	private JButton btnMostrarMatriz;
	private JPanel pHorario;
	private JTable tblHorario;
	private DefaultTableModel tblModel;
	private JScrollPane spTabla;
	private JButton btnExisteAsignatura;
	private JButton btnHorarioAsig;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					HorarioGUI window = new HorarioGUI();
					window.Ventana.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public HorarioGUI() {
		initialize();
		horario = new Horario(7, 5);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		Ventana = new JFrame();
		Ventana.setBounds(100, 100, 450, 300);
		Ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Ventana.getContentPane().setLayout(new GridLayout(0, 1, 0, 0));
		
		JPanel pAddDatos = new JPanel();
		Ventana.getContentPane().add(pAddDatos);
		pAddDatos.setLayout(new GridLayout(0, 2, 0, 0));
		
		JLabel lblNombreClase = new JLabel("Nombre de la Clase:");
		pAddDatos.add(lblNombreClase);
		
		tfNombreClase = new JTextField();
		pAddDatos.add(tfNombreClase);
		tfNombreClase.setColumns(10);
		
		JLabel lblSalon = new JLabel("Sal\u00F3n: ");
		pAddDatos.add(lblSalon);
		
		tfSalon = new JTextField();
		pAddDatos.add(tfSalon);
		tfSalon.setColumns(10);
		
		JLabel lblDiaSemana = new JLabel("Dia de la semana");
		pAddDatos.add(lblDiaSemana);
		
		cbDiaSemana = new JComboBox();
		cbDiaSemana.setModel(new DefaultComboBoxModel(new String[] {"Lunes", "Martes", "Mi\u00E9rcoles", "Jueves", "Viernes"}));
		pAddDatos.add(cbDiaSemana);
		
		JLabel lblHora = new JLabel("Hora del per\u00EDodo:");
		pAddDatos.add(lblHora);
		
		
		cbPeriodo = new JComboBox();
		cbPeriodo.setModel(new DefaultComboBoxModel(new String[] {"7:00 - 7:45", "7:50 - 8:35", "8:40 - 9:25", "9:30 -10:15", "10:15 - 10:40", "10:40 - 11:25", "11:30 - 12:15", "12:20 - 13:05"}));
		pAddDatos.add(cbPeriodo);
		
		btnIngresarClase = new JButton("IngresarClase");
		pAddDatos.add(btnIngresarClase);
		btnIngresarClase.addActionListener(new MiListener());
		
		btnMostrarMatriz = new JButton("Mostrar Matriz");
		pAddDatos.add(btnMostrarMatriz);
		
		btnExisteAsignatura = new JButton("\u00BFExiste Asignatura?");
		btnExisteAsignatura.addActionListener(new MiListener());
		pAddDatos.add(btnExisteAsignatura);
		
		btnHorarioAsig = new JButton("Horario Asignatura");
		btnHorarioAsig.addActionListener(new MiListener());
		pAddDatos.add(btnHorarioAsig);
		btnMostrarMatriz.addActionListener(new MiListener());
		
		pHorario = new JPanel();
		Ventana.getContentPane().add(pHorario);
		
		tblHorario = new JTable();
		String[] header = {"Lunes", "Martes", "Miércoles", "Jueves", "Viernes"};
		tblModel = new DefaultTableModel(header, 7);
		pHorario.setLayout(new GridLayout(0, 1, 0, 0));
		
		spTabla = new JScrollPane(tblHorario);
		pHorario.add(spTabla);
		tblHorario.setModel(tblModel);
		spTabla.setViewportView(tblHorario);
	}
	
	private class MiListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			if (e.getSource() == btnIngresarClase) {
				int periodo = cbPeriodo.getSelectedIndex();
				int dia = cbDiaSemana.getSelectedIndex();
				horario.reservarPeriodo(tfNombreClase.getText(), tfSalon.getText(), periodo, dia);
				tblModel.setValueAt(horario.obtenerAsignatura(periodo, dia), periodo, dia);
			}
			if (e.getSource() == btnMostrarMatriz) {
				String[][] matriz = horario.devolverHorario();
				for (int i = 0; i<horario.getFilas(); i++) {
					for (int j=0; j<horario.getColumnas(); j++)
						System.out.print(matriz[i][j]);
					System.out.println();
				}
					
			}
			
			if (e.getSource() == btnExisteAsignatura) {
				boolean existe = horario.buscarAsignatura(tfNombreClase.getText(), tfSalon.getText());
				String msg = "";
				if (existe)
					msg = "Si existe en el horario";
				else
					msg = "No existe en el horario";
				JOptionPane.showMessageDialog(Ventana, msg);
			}
			
			if (e.getSource() == btnHorarioAsig) {
				String msg = "";
				ArrayList<Integer> indices = horario.asignaturaIndex(tfNombreClase.getText(), tfSalon.getText());
				if (indices.isEmpty())
					JOptionPane.showMessageDialog(Ventana, "La clase no está en el horario");
				else {
					int i = 0;
					while (i<indices.size()) {
						msg = msg + "periodo: "+cbPeriodo.getItemAt(indices.get(i))+" Día: "+cbDiaSemana.getItemAt(indices.get(i+1))+"\n";
						i = i+2;
					}
					JOptionPane.showMessageDialog(Ventana, msg, "La asignatura "+tfNombreClase.getText()+" está: ",JOptionPane.INFORMATION_MESSAGE);
				}
			}
		}
		
	}

}

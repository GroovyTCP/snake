package viborita.interfaz;

import java.awt.EventQueue;
import java.awt.PointerInfo;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JTextPane;

import viborita.cliente.HiloCliente;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class CrearSala extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField textFieldNomSala;
	private JTextPane textPaneDescrip;
	private HiloCliente connectionThread;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CrearSala window = new CrearSala();
					window.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public CrearSala() {
		initialize();
		setVisible(true);
	}
 
	public CrearSala(HiloCliente connectionThread) {
		initialize();
		setVisible(true);
		this.connectionThread = connectionThread;
	}

	/**
	 * Initialize the contents of the 
	 */
	private void initialize() {
		setBounds(100, 100, 450, 300);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setTitle("Crear sala");
		
		getContentPane().setLayout(null);
		
		JLabel lblNombreDeSala = new JLabel("    Nombre de sala:");
		lblNombreDeSala.setBounds(22, 23, 106, 25);
		getContentPane().add(lblNombreDeSala);
		
		textFieldNomSala = new JTextField();
		textFieldNomSala.setBounds(138, 25, 246, 20);
		getContentPane().add(textFieldNomSala);
		textFieldNomSala.setColumns(10);
		
//		JLabel lblDescripcion = new JLabel("      Descripcion:");
//		lblDescripcion.setBounds(37, 68, 91, 25);
//		getContentPane().add(lblDescripcion);
		
		textPaneDescrip = new JTextPane();
		textPaneDescrip.setBounds(138, 68, 246, 96);
		getContentPane().add(textPaneDescrip);
		
		JButton btnCrearSala = new JButton("Crear sala");
		btnCrearSala.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				SalaV sala = new SalaV(connectionThread);
				sala.setDescripcionSala(textPaneDescrip.getText());
				sala.setNomSala(textFieldNomSala.getText());
				sala.setIdSala(generarIdSala());
			}
		});
		btnCrearSala.setBounds(189, 190, 140, 39);
		getContentPane().add(btnCrearSala);
	}

	public String getDescripcion() {
		return textPaneDescrip.getText();
	}
	
	public String getNomSala() {
		return textFieldNomSala.getText();
	}
	
	public int generarIdSala() {
		return (int)(Math.random() * 1000) +1;
	}
}

package viborita.interfaz;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JTextPane;

import viborita.cliente.HiloCliente;
import viborita.entidades.Cabeza;
import viborita.entidades.Cuerpo;
import viborita.entidades.Direcciones;
import viborita.entidades.Mapa;
import viborita.entidades.Punto;
import viborita.entidades.Ventana;
import viborita.entidades.Vibora;

public class SalaV {

	private int idSala;
	private JFrame frame;
	private JTextPane txtpnDescrip;
	private JLabel lblNomSala;
	private JLabel lblNomDueno;
	private HiloCliente connectionThread;

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					Sala window = new Sala();
//					window.frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}

	/**
	 * Create the application.
	 */
	public SalaV() {
		initialize();
		frame.setVisible(true);
	}

	public SalaV(HiloCliente connectionThread) {
		initialize();
		this.connectionThread = connectionThread;
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		frame.addWindowListener(new java.awt.event.WindowAdapter() {
			@Override
			public void windowClosing(java.awt.event.WindowEvent windowEvent) {

				int jPaneResult = JOptionPane.showConfirmDialog(windowEvent.getComponent(),
						"¿Estas seguro que deseas abandonar la sala?", "¿Abandonar sala?",
						JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
				if (jPaneResult == JOptionPane.YES_OPTION) {
					frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

				}
			}
		});
		
		frame.getContentPane().setLayout(null);
		
		JButton btnJugar = new JButton("Iniciar Juego");
		btnJugar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Vibora[] snake = new Vibora[2];
				snake[0] = new Vibora(new Cabeza(new Punto(60, 50)), "1", Color.RED, Direcciones.DERECHA);
				ArrayList<Punto> cuerpo1 = new ArrayList<Punto>();
				cuerpo1.add(new Punto(50, 50));
				cuerpo1.add(new Punto(40, 50));
				snake[0].setCuerpo(new Cuerpo(cuerpo1));
				
				snake[1] = new Vibora(new Cabeza(new Punto(540, 450)), "2", Color.CYAN, Direcciones.IZQUIERDA);
				ArrayList<Punto> cuerpo2 = new ArrayList<Punto>();
				cuerpo2.add(new Punto(550, 450));
				cuerpo2.add(new Punto(560, 450));
				snake[1].setCuerpo(new Cuerpo(cuerpo2));
				
				/*
				 * snake array viboras
				 * 600 ancho mapa
				 * 500 largo mapa
				 * 100 puntaje por comer una fruta
				 */
				Mapa game = new Mapa(snake, 600, 500, 100);
				
				Runnable r = new Ventana(game);
				Thread t = new Thread(r);
				t.start();
				
			}
		});
		btnJugar.setBounds(145, 212, 125, 38);
		frame.getContentPane().add(btnJugar);
		
		JButton btnSalir = new JButton("Salir de sala");
		btnSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int jPaneResult = JOptionPane.showConfirmDialog(null,
						"Estas seguro que deseas abandonar la sala?", "Abandonar sala?",
						JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
				if (jPaneResult == JOptionPane.YES_OPTION) {	
					frame.dispose();
				}
			}
		});
		btnSalir.setBounds(10, 212, 125, 38);
		frame.getContentPane().add(btnSalir);
		
		JList listUsuarios = new JList();
		listUsuarios.setBounds(276, 11, 148, 239);
		frame.getContentPane().add(listUsuarios);
		
		JLabel lblSala = new JLabel("         Sala :");
		lblSala.setBounds(0, 0, 77, 23);
		frame.getContentPane().add(lblSala);
		
		JLabel lblDueno = new JLabel("         Due\u00F1o :");
		lblDueno.setBounds(0, 34, 77, 23);
		frame.getContentPane().add(lblDueno);
		
		txtpnDescrip = new JTextPane();
		txtpnDescrip.setEditable(false);
		txtpnDescrip.setText("Bienvenido!");
		txtpnDescrip.setBounds(10, 85, 260, 116);
		frame.getContentPane().add(txtpnDescrip);
		
		lblNomSala = new JLabel("");
		lblNomSala.setBounds(74, 2, 192, 21);
		frame.getContentPane().add(lblNomSala);
		
		lblNomDueno = new JLabel("");
		lblNomDueno.setBounds(87, 38, 183, 19);
		frame.getContentPane().add(lblNomDueno);
		
		JLabel lblNewLabel = new JLabel("Descripcion sala");
		lblNewLabel.setBounds(10, 68, 85, 14);
		frame.getContentPane().add(lblNewLabel);
	}
	
	public void setNomSala(String nomSala) {
		this.lblNomSala.setText(nomSala);
	}
	
	public void setDescripcionSala(String descripcion) {
		if(descripcion.length() != 0)
			this.txtpnDescrip.setText(descripcion);
	}
	
	public void setIdSala(int idSala) {
		this.idSala = idSala;
	}
	
	public String getNomSala() {
		return this.lblNomSala.getText();
	}
	
	public void setDueno(String dueno) {
		this.lblNomDueno.setText(dueno);
	}
}

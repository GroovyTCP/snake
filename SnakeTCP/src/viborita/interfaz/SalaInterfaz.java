package viborita.interfaz;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.management.modelmbean.ModelMBeanOperationInfo;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;

import viborita.cliente.HiloCliente;
import viborita.entidades.Cabeza;
import viborita.entidades.Cuerpo;
import viborita.entidades.Direcciones;
import viborita.entidades.Mapa;
import viborita.entidades.Musica;
import viborita.entidades.Punto;
import viborita.entidades.Ventana;
import viborita.entidades.Vibora;

public class SalaInterfaz extends JFrame {

	private List<String> salas = new ArrayList<>();
	private boolean musicOn = true;
	private JList<String> listSalas; 
	private DefaultListModel<String> modeloLista = new DefaultListModel<>();
	private Musica musica;
	private HiloCliente connectionThread;
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -453204827036494827L;

	private JPanel contentPane;
	///private JTextField textIngresarSala;

	/**
	 * Corre el frame de sala.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SalaInterfaz frame = new SalaInterfaz();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public SalaInterfaz() {
		initialize();
	}
	
	/**
	 * Crea el frame.
	 * @throws IOException 
	 * @throws UnsupportedAudioFileException 
	 * @throws LineUnavailableException 
	 */
	public SalaInterfaz(HiloCliente connectionThread) throws UnsupportedAudioFileException, IOException, LineUnavailableException {
		initialize();
		this.connectionThread = connectionThread;
	}
	
	private void initialize() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(1080,720);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(6, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		setTitle("Viborita - Salas");
		setResizable(false);
		setLocationRelativeTo(null);
		
		ImageIcon icono = new ImageIcon("recursos\\imagenes\\snake-icono.jpg");
		setIconImage(icono.getImage());
		
		JPanel fondo = new JPanel();
		fondo.setBackground(new Color(23, 32, 42));
		fondo.setForeground(UIManager.getColor("textHighlight"));
		contentPane.add(fondo, BorderLayout.CENTER);
		fondo.setLayout(null);
		
		JPanel panelPrincipal = new JPanel();
		panelPrincipal.setBackground(new Color(21, 67, 96));
		panelPrincipal.setBorder(null);
		panelPrincipal.setBounds(30, 30, 1000, 620);
		fondo.add(panelPrincipal, BorderLayout.CENTER);
		panelPrincipal.setLayout(null);
		
		JLabel lblTitulo = new JLabel("Sala");
		lblTitulo.setBounds(35, 20, 300, 40);
		lblTitulo.setForeground(Color.WHITE);
		lblTitulo.setOpaque(true);
		lblTitulo.setBackground(new Color(154, 172, 184));
		panelPrincipal.add(lblTitulo);
		lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitulo.setFont(new Font("ComicSans", Font.PLAIN, 25));
		
//		JPanel panelSala = new JPanel();
//		panelSala.setBackground(Color.LIGHT_GRAY);
//		panelSala.setBounds(350, 20, 630, 570);
//		panelPrincipal.add(panelSala);
//		panelSala.setLayout(null);
		
		listSalas = new JList<>();
		listSalas.setBackground(Color.LIGHT_GRAY);
		listSalas.setBounds(350, 20, 630, 570);
		panelPrincipal.add(listSalas);
		listSalas.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		listSalas.setModel(modeloLista);
		
		
		
		
//		JButton btnIniciar = new JButton("Iniciar Juego");
//		btnIniciar.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent arg0) {
//				
//				Vibora[] snake = new Vibora[2];
//				snake[0] = new Vibora(new Cabeza(new Punto(60, 50)), "1", Color.RED, Direcciones.DERECHA);
//				ArrayList<Punto> cuerpo1 = new ArrayList<Punto>();
//				cuerpo1.add(new Punto(50, 50));
//				cuerpo1.add(new Punto(40, 50));
//				snake[0].setCuerpo(new Cuerpo(cuerpo1));
//				
//				snake[1] = new Vibora(new Cabeza(new Punto(540, 450)), "2", Color.CYAN, Direcciones.IZQUIERDA);
//				ArrayList<Punto> cuerpo2 = new ArrayList<Punto>();
//				cuerpo2.add(new Punto(550, 450));
//				cuerpo2.add(new Punto(560, 450));
//				snake[1].setCuerpo(new Cuerpo(cuerpo2));
//				
//				/*
//				 * snake array viboras
//				 * 600 ancho mapa
//				 * 500 largo mapa
//				 * 100 puntaje por comer una fruta
//				 */
//				Mapa game = new Mapa(snake, 600, 500, 100);
//				
//				Runnable r = new Ventana(game);
//				Thread t = new Thread(r);
//				t.start();
//				
//				clip.stop(); //musica fuera
//			}
//		});
//		
//		btnIniciar.setForeground(new Color(51, 153, 255));
//		btnIniciar.setBounds(35, 550, 300, 40);
//		btnIniciar.setFocusable(false);
//		btnIniciar.setFont(new Font("ComicSans", Font.PLAIN, 20));
//		panelPrincipal.add(btnIniciar);
		
		JButton btnUnirse = new JButton("Unirse a sala");
		btnUnirse.setForeground(new Color(51, 153, 255));
		btnUnirse.setBounds(35, 550, 300, 40);
		btnUnirse.setFocusable(false);
		btnUnirse.setFont(new Font("ComicSans", Font.PLAIN, 20));
		panelPrincipal.add(btnUnirse);
		
		JButton btnCreacionSala = new JButton("Creacion sala");
		btnCreacionSala.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				musica.detener();
				CrearSala crearSala = new CrearSala(connectionThread);
				crearSala.setVisible(true);
				ponerSalasEnLista();
			}
		});
		
		
		btnCreacionSala.setForeground(new Color(51, 153, 255));
		btnCreacionSala.setBounds(35, 500, 300, 40);
		btnCreacionSala.setFocusable(false);
		btnCreacionSala.setFont(new Font("ComicSans", Font.PLAIN, 20));
		panelPrincipal.add(btnCreacionSala);
		
		//boton cerrar Sesion
		JButton btnCerrarSesion = new JButton();
		btnCerrarSesion.setBackground(Color.LIGHT_GRAY);
		btnCerrarSesion.setIcon(new ImageIcon("recursos\\imagenes\\botonSalir.png"));
		btnCerrarSesion.setBounds(885, 595, 55, 25);
		panelPrincipal.add(btnCerrarSesion);
		btnCerrarSesion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				///Cerrar sesion y volver a abrir la ventana login
			}
		});
		
		//boton musica
		JButton btnMusica = new JButton();
		btnMusica.setBackground(Color.LIGHT_GRAY);
		btnMusica.setIcon(new ImageIcon("recursos\\imagenes\\iconoMusica.png"));
		btnMusica.setBounds(945, 595, 55, 25);
		panelPrincipal.add(btnMusica);
		btnMusica.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(musicOn == false) {
					musica.reproducir();
					musicOn=true;
				}
				else {
					musica.detener();
					musicOn=false;
				}
					
			}
		});

		//Musica
		try {
			musica = new Musica("musicaSalas.wav");
		} catch (UnsupportedAudioFileException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (LineUnavailableException e) {
			e.printStackTrace();
		}
		musica.reproducir();	
	}
	
	public void ponerSalasEnLista() {
		
		for(String nombre : this.salas)
			modeloLista.addElement(nombre);
	}
}

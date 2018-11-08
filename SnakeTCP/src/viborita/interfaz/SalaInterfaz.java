package viborita.interfaz;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;

import entidades.Ventana;

public class SalaInterfaz extends JFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = -453204827036494827L;

	private JPanel contentPane;
	private JTextField textIngresarSala;

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

	/**
	 * Crea el frame.
	 * @throws IOException 
	 * @throws UnsupportedAudioFileException 
	 * @throws LineUnavailableException 
	 */
	public SalaInterfaz() throws UnsupportedAudioFileException, IOException, LineUnavailableException {
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
		
		JPanel panelSala = new JPanel();
		panelSala.setBackground(Color.LIGHT_GRAY);
		panelSala.setBounds(350, 20, 630, 570);
		panelPrincipal.add(panelSala);
		panelSala.setLayout(null);
		
		JButton btnIniciar = new JButton("Iniciar Juego");
		btnIniciar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Runnable r = new Ventana();
				Thread t = new Thread(r);
				t.start();
			}
		});
		btnIniciar.setForeground(new Color(51, 153, 255));
		btnIniciar.setBounds(35, 550, 300, 40);
		btnIniciar.setFocusable(false);
		btnIniciar.setFont(new Font("ComicSans", Font.PLAIN, 20));
		panelPrincipal.add(btnIniciar);
		
		//Musica
		File cancionLogin = new File("recursos\\soundtrack\\musicaSalas.wav");
		AudioInputStream audio = AudioSystem.getAudioInputStream(cancionLogin);
		Clip clip = AudioSystem.getClip();
		clip.open(audio);
		clip.start();
		
	}
}

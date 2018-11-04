package viborita.interfaz;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;

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
	 */
	public SalaInterfaz() {
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		
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
		
		JLabel lblTitulo = new JLabel("Salas");
		lblTitulo.setBounds(35, 20, 300, 40);
		lblTitulo.setForeground(Color.WHITE);
		lblTitulo.setOpaque(true);
		lblTitulo.setBackground(new Color(154, 172, 184));
		panelPrincipal.add(lblTitulo);
		lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitulo.setFont(new Font("ComicSans", Font.PLAIN, 25));
		
//		JLabel lblAgregarSala = new JLabel("Agregar Sala: ");
//		lblAgregarSala.setFont(new Font("Tempus Sans ITC", Font.PLAIN, 13));
//		lblAgregarSala.setBounds(187, 104, 107, 16);
//		panelPrincipal.add(lblAgregarSala);
//		
		JPanel panelSala = new JPanel();
		panelSala.setBackground(Color.LIGHT_GRAY);
		panelSala.setBounds(350, 20, 630, 570);
		panelPrincipal.add(panelSala);
		panelSala.setLayout(null);
//		
//		JLabel ColorDeFondo = new JLabel("");
//		ColorDeFondo.setIcon(new ImageIcon("C:\\Users\\lucki\\Desktop\\medio.jpg"));
//		ColorDeFondo.setBounds(0, 0, 150, 234);
//		PanelSala.add(ColorDeFondo);
//		
//		JList ListaDeSalas = new JList();
//		ListaDeSalas.setBounds(0, 229, 150, -223);
//		PanelSala.add(ListaDeSalas);
//		
//		JButton btnIngresarEnSala = new JButton("Unirse a la Sala");
//		btnIngresarEnSala.setBackground(new Color(30, 144, 255));
//		btnIngresarEnSala.setBounds(12, 325, 151, 25);
//		panelPrincipal.add(btnIngresarEnSala);
//		
//		TextIngresarSala = new JTextField();
//		TextIngresarSala.setToolTipText("Ingrese Nombre de sala a crear");
//		TextIngresarSala.setBounds(187, 128, 116, 22);
//		panelPrincipal.add(TextIngresarSala);
//		TextIngresarSala.setColumns(10);
		
		JButton btnAgregar = new JButton("Crear sala");
		btnAgregar.setForeground(new Color(51, 153, 255));
		btnAgregar.setBounds(35, 70, 300, 40);
		btnAgregar.setFocusable(false);
		btnAgregar.setFont(new Font("ComicSans", Font.PLAIN, 20));
		panelPrincipal.add(btnAgregar);
		
	}
}

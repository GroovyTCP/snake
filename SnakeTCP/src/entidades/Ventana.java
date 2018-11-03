package entidades;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Ventana extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	public Ventana() {
		super("Snake");
		setResizable(false);
		
		addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent tecla) {
				setMovimiento(tecla);
			}
		});
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 600, 500);
		
		Vibora snake = new Vibora(new Cabeza(new Punto(100, 100)), "red", new Punto(1, 0));
		ArrayList<Punto> cuerpo = new ArrayList<Punto>();
		cuerpo.add(new Punto(90, 100));
		cuerpo.add(new Punto(80, 100));
		cuerpo.add(new Punto(70, 100));
		cuerpo.add(new Punto(60, 100));
		snake.setCuerpo(new Cuerpo(cuerpo));
		
		contentPane = new SnakeGrafico(snake);
		setBackground(Color.BLACK);
		setContentPane(contentPane);
		setLocationRelativeTo(null);
		setVisible(true);
		ActionListener listen = new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
            	Vibora nuevo = ((SnakeGrafico) contentPane).getVibora();
            	nuevo.mover(nuevo.getDireccionActual());
    			((SnakeGrafico) contentPane).setVibora(nuevo);
    			repaint();

            }
        };
        
        Timer timerGamer = new Timer(500, listen);
	}
	
	public void setMovimiento(KeyEvent evento){
    	Vibora nuevo = ((SnakeGrafico) contentPane).getVibora();

		System.out.println("-------------------------------");
		if(evento.getKeyCode() == KeyEvent.VK_LEFT) {
			nuevo.mover(Direcciones.IZQUIERDA);
			((SnakeGrafico) contentPane).setVibora(nuevo);
		}
		if(evento.getKeyCode() == KeyEvent.VK_RIGHT) {
			nuevo.mover(Direcciones.DERECHA);
			((SnakeGrafico) contentPane).setVibora(nuevo);
		}
		if(evento.getKeyCode() == KeyEvent.VK_UP) {
			nuevo.mover(Direcciones.ARRIBA);
			((SnakeGrafico) contentPane).setVibora(nuevo);
		}
		if(evento.getKeyCode() == KeyEvent.VK_DOWN) {
			nuevo.mover(Direcciones.ABAJO);
			((SnakeGrafico) contentPane).setVibora(nuevo);
		}
		repaint();
	}
	
	public static void main(String[] args) {
		new Ventana();
	}
}

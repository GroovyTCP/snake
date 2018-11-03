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

import viborita.entidades.Cabeza;
import viborita.entidades.Cuerpo;
import viborita.entidades.Punto;
import viborita.entidades.Vibora;

public class Ventana extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private Vibora snake;
	
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
		
		snake = new Vibora(new Cabeza(new Punto(100, 100)), "red", Direcciones.DERECHA);
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
		while(!snake.isMuerta()) {
			try {
				Thread.sleep(50);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			snake.mover();
			repaint();
//			setMovimiento();
		}
		
		
	}
	
	public void setMovimiento(KeyEvent evento){
//    	Vibora nuevo = ((SnakeGrafico) contentPane).getVibora();

		System.out.println("-------------------------------");
		
		Punto direccionActual = snake.getDireccionActual();
		
		if(evento.getKeyCode() == KeyEvent.VK_LEFT && direccionActual != Direcciones.DERECHA) {
//			nuevo.mover(Direcciones.IZQUIERDA);
//			((SnakeGrafico) contentPane).setVibora(nuevo);
			
			snake.setDireccionActual(Direcciones.IZQUIERDA);
		}
		if(evento.getKeyCode() == KeyEvent.VK_RIGHT && direccionActual != Direcciones.IZQUIERDA) {
//			nuevo.mover(Direcciones.DERECHA);
//			((SnakeGrafico) contentPane).setVibora(nuevo);
			snake.setDireccionActual(Direcciones.DERECHA);
		}
		if(evento.getKeyCode() == KeyEvent.VK_UP && direccionActual != Direcciones.ABAJO) {
//			nuevo.mover(Direcciones.ARRIBA);
//			((SnakeGrafico) contentPane).setVibora(nuevo);
			snake.setDireccionActual(Direcciones.ARRIBA);
		}
		if(evento.getKeyCode() == KeyEvent.VK_DOWN && direccionActual != Direcciones.ARRIBA) {
//			nuevo.mover(Direcciones.ABAJO);
//			((SnakeGrafico) contentPane).setVibora(nuevo);
			snake.setDireccionActual(Direcciones.ABAJO);
		}
		repaint();
	}
	
	public void setMovimiento(){
    	Vibora nuevo = ((SnakeGrafico) contentPane).getVibora();

		System.out.println("-------------------------------");
		
		
			nuevo.mover(Direcciones.DERECHA);
			((SnakeGrafico) contentPane).setVibora(nuevo);
		repaint();
	}
	
	public static void main(String[] args) {
		new Ventana();
	}
}

package entidades;

import java.awt.Color;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import viborita.entidades.Cabeza;
import viborita.entidades.Cuerpo;
import viborita.entidades.Mapa;
import viborita.entidades.Punto;
import viborita.entidades.Vibora;


// Serpiente roja, se mueve con las flechitas y la otra con WASD

import viborita.entidades.Cabeza;
import viborita.entidades.Cuerpo;
import viborita.entidades.Punto;
import viborita.entidades.Vibora;

public class Ventana extends JFrame implements Runnable {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private Mapa juego;
	
	public Ventana() {
		
	}
	
	public Ventana(Mapa mapa) {
		super("Snake");
		setResizable(false);
		juego = mapa;
		
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		addWindowListener(new java.awt.event.WindowAdapter() {
		    @Override
		    public void windowClosing(java.awt.event.WindowEvent windowEvent) {
		    	
		    	int jPaneResult = JOptionPane.showConfirmDialog(windowEvent.getComponent(), 
			            "¿Estás seguro que deseas abandonar la partida?", "¿Abandonar partida?", 
			            JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
		        if (jPaneResult == JOptionPane.YES_OPTION){
		            setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		            
		        }
		    }
		});
		
		addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent tecla) {
				setMovimiento(tecla);
			}
		});
		setBounds(0, 0, mapa.getAncho(), mapa.getLargo());
		
		contentPane = new SnakeGrafico(mapa);
		setBackground(Color.BLACK);
		setContentPane(contentPane);
		setLocationRelativeTo(null);
		setVisible(true);
		
		int temporizadorFruta = 0;
		
		while(mapa.cantidadViborasVivas() > 0) {
			try {
				Thread.sleep(200);
				temporizadorFruta++;
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			if (temporizadorFruta % 3 == 0) {
				juego.mandarFruta();
				temporizadorFruta = 0;
			}
			juego.evaluarMovimientoViborita();
			repaint();
		}
		
		//dispose();
		
	}
	
	public void setMovimiento(KeyEvent evento){

		System.out.println("-------------------------------");
		
		int viboraNumero = 0;
		Punto direccionActual = juego.getViboras()[viboraNumero].getDireccionActual();
		
		if(evento.getKeyCode() == KeyEvent.VK_LEFT && direccionActual != Direcciones.DERECHA) {
			juego.getViboras()[viboraNumero].setDireccionActual(Direcciones.IZQUIERDA);
		}
		if(evento.getKeyCode() == KeyEvent.VK_RIGHT && direccionActual != Direcciones.IZQUIERDA) {
			juego.getViboras()[viboraNumero].setDireccionActual(Direcciones.DERECHA);
		}
		if(evento.getKeyCode() == KeyEvent.VK_UP && direccionActual != Direcciones.ABAJO) {
			juego.getViboras()[viboraNumero].setDireccionActual(Direcciones.ARRIBA);
		}
		if(evento.getKeyCode() == KeyEvent.VK_DOWN && direccionActual != Direcciones.ARRIBA) {
			juego.getViboras()[viboraNumero].setDireccionActual(Direcciones.ABAJO);
		}
		
		// Vibora numero 2
		viboraNumero = 1;
		Punto direccionActual2 = juego.getViboras()[viboraNumero].getDireccionActual();
		
		if(evento.getKeyCode() == KeyEvent.VK_A && direccionActual2 != Direcciones.DERECHA) {
			juego.getViboras()[viboraNumero].setDireccionActual(Direcciones.IZQUIERDA);
		}
		if(evento.getKeyCode() == KeyEvent.VK_D && direccionActual2 != Direcciones.IZQUIERDA) {
			juego.getViboras()[viboraNumero].setDireccionActual(Direcciones.DERECHA);
		}
		if(evento.getKeyCode() == KeyEvent.VK_W && direccionActual2 != Direcciones.ABAJO) {
			juego.getViboras()[viboraNumero].setDireccionActual(Direcciones.ARRIBA);
		}
		if(evento.getKeyCode() == KeyEvent.VK_S && direccionActual2 != Direcciones.ARRIBA) {
			juego.getViboras()[viboraNumero].setDireccionActual(Direcciones.ABAJO);
		}
		repaint();
	}
	
//	public static void main(String[] args) {
//		Vibora[] snake = new Vibora[2];
//		snake[0] = new Vibora(new Cabeza(new Punto(100, 100)), Color.RED, Direcciones.DERECHA);
//		ArrayList<Punto> cuerpo = new ArrayList<Punto>();
//		cuerpo.add(new Punto(90, 100));
//		cuerpo.add(new Punto(80, 100));
//		cuerpo.add(new Punto(70, 100));
//		cuerpo.add(new Punto(60, 100));
//		snake[0].setCuerpo(new Cuerpo(cuerpo));
//		
//		snake[1] = new Vibora(new Cabeza(new Punto(160, 160)), Color.CYAN, Direcciones.DERECHA);
//		
//		Mapa game = new Mapa(snake, 300, 300);
//		new Ventana(game);
//	}

	@Override
	public void run() {
		Vibora[] snake = new Vibora[2];
		snake[0] = new Vibora(new Cabeza(new Punto(100, 100)), Color.RED, Direcciones.DERECHA);
		ArrayList<Punto> cuerpo = new ArrayList<Punto>();
		cuerpo.add(new Punto(90, 100));
		cuerpo.add(new Punto(80, 100));
		cuerpo.add(new Punto(70, 100));
		cuerpo.add(new Punto(60, 100));
		snake[0].setCuerpo(new Cuerpo(cuerpo));
		
		snake[1] = new Vibora(new Cabeza(new Punto(160, 160)), Color.CYAN, Direcciones.DERECHA);
		
		Mapa game = new Mapa(snake, 300, 300);
		new Ventana(game);
	}
}

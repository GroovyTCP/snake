package entidades;

import java.util.ArrayList;

import viborita.entidades.Fruta;
import viborita.entidades.Vibora;

public class Tablero {
	
	public static final int WIDTH = 600;
	public static final int HEIGHT = 500;
	public static final int SNAKE = 10;

	public static int[][] tablero = new int[WIDTH/SNAKE][HEIGHT/SNAKE];
	
	private ArrayList<Vibora> viboras;
	private Fruta fruta;
	
	public Tablero() {
		this.viboras = new ArrayList<Vibora>();
	}
}

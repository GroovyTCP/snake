package entidades;

import java.util.ArrayList;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ArrayList<Punto> partes1 = new ArrayList<>();
		partes1.add(new Punto (3,0));
		partes1.add(new Punto (4,0));
		Cuerpo cuerpo1 = new Cuerpo(partes1);
		
		ArrayList<Punto> partes2 = new ArrayList<>();
		partes2.add(new Punto (3,1));
		partes2.add(new Punto (4,1));
		Cuerpo cuerpo2 = new Cuerpo(partes2);
		
		Vibora v1 = new Vibora(0,new Cabeza(new Punto(2,0)),cuerpo1,"rojo",new Punto(-1,0));
		Vibora v2 = new Vibora(0,new Cabeza(new Punto(2,1)),cuerpo2,"azul",new Punto(-1,0));
		
		Vibora[] viboritas = new Vibora[2];
		viboritas[0] = v1;
		viboritas[1] = v2;
		
		
		Mapa mapa = new Mapa(viboritas,10,10);
		
		///Una se mueve pa arriba, otra pa abajo
		Punto p1 = new Punto(0,-1);
		Punto p2 = new Punto(0,1);
		Punto[] movimientos = new Punto[2];
		movimientos[0] = p1;
		movimientos[1] = p2;
		
		mapa.evaluarMovimientoViborita(movimientos);
		
		System.out.println(v1.isMuerta());
		
	}

}

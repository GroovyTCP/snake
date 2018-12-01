package entidades.test;


import static org.junit.Assert.*;

import java.awt.Color;
import java.util.ArrayList;

import org.junit.Test;

import viborita.entidades.Cabeza;
import viborita.entidades.Cuerpo;
import viborita.entidades.Punto;
import viborita.entidades.Vibora;

public class MovimientosTest {

	@Test
	public void test() {
		Vibora v1 = new Vibora(new Cabeza(new Punto(5, 5)), Color.RED, new Punto(6,5));
		ArrayList<Punto> a = new ArrayList<Punto>();
		a.add(new Punto(4, 5));
		
		v1.setCuerpo(new Cuerpo(a));
		
		assertEquals(new Punto(5,5), v1.getCabeza().getPosicion());
		assertEquals(new Punto(4,5), v1.getCuerpo().getCuerpo().get(0));
		
		v1.mover(new Punto(1, 0));	///Me muevo hacia la derecha
		
		assertEquals(new Punto(6,5), v1.getCabeza().getPosicion());
		assertEquals(new Punto(5,5), v1.getCuerpo().getCuerpo().get(0));
		
		v1.mover(new Punto(1, 0));	///Me muevo hacia arriba
		
		assertEquals(new Punto(7,5), v1.getCabeza().getPosicion());
		assertEquals(new Punto(6,5), v1.getCuerpo().getCuerpo().get(0));
		
		v1.mover(new Punto(0, -1));	///Me muevo hacia abajo

		assertEquals(new Punto(7,4), v1.getCabeza().getPosicion());
		assertEquals(new Punto(7,5), v1.getCuerpo().getCuerpo().get(0));
		
		v1.mover(new Punto(0, -1)); ///Me muevo hacia abajo

		assertEquals(new Punto(7,3), v1.getCabeza().getPosicion());
		assertEquals(new Punto(7,4), v1.getCuerpo().getCuerpo().get(0));
		
		v1.mover(new Punto(-1, 0));	///Me muevo hacia la izquierda
		
		assertEquals(new Punto(6,3), v1.getCabeza().getPosicion());
		assertEquals(new Punto(7,3), v1.getCuerpo().getCuerpo().get(0));
		
		v1.mover(new Punto(0, 1));	///Me muevo hacia arriba
		
		assertEquals(new Punto(6,4), v1.getCabeza().getPosicion());
		assertEquals(new Punto(6,3), v1.getCuerpo().getCuerpo().get(0));
	}

}

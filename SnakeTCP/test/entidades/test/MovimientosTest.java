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
		
		assertEquals(5, v1.getCabeza().getCabeza().getX());
		assertEquals(5, v1.getCabeza().getCabeza().getY());
		
		assertEquals(4, v1.getCuerpo().getCuerpo().get(0).getX());
		assertEquals(5, v1.getCuerpo().getCuerpo().get(0).getY());
		
		
		v1.mover(new Punto(1, 0));
		
		assertEquals(6, v1.getCabeza().getCabeza().getX());
		assertEquals(5, v1.getCabeza().getCabeza().getY());
		
		assertEquals(5, v1.getCuerpo().getCuerpo().get(0).getX());
		assertEquals(5, v1.getCuerpo().getCuerpo().get(0).getY());
		
		
		v1.mover(new Punto(1, 0));
		
		assertEquals(7, v1.getCabeza().getCabeza().getX());
		assertEquals(5, v1.getCabeza().getCabeza().getY());
		
		assertEquals(6, v1.getCuerpo().getCuerpo().get(0).getX());
		assertEquals(5, v1.getCuerpo().getCuerpo().get(0).getY());
		
		
		v1.mover(new Punto(0, -1));

		assertEquals(7, v1.getCabeza().getCabeza().getX());
		assertEquals(4, v1.getCabeza().getCabeza().getY());
		
		assertEquals(7, v1.getCuerpo().getCuerpo().get(0).getX());
		assertEquals(5, v1.getCuerpo().getCuerpo().get(0).getY());
		
		v1.mover(new Punto(0, -1));

		assertEquals(7, v1.getCabeza().getCabeza().getX());
		assertEquals(3, v1.getCabeza().getCabeza().getY());
		
		assertEquals(7, v1.getCuerpo().getCuerpo().get(0).getX());
		assertEquals(4, v1.getCuerpo().getCuerpo().get(0).getY());
		
		v1.mover(new Punto(0, -1));
		
		assertEquals(7, v1.getCabeza().getCabeza().getX());
		assertEquals(2, v1.getCabeza().getCabeza().getY());
		
		assertEquals(7, v1.getCuerpo().getCuerpo().get(0).getX());
		assertEquals(3, v1.getCuerpo().getCuerpo().get(0).getY());
	}

}

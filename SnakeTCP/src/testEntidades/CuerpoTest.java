package testEntidades;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import entidades.Cuerpo;
import entidades.Punto;

public class CuerpoTest {

	@Test
	public void moverCuerpo() {
		///Inicialmente la cabeza esta en (4,1)
		///La cabeza ya se movio hacia arriba
		List<Punto> partes = new ArrayList<>();

		partes.add(new Punto(5,1));
		partes.add(new Punto(6,1));
		partes.add(new Punto(6,2));		
		
		List<Punto> resultado = new ArrayList<>();
		
		resultado.add(new Punto(4,1));
		resultado.add(new Punto(5,1));
		resultado.add(new Punto(6,1));
		
		Cuerpo cuerpo = new Cuerpo(partes);
		
		cuerpo.moverCuerpo(new Punto (4,1));
		
		Assert.assertEquals(resultado.get(0).getX(), cuerpo.getCuerpo().get(0).getX());
		Assert.assertEquals(resultado.get(0).getY(), cuerpo.getCuerpo().get(0).getY());
		Assert.assertEquals(resultado.get(1).getX(), cuerpo.getCuerpo().get(1).getX());
		Assert.assertEquals(resultado.get(1).getY(), cuerpo.getCuerpo().get(1).getY());
		Assert.assertEquals(resultado.get(2).getX(), cuerpo.getCuerpo().get(2).getX());
		Assert.assertEquals(resultado.get(2).getY(), cuerpo.getCuerpo().get(2).getY());
		
	}
	
	@Test
	public void moverCuerpo_CabezaEnEsquina_CuerpoEn_U() {
		///Inicialmente la cabeza esta en (0,0)
		///La cabeza se muovió hacia abajo
		
		List<Punto> partes = new ArrayList<>();

		partes.add(new Punto(0,1));
		partes.add(new Punto(1,1));
		partes.add(new Punto(2,1));		
		
		List<Punto> resultado = new ArrayList<>();
		
		resultado.add(new Punto(0,0));
		resultado.add(new Punto(0,1));
		resultado.add(new Punto(1,1));
		
		Cuerpo cuerpo = new Cuerpo(partes);
		
		cuerpo.moverCuerpo(new Punto(0,0));
		
		Assert.assertEquals(resultado.get(0).getX(), cuerpo.getCuerpo().get(0).getX());
		Assert.assertEquals(resultado.get(0).getY(), cuerpo.getCuerpo().get(0).getY());
		Assert.assertEquals(resultado.get(1).getX(), cuerpo.getCuerpo().get(1).getX());
		Assert.assertEquals(resultado.get(1).getY(), cuerpo.getCuerpo().get(1).getY());
		Assert.assertEquals(resultado.get(2).getX(), cuerpo.getCuerpo().get(2).getX());
		Assert.assertEquals(resultado.get(2).getY(), cuerpo.getCuerpo().get(2).getY());
	}
}

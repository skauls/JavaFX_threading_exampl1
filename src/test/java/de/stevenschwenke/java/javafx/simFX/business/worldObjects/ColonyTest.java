package de.stevenschwenke.java.javafx.simFX.business.worldObjects;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.util.HashSet;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import de.stevenschwenke.java.javafx.simFX.business.UserInterface;
import de.stevenschwenke.java.javafx.simFX.business.logicalObjects.CartesianCoordinate;
import de.stevenschwenke.java.javafx.simFX.business.logicalObjects.GroupMembership;

/**
 * Tests for {@link Colony}.
 * 
 * @author Steven Schwenke
 * 
 */
public class ColonyTest {

	@Before
	public void init() {
		World.getInstance().init(Mockito.mock(UserInterface.class), 100, 100,
				false);
	}

	@Test(expected = RuntimeException.class)
	public void attackingItselfIsNotPossible() {

		Colony c = new Colony(GroupMembership.PLAYER, new CartesianCoordinate(
				0, 0));

		c.attack(c);
	}

	@Test
	public void successfulAttackingProcessesEnergyRight() {
		Colony attacker = new Colony(GroupMembership.PLAYER,
				new CartesianCoordinate(0, 0));
		attacker.setAvailableEnergy(20);

		Colony victim = new Colony(GroupMembership.FOE1,
				new CartesianCoordinate(10, 10));
		victim.setAvailableEnergy(5);

		attacker.attack(victim);

		assertEquals(15, attacker.getAvailableEnergy());
		assertEquals(0, victim.getAvailableEnergy());
	}

	@Test
	public void unsuccessfulAttackingProcessesEnergyRight() {
		Colony attacker = new Colony(GroupMembership.PLAYER,
				new CartesianCoordinate(0, 0));
		attacker.setAvailableEnergy(5);

		Colony victim = new Colony(GroupMembership.FOE1,
				new CartesianCoordinate(10, 10));
		victim.setAvailableEnergy(20);

		attacker.attack(victim);

		assertEquals(0, attacker.getAvailableEnergy());
		assertEquals(15, victim.getAvailableEnergy());
	}

	@Test
	public void findNearestResourceReturnsNullWhenThereIsNoResource() {
		// TODO Think about what to return when there is absolutely no resource
		// on the map. Null is bad.

		assertNull(Colony.findNearestResource(new CartesianCoordinate(10, 10),
				new HashSet<Resource>()));
	}

	@Test
	public void findNearestResourceWhenResourceIsOnPosition() {
		Set<Resource> resourceSet = new HashSet<Resource>();
		Resource resourceOnPlace = new Resource(new CartesianCoordinate(10, 10));
		resourceSet.add(resourceOnPlace);

		Resource nearestResource = Colony.findNearestResource(
				new CartesianCoordinate(10, 10), resourceSet);

		assertEquals(resourceOnPlace, nearestResource);
	}

	@Test
	public void findNearestResourceWithTwoResources() {
		Set<Resource> resourceSet = new HashSet<Resource>();
		Resource r1 = new Resource(new CartesianCoordinate(0, 10));
		Resource r2 = new Resource(new CartesianCoordinate(0, 20));
		resourceSet.add(r1);
		resourceSet.add(r2);

		Resource nearestResource = Colony.findNearestResource(
				new CartesianCoordinate(0, 0), resourceSet);

		assertEquals(r1, nearestResource);
	}

	@Test
	public void findNearestResourceWithThreeResources() {
		Set<Resource> resourceSet = new HashSet<Resource>();
		Resource r1 = new Resource(new CartesianCoordinate(0, 30));
		Resource r2 = new Resource(new CartesianCoordinate(0, 20));
		Resource r3 = new Resource(new CartesianCoordinate(0, 30));
		resourceSet.add(r1);
		resourceSet.add(r2);
		resourceSet.add(r3);

		Resource nearestResource = Colony.findNearestResource(
				new CartesianCoordinate(0, 0), resourceSet);

		assertEquals(r2, nearestResource);
	}
}

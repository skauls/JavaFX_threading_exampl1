package business.worldObjects;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import ui.UserInterface;
import business.logicalObjects.CartesianCoordinate;
import business.logicalObjects.GroupMembership;

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
}

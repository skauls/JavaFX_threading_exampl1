package business.logicalObjects;

import business.worldObjects.Colony;
import business.worldObjects.World;
import business.worldObjects.WorldObject;

/**
 * This class holds all UI-relevant interactions between {@link WorldObject}s.
 * This includes the visual manipulation of one {@link WorldObject} by another,
 * for example a {@link Colony} harvests a {@link Resource} by sending out the
 * red-beam-of-ultra-resource-gathering. It does NOT include the creation of a
 * new {@link Colony}; this is only done in the {@link World} without some
 * effect at the UI. If there were some sort of animation for this, it is to be
 * included in this enum as well.
 * 
 * @author Steven Schwenke
 * 
 */
public enum Interaction {

	/** A resource is harvested */
	HARVEST;

}

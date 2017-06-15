package entity;

import mind.need.Hunger;
import mind.need.Safety;
import mind.sense.Sight;
import org.joml.Vector2f;
import world.World;

public class Rabbit extends Mammal {
	
	public Rabbit(World world, Vector2f position) {
		super("rabbit", world, new Vector2f(2, 2), position);

		this.addSense(new Sight.SightBuilder(this).angle(270).build());
		this.addNeed(new Hunger(this, 1.0 / 5.0, 0.15));
		this.addNeed(new Safety(this));
	}
}

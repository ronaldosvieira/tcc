package entity;

import java.util.Random;

import org.joml.Vector2f;

import io.Window;
import render.Animation;
import render.Camera;
import world.World;

public class Wolf extends Entity {
	private int lastAnim;
	
	private float speed;
	private float lastMove;
	private Vector2f direction;
	
	public Wolf(Transform transform) {
		super(EntityAnim.AMOUNT, transform);
		
		for (EntityAnim anim : EntityAnim.values()) {
			setAnimation(anim.index(), new Animation(anim.amount(), 8, "wolf/" + anim.path()));
		}
		
		this.speed = 4f;
		this.lastMove = 0.0f;
		this.direction = new Vector2f(0, 0);
	}
	
	public void move(float delta) {
		Vector2f movement = new Vector2f();
		movement.add(speed * delta * direction.x, 
				speed * delta * direction.y);
		super.move(movement);
	}
	
	public void updateAnimation() {
		if (direction.length() > 0) {
			if (Math.abs(direction.x) > Math.abs(direction.y)) {
				if (direction.x > 0) useAnimation(EntityAnim.WALK_E.index());
				else useAnimation(EntityAnim.WALK_W.index());
			} else {
				if (direction.y > 0) useAnimation(EntityAnim.WALK_N.index());
				else useAnimation(EntityAnim.WALK_S.index());
			}
		} else {
			if (lastAnim >= 4) useAnimation(lastAnim - 4);
		}
	}

	@Override
	public void update(float delta, Window window, Camera camera, World world) {
		Random random = new Random();
		
		if (lastMove < random.nextFloat() * 2.0f + 1.0f) {
			lastMove += delta;
		} else {
			lastMove = 0.0f;
			
			if (direction.length() == 0) {
				this.direction.set(random.nextFloat() * 2 - 1, 
						random.nextFloat() * 2 - 1).normalize();
			} else {
				direction.set(0.0f, 0.0f);
			}
		}
		
		updateAnimation();
		move(delta);
	}

	@Override
	public void useAnimation(int index) {
		this.lastAnim = index;
		super.useAnimation(index);
	}
}

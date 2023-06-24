package game.objects.creatures;

import java.awt.*;

import game.Game;
import game.objects.GameObject;

public abstract class Creature extends GameObject{

	protected final Game game;
	protected double centerX;
	protected double centerY;
	protected double speed;
	protected final double radius;
	protected Color color;
	protected double preferredMovingDirection;
	protected double rotation;
	protected double rotationSpeed;
	protected double movingDirectionX;
	protected double movingDirectionY;
	
	
	public Creature(Game game, double centerX, double centerY, double radius, double speed, Color color, double rotationSpeed) {
		this.game = game;
		this.centerX = centerX;
		this.centerY = centerY;
		this.radius = radius;
		this.speed = speed;
		this.color = color;
		this.rotationSpeed = rotationSpeed;
	}

	public void tick() {
		updateRotation();
		tickMovingDirection();

		centerX += movingDirectionX * speed;
		centerY += movingDirectionY * speed;
	}

	private void tickMovingDirection() {
		movingDirectionX = Math.cos(Math.toRadians(rotation)) * preferredMovingDirection;
		movingDirectionY = Math.sin(Math.toRadians(rotation)) * preferredMovingDirection;
	}

	private void updateRotation(){
		if (rotation > 6.3){
			rotation = 0;
		} else if (rotation < 0 ) {
			rotation = 6.3;
		}
	}

	public double getCenterX() {
		return centerX;
	}

	public double getCenterY() {
		return centerY;
	}

	public double getSpeed() {
		return speed;
	}

	public void setSpeed(double speed) {
		this.speed = speed;
	}

	public double getRadius() {
		return radius;
	}
	
	public void setRotation(double rotation){
		this.rotation = rotation;
	}
	
}

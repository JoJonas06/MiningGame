package game.objects.creatures;

import java.awt.*;

import game.Collision;
import game.Game;
import game.GameMap;
import game.objects.GameObject;

public abstract class Creature extends GameObject{

	protected final Game game;
	protected double centerX;
	protected double centerY;
	protected double speed;
	protected final double radius;
	protected Color color;
	protected int moveForward;
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
		GameMap map = game.getMap();
		tickMovingDirection();

		centerX += movingDirectionX * speed;
		centerY += movingDirectionY * speed;

		if(Collision.wallCollision(game)){
			if(centerX <= 1) {
				centerX += 0.1;
			}
			if(centerX > map.getWidth() - 0.5) {
				centerX -= 0.1;
			}
			if(centerY <= 1) {
				centerY += 0.1;
			}
			if(centerY > map.getHeight() - 0.5) {
				centerY -= 0.1;
			}
		}

		Collision.rockCollision(game);
		Collision.airCollision(game);
	}

	private void tickMovingDirection() {
		movingDirectionX = Math.cos(rotation) * moveForward;
		movingDirectionY = Math.sin(rotation) * moveForward;
	}

	public double getCenterX() {
		return centerX;
	}

	public double getCenterY() {
		return centerY;
	}

	public void setCenterX(double centerX) {
		this.centerX = centerX;
	}

	public void setCenterY(double centerY) {
		this.centerY = centerY;
	}

	public double getSpeed() {
		return speed;
	}

	public void setSpeed(double speed) {
		this.speed = speed;
	}
}

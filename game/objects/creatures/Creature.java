package game.objects.creatures;

import java.awt.*;

import game.Collision;
import game.Game;
import game.GameMap;
import game.objects.GameObject;

public abstract class Creature extends GameObject{

	protected final Game game;
	protected double screenX;
	protected double screenY;
	protected double worldX;
	protected double worldY;
	protected double speed;
	protected double radius;
	protected Color color;
	protected int moveForward;
	protected double rotation;
	protected double rotationSpeed;
	protected double movingDirectionX;
	protected double movingDirectionY;
	
	
	public Creature(Game game, double screenX, double screenY, double radius, double speed, Color color, double rotationSpeed) {
		this.game = game;
		this.screenX = screenX;
		this.screenY = screenY;
		this.radius = radius;
		this.speed = speed;
		this.color = color;
		this.rotationSpeed = rotationSpeed;
	}

	public void tick() {
		GameMap map = game.getMap();
		tickMovingDirection();

		screenX += movingDirectionX * speed;
		screenY += movingDirectionY * speed;

		Collision.waterCollision(game);
		Collision.rockCollision(game);
		Collision.airCollision(game);
	}

	private void tickMovingDirection() {
		movingDirectionX = Math.cos(rotation) * moveForward;
		movingDirectionY = Math.sin(rotation) * moveForward;
	}

	public double getScreenX() {
		return screenX;
	}

	public double getScreenY() {
		return screenY;
	}

	public double getWorldX(){return worldX;}
	public double getWorldY(){return worldY;}
	public void setScreenX(double screenX) {
		this.screenX = screenX;
	}

	public void setScreenY(double screenY) {
		this.screenY = screenY;
	}

	public double getSpeed() {
		return speed;
	}

	public void setSpeed(double speed) {
		this.speed = speed;
	}
}

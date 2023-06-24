package game.objects.creatures;

import java.awt.*;

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
	protected double angle;
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
		updateAngle();
		tickMovingDirection();

		centerX += movingDirectionX * speed;
		centerY += movingDirectionY * speed;

		tickWallCollisions();
	}

	private void tickMovingDirection() {
			movingDirectionX = Math.cos(Math.toRadians(angle)) * moveForward;
			movingDirectionY = Math.sin(Math.toRadians(angle)) * moveForward;
	}

	private void updateAngle(){
		if (angle > 359){
			angle = 0;
		} else if (angle < 0 ) {
			angle = 359;
		}
	}

	private void tickWallCollisions(){
		GameMap map = game.getMap();

		if(map.isNotFree((int) centerX, (int)centerY)){
			System.out.println("Da ist kein Block");
		}else{
			System.out.println("Block");
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
	
	public void setAngle(double angle){
		this.angle = angle;
	}
	
}

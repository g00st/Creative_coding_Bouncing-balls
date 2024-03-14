import processing.core.PApplet;
import processing.core.PVector;
import processing.opengl.PShader;
import controlP5.*;

import java.io.Console;
import java.lang.constant.Constable;
import java.util.ArrayList;


public class MySketch extends PApplet {
	ControlP5 cp5;
	int points = 5;
	float maxVelocity = 5;
	boolean tringles = false;

	ArrayList<Ball> balls = new ArrayList<Ball>();
	public void setup() {
		this.getSurface().setResizable(true);
		cp5 = new ControlP5(this);
		cp5.addSlider("points")
				.setPosition(10, 10)
				.setRange(1, 100)
				.setValue(5)
				.setSize(100, 20).onChange( value -> {
					balls.clear();
					for (int i = 0; i < points; i++) {
						balls.add(new Ball(new PVector(random(width), random(height)), new PVector(random(-maxVelocity, maxVelocity), random(-maxVelocity, maxVelocity))));
					}
				});
		cp5.addSlider("maxVelocity")
				.setPosition(10, 40)
				.setRange(1f, 10f)
				.setValue(5)
				.setSize(100, 20).onChange( value -> {
						Ball.maxVelocity = maxVelocity;
				});
		cp5.addToggle("tringles")
				.setPosition(10, 70)
				.setSize(100, 20);
	}


	public void settings() {
		size(500, 500);
	}



	public void draw(){
		Ball.width = width;
		Ball.height = height;
		Ball.tringles = tringles;
		background(64);
		ellipse(mouseX, mouseY, 20, 20);
		for (var ball : balls) {
			PVector acc = PVector.sub(new PVector(mouseX, mouseY), ball.position).normalize().mult(0.5f);
			if (mousePressed) {
				acc.mult(-1);
			}
			ball.update(acc);
			ball.draw(this);
		}
	}
}

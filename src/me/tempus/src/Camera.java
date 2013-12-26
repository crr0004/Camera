package me.tempus.src;

import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.util.vector.Vector3f;

public class Camera {

	private Vector3f pos = new Vector3f(0,10,0);
	private Vector3f rotate = new Vector3f(0,0,0);
	
	private final float cameraRotationVelocity = 100;
	
	private final float cameraVelocity = 1f;
	
	public void transform(){
		PVM.rotate(rotate.x, rotate.y, rotate.z);
		PVM.translate(-pos.x, -pos.y, -pos.z);
		
		PVM.bufferMatricies();
	}
	
	
	public void pollInput(){
		
		//if(Mouse.isButtonDown(0)){
			processMouseVelocity(Mouse.getDX(), Mouse.getDY());
		//}
		
		while(Keyboard.next()){
			final boolean state = Keyboard.getEventKeyState();
			switch (Keyboard.getEventKey()){
			case Keyboard.KEY_UP:
				if(state){
					pos.z += -cameraVelocity * Math.cos(Math.toRadians(rotate.y));
					pos.x += cameraVelocity * Math.sin(Math.toRadians(rotate.y));
				}
				break;
			case Keyboard.KEY_DOWN:
				if(state){
					pos.z += cameraVelocity * Math.cos(Math.toRadians(rotate.y));
					pos.x += -cameraVelocity * Math.sin(Math.toRadians(rotate.y));
				}
				break;
			case Keyboard.KEY_LEFT:
				if(state){
					pos.z += -cameraVelocity * Math.sin(Math.toRadians(rotate.y));
					pos.x += -cameraVelocity * Math.cos(Math.toRadians(rotate.y));
				}
				break;
			case Keyboard.KEY_RIGHT:
				if(state){
					pos.z += cameraVelocity * Math.sin(Math.toRadians(rotate.y));
					pos.x += cameraVelocity * Math.cos(Math.toRadians(rotate.y));
				}
				break;
			case Keyboard.KEY_ESCAPE:
				System.exit(0);
				break;
			case Keyboard.KEY_SPACE:
				rotate.x = 0;
				rotate.y = 0;
				rotate.z = 0;
				pos.x = 0;
				pos.z = 0;
				pos.y = 10;
				break;
			}
		}
	}
	
	private void processMouseVelocity(float x, float y){
		
		
		rotate.y += x * (cameraRotationVelocity /100f); // Side to side
		if(rotate.y >= 360){
			rotate.y = 0;
		}else if(rotate.y <= 0){
			rotate.y = 360;
		}
		
		//final float j = y * (cameraRotationVelocity /100f);
		//if((rotate.x + j) < 90 && (rotate.x + j) > 0){
			//rotate.x += j * Math.cos(Math.toRadians(rotate.y)); // Up and down
			//rotate.z += j * Math.sin(Math.toRadians(rotate.y)); // Up and down
		//}else if(((rotate.x + j) > -90) && ((rotate.x + j) < 0)){
			//rotate.x += j;
		//}
		
		/**
		if(rotate.x > 90){
			rotate.x = 90;
		}else if (rotate.x < -90){
			rotate.x = -90;
		}
		if(rotate.z > 90){
			rotate.z = 90;
		}else if (rotate.z < -90){
			rotate.z = -90;
		}
		*/
		
	}
}

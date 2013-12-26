package me.tempus.src;

import org.lwjgl.LWJGLException;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL20;
import org.lwjgl.util.vector.Vector3f;

public class CameraSrc {

	private boolean done = false;
	
	public NoLightingShader shader;
	
	final Box box = new Box(new Vector3f(2.5f,5,-15f), new Vector3f(0,0,1));
	final Plane plane = new Plane(new Vector3f(0,0,0), new Vector3f(0,1,0));
	final Camera camera = new Camera();
	
	private void createScreen(int width, int height){
		try {

			//Display.setFullscreen(fullscreen);

			Display.setDisplayMode(new DisplayMode(width, height));
			Display.setTitle("Camera");
				Display.create();
			
			GL11.glViewport(0, 0, width, height);

			GL11.glEnable(GL11.GL_TEXTURE_2D);                          // Enable Texture Mapping
			
			GL11.glShadeModel(GL11.GL_SMOOTH);                          // Enables Smooth Color Shading
			GL11.glClearColor(0.5f, 0.5f, 0.5f, 0.0f);                // This Will Clear The Background Color To Black
			GL11.glClearDepth(1.0);                                   // Enables Clearing Of The Depth Buffer
			GL11.glEnable(GL11.GL_DEPTH_TEST);                          // Enables Depth Testing
			GL11.glDepthFunc(GL11.GL_LEQUAL);                           // The Type Of Depth Test To Do
			PVM.setUpProjection(45f, width, height, 0.1f, 100f);
			System.out.println("OpenGL version: " + GL11.glGetString(GL11.GL_VERSION));
		} catch (LWJGLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void initOPG(){
		Keyboard.enableRepeatEvents(true);
	}
	
	public void loop(){
		
		createScreen(1024, 1024);
		initOPG();
		setUpShaders();
		
		plane.create();
		box.create();
		
		while(!done){
			update();
			draw();
			
		}
		return;
	}
	
	private void setUpShaders(){
		shader = new NoLightingShader("TexturedShader.vert", "TexturedShader.frag");
		shader.load();
	}
	
	private void update(){
		
		camera.pollInput();
		
	}
	
	private void draw(){
		
		GL20.glUseProgram(shader.getShaderID());
		GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);
		
		PVM.loadIdentity();
		camera.transform();
		
		box.draw(shader);
		plane.draw(shader);
		
		GL20.glUseProgram(0);
		Display.sync(60);
		Display.update();
		
	}
	
	
	
	public static void main(String[] args){
		(new CameraSrc()).loop();
	}
}

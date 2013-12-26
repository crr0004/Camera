package me.tempus.src;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.nio.FloatBuffer;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL15;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;

public class Utilities {

	
	/**
	 * 
	 * @param vertPath Path to vertex (vert) shader source
	 * @param fragPath Path to fragment (frag) shader source
	 * @return Vertsouce and FragSouce in stringbuilder array. VertSource at posistion 0 in array
	 */
	
	public static StringBuilder[] loadShaders(String vertPath, String fragPath){
		
		StringBuilder vertSource = new StringBuilder();
		StringBuilder fragSource = new StringBuilder();
		
		try{
			File file = new File(vertPath);
			BufferedReader reader = new BufferedReader(new FileReader(vertPath));
			String line;
			while((line = reader.readLine()) != null){
				vertSource.append(line).append("\n");
			}
			reader.close();
			BufferedReader reader1 = new BufferedReader(new FileReader(fragPath));
			while((line = reader1.readLine()) != null){
				fragSource.append(line).append("\n");
			}
			reader1.close();
		}catch(Exception e){
			e.printStackTrace();
		}
				
		
		return new StringBuilder[]{vertSource, fragSource};
	}
	
	/**
	 * Creates a VAO with vertex and texture coord vbos
	 * It will bind the vertex shader location to 0
	 * 
	 * @param vertices Vertices
	 * @param textureCoords
	 * @return The ID of the VAO to draw
	 */
	public static int creatVAO(FloatBuffer vertices, FloatBuffer textureCoords){
		final int vaoID = GL30.glGenVertexArrays();
		GL30.glBindVertexArray(vaoID);
		
		final int vboId = GL15.glGenBuffers();
		GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, vboId);
		GL15.glBufferData(GL15.GL_ARRAY_BUFFER, vertices, GL15.GL_STATIC_DRAW);
		GL20.glVertexAttribPointer(0, 3, GL11.GL_FLOAT, false, 0, 0);
		GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, 0);
		
		final int vbotId = GL15.glGenBuffers();
		GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, vbotId);
		GL15.glBufferData(GL15.GL_ARRAY_BUFFER, textureCoords, GL15.GL_STATIC_DRAW);
		GL20.glVertexAttribPointer(1, 2, GL11.GL_FLOAT, false, 0, 0);
		GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, 0);
		GL30.glBindVertexArray(0);
		
		return vaoID;
	}
}

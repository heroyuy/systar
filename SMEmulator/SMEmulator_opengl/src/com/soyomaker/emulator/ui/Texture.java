package com.soyomaker.emulator.ui;

import static org.lwjgl.opengl.EXTFramebufferObject.GL_COLOR_ATTACHMENT0_EXT;
import static org.lwjgl.opengl.EXTFramebufferObject.GL_FRAMEBUFFER_EXT;
import static org.lwjgl.opengl.EXTFramebufferObject.glBindFramebufferEXT;
import static org.lwjgl.opengl.EXTFramebufferObject.glFramebufferTexture2DEXT;
import static org.lwjgl.opengl.EXTFramebufferObject.glGenFramebuffersEXT;
import static org.lwjgl.opengl.GL11.GL_TEXTURE_2D;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.color.ColorSpace;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.ComponentColorModel;
import java.awt.image.DataBuffer;
import java.awt.image.DataBufferByte;
import java.awt.image.Raster;
import java.awt.image.WritableRaster;
import java.io.File;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.Hashtable;

import javax.imageio.ImageIO;

import org.lwjgl.opengl.EXTTextureMirrorClamp;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GLContext;

public class Texture {

	/** The colour model including alpha for the GL image */
	private static final ColorModel glAlphaColorModel = new ComponentColorModel(
			ColorSpace.getInstance(ColorSpace.CS_sRGB), new int[] { 8, 8, 8, 8 }, true, false,
			ComponentColorModel.TRANSLUCENT, DataBuffer.TYPE_BYTE);

	/** The colour model for the GL image */
	private static final ColorModel glColorModel = new ComponentColorModel(ColorSpace.getInstance(ColorSpace.CS_sRGB),
			new int[] { 8, 8, 8, 0 }, false, false, ComponentColorModel.OPAQUE, DataBuffer.TYPE_BYTE);

	/**
	 * 将图像转换为ByteBuffer
	 * 
	 * @param image
	 *            图像
	 * @return 图像数据的ByteBuffer
	 */
	@SuppressWarnings("rawtypes")
	public static ByteBuffer imageToByteBuffer(BufferedImage image) {
		ByteBuffer imageBuffer = null;
		WritableRaster raster = null;
		BufferedImage texImage = null;
		int width = image.getWidth();
		int height = image.getHeight();
		// 创建一个raster作为数据源
		boolean hasAlpha = image.getColorModel().hasAlpha();
		if (hasAlpha) {
			raster = Raster.createInterleavedRaster(DataBuffer.TYPE_BYTE, width, height, 4, null);
			texImage = new BufferedImage(glAlphaColorModel, raster, false, new Hashtable());
		} else {
			raster = Raster.createInterleavedRaster(DataBuffer.TYPE_BYTE, width, height, 3, null);
			texImage = new BufferedImage(glColorModel, raster, false, new Hashtable());
		}
		// 复制数据
		Graphics2D g = (Graphics2D) texImage.getGraphics();

		if (hasAlpha) {
			// 兼容mac系统需要进行下面操作
			g.setColor(new java.awt.Color(0f, 0f, 0f, 0f));
			g.fillRect(0, 0, width, height);
		}
		g.drawImage(image, 0, 0, null);
		// 创建ByteBuffer
		byte[] data = ((DataBufferByte) texImage.getRaster().getDataBuffer()).getData();
		imageBuffer = ByteBuffer.allocateDirect(data.length);
		imageBuffer.order(ByteOrder.nativeOrder());
		imageBuffer.put(data, 0, data.length);
		imageBuffer.flip();
		g.dispose();

		return imageBuffer;

	}

	private int frameBufferID = 0;

	private int textureID = 0;

	private int width = 0;

	private int height = 0;

	private Painter painter = null;

	/**
	 * 创建空白纹理
	 * 
	 * @param width
	 *            纹理宽度
	 * @param height
	 *            纹理高度
	 */
	public Texture(int width, int height) {
		initWithBufferedImage(new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB));
	}

	/**
	 * 从文件中创建纹理
	 * 
	 * @param file
	 *            纹理文件
	 */
	public Texture(String file) {
		try {
			BufferedImage image = ImageIO.read(new File(file));
			BufferedImage texImage = new BufferedImage(image.getWidth(), image.getHeight(), BufferedImage.TYPE_INT_ARGB);
			Graphics g = texImage.getGraphics();
			g.drawImage(image, 0, 0, null);
			g.dispose();
			initWithBufferedImage(texImage);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 清除图像
	 * 
	 * @param x
	 *            x坐标
	 * @param y
	 *            y坐标
	 * @param width
	 *            宽度
	 * @param height
	 *            高度
	 */
	public void clear(int x, int y, int width, int height) {
		// TODO 改进
		ByteBuffer buffer = ByteBuffer.allocateDirect(width * height);
		this.setData(buffer, x, y, width, height);
	}

	/**
	 * 将图像的源区域拷贝到指定点
	 * 
	 * @param srcX
	 *            源区域x坐标
	 * @param srcY
	 *            源区域y坐标
	 * @param srcWidth
	 *            源区域宽度
	 * @param srcHeight
	 *            源区域高度
	 * @param x
	 *            指定点x坐标
	 * @param y
	 *            指定点y坐标
	 */
	public void copyArea(int srcX, int srcY, int srcWidth, int srcHeight, int x, int y) {
		// TODO 实现
	}

	/**
	 * 获取纹理有效高度
	 * 
	 * @return 纹理有效高度
	 */
	public int getHeight() {
		return height;
	}

	/**
	 * 获取纹理的画笔
	 * 
	 * @return 画笔
	 */
	public Painter getPainter() {
		if (this.frameBufferID == 0 || this.painter == null) {
			this.frameBufferID = glGenFramebuffersEXT();
			glBindFramebufferEXT(GL_FRAMEBUFFER_EXT, this.frameBufferID);
			glFramebufferTexture2DEXT(GL_FRAMEBUFFER_EXT, GL_COLOR_ATTACHMENT0_EXT, GL_TEXTURE_2D, this.textureID, 0);
			glBindFramebufferEXT(GL_FRAMEBUFFER_EXT, 0);
			this.painter = new Painter(this.frameBufferID);
			this.painter.setViewPort(new Rect(0, 0, this.width, this.height));
		}
		return this.painter;
	}

	public Color getRGB(int x, int y) {
		Painter p = this.getPainter();
		p.startPaint();
		ByteBuffer buffer = ByteBuffer.allocateDirect(4);
		GL11.glReadPixels(x, y, 1, 1, GL11.GL_RGBA, GL11.GL_UNSIGNED_BYTE, buffer);
		p.stopPaint();
		int rgba = buffer.getInt();
		Color c = Color.colorFromRGBA(rgba);
		return c;
	}

	/**
	 * 获取纹理的ID
	 * 
	 * @return 纹理的ID
	 */
	public int getTextureID() {
		return textureID;
	}

	/**
	 * 获取纹理有效宽度
	 * 
	 * @return 获取纹理有效宽度
	 */
	public int getWidth() {
		return width;
	}

	public Texture scaleTexture(int width, int height) {
		Texture texture = new Texture(width, height);
		Painter p = texture.getPainter();
		p.startPaint();
		p.scale(1.0f * width / this.getWidth(), 1.0f * height / this.getHeight(), 0, 0);
		p.drawTexture(this, 0, 0, Painter.LT);
		p.stopPaint();
		return texture;
	}

	/**
	 * 设置纹理上的数据
	 * 
	 * @param byteBuffer
	 *            数据
	 * @param x
	 *            替换区域的x坐标
	 * @param y
	 *            替换区域的y坐标
	 * @param width
	 *            替换区域的宽度
	 * @param height
	 *            替换区域的高度
	 */
	public void setData(ByteBuffer byteBuffer, int x, int y, int width, int height) {
		GL11.glBindTexture(GL11.GL_TEXTURE_2D, this.textureID);
		GL11.glTexSubImage2D(GL11.GL_TEXTURE_2D, 0, x, y, width, height, GL11.GL_RGBA, GL11.GL_UNSIGNED_BYTE,
				byteBuffer);
	}

	/**
	 * 子纹理
	 * 
	 * @param x
	 *            区域x坐标
	 * @param y
	 *            区域y坐标
	 * @param width
	 *            区域宽度
	 * @param height
	 *            区域高度
	 * @return
	 */
	public Texture subTexture(int x, int y, int width, int height) {
		Texture texture = new Texture(width, height);
		Painter p = texture.getPainter();
		p.startPaint();
		p.drawTexture(this, x, y, width, height, 0, 0, Painter.LT);
		p.stopPaint();
		return texture;
	}

	/**
	 * 变色处理
	 * 
	 * @param red
	 *            红色分量
	 * @param green
	 *            绿色分量
	 * @param blue
	 *            蓝色分量
	 * @return 变色处理后的图像
	 */
	public Texture tone(float red, float green, float blue) {
		// TODO 实现
		return this;
	}

	public String toString() {
		return "texture[" + this.textureID + "]{w=" + this.getWidth() + " h=" + this.getHeight() + "}";
	}

	private void initWithBufferedImage(BufferedImage image) {
		// 纹理有效高度
		this.width = image.getWidth();
		this.height = image.getHeight();
		// 创建纹理
		this.textureID = GL11.glGenTextures();
		GL11.glBindTexture(GL11.GL_TEXTURE_2D, this.textureID);
		GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MIN_FILTER, GL11.GL_LINEAR);
		GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MAG_FILTER, GL11.GL_LINEAR);
		if (GLContext.getCapabilities().GL_EXT_texture_mirror_clamp) {
			GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_WRAP_S,
					EXTTextureMirrorClamp.GL_MIRROR_CLAMP_TO_EDGE_EXT);
			GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_WRAP_T,
					EXTTextureMirrorClamp.GL_MIRROR_CLAMP_TO_EDGE_EXT);
		} else {
			GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_WRAP_S, GL11.GL_CLAMP);
			GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_WRAP_T, GL11.GL_CLAMP);
		}
		GL11.glTexImage2D(GL11.GL_TEXTURE_2D, 0, GL11.GL_RGBA8, this.width, this.height, 0, GL11.GL_RGBA,
				GL11.GL_UNSIGNED_BYTE, Texture.imageToByteBuffer(image));
	}
}

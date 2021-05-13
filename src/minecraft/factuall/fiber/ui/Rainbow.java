package factuall.fiber.ui;

import java.awt.Color;
import java.lang.Math;

public class Rainbow {
	
	static int currentColorOffset;
	final static int ARRAY_SIZE = 500000;
	static double jump = 360.0 / (ARRAY_SIZE*1.0);
	static Color[] colors = new Color[ARRAY_SIZE];;
	
	public static void init() {
		for (int i = 0; i < colors.length; i++) {
			colors[i] = Color.getHSBColor((float)(jump*i), 1f, 1f);
		}
	}

	public static void RainbowCycle() {
		if(currentColorOffset < ARRAY_SIZE-1) {
			currentColorOffset++;
		}else {
			currentColorOffset = 0;
		}
	}
	
	public static Color getRainbow() {
		return colors[currentColorOffset];
	}
	
	public static Color getRainbowWithOffset(int offset) {
		if(currentColorOffset+offset < ARRAY_SIZE-1) {
			return colors[currentColorOffset + offset];
		}else {
			return colors[(currentColorOffset + offset) - ARRAY_SIZE];
		}
	}
	
}


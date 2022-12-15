/*
 *  MIT License
 *
 *  Copyright (c) 2020 Ridanisaurus
 *
 *  Permission is hereby granted, free of charge, to any person obtaining a copy
 *  of this software and associated documentation files (the "Software"), to deal
 *  in the Software without restriction, including without limitation the rights
 *  to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 *  copies of the Software, and to permit persons to whom the Software is
 *  furnished to do so, subject to the following conditions:
 *
 *  The above copyright notice and this permission notice shall be included in all
 *  copies or substantial portions of the Software.
 *
 *  THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 *  IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 *  FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 *  AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 *  LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 *  OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 *  SOFTWARE.
 */

package com.ridanisaurus.emendatusenigmatica.util;

import java.awt.*;

public class ColorHelper {
	public static int HEXtoDEC(String hex) {
		return Integer.parseInt(hex, 16);
	}

	public static Color HEXtoColor(String hex) {
		String digits;
		int n = hex.length();
		if (hex.startsWith("#")) {
			digits = hex.substring(1, Math.min(hex.length(), 7));
		} else {
			digits = hex;
		}
		if (digits.length() == 3) {
			final String r = digits.substring(0, 1);
			final String g = digits.substring(1, 2);
			final String b = digits.substring(2, 3);
			digits = String.format("%s%s%s%s%s%s", r, r, g, g, b, b);
		}
		String hstr = "0x" + digits;
		Color c;
		try {
			c = Color.decode(hstr);
		} catch (NumberFormatException nfe) {
			c = null;
		}
		return c;
	}

	public static float[] HEXtoRGB(String hex) {
		int r = HEXtoColor(hex).getRed();
		int g = HEXtoColor(hex).getGreen();
		int b = HEXtoColor(hex).getBlue();
		return Color.RGBtoHSB(r,g,b,null);
	}

	public static float[] HEXtoHSV(String hex) {
		float r = HEXtoColor(hex).getRed();
		float g = HEXtoColor(hex).getGreen();
		float b = HEXtoColor(hex).getBlue();
		float h, s, v;
		float min, max, delta;
		min = Math.min(Math.min(r, g), b);
		max = Math.max(Math.max(r, g), b);
		// Value
		v = max;
		delta = max - min;
		// Saturation
		if (max != 0)
			s = delta / max;
		else {
			s = 0;
			h = -1;
			return new float[] { h, s, v };
		}
		// Hue
		if (r == max)
			h = (g - b) / delta; // between yellow & magenta
		else if (g == max)
			h = 2 + (b - r) / delta; // between cyan & yellow
		else
			h = 4 + (r - g) / delta; // between magenta & cyan
		h *= 60; // degrees
		if (h < 0) h += 360;
		h = (float) (h * 1.0);
		s = (float) (s * 100.0);
		v = (float) ((v / 256.0) * 100.0);
		return new float[] { Math.round(h), Math.round(s), Math.round(v) };
	}

	public static float getHue(String hex) {
		float[] hsv = HEXtoHSV(hex);
		return hsv[0];
	}

	public static float getSaturation(String hex) {
		float[] hsv = HEXtoHSV(hex);
		return hsv[1];
	}

	public static float getValue(String hex) {
		float[] hsv = HEXtoHSV(hex);
		return hsv[2];
	}

	public static String RGBtoDEC(float r, float g, float b) {
		String rs = Integer.toHexString((int)(r * 256));
		String gs = Integer.toHexString((int)(g * 256));
		String bs = Integer.toHexString((int)(b * 256));
		return rs + gs + bs;
	}

	public static String HSVtoHEX(float h, float s, float v) {
		float R, G, B;
		h /= 360f;
		s /= 100f;
		v /= 100f;
		if (s == 0)
		{
			R = v * 255;
			G = v * 255;
			B = v * 255;
		} else {
			float var_h = h * 6;
			if (var_h == 6)
				var_h = 0; // H must be < 1
			int var_i = (int) Math.floor((double) var_h); // Or ... var_i =
			// floor( var_h )
			float var_1 = v * (1 - s);
			float var_2 = v * (1 - s * (var_h - var_i));
			float var_3 = v * (1 - s * (1 - (var_h - var_i)));
			float var_r;
			float var_g;
			float var_b;
			if (var_i == 0) {
				var_r = v;
				var_g = var_3;
				var_b = var_1;
			} else if (var_i == 1) {
				var_r = var_2;
				var_g = v;
				var_b = var_1;
			} else if (var_i == 2) {
				var_r = var_1;
				var_g = v;
				var_b = var_3;
			} else if (var_i == 3) {
				var_r = var_1;
				var_g = var_2;
				var_b = v;
			} else if (var_i == 4) {
				var_r = var_3;
				var_g = var_1;
				var_b = v;
			} else {
				var_r = v;
				var_g = var_1;
				var_b = var_2;
			}
			R = var_r * 255; // RGB results from 0 to 255
			G = var_g * 255;
			B = var_b * 255;
		}

		String rs = Integer.toHexString((int) (R));
		String gs = Integer.toHexString((int) (G));
		String bs = Integer.toHexString((int) (B));

		if (rs.length() == 1)
			rs = "0" + rs;
		if (gs.length() == 1)
			gs = "0" + gs;
		if (bs.length() == 1)
			bs = "0" + bs;
		return rs + gs + bs;
	}

	public static String hueShift(String hex, int factor, boolean highlight) {
		float h = ColorHelper.getHue(hex);
		float s = ColorHelper.getSaturation(hex);
		float v = ColorHelper.getValue(hex);
		float h2 = 0;
		float s2;
		float v2;
//			 0 Red
//			 60 Yellow
//			 120 Green
//			 180 Cyan
//			 240 Blue
//			 300 Magenta
		if (highlight) {
			if (h >= 0 && h < 60) {
				h2 = h + (5 * factor);
			}
			if (h >= 60 && h < 120) {
				h2 = h - (5 * factor);
			}
			if (h >= 120 && h < 180) {
				h2 = h + (10 * factor);
			}
			if (h >= 180 && h < 240) {
				h2 = h - (10 * factor);
			}
			if (h >= 240 && h < 300) {
				h2 = h + (10 * factor);
			}
			if (h >= 300 && h <= 360) {
				h2 = h + (10 * factor);
			}
			s2 = s - (2 * factor);
			v2 = v + (10 * factor);
		} else {
			if (h >= 0 && h < 60) {
				h2 = h - (5 * factor);
			}
			if (h >= 60 && h < 120) {
				h2 = h + (5 * factor);
			}
			if (h >= 120 && h < 180) {
				h2 = h - (10 * factor);
			}
			if (h >= 180 && h < 240) {
				h2 = h + (10 * factor);
			}
			if (h >= 240 && h < 300) {
				h2 = h - (10 * factor);
			}
			if (h >= 300 && h <= 360) {
				h2 = h - (10 * factor);
			}
			s2 = s + (5 * factor);
			v2 = v - (20 * factor);
		}

		if (h2 < 0 || h2 > 360) h2 = ((h2 + 360) % 360);

		if (s2 < 0) s2 = 10;
		if (s2 > 100) s2 = 100;

		if (v2 < 0) v2 = 10;
		if (v2 > 100) v2 = 100;

		return ColorHelper.HSVtoHEX(h2, s2, v2);
	}
}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gudigital.util;

import java.util.StringTokenizer;
import java.util.Vector;

/**
 * 
 * @author zj
 */
public class LangUtil {

	public static boolean between(int n, int n1, int n2) {
		int min = Math.min(n1, n2);
		int max = Math.max(n1, n2);
		return (n >= min && n <= max);
	}

	public static int[] bytesToInts(byte[] bs) {
		int[] a = new int[bs.length / 4];
		for (int i = 0; i < a.length; i++) {
			int pos = i << 2;
			a[i] = (bs[pos] << 24 & 0xFF000000) | (bs[pos + 1] << 16 & 0x00FF0000) | (bs[pos + 2] << 8 & 0x0000FF00)
					| (bs[pos + 3] & 0x000000FF);
		}
		return a;
	}

	public static void copyMatrix(int[][] dst, int[][] frm) {
		if (dst != null && frm != null) {
			int minX = Math.min(dst.length, frm.length);
			for (int i = 0; i < minX; i++) {
				int minY = Math.min(dst[i].length, frm[i].length);
				for (int j = 0; j < minY; j++) {
					dst[i][j] = frm[i][j];
				}
			}
		}
	}

	public static Object createClassInstance(String className) {
		try {
			return Class.forName(className).newInstance();
		} catch (InstantiationException e) {
		} catch (IllegalAccessException e) {
		} catch (ClassNotFoundException e) {
		}
		return null;
	}

	public static String[] getTokens(String s, String splitter) {
		if (s == null) {
			return new String[0];
		}

		StringTokenizer stn = new StringTokenizer(s, splitter);
		Vector<String> ret = new Vector<String>();
		while (stn.hasMoreTokens()) {
			ret.add(stn.nextToken());
		}
		String[] ss = new String[ret.size()];
		for (int i = 0; i < ss.length; i++) {
			ss[i] = ret.get(i);
		}
		return ss;
	}

	public static byte[] intsToBytes(int[] is) {
		byte[] a = new byte[is.length << 2];
		for (int i = 0; i < is.length; i++) {
			int pos = i << 2;
			a[pos] = (byte) (is[i] >> 24);
			a[pos + 1] = (byte) (is[i] >> 16);
			a[pos + 2] = (byte) (is[i] >> 8);
			a[pos + 3] = (byte) (is[i]);
		}
		return a;
	}

	public static int parseInt(String s) {
		try {
			return Integer.parseInt(s);
		} catch (NumberFormatException numberFormatException) {
			return 0;
		}
	}

	public static void resetMaxtrix(int[][] dst, int v) {
		for (int x = 0; x < dst.length; x++) {
			for (int y = 0; y < dst[x].length; y++) {
				dst[x][y] = v;
			}
		}
	}

	public String composeString(String s, Object[] params) {
		String s2 = s;
		for (int i = 0; i < params.length; i++) {
			s2 = s2.replaceAll("%%" + i, params[i].toString());
		}
		return s2;
	}
}

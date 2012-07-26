/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gudigital.util;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * 
 * @author zhangjun
 */
public class IOLib {

	public static void backupFile(String file) {
		try {
			copyFile(file, file + ".bak");
		} catch (IOException ex) {
			Logger.getLogger(IOLib.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

	public static void copyFile(String fileFromPath, String fileToPath) throws IOException {
		if (fileFromPath == null || fileToPath == null) {
			return;
		}
		if (fileFromPath.equalsIgnoreCase(fileToPath)) {
			return;
		}

		InputStream in = null;
		OutputStream out = null;
		try {
			in = new FileInputStream(fileFromPath);
			out = new FileOutputStream(fileToPath);
			int length = in.available();
			int len = (length % 1024 == 0) ? (length / 1024) : (length / 1024 + 1);
			byte[] temp = new byte[1024];
			for (int i = 0; i < len; i++) {
				in.read(temp);
				out.write(temp);
			}
		} finally {
			if (in != null) {
				in.close();
			}
			if (out != null) {
				out.close();
			}
		}
	}

	public static boolean existFile(String file) {
		File f = new File(file);
		return f.exists();
	}

	public static String[] listFile(String dir, String exts) {
		File dirFile = new File(dir);
		final String[] accExts = LangUtil.getTokens(exts, ",");
		if (dirFile.isDirectory()) {
			String[] files = dirFile.list(new FilenameFilter() {

				public boolean accept(File dir, String name) {
					for (int i = 0; i < accExts.length; i++) {
						if (name.toLowerCase().endsWith(accExts[i].toLowerCase())) {
							return true;
						}
					}
					return false;
				}
			});
			return files;
		}
		return new String[0];
	}

	public static byte[] readFileAsBytes(String name) {
		byte[] ret = new byte[0];
		try {
			InputStream is = new FileInputStream(name);
			ByteArrayOutputStream os = new ByteArrayOutputStream();
			byte[] buffer = new byte[100000];
			while (is.available() > 0) {
				int len = is.read(buffer, 0, buffer.length);
				os.write(buffer, 0, len);
			}
			ret = os.toByteArray();

			is.close();
			os.close();
		} catch (IOException ex) {
		}
		return ret;
	}

	public static String readFileAsString(String file, String char_set) {
		byte[] bs = readFileAsBytes(file);
		try {
			return new String(bs, char_set);
		} catch (UnsupportedEncodingException ex) {
			Logger.getLogger(IOLib.class.getName()).log(Level.SEVERE, null, ex);
		}
		return "";
	}

	public static String[] readFileAsStrings(String file) {
		Vector<String> lines = new Vector<String>();
		try {
			BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
			String line = null;
			while ((line = reader.readLine()) != null) {
				lines.add(line);
			}
		} catch (IOException ex) {
			Logger.getLogger(IOLib.class.getName()).log(Level.SEVERE, null, ex);
		}

		String[] ret = new String[lines.size()];
		for (int i = 0; i < lines.size(); i++) {
			ret[i] = lines.get(i);
		}
		return ret;
	}

	public static String readResouceAsString(String name, String encode) {
		byte[] bytes = readResourceAsBytes(name);
		try {
			return new String(bytes, encode);
		} catch (UnsupportedEncodingException ex) {
			Logger.getLogger(IOLib.class.getName()).log(Level.WARNING, null, ex);
		}
		return null;
	}

	public static byte[] readResourceAsBytes(String name) {
		InputStream is = ClassLoader.getSystemResourceAsStream(name);
		ByteArrayOutputStream os = new ByteArrayOutputStream();

		byte[] ret = new byte[0];
		try {
			byte[] buffer = new byte[10000];
			while (is.available() > 0) {
				int len = is.read(buffer, 0, buffer.length);
				os.write(buffer, 0, len);
			}
			ret = os.toByteArray();

			is.close();
			os.close();
		} catch (IOException ex) {
			Logger.getLogger(IOLib.class.getName()).log(Level.WARNING, null, ex);
		}
		return ret;
	}

	public static void writeFile(String file, byte[] content) {
		BufferedOutputStream bos = null;
		try {
			File f = new File(file);
			bos = new BufferedOutputStream(new FileOutputStream(f));
			bos.write(content);
			bos.flush();
			bos.close();
		} catch (IOException ex) {
			Logger.getLogger(IOLib.class.getName()).log(Level.SEVERE, null, ex);
		} finally {
			try {
				bos.close();
			} catch (IOException ex) {
				Logger.getLogger(IOLib.class.getName()).log(Level.SEVERE, null, ex);
			}
		}
	}

	public static void writeToFile(String file, String content) {
		FileWriter writer = null;
		try {
			writer = new FileWriter(file);
			writer.write(content);
			writer.flush();
			writer.close();
		} catch (IOException ex) {
			Logger.getLogger(IOLib.class.getName()).log(Level.SEVERE, null, ex);
		}
	}
}

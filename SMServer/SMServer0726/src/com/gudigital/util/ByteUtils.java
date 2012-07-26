/*    */package com.gudigital.util;

/*    */
/*    */import java.nio.ByteBuffer;

/*    */
/*    */public class ByteUtils
/*    */{
	/*    */private static final int HEX_BYTES_PER_LINE = 16;
	/*    */private static final char TAB = '\t';
	/* 9 */private static final String NEW_LINE = System.getProperty("line.separator");
	/*    */private static final char DOT = '.';

	/*    */
	/*    */public static String fullHexDump(byte[] buffer)
	/*    */{
		/* 32 */return fullHexDump(buffer, 16);
		/*    */}

	/*    */
	/*    */public static String fullHexDump(byte[] buffer, int bytesPerLine)
	/*    */{
		/* 37 */StringBuilder sb = new StringBuilder("Binary size: ").append(buffer.length).append("\n");
		/* 38 */StringBuilder hexLine = new StringBuilder();
		/* 39 */StringBuilder chrLine = new StringBuilder();
		/*    */
		/* 41 */int index = 0;
		/* 42 */int count = 0;
		/*    */do
		/*    */{
			/* 48 */byte currByte = buffer[index];
			/*    */
			/* 51 */String hexByte = Integer.toHexString(currByte & 0xFF);
			/*    */
			/* 53 */if (hexByte.length() == 1) {
				/* 54 */hexLine.append("0");
				/*    */}
			/* 56 */hexLine.append(hexByte.toUpperCase()).append(" ");
			/*    */
			/* 58 */char currChar = (currByte >= 33) && (currByte <= 126) ? (char) currByte : '.';
			/* 59 */chrLine.append(currChar);
			/*    */
			/* 61 */count++;
			if (count == bytesPerLine)
			/*    */{
				/* 63 */count = 0;
				/* 64 */sb.append(hexLine).append('\t').append(chrLine).append(NEW_LINE);
				/*    */
				/* 66 */hexLine.delete(0, hexLine.length());
				/* 67 */chrLine.delete(0, chrLine.length());
				/*    */}
			/*    */
			/* 71 */index++;
		} while (index < buffer.length);
		/*    */
		/* 74 */if (count != 0)
		/*    */{
			/* 76 */for (int j = bytesPerLine - count; j > 0; j--)
			/*    */{
				/* 78 */hexLine.append("   ");
				/* 79 */chrLine.append(" ");
				/*    */}
			/*    */
			/* 82 */sb.append(hexLine).append('\t').append(chrLine).append(NEW_LINE);
			/*    */}
		/*    */
		/* 85 */return sb.toString();
		/*    */}

	/*    */
	/*    */
	/*    */public static String fullHexDump(ByteBuffer buffer)
	/*    */{
		/* 27 */return fullHexDump(buffer.array(), 16);
		/*    */}

	/*    */
	/*    */public static String fullHexDump(ByteBuffer buffer, int bytesPerLine)
	/*    */{
		/* 22 */return fullHexDump(buffer.array(), bytesPerLine);
		/*    */}

	/*    */
	/*    */public static byte[] resizeByteArray(byte[] source, int pos, int size)
	/*    */{
		/* 14 */byte[] tmpArray = new byte[size];
		/* 15 */System.arraycopy(source, pos, tmpArray, 0, size);
		/*    */
		/* 17 */return tmpArray;
		/*    */}
}

/*
 * Location: E:\sfs\SFS2X\trunk\bin\lib\sfs2x-core.jar Qualified Name:
 * com.smartfoxserver.bitswarm.util.ByteUtils JD-Core Version: 0.6.0
 */
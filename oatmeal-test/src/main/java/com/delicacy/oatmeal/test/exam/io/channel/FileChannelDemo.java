package com.delicacy.oatmeal.test.exam.io.channel;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class FileChannelDemo {
	
	private static final String FILEPATH_CHANNEL ="src/main/java/com/delicacy/oatmeal/test/exam/io/channel/channel.txt"; 
	private static final String FILEPATH_FROM ="src/main/java/com/delicacy/oatmeal/test/exam/io/channel/fromFile.txt"; 
	private static final String FILEPATH_TO ="src/main/java/com/delicacy/oatmeal/test/exam/io/channel/toFile.txt"; 
	
	public static void main(String[] args) throws IOException {
		method1();
		//copyMethod();
	}

	/**
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	@SuppressWarnings("resource")
	protected static void copyMethod() throws FileNotFoundException,
			IOException {
		RandomAccessFile fromFile = new RandomAccessFile(FILEPATH_FROM, "rw");
		FileChannel      fromChannel = fromFile.getChannel();

		RandomAccessFile toFile = new RandomAccessFile(FILEPATH_TO, "rw");
		FileChannel      toChannel = toFile.getChannel();

		long position = 0;
		long count = fromChannel.size();

		//toChannel.transferFrom(fromChannel,position, count);
		fromChannel.transferTo(position, count, toChannel);
		fromChannel.close();
		toChannel.close();
	}

	/**
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	protected static void method1() throws FileNotFoundException, IOException {
		///oatmeal-test/src/main/java/com/delicacy/oatmeal/test/exam/io/channel/channel.txt
		RandomAccessFile aFile = new RandomAccessFile(FILEPATH_CHANNEL, "rw");
		FileChannel inChannel = aFile.getChannel();
		ByteBuffer buf = ByteBuffer.allocate(48);
		int bytesRead = -1;
		while ((bytesRead = inChannel.read(buf)) != -1) {
			System.out.println("Read " + bytesRead);
			buf.flip();
			/*while (buf.hasRemaining()) {
				System.out.print((char) buf.get());
			}*/
			//根据缓冲区可读字节数创建字节数组
			byte[] bytes = new byte[buf.remaining()];
			//将缓冲区可读字节数组复制到新建的数组中
			buf.get(bytes);
			String expression = new String(bytes,"UTF-8");
			System.out.println( expression);
			
			System.out.println();
			buf.clear();
		}
		aFile.close();
	}

}

package com.java.nio;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.EnumSet;
import java.util.Random;

public class SimpleFileChannel {
	public static void main(String[] args) throws IOException {
		SimpleFileChannel s = new SimpleFileChannel();
		String path = System.getProperty("user.dir") + File.separator + "src" + File.separator + "main" + File.separator
				+ "resources" + File.separator + "nio";

		Random r = new Random();
		r.ints(500, 0, 100).asLongStream().forEach(x -> {
			try {
				s.writeFile(path + File.separator + "Sample.txt", String.valueOf(x));
			} catch (IOException e) {
				e.printStackTrace();
			}
		});

		s.readFile(path + File.separator + "Sample.txt");
		s.readFileUsingRAF(path + File.separator + "Sample.txt");
	}

	private void readFile(final String path) throws IOException {
		Path abspath = Paths.get(path);
		EnumSet<StandardOpenOption> options = EnumSet.of(StandardOpenOption.READ);

		// Channel
		FileChannel channel = FileChannel.open(abspath, options);

		// Buffer
		ByteBuffer buffer = ByteBuffer.allocate(48);
		
		// SeekableByteChannel
		// channel.truncate(5);
		// channel.position(5);
		
		// FileChannel is a GatheringByteChannel and ScatteringByteChannel
		// Gather channel.write(new ByteBuffer[]{header, body});
		// Scatter channel.read(new ByteBuffer[]{(header = ByteBuffer.allocate(6)), (body = ByteBuffer.allocate(10))});
		
		// channel.transferTo(position, count, target)
		// channel.transferFrom(src, position, count)
		
		while (channel.read(buffer) > 0) {
			buffer.flip();

			while (buffer.hasRemaining()) {
				System.out.print(Character.toChars(buffer.get())[0]);
			}

			buffer.clear();
		}
		channel.close();
	}

	private void writeFile(final String path, final String message) throws IOException {
		Path abspath = Paths.get(path);
		EnumSet<StandardOpenOption> options = EnumSet.of(StandardOpenOption.APPEND);

		// Channel
		FileChannel channel = FileChannel.open(abspath, options);

		// Buffer
		ByteBuffer buffer = ByteBuffer.wrap((message + "\n").getBytes());

		channel.write(buffer);
		channel.close();
	}

	private void readFileUsingRAF(final String path) throws IOException {
		RandomAccessFile raf = new RandomAccessFile(path, "r");
		FileChannel channel = raf.getChannel();
		
		System.out.println("File Size"+ channel.size());
		System.out.println("raf == " + channel.read(ByteBuffer.allocate(10)));
		raf.close();
		channel.close();
	}
}

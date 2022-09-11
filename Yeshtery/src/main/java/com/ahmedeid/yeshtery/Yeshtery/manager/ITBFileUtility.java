package com.ahmedeid.yeshtery.Yeshtery.manager;

import java.io.*;

/**
 * A utility that downloads a file from a URL.
 * 
 * @author Mohamed Mohsen
 * 
 */
public class ITBFileUtility {
	private static final int BUFFER_SIZE = 8 * 1024;

	public static byte[] getByteContentNetwFile(String fileUrl) throws IOException {

		// Code to download
		InputStream in = new BufferedInputStream(new FileInputStream(new File(fileUrl)));
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		byte[] buf = new byte[BUFFER_SIZE];
		int n = 0;
		while (-1 != (n = in.read(buf))) {
			out.write(buf, 0, n);
		}
		out.close();
		in.close();
		byte[] response = out.toByteArray();

		out.close();
		in.close();
		System.out.println("Finished");
		// FileOutputStream fos = new FileOutputStream(new
		// File(fileUrl).getName());
		// fos.write(response);
		// fos.close();
		// End download code
		// for (byte b : response) {
		// System.out.println(b);
		// }
		return response;

	}

}
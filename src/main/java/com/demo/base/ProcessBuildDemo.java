package com.demo.base;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;

public class ProcessBuildDemo {
	
	
	static URL resource = ProcessBuildDemo.class.getClass().getResource("/");

	public static void main(String[] args) throws IOException {

		String sourcePath = "D:/protractordemo/protractor-demo/app";
		String destinationPath = "D:/protractordemo/protractor-demo/instrument";
		
		String[] command = { "CMD", "/K",
				resource.toString().replace("target/classes/", "src/main/resources/jscoverage.exe").replace("file:/", ""),
				sourcePath, destinationPath };
		ProcessBuilder probuilder = new ProcessBuilder(command);
		Process process = probuilder.start();
		InputStream is = process.getInputStream();
		InputStreamReader isr = new InputStreamReader(is);
		BufferedReader br = new BufferedReader(isr);
		String line =null;
		while ((line =br.readLine()) != null) {
			System.out.println(line);
			break;
		}
		br.close();
		isr.close();
		is.close();
	}

}

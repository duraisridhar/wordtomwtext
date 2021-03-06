package com.thuvienkhoahoc.wordtomwtext.test;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

import javax.swing.JFrame;

import net.sourceforge.jwbf.actions.mw.util.ActionException;

import com.thuvienkhoahoc.wordtomwtext.Application;
import com.thuvienkhoahoc.wordtomwtext.data.Image;
import com.thuvienkhoahoc.wordtomwtext.data.Page;
import com.thuvienkhoahoc.wordtomwtext.data.Project;
import com.thuvienkhoahoc.wordtomwtext.ui.PnlUploader;

@SuppressWarnings("serial")
public class UploaderTester extends JFrame {

	/**
	 * @param args
	 * @throws IOException 
	 * @throws ActionException 
	 */
	public static void main(String[] args) throws Exception {
		// prepare data
		Properties prop = new Properties();
		prop.load(new FileInputStream(
				"test/config/testconfig.properties"));
		Application.getInstance().login(
				prop.getProperty("site"), 
				prop.getProperty("user"), 
				prop.getProperty("pass"));
		
		Project project = new Project();
		String id = System.currentTimeMillis() + "";
//		String id = "";
		
		Page page = new Page("Thử nghiệm" + id);
		FileReader input = new FileReader("test/data/mwtext.txt");
		char[] buffer = new char[1024];
		int numRead = 0;
		StringBuffer sb = new StringBuffer();
		while ((numRead = input.read(buffer)) > 0) {
			sb.append(buffer, 0, numRead);
		}
		input.close();
		page.setText(sb.toString());
		project.addPage(page);
		
		Page page2 = new Page("Đơn giản" + id);
		page2.setText("abc");
		project.addPage(page2);
		
		Image image = new Image("picture-0" + id + ".png", "test/data/picture-0.png");
		project.addImage(image);
		
		// initialize frame
		JFrame frame = new JFrame();
		frame.setTitle("Project editor tester");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		PnlUploader uploader = new PnlUploader();
		uploader.load(project);
		uploader.setPreferredSize(new Dimension(450, 380));
		frame.getContentPane().add(uploader, BorderLayout.CENTER);

		frame.pack();
		frame.setVisible(true);
	}

}

package com.withiter.frame;

import java.awt.Graphics;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

import com.sun.awt.AWTUtilities;
import com.withiter.entity.USBConfig;
import com.withiter.utils.OSUtils;

public class LoadingFrame extends JFrame implements Runnable {

	private static final long serialVersionUID = -8236850107130802140L;
	private static LoadingFrame loadingFrame;

	public static LoadingFrame instance() {
		if (loadingFrame == null)
			loadingFrame = new LoadingFrame();
		return loadingFrame;
	}

	private void initConf() throws IOException {
		File f = null;
		if (OSUtils.isMac()) {
			f = new File("/Users/user/USBConfs");
		}
		if (OSUtils.isWin()) {
			f = new File("C:/USBConfs");
		}

		if (!f.exists()) {
			System.out.println("C:/USBConfs does not exist");
			f.createNewFile();
			String ext = "ext=wmv avi rm rmvb mpg mp4";
			String video_new_folder = "video_new_folder=videonew";
			String ini_new_folder = "ini_new_folder=ininew";
			String video_path = "video_path=\\Storage card\\videodata\\";
			String video_max_number = "video_max_number=50";
			String news_max_chars = "news_max_chars=100";
			FileWriter fw = new FileWriter(f);
			BufferedWriter bw = new BufferedWriter(fw);
			bw.append(ext);
			bw.newLine();
			bw.append(video_new_folder);
			bw.newLine();
			bw.append(ini_new_folder);
			bw.newLine();
			bw.append(video_path);
			bw.newLine();
			bw.append(video_max_number);
			bw.newLine();
			bw.append(news_max_chars);
			bw.flush();
			bw.close();
			Set<String> exts = new HashSet<String>();
			exts.addAll(Arrays.asList(ext.split("=")[1].split(" ")));
			String videoNewFolder = video_new_folder.split("=")[1];
			String iniNewFolder = ini_new_folder.split("=")[1];
			String videoPath = video_path.split("=")[1];
			int videoMaxNumber = Integer
					.parseInt(video_max_number.split("=")[1]);
			int newsMaxChars = Integer.parseInt(news_max_chars.split("=")[1]);
			USBConfig.initParams(exts, videoNewFolder, iniNewFolder,
					videoPath, videoMaxNumber, newsMaxChars);
		} else {
			System.out.println("C:/USBConfs does exist");
			BufferedReader br = new BufferedReader(new FileReader(f));
			String line = null;

			Set<String> exts = new HashSet<String>();
			String videoNewFolder = "";
			String initNewFolder = "";
			String videoPath = "";
			int videoMaxNumber = 50;
			int newsMaxChars = 100;
			while ((line = br.readLine()) != null) {
				if (line.startsWith("ext")) {
					exts.addAll(Arrays.asList(line.split("=")[1].split(" ")));
				}
				if (line.startsWith("video_new_folder")) {
					videoNewFolder = line.split("=")[1];
				}
				if (line.startsWith("ini_new_folder")) {
					initNewFolder = line.split("=")[1];
				}
				if (line.startsWith("video_path")) {
					videoPath = line.split("=")[1];
				}
				if (line.startsWith("video_max_number")) {
					videoMaxNumber = Integer.parseInt(line.split("=")[1]);
				}
				if (line.startsWith("newsMaxChars")) {
					newsMaxChars = Integer.parseInt(line.split("=")[1]);
				}
			}
			br.close();
			USBConfig.initParams(exts, videoNewFolder, initNewFolder,
					videoPath, videoMaxNumber, newsMaxChars);
		}

		USBConfig.description();
	}

	public LoadingFrame() {
		super("U盘系统加载中...");
		setSize(230, 150);
		loadingFrame = this;
		setUndecorated(true);
		setAlwaysOnTop(true);
		setLocationRelativeTo(null);
		AWTUtilities.setWindowOpaque(this, false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setIconImage(new ImageIcon(this.getClass().getResource(
				"/images/icon.png")).getImage());
		setVisible(true);

		// initial configurations
		try {
			initConf();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);
		ImageIcon bg = new ImageIcon(this.getClass().getResource(
				"/images/loading.png"));
		g.drawImage(bg.getImage(), 0, 0, getWidth(), getHeight(), this);
	}

	@Override
	public void run() {
		instance();
	}
}

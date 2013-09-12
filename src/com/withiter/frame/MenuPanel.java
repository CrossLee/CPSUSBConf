package com.withiter.frame;

import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JPanel;

import com.withiter.listener.MenuListener;

public class MenuPanel extends JPanel {
	/**
	 * MenuPanel
	 */
	private static final long serialVersionUID = -2703153422697822701L;
	private static MenuPanel menuPanel;

	public static MenuPanel instance() {
		if (menuPanel == null)
			menuPanel = new MenuPanel();
		return menuPanel;
	}

	public MenuPanel() {
		setOpaque(false);
		setLayout(new FlowLayout(FlowLayout.RIGHT, 0, 15));
		setPreferredSize(new Dimension(200, 600));

		ImageButton btnVideo, btnWeather, btnTemperature, btnWords, btnLog, btnBackup;
		btnVideo = new ImageButton("menu", "视频管理", 16);
		btnWords = new ImageButton("menu", "新闻管理", 16);
		btnTemperature = new ImageButton("menu", "温度管理", 16);
		btnLog = new ImageButton("menu", "日志管理", 16);
		btnWeather = new ImageButton("menu", "天气管理", 16);
		btnBackup = new ImageButton("menu", "其他信息", 16);

		btnVideo.setPreferredSize(new Dimension(180, 50));
		btnWords.setPreferredSize(new Dimension(180, 50));
		btnTemperature.setPreferredSize(new Dimension(180, 50));
		btnLog.setPreferredSize(new Dimension(180, 50));
		btnWeather.setPreferredSize(new Dimension(180, 50));
		btnBackup.setPreferredSize(new Dimension(180, 50));

		MenuListener menuListener = new MenuListener(btnVideo,btnWords
				, btnTemperature, btnLog, btnWeather, btnBackup);
		btnVideo.addActionListener(menuListener);
		btnWords.addActionListener(menuListener);
		btnTemperature.addActionListener(menuListener);
		btnLog.addActionListener(menuListener);
		btnWeather.addActionListener(menuListener);
		btnBackup.addActionListener(menuListener);

		add(btnVideo);
		add(btnWords);
		add(btnTemperature);
		add(btnLog);
		add(btnWeather);
		add(btnBackup);
	}
}
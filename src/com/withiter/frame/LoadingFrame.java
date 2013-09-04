package com.withiter.frame;

import java.awt.Graphics;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

import com.sun.awt.AWTUtilities;

public class LoadingFrame extends JFrame implements Runnable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -8236850107130802140L;
	private static LoadingFrame loadingFrame;

	public static LoadingFrame instance() {
		if (loadingFrame == null)
			loadingFrame = new LoadingFrame();
		return loadingFrame;
	}

	public LoadingFrame() {
		super("CPS USB Conf");
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
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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

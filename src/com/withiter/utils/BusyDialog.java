package com.withiter.utils;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.LinearGradientPaint;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JRootPane;

import org.jdesktop.swingx.JXBusyLabel;
import org.jdesktop.swingx.JXLabel;
import org.jdesktop.swingx.JXPanel;
import org.jdesktop.swingx.painter.MattePainter;
import org.jdesktop.swingx.painter.Painter;

import com.sun.awt.AWTUtilities;

public class BusyDialog {

	public JFrame frame = new JFrame("Loading");

	/** simple main driver for this class */

	public static void main(String[] args) {
		new BusyDialog();
	}

	/**
	 * creates a JFrame and calls {@link #doInit} to create a JXPanel and adds
	 * the panel to this frame.
	 */

	public BusyDialog() {

		// add the panel to this frame
		frame.add(doInit());

		// center the frame and show it
		frame.setSize(new Dimension(250, 80));
		frame.getRootPane().setWindowDecorationStyle(JRootPane.NONE);
		
		
		frame.setUndecorated(true);
		frame.setAlwaysOnTop(true);
		frame.setLocationRelativeTo(null);
//		AWTUtilities.setWindowOpaque(this, false);
		frame.pack();
		frame.setVisible(true);
	}

	/** creates a JXLabel and attaches a painter to it. */

	private Component doInit() {

		JXPanel panel = new JXPanel();
		panel.setLayout(new BorderLayout());

		// create a busylabel
		final JXBusyLabel busylabel1 = createSimpleBusyLabel();
		busylabel1.setBusy(true);
		final JXLabel label = createLabel("正在操作，请稍等...");

		// panel.setAlpha(0.7f);
		panel.add(label, BorderLayout.NORTH);

		JXPanel busylabels = new JXPanel(new FlowLayout(FlowLayout.CENTER, 40,
				2));
		busylabels.add(busylabel1);
		panel.add(busylabels, BorderLayout.CENTER);
		panel.setPreferredSize(new Dimension(250, 80));
		return panel;
	}

	public JXBusyLabel createSimpleBusyLabel() {
		JXBusyLabel label = new JXBusyLabel();
		return label;
	}

	public JXLabel createLabel(String str) {
		JXLabel label = new JXLabel();
		label.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
		label.setFont(new Font("Segoe UI", Font.BOLD, 14));
		label.setText("<html>" + str + "</html>");
		label.setHorizontalAlignment(JXLabel.LEFT);
		label.setBackgroundPainter(getPainter());
		return label;
	}

	/** this painter draws a gradient fill */
	public Painter<?> getPainter() {
		int width = 100;
		int height = 100;
		Color color1 = Color.WHITE;
		Color color2 = Color.GRAY;
		LinearGradientPaint gradientPaint = new LinearGradientPaint(0.0f, 0.0f,
				width, height,
				new float[] { 0.0f, 1.0f },
				new Color[] { color1, color2 });
		MattePainter mattePainter = new MattePainter(gradientPaint);
		return mattePainter;
	}
}
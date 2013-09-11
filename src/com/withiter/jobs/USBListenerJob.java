package com.withiter.jobs;

import java.io.File;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JOptionPane;

import com.withiter.entity.USBConfig;

public class USBListenerJob implements Runnable {

	public static String uDiskDrive = "";
	private File[] roots = File.listRoots();
	private Vector<File> fileVector = new Vector<File>();

	public USBListenerJob() {
	}

	public void run() {
		System.out.println("check system start...");
		while (true) {
			File[] tempFile = File.listRoots();
			boolean sign = false;
			fileVector.removeAllElements();
			if (tempFile.length > roots.length) {
				for (int i = tempFile.length - 1; i >= 0; i--) {
					sign = false;
					for (int j = roots.length - 1; j >= 0; j--) {
						if (tempFile[i].equals(roots[j])) {
							sign = true;
						}
					}
					if (!sign) {
						fileVector.add(tempFile[i]);
						uDiskDrive = tempFile[i].toString();
						JOptionPane.showMessageDialog(null, "检测到U盘插入"+uDiskDrive, "提示",
								JOptionPane.OK_OPTION);
						System.out.println("Enter:" + uDiskDrive);
						USBConfig.drivePath = uDiskDrive;
						// load information from usb
//						new FindFileThread(fileVector).start();// find files thread
					}
				}
				roots = File.listRoots();// update roots
			} else {
				for (int i = roots.length - 1; i >= 0; i--) {
					sign = false;
					for (int j = tempFile.length - 1; j >= 0; j--) {
						if (tempFile[j].equals(roots[i])) {
							sign = true;
						}
					}
					if (sign == false) {
						System.out.println("Quit:" + roots[i].toString());
					}
				}
				roots = File.listRoots();// update roots
			}
			try {
				Thread.sleep(1000);
			} catch (InterruptedException ex) {
				Logger.getLogger(USBListenerJob.class.getName()).log(
						Level.SEVERE, null, ex);
			}
		}
	}

	public static void main(String args[]) {
		new Thread(new USBListenerJob()).start();
	}
}

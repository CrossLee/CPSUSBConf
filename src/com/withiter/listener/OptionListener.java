package com.withiter.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileFilter;

import com.withiter.dao.VideoDao;
import com.withiter.entity.USBConfig;
import com.withiter.frame.MainFrame;
import com.withiter.frame.MainPanel;
import com.withiter.utils.BusyDialog;
import com.withiter.utils.CopyWorker;
import com.withiter.utils.LodingWorker;

public class OptionListener implements ActionListener {
	JButton jbtnAdd;
	JButton jbtnDel;
	JButton jbtnEdit;
	JButton jbtRefresh;
	JButton jbtExit;
	JButton jbtLock;

	public OptionListener(JButton jbtnAdd, JButton jbtnDel, JButton jbtnEdit,
			JButton jbtRefresh, JButton jbtExit, JButton jbtLock) {
		super();
		this.jbtnAdd = jbtnAdd;
		this.jbtnDel = jbtnDel;
		this.jbtnEdit = jbtnEdit;
		this.jbtRefresh = jbtRefresh;
		this.jbtExit = jbtExit;
		this.jbtLock = jbtLock;
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		// TODO add menu functions here

		// check the current page
		if (MainFrame.CURRENT_PAGE.equals("video")) {
			if (e.getSource() == jbtnAdd) {
				JFileChooser fileChooser = new JFileChooser();
				fileChooser.setFileFilter(new VideoFilter());
				int i = fileChooser.showOpenDialog(null);
				fileChooser
						.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
				if (i == JFileChooser.APPROVE_OPTION) {
					File file = fileChooser.getSelectedFile();
					System.out.println(file.getName());
					if (VideoDao.instance().videoNotExist(file.getName())) {
						File des = new File(USBConfig.drivePath
								+ USBConfig.VIDEO_NEW_FOLDER + "\\"
								+ file.getName());
						BusyDialog bd = new BusyDialog();
						ExecutorService executor = Executors
								.newCachedThreadPool();

						CountDownLatch latch = new CountDownLatch(1);
						CopyWorker w1 = new CopyWorker(latch, "CopyWorker", file, des);
						LodingWorker loading = new LodingWorker(latch, bd);

						executor.execute(w1);
						executor.execute(loading);
						executor.shutdown();
						
					} else {
						JOptionPane.showMessageDialog(null,
								"此文件已存在：" + file.getName(), "提示",
								JOptionPane.OK_OPTION);
					}
				}
			} else if (e.getSource() == jbtnDel) {
//				TakeRoomDialog.instance().open();
			} else if (e.getSource() == jbtRefresh) {
				MainPanel.instance().refresh();
			} else if (e.getSource() == jbtExit) {
				System.exit(0);
			}
		}
		if (MainFrame.CURRENT_PAGE.equals("news")) {
			if (e.getSource() == jbtnAdd) {
				// BookRoomDialog.instance().open();
			} else if (e.getSource() == jbtnDel) {
//				TakeRoomDialog.instance().open();
			} else if (e.getSource() == jbtRefresh) {
				MainPanel.instance().refresh();
			} else if (e.getSource() == jbtExit) {
				System.exit(0);
			}
		}
		if (MainFrame.CURRENT_PAGE.equals("temperature")) {
			if (e.getSource() == jbtnEdit) {
				// ConfigDialog.instance().open();
			} else if (e.getSource() == jbtRefresh) {
				MainPanel.instance().refresh();
			} else if (e.getSource() == jbtExit) {
				System.exit(0);
			}
		}
		if (MainFrame.CURRENT_PAGE.equals("log")) {
			if (e.getSource() == jbtRefresh) {
				MainPanel.instance().refresh();
			} else if (e.getSource() == jbtExit) {
				System.exit(0);
			}
		}
	}

}

class VideoFilter extends FileFilter {

	public boolean accept(java.io.File f) {
		if (f.isDirectory()) {
			return true;
		}
		boolean flag = false;
		String fileName = f.getName().toLowerCase();
		String ext = fileName.substring(fileName.lastIndexOf(".") + 1,
				fileName.length());
		if (USBConfig.EXTS.contains(ext)) {
			flag = true;
		}
		return flag;
	}

	@Override
	public String getDescription() {
		return "请选择视频，格式：*.wmv，*.avi，*.rm，*.rmvb，*.mp4，*.mpg";
	}
}
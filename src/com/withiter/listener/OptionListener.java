package com.withiter.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileFilter;

import com.withiter.dao.NewsDao;
import com.withiter.dao.VideoDao;
import com.withiter.entity.News;
import com.withiter.entity.USBConfig;
import com.withiter.entity.Video;
import com.withiter.frame.DataTable;
import com.withiter.frame.MainFrame;
import com.withiter.frame.MainPanel;
import com.withiter.frame.NewsDialog;
import com.withiter.utils.BusyDialog;
import com.withiter.utils.CopyWorker;
import com.withiter.utils.DeleteWorker;
import com.withiter.utils.LodingWorker;
import com.withiter.utils.NewsWorker;

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
				if(VideoDao.instance().getvideoList().size() >= 50){
					JOptionPane.showMessageDialog(null,
							"最多可添加50个视频，请删除一些视频再进行添加操作", "提示",
							JOptionPane.OK_OPTION);
					return;
				}
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
						CopyWorker w1 = new CopyWorker(latch, "CopyWorker",
								file, des);
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
				System.out.println("Delete button clicked!");
				DataTable table = MainPanel.instance().getTable();
				int rows = table.getRowCount();
				System.out.println(rows);
				boolean value = false;
				Set<String> s = new HashSet<String>();
				for (int i = 0; i < rows; i++) {
					boolean rValue = (Boolean) table.getValueAt(i, 0);
					if (rValue) {
						String videoName = (String) table.getValueAt(i, 1);
						s.add(videoName);
					}
					value |= rValue;
				}
				System.out.println("s.toString():"+s.toString());
				if (!value) {
					JOptionPane.showMessageDialog(null, "请至少选择一项删除", "提示",
							JOptionPane.OK_OPTION);
				} else {
					
					List<Video> vList = VideoDao.instance().getvideoList();
					List<Video> remainedList = new ArrayList<Video>();
					List<String> vNeedToDelete = new ArrayList<String>();
					for(int i = 0; i < vList.size(); i ++){
						Video v = vList.get(i);
						if(s.contains(v.name)){
							vNeedToDelete.add(v.path);
						}else{
							remainedList.add(v);
						}
					}
					
					System.out.println("vNeedToDelete.toString() : "+vNeedToDelete.toString());
					System.out.println("remainedList.toString(): "+remainedList.toString());
					
					VideoDao.videoList = remainedList;
					
					BusyDialog bd = new BusyDialog();
					ExecutorService executor = Executors
							.newCachedThreadPool();

					CountDownLatch latch = new CountDownLatch(1);
					DeleteWorker w1 = new DeleteWorker(latch, "DeleteWorker",
							vNeedToDelete);
					LodingWorker loading = new LodingWorker(latch, bd);

					executor.execute(w1);
					executor.execute(loading);
					executor.shutdown();
				}
				MainPanel.instance().refresh();
			} else if (e.getSource() == jbtRefresh) {
				MainPanel.instance().refresh();
			} else if (e.getSource() == jbtExit) {
				System.exit(0);
			}
		}
		
		if (MainFrame.CURRENT_PAGE.equals("news")) {
			if (e.getSource() == jbtnAdd) {
				NewsDialog.instance().open();
			} else if (e.getSource() == jbtnDel) {
				System.out.println("News delete button clicked!");
				DataTable table = MainPanel.instance().getTable();
				int rows = table.getRowCount();
				System.out.println(rows);
				boolean value = false;
				List<String> s = new ArrayList<String>();
				for (int i = 0; i < rows; i++) {
					boolean rValue = (Boolean) table.getValueAt(i, 0);
					if (rValue) {
						String newsName = (String) table.getValueAt(i, 1);
						s.add(newsName);
					}
					value |= rValue;
				}
				System.out.println("s.toString():"+s.toString());
				if (!value) {
					JOptionPane.showMessageDialog(null, "请至少选择一项删除", "提示",
							JOptionPane.OK_OPTION);
				} else {
					List<News> vList = NewsDao.instance().getnewsList();
					List<News> remainedList = new ArrayList<News>();
					List<String> vNeedToDelete = new ArrayList<String>();
					for(int i = 0; i < vList.size(); i ++){
						News v = vList.get(i);
						if(s.contains(v.content)){
							vNeedToDelete.add(v.content);
						}else{
							remainedList.add(v);
						}
					}
					
					System.out.println("vNeedToDelete.toString() : "+vNeedToDelete.toString());
					System.out.println("remainedList.toString(): "+remainedList.toString());
					
					NewsDao.newsList = remainedList;
					
					BusyDialog bd = new BusyDialog();
					ExecutorService executor = Executors
							.newCachedThreadPool();

					CountDownLatch latch = new CountDownLatch(1);
					NewsWorker w1 = new NewsWorker(latch, "NewsWorker");
					LodingWorker loading = new LodingWorker(latch, bd);

					executor.execute(w1);
					executor.execute(loading);
					executor.shutdown();
				}
				MainPanel.instance().refresh();
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
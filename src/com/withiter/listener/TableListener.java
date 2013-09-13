package com.withiter.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.swing.JFileChooser;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import javax.swing.table.DefaultTableModel;

import com.withiter.dao.VideoDao;
import com.withiter.entity.USBConfig;
import com.withiter.frame.DataTable;
import com.withiter.frame.MainPanel;
import com.withiter.utils.BusyDialog;
import com.withiter.utils.CopyWorker;
import com.withiter.utils.LodingWorker;

public class TableListener extends MouseAdapter implements ActionListener {
	private DataTable table;
	private JPopupMenu menu;

	public TableListener(JPopupMenu menu) {
		this.table = MainPanel.instance().getTable();
		this.menu = menu;
	}

	public void mousePressed(MouseEvent e) {
		if (table.getSelectedRow() < 0) {
			int modifiers = e.getModifiers();
			modifiers -= MouseEvent.BUTTON3_MASK;
			modifiers |= MouseEvent.BUTTON1_MASK;
			MouseEvent ne = new MouseEvent(e.getComponent(), e.getID(),
					e.getWhen(), modifiers, e.getX(), e.getY(),
					e.getClickCount(), false);
			table.dispatchEvent(ne);
		}
	}

	public void mouseReleased(MouseEvent e) {
		if ((e.getModifiers() & MouseEvent.BUTTON3_MASK) != 0
				&& !e.isControlDown() && !e.isShiftDown()) {
			menu.show(table, e.getX(), e.getY());
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (table.getSelectedRow() < 0)
			return;
		String strAction = ((JMenuItem) e.getSource()).getText().trim();
		if (!isSure("<html>您确定要进行<b><font size=6> " + strAction
				+ " </font></b>操作吗？"))
			return;
		
		// TODO add right click funtion
		
//		if(strAction.equals("添加视频")){
//			JFileChooser fileChooser = new JFileChooser();
//			fileChooser.setFileFilter(new VideoFilter());
//			int i = fileChooser.showOpenDialog(null);
//			fileChooser
//					.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
//			if (i == JFileChooser.APPROVE_OPTION) {
//				File file = fileChooser.getSelectedFile();
//				System.out.println(file.getName());
//				if (VideoDao.instance().videoNotExist(file.getName())) {
//					File des = new File(USBConfig.drivePath
//							+ USBConfig.VIDEO_NEW_FOLDER + "\\"
//							+ file.getName());
//					BusyDialog bd = new BusyDialog();
//					ExecutorService executor = Executors
//							.newCachedThreadPool();
//
//					CountDownLatch latch = new CountDownLatch(1);
//					CopyWorker w1 = new CopyWorker(latch, "CopyWorker", file, des);
//					LodingWorker loading = new LodingWorker(latch, bd);
//
//					executor.execute(w1);
//					executor.execute(loading);
//					executor.shutdown();
//					
//				} else {
//					JOptionPane.showMessageDialog(null,
//							"此文件已存在：" + file.getName(), "提示",
//							JOptionPane.OK_OPTION);
//				}
//			}
//		}
		JOptionPane.showMessageDialog(null, strAction + "成功！");
	}

	private boolean isSure(String msg) {
		return (JOptionPane.showConfirmDialog(null, msg, "消息",
				JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION);
	}

	private String getValue(int row, int column) {
		DefaultTableModel tableModel = (DefaultTableModel) table.getModel();
		return tableModel.getValueAt(row, column).toString();
	}

	private String getSelectedValue(int column) {
		if (table.getSelectedRow() < 0)
			return null;
		DefaultTableModel tableModel = (DefaultTableModel) table.getModel();
		return tableModel.getValueAt(table.getSelectedRow(), column).toString();
	}

	private void removeSelectedRow() {
		DefaultTableModel tableModel = (DefaultTableModel) table.getModel();
		tableModel.removeRow(table.getSelectedRow());
	}

	private void removeSelectedRows() {
		if (table.getSelectedRow() < 0)
			return;
		DefaultTableModel tableModel = (DefaultTableModel) table.getModel();
		boolean isEndSelect = false;
		if (table.getSelectedRows()[table.getSelectedRows().length - 1] == tableModel
				.getRowCount() - 1) {
			isEndSelect = true;
		}
		while (table.getSelectedRow() >= 0) {
			tableModel.removeRow(table.getSelectedRow());
		}
		if (isEndSelect && tableModel.getRowCount() > 0) {
			tableModel.removeRow(tableModel.getRowCount() - 1);
		}
		if (tableModel.getRowCount() <= 0) {
			MainPanel.instance().refresh();
		}
	}
}

package com.withiter.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import com.withiter.dao.RoomTypeDao;
import com.withiter.entity.RoomType;
import com.withiter.frame.AddRoomTypeDialog;
import com.withiter.frame.MainPanel;

public class AddRoomTypeListener implements ActionListener {
	private JTextField jtfRoomType, jtfRoomPrice, jtfPriceAdded;
	private JButton cancel;

	public AddRoomTypeListener(JTextField jtfRoomType, JTextField jtfRoomPrice,
			JTextField jtfPriceAdded, JButton cancel) {
		this.jtfRoomType = jtfRoomType;
		this.jtfRoomPrice = jtfRoomPrice;
		this.jtfPriceAdded = jtfPriceAdded;
		this.cancel = cancel;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == cancel) {
			AddRoomTypeDialog.instance().dispose();
		} else {
			if (jtfRoomType.getText().equals("")
					|| jtfRoomPrice.getText().equals("")
					|| jtfPriceAdded.getText().equals("")) {
				JOptionPane.showMessageDialog(null, "请将信息填写完整！");
				return;
			}
			RoomTypeDao.instance().addRoomType(
					new RoomType(jtfRoomType.getText(), Integer
							.parseInt(jtfRoomPrice.getText()), Integer
							.parseInt(jtfPriceAdded.getText())));
			MainPanel.instance().refresh();
			AddRoomTypeDialog.instance().dispose();
			JOptionPane.showMessageDialog(null, "添加成功！");
		}
	}
}

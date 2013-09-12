package com.withiter.dao;

import java.util.ArrayList;
import java.util.List;

import com.withiter.entity.Log;

public class LogDao {
	private static List<Log> logList;
	private static LogDao logDao;

	public static LogDao instance() {
		if (logDao == null)
			logDao = new LogDao();
		return logDao;
	}

	public LogDao() {
		logList = new ArrayList<Log>();
	}

	public void addLog(Log Log) {
		logList.add(Log);
	}
	public void deleteLog(Log Log) {
		logList.remove(Log);
	}

	private Object[] formatData(Log Log) {
		Object[] result = new Object[6];
		result[0] = Log.ip;
		result[1] = Log.mac;
		result[2] = Log.date;
		result[3] = Log.operation;
		result[4] = Log.filename;
		result[5] = Log.result;
		return result;
	}

	public Object[][] getLogsData() {
		Object[][] result = new Object[LogDao.instance().getlogList()
				.size()][6];
		int i = 0;
		for (Log Log : logList) {
			result[i] = formatData(Log);
			i++;
		}
		return result;
	}

	public List<Log> getlogList() {
		return logList;
	}

}

package com.withiter.dao;

import java.util.ArrayList;
import java.util.List;

import com.withiter.entity.News;

public class NewsDao {
	private static List<News> newsList;
	private static NewsDao newsDao;

	public static NewsDao instance() {
		if (newsDao == null)
			newsDao = new NewsDao();
		return newsDao;
	}

	public NewsDao() {
		newsList = new ArrayList<News>();
	}

	public void addNews(News News) {
		newsList.add(News);
	}
	public void deleteNews(News News) {
		newsList.remove(News);
	}

	private Object[] formatData(News News) {
		Object[] result = new Object[5];
//		result[0] = News.index;
		result[0] = News.content;
//		result[2] = News.path;
//		result[3] = News.size;
//		result[4] = News.updateTime;
		return result;
	}

	public Object[][] getNewssData() {
		Object[][] result = new Object[NewsDao.instance().getnewsList()
				.size()][5];
		int i = 0;
		for (News News : newsList) {
			result[i] = formatData(News);
			i++;
		}
		return result;
	}

	public List<News> getnewsList() {
		return newsList;
	}

}

package com.withiter.dao;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.withiter.entity.News;
import com.withiter.entity.USBConfig;
import com.withiter.entity.Video;
import com.withiter.utils.FileReaderUtils;

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
		result[0] = new Boolean(false);
		result[1] = News.content;
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
	
	public void writeToIniFile() throws IOException {
		System.out.println("start to write info into news.ini");
		
		String newsIni = USBConfig.drivePath + USBConfig.INIT_NEW_FOLDER + "\\news.ini";
		File f = new File(newsIni);
		if(f.exists()){
			f.deleteOnExit();
		}
		f.createNewFile();
		int sum = this.getnewsList().size();
		List<String> lines = new ArrayList<String>();
		lines.add("[news]");
		lines.add("sum="+sum);
		StringBuilder line = new StringBuilder("news");
		for(int i=0; i < sum; i++){
			News n = this.getnewsList().get(i);
			line.append(i).append("=").append(n.content);
			lines.add(line.toString());
			line = new StringBuilder("news");
		}
		System.out.println("the lines in memory ===============");
		for(String s : lines){
			System.out.println(s);
		}
		System.out.println("the lines in memory ===============");
		
		FileReaderUtils.writeToFile(lines, f);
		
	}

}

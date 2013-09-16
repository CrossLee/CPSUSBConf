package com.withiter.dao;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.withiter.entity.USBConfig;
import com.withiter.entity.Video;
import com.withiter.utils.FileReaderUtils;

public class VideoDao {
	public static List<Video> videoList;
	private static VideoDao videoDao;

	public static VideoDao instance() {
		if (videoDao == null)
			videoDao = new VideoDao();
		return videoDao;
	}

	public VideoDao() {
		videoList = new ArrayList<Video>();
	}

	public void addVideo(Video video) {
		if(videoList.size() < 50){
			videoList.add(video);
		}
	}
	public void deleteVideo(Video video) {
		videoList.remove(video);
	}

	private Object[] formatData(Video video) {
		Object[] result = new Object[6];
		result[0] = new Boolean(false);
		result[1] = video.name;
		result[2] = video.ext;
		result[3] = video.path;
		result[4] = video.size;
		result[5] = video.updateTime;
		return result;
	}

	public Object[][] getVideosData() {
		Object[][] result = new Object[VideoDao.instance().getvideoList()
				.size()][5];
		int i = 0;
		for (Video video : videoList) {
			result[i] = formatData(video);
			i++;
		}
		return result;
	}

	public List<Video> getvideoList() {
		return videoList;
	}
	
	public boolean videoNotExist(String name){
		boolean flag = true;
		for(Video v : videoList){
			if(v.name.equalsIgnoreCase(name)){
				flag = false;
				break;
			}
		}
		return flag;
	}

	public void writeToIniFile() throws IOException {
		System.out.println("start to write info into video.ini");
		
		String videoIni = USBConfig.drivePath + USBConfig.INIT_NEW_FOLDER + "\\video.ini";
		File f = new File(videoIni);
		if(f.exists()){
			f.delete();
		}
		f.createNewFile();
		int sum = this.getvideoList().size();
		List<String> lines = new ArrayList<String>();
		lines.add("[video]");
		lines.add("sum="+sum);
		StringBuilder line = new StringBuilder("video");
		for(int i=0; i < sum; i++){
			Video v = this.getvideoList().get(i);
			line.append(i).append("=").append(USBConfig.VIDEO_PATH).append(v.name);
			lines.add(line.toString());
			line = new StringBuilder("video");
		}
		System.out.println("the lines in memory ===============");
		for(String s : lines){
			System.out.println(s);
		}
		System.out.println("the lines in memory ===============");
		
		FileReaderUtils.writeToFile(lines, f);
		
	}

}

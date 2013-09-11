package com.withiter.dao;

import java.util.ArrayList;
import java.util.List;

import com.withiter.entity.Video;

public class VideoDao {
	private static List<Video> videoList;
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
		videoList.add(video);
	}
	public void deleteVideo(Video video) {
		videoList.remove(video);
	}

	private Object[] formatData(Video video) {
		Object[] result = new Object[5];
		result[0] = video.name;
		result[1] = video.ext;
		result[2] = video.path;
		result[3] = video.size;
		result[4] = video.updateTime;
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

}

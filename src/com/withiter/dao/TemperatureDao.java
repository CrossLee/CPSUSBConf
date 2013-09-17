package com.withiter.dao;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.withiter.entity.Temperature;
import com.withiter.entity.USBConfig;
import com.withiter.utils.FileReaderUtils;

public class TemperatureDao {
	private static List<Temperature> temperatureList;
	private static TemperatureDao temperatureDao;

	public static TemperatureDao instance() {
		if (temperatureDao == null)
			temperatureDao = new TemperatureDao();
		return temperatureDao;
	}

	public TemperatureDao() {
		temperatureList = new ArrayList<Temperature>();
	}

	public void addTemperature(Temperature Temperature) {
		temperatureList.add(Temperature);
	}
	public void deleteTemperature(Temperature Temperature) {
		temperatureList.remove(Temperature);
	}

	private Object[] formatData(Temperature Temperature) {
		Object[] result = new Object[5];
		result[0] = Temperature.content;
		return result;
	}

	public Object[][] getTemperaturesData() {
		Object[][] result = new Object[TemperatureDao.instance().gettemperatureList()
				.size()][5];
		int i = 0;
		for (Temperature Temperature : temperatureList) {
			result[i] = formatData(Temperature);
			i++;
		}
		return result;
	}

	public List<Temperature> gettemperatureList() {
		return temperatureList;
	}

	public void writeToIniFile() throws IOException {
		System.out.println("start to write info into temperature.ini");
		
		String temperatureIni = USBConfig.drivePath + USBConfig.INIT_NEW_FOLDER + "\\temperature.ini";
		File f = new File(temperatureIni);
		if(f.exists()){
			f.delete();
		}
		f.createNewFile();
		List<String> lines = new ArrayList<String>();
		lines.add("[temperature]");
		lines.add("strdata="+this.gettemperatureList().get(0).content);
		System.out.println("the lines in memory ===============");
		for(String s : lines){
			System.out.println(s);
		}
		System.out.println("the lines in memory ===============");
		
		FileReaderUtils.writeToFile(lines, f);
	}

}

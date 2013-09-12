package com.withiter.dao;

import java.util.ArrayList;
import java.util.List;

import com.withiter.entity.Temperature;

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

}

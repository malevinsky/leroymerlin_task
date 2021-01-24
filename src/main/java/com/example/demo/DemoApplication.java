package com.example.demo;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.HashMap;

/**
 *  created by malevinsky
 *  email: malevwork@gmail.com
 *  telegram: @theos_deus
 *  date: 23.01.2021
 */

@SpringBootApplication
public class DemoApplication {
	public static void main(String[] args) {
		String link = "/home/theodora/Downloads/demo/src/main/resources/file.json";

		try {
			String content = new String(Files.readAllBytes(Paths.get(link)));
			JSONObject obj = new JSONObject(content);

			JSONObject displayed = obj.getJSONObject("displayedName");
			JSONObject disp_fin = displayed.getJSONObject("displayedName");
			JSONArray aaa = disp_fin.getJSONArray("value");
			String f = aaa.toString();
			f = removeCharAt(f, 0);
			f = removeCharAt(f, f.length() - 1);
			//System.out.println("Название товара:\n" + disp_fin.getJSONArray("value"));
			System.out.println(f);

			JSONObject stock = obj.getJSONObject("stock");
			JSONObject stocks = stock.getJSONObject("stocks");
			JSONObject region = stocks.getJSONObject("34");
			JSONArray ja = new JSONArray();
			ja.put(region);

			for (int i = 0, size = ja.length(); i < size; i++)
			{
				JSONObject objectInArray = ja.getJSONObject(i);
				String[] elementNames = JSONObject.getNames(objectInArray);

				int min = 0;

				ArrayList<Integer> array_of_shops = new ArrayList<>();
				HashMap<Integer, Integer> map = new HashMap<>();

				for (String elementName : elementNames) {
					String valuee = objectInArray.getString(elementName);
					int x = Integer.parseInt(valuee);
					int y = Integer.parseInt(elementName);
					map.put(x, y);

					min = Math.max(min, x);
					if (x > 0) {
						array_of_shops.add(y);
					}
				}
				all_shops(array_of_shops);
				maximum(map, min);
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public static String removeCharAt(String s, int pos) {
		return s.substring(0, pos) + s.substring(pos + 1); // Возвращаем подстроку s, которая начиная с нулевой позиции переданной строки (0) и заканчивается позицией символа (pos), который мы хотим удалить, соединенную с другой подстрокой s, которая начинается со следующей позиции после позиции символа (pos + 1), который мы удаляем, и заканчивается последней позицией переданной строки.
	}

	public static void all_shops(ArrayList<Integer> array_of_shops) {
		Collections.sort(array_of_shops);
		System.out.println("\nМагазины, в которых имеется товар:\n" + array_of_shops + "\n");
	}

	public static void maximum(HashMap<Integer, Integer> map, int min) {
		int biggest = min;
		int shop = map.remove(biggest);
		System.out.println("Больше всего товара находится в магазине " + shop + " в колличестве " + biggest + " штук");
	}
}
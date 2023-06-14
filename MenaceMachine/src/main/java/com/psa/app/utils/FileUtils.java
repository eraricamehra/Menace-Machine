package com.psa.app.utils;

import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.psa.app.model.Bead;
import com.psa.app.model.Box;
import com.psa.app.service.MenaceMachineService;

public class FileUtils {
	
	static Logger log = LoggerFactory.getLogger(MenaceMachineService.class);

	
	public static Properties loadPropertiesFile(String filename) throws Exception {
		Properties prop = new Properties();
		InputStream in = new FileInputStream(filename);
		prop.load(in);
		in.close();
		return prop;
	}
	
	
	public static void writeToFile(String content, String filename) throws IOException {

		FileWriter writer = new FileWriter(filename);
		BufferedWriter bw = new BufferedWriter(writer);
		bw.write(content.toString());
		bw.close();

	}

	// TODO: getTrainingset
	public static void writeToProps(Map<String, Box> hashmap) {
		Properties properties = new Properties();
		for (Map.Entry<String, Box> entry : hashmap.entrySet()) {
//			log.info(entry.getKey() + ": " + entry.getValue().toString());
			properties.put(entry.getKey(), entry.getValue().toString());
		}
		try {
			properties.store(new FileOutputStream("training.properties"), "training data");
		} catch (IOException e) {
		}
	}

	public static Map<String, Box> readTrainingData() throws Exception {
		Properties props = loadPropertiesFile("training.properties");
		Map<String, Box> hashmap = new HashMap<>();

		for (Map.Entry<Object, Object> entry : props.entrySet()) {
			List<Bead> beads = new ArrayList<Bead>();
			String beadstring = entry.getValue().toString();
			if(beadstring != null &&  !beadstring.isEmpty() && beadstring.length() > 1) {
			String beadstr = beadstring.substring(1);
			String[] arr = beadstr.split(",");
			for (String s : arr) {
				beads.add(new Bead(Integer.parseInt(s)));
			}
			}
			hashmap.put(entry.getKey().toString(), new Box(beads));
		}
		return hashmap;

	}


}

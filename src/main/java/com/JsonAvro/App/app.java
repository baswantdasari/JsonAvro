package com.JsonAvro.App;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.json.JSONObject;
import org.kitesdk.data.spi.JsonUtil;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class app {

	public static void main(String[] args) {

		String file = "src/main/resources/InputJson.json";
		String json;
		BufferedWriter out = null;
		try {
			json = new String(Files.readAllBytes(Paths.get(file)));

			String avroSchema = JsonUtil.inferSchema(JsonUtil.parse(json), "avro").toString();
			String prettyJson = formatJsonString(avroSchema);
			out = new BufferedWriter(new FileWriter("src/main/resources/OutputJson.json"));
			out.write(prettyJson);
			out.close();
		} catch (IOException error) {
			error.printStackTrace();
		}
	}

	public static String formatJsonString(String jsonString) {

		JSONObject json = new JSONObject(jsonString);

		Gson gson = new GsonBuilder().setPrettyPrinting().create();

		return gson.toJson(json);

	}

}

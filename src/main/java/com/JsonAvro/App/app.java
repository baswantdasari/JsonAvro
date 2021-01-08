package com.JsonAvro.App;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.kitesdk.data.spi.JsonUtil;

public class app {

	public static void main(String[] args) {

		String file = "src/main/resources/InputJson.json";
		String json;
		BufferedWriter out = null;
		try {
			json = new String(Files.readAllBytes(Paths.get(file)));

			String avroSchema = JsonUtil.inferSchema(JsonUtil.parse(json), "avro").toString();
			out = new BufferedWriter(new FileWriter("test.json"));
			out.write(avroSchema);
			out.close();
		} catch (IOException error) {
			error.printStackTrace();
		}
	}

}

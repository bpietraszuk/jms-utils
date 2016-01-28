package home.mlody.utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class FileUtils {
	public static void writeToFile(String content) {
		try {
		    Files.write(Paths.get("/opt/logsapp/test"), content.getBytes(), StandardOpenOption.APPEND);
		}catch (IOException e) {
			e.printStackTrace();
		}
	}
}

package com.estate.utils;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

@Component
public class UploadFileUtils {

	private static Logger LOGGER = LoggerFactory.getLogger(UploadFileUtils.class);

	@Value("${dir.default}")
	private String dirDefault;

	public void writeOrUpdate(String path, byte[] bytes) {
		path = dirDefault + File.separator + path;
		File file = new File(dirDefault + File.separator + StringUtils.substringBeforeLast(File.separator, path));
		if (!file.exists()) {
			file.mkdir();
		}
		try(FileOutputStream outputStream = new FileOutputStream(path)) {
			outputStream.write(bytes);
		} catch (IOException e) {
			LOGGER.error(e.getMessage(), e);
		}
	}
}

package com.castis.adlib.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.io.FilenameUtils;

import java.io.File;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FileInfo {

	public File file;
	public String extension;
	public String baseName;

	public String sdFile;
	public String hdFile;
	public String fileName;
	public Boolean isHdFile;

	public FileInfo(File file) {
		this.file = file;
		this.fileName = file.getName();
		this.baseName = FilenameUtils.getBaseName(file.getName());
		this.extension = FilenameUtils.getExtension(file.getName());

		this.sdFile = this.baseName + "_sd.mpg";
		this.hdFile = this.baseName + "_hd.mpg";

	}

	public FileInfo(File file, String encodingPath) {
		this.file = file;
		this.fileName = file.getName();
		this.baseName = FilenameUtils.getBaseName(file.getName());
		this.extension = FilenameUtils.getExtension(file.getName());

		if(encodingPath != null) {
			this.sdFile = encodingPath + "/" + this.baseName + "_sd.mpg";
			this.hdFile = encodingPath + "/" + this.baseName + "_hd.mpg";
		}
	}

	public FileInfo(String adFileName) {
		this.file = new File(adFileName);
		this.fileName = file.getName();
		this.extension = FilenameUtils.getExtension(file.getName());

		String baseName = FilenameUtils.getBaseName(file.getName());
		String[] strArray = baseName.split("_");
		this.baseName = strArray[0];

		this.isHdFile = !strArray[1].equalsIgnoreCase("sd");

	}
}

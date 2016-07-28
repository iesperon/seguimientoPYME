package es.udc.fi.tfg.seguimiento.utils;

import org.springframework.web.multipart.MultipartFile;

public class FileUpload {

	public MultipartFile file;

	public MultipartFile getFile() {
		return file;
	}

	public void setFile(MultipartFile file) {
		this.file = file;
	}
	
	 
}

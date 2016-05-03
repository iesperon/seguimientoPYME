package es.udc.fi.tfg.seguimiento.utils;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.springframework.web.multipart.MultipartFile;

public class SaveImage {

	public void validateImage(MultipartFile image){
		if(!image.getContentType().equals("image/jpeg")){
		throw new ImageUploadException("OnlyJPGimagesaccepted");
		}
	}
	
	public void saveImage(String filename, MultipartFile image) throws ImageUploadException{
		try{
			String workingDir = System.getProperty("user.dir");
			File file = new File(workingDir+"/src/main/resources/"+filename);
			FileUtils.writeByteArrayToFile(file, image.getBytes());
		}catch(IOException e){
			throw new ImageUploadException("No se puede guardar la imagen", e);
		}
		
	}
}

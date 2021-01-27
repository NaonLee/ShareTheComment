package com.spring.shareComm.common.file;

import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class DownloadController {
	private static final String IMAGE_REPO = "C:\\shareTheComment\\article_image";
	
	@RequestMapping("/download.do")
	protected void download(@RequestParam("imageFileName") String imageFileName, @RequestParam("articleNO") int articleNO, HttpServletResponse response) throws Exception{
		OutputStream out = response.getOutputStream();			//to out images
		String filePath = IMAGE_REPO + "\\" + articleNO + "\\" + imageFileName;	//image file's path
		File image = new File(filePath);						//get file by filePath
		
		response.setHeader("Cache-Control", "no-cache");
		response.addHeader("Content-disposition", "attachment; fileName=" + imageFileName);
		FileInputStream is = new FileInputStream(image);
		byte[] buffer = new byte[1024 * 8];
		while (true) {
			int count = is.read(buffer); // The number of characters in buffer
			if (count == -1) // Check if an index is at the last point in buffer(read all characters in buffer)
				break;
			out.write(buffer, 0, count);
		}
		is.close();
		out.close();
	}
}

package com.study.spring;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Controller
public class FileuploadController
{
	@RequestMapping("/fileUpload/uploadPath.do")
	public void uploadPath(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		String path = req.getSession().getServletContext().getRealPath("/resources/upload");
		resp.setContentType("text/html; charset=utf-8");
		PrintWriter pw = resp.getWriter();
		pw.print("/upload 디렉토리의 물리적경로 : "+path);
	}
	
	@RequestMapping("/fileUpload/uploadForm.do")
	public String uploadForm() {
		return "06FileUpload/uploadForm";
	}
	
	public static String getUuid() {
		String uuid = UUID.randomUUID().toString();
		System.out.println("생성된UUID-1:"+ uuid);
		uuid = uuid.replaceAll("-", "");
		System.out.println("생성된UUID-2:"+ uuid);
		return uuid;
	}
	
	@RequestMapping(method=RequestMethod.POST,
			value="/fileUpload/uploadAction.do")
	public String uploadAction(Model model,
							MultipartHttpServletRequest req) {
		
		String path = req.getSession().getServletContext()
							.getRealPath("/resources/upload");
		
		MultipartFile mfile = null;
		List<Object> resultList = new ArrayList<Object>();
		try
		{
			String title = req.getParameter("title");
			Iterator itr = req.getFileNames();
			
			while (itr.hasNext())
			{
				mfile = req.getFile(itr.next().toString());
				String originalName = 
					new String(mfile.getOriginalFilename().getBytes(),
						"UTF-8");
				
				if("".equals(originalName)) continue;
				String ext = originalName.substring(
						originalName.lastIndexOf('.'));
				String saveFileName = getUuid() + ext;
				mfile.transferTo(new File(path + File.separator + 
						saveFileName));
				
				Map<String, String> fileMap = new HashMap<String, String>();
				fileMap.put("originalName", originalName);
				fileMap.put("saveFileName", saveFileName);
				fileMap.put("title", title);
				
				resultList.add(fileMap);
			}
		} catch (Exception e)
		{
			e.printStackTrace();
		}

		model.addAttribute("resultList", resultList);
		return "06FileUpload/uploadAction";
	}
	
}



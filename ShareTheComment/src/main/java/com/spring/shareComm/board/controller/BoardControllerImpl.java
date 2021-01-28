package com.spring.shareComm.board.controller;

import java.io.File;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;


import com.spring.shareComm.board.service.BoardService;
import com.spring.shareComm.board.vo.ArticleVO;
import com.spring.shareComm.common.paging.Criteria;
import com.spring.shareComm.common.paging.PageMaker;
import com.spring.shareComm.member.vo.MemberVO;

@Controller("boardController")
public class BoardControllerImpl implements BoardController {
	@Autowired
	BoardService boardService;
	@Autowired
	ArticleVO articleVO;
	private static final String IMAGE_REPO = "C:\\shareTheComment\\article_image";			//set the directory for images

	//Home, main
	@RequestMapping(value= {"/","/main.do"}, method=RequestMethod.GET)
	public ModelAndView main(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView mav = new ModelAndView("main");

		return mav;
	}
	
	//Pop-up window 
	@RequestMapping(value= {"/popUp.do"}, method=RequestMethod.GET)
	public ModelAndView popUp(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView mav = new ModelAndView("popUp");
		return mav;
	}
	
	@Override
	@RequestMapping(value="/board/listArticles.do")		//list all article
	public ModelAndView allArticles(HttpServletRequest request, Criteria criteria) throws Exception {
		ModelAndView mav = new ModelAndView();
		
		PageMaker pageMaker = new PageMaker();
		pageMaker.setCriteria(criteria);
		pageMaker.setTotalCount(boardService.count());				//number of articles

		List articles = boardService.allArticles(criteria);
		/* to check level
		 * Iterator<ArticleVO> a = articles.iterator();
		 * 
		 * while(a.hasNext()) { int n = a.next().getLevel();
		 * System.out.println("level: " + n); }
		 */
		String viewName = getViewName(request);
		mav.addObject("articles", articles);			//pass all article information list
		mav.addObject("pageMaker", pageMaker);
		mav.setViewName(viewName);
		return mav;
	}

	@Override
	@RequestMapping(value="/board/addArticle.do", method=RequestMethod.POST)	//add a new article
	@ResponseBody
	public ResponseEntity addArticle(MultipartHttpServletRequest multipartRequest, HttpServletResponse response) throws Exception {
		multipartRequest.setCharacterEncoding("UTF-8");
		Map<String, Object> articleMap = new HashMap<String, Object>();		//Save article data to Map
		Enumeration enu = multipartRequest.getParameterNames();				//bring all names of data
		
		while(enu.hasMoreElements()) {								//loop until all data names has been reviewed
			String name = (String)enu.nextElement();				//select the next name
			String value = multipartRequest.getParameter(name);		//bring data by selected names
			articleMap.put(name, value);							//save name as a key, and save data as a value
		}
		
		String imageFileName = upload(multipartRequest);			//Image file upload method(line 221)
		HttpSession session = multipartRequest.getSession();
		MemberVO memberVO = (MemberVO)session.getAttribute("logMember");
		String id = memberVO.getId();
		articleMap.put("id", id);
		articleMap.put("imageFileName", imageFileName);
		
		String message;
		ResponseEntity resEnt = null;
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.add("Content-Type", "text/html; charset=utf-8");
		try {	//add new image
				int articleNO = boardService.addArticle(articleMap);
				if(imageFileName != null && imageFileName.length() != 0) {		//if there is the image selected
					File srcFile = new File(IMAGE_REPO + "\\" + "temp" + "\\" + imageFileName);
					File destDir = new File(IMAGE_REPO + "\\" + articleNO);
					FileUtils.moveFileToDirectory(srcFile, destDir, true); 		//move to destination directory form temporary
				}
				
				//alert the success of writing the article
				message = "<script>";
				message += " alert('New article has been added.');";
				message += " location.href='" + multipartRequest.getContextPath() + "/board/listArticles.do'; ";
				message += " </script>";
				
				resEnt = new ResponseEntity(message, responseHeaders, HttpStatus.CREATED);
		} catch (Exception e) {		//if there is an error
			File srcFile = new File(IMAGE_REPO + "\\" + "temp" + "\\" + imageFileName);
			srcFile.delete();		//remove temporary file
			
			//alert the failure
			message = " <script>";
			message += " alert('Error has been occured! Please retry.');";
			message += " location.href='" + multipartRequest.getContextPath() + "/board/articleForm.do'; ";	//back to add page
			message += "</script>";
			resEnt = new ResponseEntity(message, responseHeaders, HttpStatus.CREATED);
			e.printStackTrace();
		}
		return resEnt;
		
	}

	@RequestMapping(value="/board/replyArticle.do")			//reply (need parentNO)
	@ResponseBody
	public ResponseEntity reply(MultipartHttpServletRequest multipartRequest, HttpServletResponse response) throws Exception{
		multipartRequest.setCharacterEncoding("UTF-8");
		Map<String, Object> articleMap = new HashMap<String, Object>();		//Save article data to Map
		Enumeration enu = multipartRequest.getParameterNames();				//bring all names of data
		
		while(enu.hasMoreElements()) {								//loop until all data names has been reviewed
			String name = (String)enu.nextElement();				//select the next name
			String value = multipartRequest.getParameter(name);		//bring data by selected names
			articleMap.put(name, value);							//save name as a key, and save data as a value + parentNO is included
		}
		
		String imageFileName = upload(multipartRequest);			//Image file upload method(line 221)
		HttpSession session = multipartRequest.getSession();
		MemberVO memberVO = (MemberVO)session.getAttribute("logMember");
		String id = memberVO.getId();
		articleMap.put("id", id);
		articleMap.put("imageFileName", imageFileName);
		
		String message;
		ResponseEntity resEnt = null;
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.add("Content-Type", "text/html; charset=utf-8");
		try {	//add new image
				int articleNO = boardService.replyArticle(articleMap);
				if(imageFileName != null && imageFileName.length() != 0) {		//if there is the image selected
					File srcFile = new File(IMAGE_REPO + "\\" + "temp" + "\\" + imageFileName);
					File destDir = new File(IMAGE_REPO + "\\" + articleNO);
					FileUtils.moveFileToDirectory(srcFile, destDir, true); 		//move to destination directory form temporary
				}
				
				//alert the success of writing the article
				message = "<script>";
				message += " alert('New article has been added.');";
				message += " location.href='" + multipartRequest.getContextPath() + "/board/listArticles.do'; ";
				message += " </script>";
				
				resEnt = new ResponseEntity(message, responseHeaders, HttpStatus.CREATED);
				
		} catch (Exception e) {		//if there is an error
			File srcFile = new File(IMAGE_REPO + "\\" + "temp" + "\\" + imageFileName);
			srcFile.delete();		//remove temporary file
			
			//alert the failure
			message = " <script>";
			message += " alert('Error has been occured! Please retry.');";
			message += " location.href='" + multipartRequest.getContextPath() + "/board/replyForm.do'; ";	//Back to reply page
			message += "</script>";
			resEnt = new ResponseEntity(message, responseHeaders, HttpStatus.CREATED);
			e.printStackTrace();
		}
		return resEnt;
	}
	
	@RequestMapping(value="/board/viewArticle.do")	//view article detail
	public ModelAndView viewArticle(@RequestParam("articleNO") int articleNO, HttpServletRequest request) throws Exception {
		System.out.println("call view-article");
		
		articleVO = boardService.article(articleNO);
		ModelAndView mav = new ModelAndView();
		String viewName = getViewName(request);
		
		mav.addObject("article", articleVO);		//pass article information to viewArticle.jsp

		
		
		mav.setViewName(viewName);
		return mav;
	}
	
	
	//Count like
	@RequestMapping(value="/likeCount", method = RequestMethod.POST)
	public void likeCount(@RequestParam Map<String, String> article, HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		int articleNO = Integer.parseInt(article.get("articleNO"));
		articleVO = boardService.article(articleNO);				

		int likeCount = articleVO.getLikeCount();					
		response.getWriter().write(Integer.toString(likeCount));	//Pass number of like

	}
	
	@RequestMapping(value="/board/modArticle.do")	//modify article
	@ResponseBody
	public ResponseEntity  modArticle(MultipartHttpServletRequest multipartRequest, HttpServletResponse response) throws Exception {
		multipartRequest.setCharacterEncoding("UTF-8");
		//similart to adding article
		Map<String,Object> articleMap = new HashMap<String,Object>();
		Enumeration enu=multipartRequest.getParameterNames();
		while(enu.hasMoreElements()){
			String name=(String)enu.nextElement();
			String value=multipartRequest.getParameter(name);
			articleMap.put(name,value);
		}
		
		String imageFileName= upload(multipartRequest);
		articleMap.put("imageFileName", imageFileName);
		
		String articleNO=(String)articleMap.get("articleNO");
		String message;
		ResponseEntity resEnt=null;
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.add("Content-Type", "text/html; charset=utf-8");
		
		try {
		       boardService.modArticle(articleMap);
		       if(imageFileName!=null && imageFileName.length()!=0) {
		         File srcFile = new File(IMAGE_REPO+"\\"+"temp"+"\\"+imageFileName);
		         File destDir = new File(IMAGE_REPO+"\\"+articleNO);
		         FileUtils.moveFileToDirectory(srcFile, destDir, true);
		         
		         //!The difference of adding article, remove the previous image directory
		         String originalFileName = (String)articleMap.get("originalFileName");
		         File oldFile = new File(IMAGE_REPO+"\\"+articleNO+"\\"+originalFileName);
		         oldFile.delete();
		       }	
		       message = "<script>";
			   message += " alert('Article has been updated.');";
			   message += " location.href='"+multipartRequest.getContextPath()+"/board/viewArticle.do?articleNO="+articleNO+"';";
			   message +=" </script>";
		       resEnt = new ResponseEntity(message, responseHeaders, HttpStatus.CREATED);
		    }catch(Exception e) {
		      File srcFile = new File(IMAGE_REPO+"\\"+"temp"+"\\"+imageFileName);
		      srcFile.delete();
		      message = "<script>";
			  message += " alert('There is an error occured. Please retry.');";
			  message += " location.href='"+multipartRequest.getContextPath()+"/board/viewArticle.do?articleNO="+articleNO+"';";
			  message +=" </script>";
		      resEnt = new ResponseEntity(message, responseHeaders, HttpStatus.CREATED);
		    }
		    return resEnt;
	}
	
	@RequestMapping(value="/board/removeArticle.do")	//delete article
	public ModelAndView removeArticle(@RequestParam("articleNO") int no, HttpServletRequest request) throws Exception {
		System.out.println("call delete-article");
		ModelAndView mav = new ModelAndView();
		boardService.removeArticle(no);					//delete article by specific number
		
		mav.setViewName("redirect:/board/listArticles.do");
		return mav;
	}

	@RequestMapping(value={"/board/*Form.do"})	//replyForm, articleForm, modForm, modArticleForm
	public ModelAndView form(@RequestParam(value="articleNO", required=false) String articleNO,
					@RequestParam(value="parentNO", required = false) String parentNO, HttpServletRequest request, HttpSession session) throws Exception {
		System.out.println("Call Form");

		ModelAndView mav = new ModelAndView();
		if(parentNO != null)	//for reply, pass parent number for replyForm
			mav.addObject("parentNO", Integer.parseInt(parentNO));
		if(articleNO != null) {
			articleVO = boardService.article(Integer.parseInt(articleNO));
			mav.addObject("article", articleVO);
		}
					
		session = request.getSession();
		String viewName = getViewName(request);
		mav.setViewName(viewName);
		return mav;
	}
	
	
	
	//return view name from requested url
		private String getViewName(HttpServletRequest request) throws Exception {
			String contextPath = request.getContextPath();
			String uri = (String) request.getAttribute("javax.servlet.include.request_uri");
			if (uri == null || uri.trim().equals("")) {
				uri = request.getRequestURI();				//if uri is null, force to bring
			}

			int begin = 0;
			if (!((contextPath == null) || ("".equals(contextPath)))) {
				begin = contextPath.length();
			}

			int end;
			if (uri.indexOf(";") != -1) {
				end = uri.indexOf(";");
			} else if (uri.indexOf("?") != -1) {
				end = uri.indexOf("?");
			} else {
				end = uri.length();
			}

			String viewName = uri.substring(begin, end);
			if (viewName.indexOf(".") != -1) {
				viewName = viewName.substring(0, viewName.lastIndexOf("."));
			}
			if (viewName.lastIndexOf("/") != -1) {
				viewName = viewName.substring(viewName.lastIndexOf("/", 1), viewName.length());
			}
			return viewName;
		}
		
		//Image upload method
		private String upload(MultipartHttpServletRequest multipartRequest) throws Exception{
			String imageFileName = null;
			Iterator<String> fileNames = multipartRequest.getFileNames();
			
			while(fileNames.hasNext()) {
				String fileName = fileNames.next();
				MultipartFile mFile = multipartRequest.getFile(fileName);		//access to data by using file names
				imageFileName = mFile.getOriginalFilename();
				File file = new File(IMAGE_REPO+"\\"+"temp"+"\\"+fileName);
				if(mFile.getSize()!=0){ //if file is exist 
					if(!file.exists()){ //if there is no file in the directory
						file.getParentFile().mkdirs();  //create the directory
						mFile.transferTo(new File(IMAGE_REPO +"\\"+"temp"+ "\\"+imageFileName)); //send temp file
					}
				}
			}
			return imageFileName;
		}
}

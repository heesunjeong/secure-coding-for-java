package kr.co.bookking.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.co.bookking.VO.QnaVO;
import kr.co.bookking.service.QnaService;

@Controller
@RequestMapping(value="/qna")
public class QnaController {
	@Autowired
	QnaService qnaService;
	
	private static final Logger logger = LoggerFactory.getLogger(BookController.class);
	
	@RequestMapping(value="/list", produces = "application/text; charset=utf8", method = RequestMethod.GET)
	public @ResponseBody String getQnaContentByQnaId(String qnaId) throws Exception {
		logger.info(qnaService.getQnaContentByQnaId(Integer.parseInt(qnaId)));
		
		return qnaService.getQnaContentByQnaId(Integer.parseInt(qnaId)); 
	}
	
	@RequestMapping(value="/popup", method = RequestMethod.GET)
	public String getQnaForm(HttpServletRequest request, String bookId, String userId, Model model) throws Exception {
		logger.info("bookId: " + bookId + "  userId: " + userId);
		model.addAttribute("bookId",bookId);
		model.addAttribute("userId",userId);
		
		return "/detail/popup";
	}
	
	@RequestMapping(value="/register", produces = "application/json; charset=utf8", method = RequestMethod.POST)
	// public @ResponseBody String insertQnaContent(String bookId, String userId, String qnaTitle, String qnaContent) throws Exception {
	public @ResponseBody String insertQnaContent(@RequestBody QnaVO qnaVO) throws Exception {

		//logger.info("bookId: "+Integer.parseInt(bookId)+"  userId: "+userId+"  qnaTitle: " + qnaTitle + "  qnaContent: " + qnaContent);
		//QnaVO qnaVO = new QnaVO(-1, Integer.parseInt(bookId), userId, qnaTitle, qnaContent, null);
		
		System.out.println("VO=" + qnaVO);
		
		if(qnaVO==null){
			System.out.println("객체가 만들어지지 않았습니다.");
		}else{
			System.out.println(qnaVO);
		}
		return String.valueOf(qnaService.insertQnaContent(qnaVO)); 
	}
	
	@RequestMapping(value="/register", produces = "application/text; charset=utf8", method = RequestMethod.GET)
	public @ResponseBody String insertQnaContentGetMethod(QnaVO qnaVO, HttpSession session, HttpServletRequest request) throws Exception {
		System.out.println("VO=" + qnaVO);
		
		// csrf토큰으로 무분별한 요청 거를 수 있임
		String sessionToken = (String)session.getAttribute("csrf");
		String requestToken = qnaVO.getCsrf();
		
		System.out.println("requestToken : " + requestToken + ", sessionToken:" + sessionToken);

		if (requestToken == null || !sessionToken.equals(requestToken)) {
			return "{\"result\":\"false\"}";
		}
		
		if(qnaVO==null){
			System.out.println("객체가 만들어지지 않았습니다.");
		}else{
			System.out.println(qnaVO);
		}
		return String.valueOf(qnaService.insertQnaContent(qnaVO)); 
	}
}

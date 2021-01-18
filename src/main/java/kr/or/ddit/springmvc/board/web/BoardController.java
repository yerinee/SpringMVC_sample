package kr.or.ddit.springmvc.board.web;

import java.lang.reflect.InvocationTargetException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.validator.GenericValidator;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springmodules.validation.commons.DefaultBeanValidator;

import egovframework.rte.fdl.property.EgovPropertyService;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;
import kr.or.ddit.springmvc.board.service.BoardService;
import kr.or.ddit.springmvc.board.vo.BoardVO;

@Controller
public class BoardController {
	/** EgovSampleService */
	@Resource(name = "boardService")
	private BoardService boardService;

	/** EgovPropertyService */
	@Resource(name = "propertiesService")
	protected EgovPropertyService propertiesService;

	/** Validator */
	@Resource(name = "beanValidator")
	protected DefaultBeanValidator beanValidator;
	
	@RequestMapping(value = "/board/retrievePagingList.do")
	public String retrievePagingList(BoardVO boardVO, ModelMap model) throws Exception {
		/** EgovPropertyService.sample */
		boardVO.setPageUnit(propertiesService.getInt("pageUnit"));
		boardVO.setPageSize(propertiesService.getInt("pageSize"));

		/** pageing setting */
		PaginationInfo paginationInfo = new PaginationInfo(); 
		boardVO.preparePaginationInfo(paginationInfo);		// 리팩토링
		
		List<BoardVO> resultList = boardService.retrievePagingList(boardVO);
		model.addAttribute("resultList", resultList);

		int totCnt = boardService.retrievePagingListCnt(boardVO);
		paginationInfo.setTotalRecordCount(totCnt);
		model.addAttribute("paginationInfo", paginationInfo);

		return "board/list";
	}
	
	
	@RequestMapping(value = "/board/updateView.do")
	public String updateView(BoardVO boardVO, ModelMap model) throws Exception {
		
		BoardVO retrieveBoardVO = boardService.retrieve(boardVO);
		
		retrieveBoardVO.copySearchCondition(boardVO); // 리팩토링

		model.addAttribute("boardVO", retrieveBoardVO);	

		return "board/edit";
	}
	
	@RequestMapping(value = "/board/update.do")
	public String update(BoardVO boardVO, ModelMap model) throws Exception {
		
		int cnt = boardService.update(boardVO);	

		return "forward:/board/retrievePagingList.do";
	}
	
	@RequestMapping(value = "/board/delete.do")
	public String delete(BoardVO boardVO, ModelMap model) throws Exception {
		
		int cnt = boardService.delete(boardVO);	

		return "forward:/board/retrievePagingList.do";
	}
	
	@RequestMapping(value = "/board/createView.do")
	public String createView(BoardVO boardVO, ModelMap model,  HttpSession session) throws Exception {
		
		String saveToken = UUID.randomUUID().toString();
		
//		boardVO.setBoardSn(null);
		boardVO = new BoardVO();
		boardVO.setSaveToken(saveToken);
		
		session.setAttribute("SAVE_TOKEN", saveToken);
		
		model.addAttribute("boardVO", new BoardVO());	

		return "board/edit";
	}
	
	@RequestMapping(value = "/board/create.do")
	public String create(BoardVO boardVO, BindingResult bindingResult, ModelMap model, HttpSession session) throws Exception {
		// Server-Side Validation
		beanValidator.validate(boardVO, bindingResult); //vaildation

		if (bindingResult.hasErrors()) {			
			return "board/edit";
		}
		
		String sessionSaveToken = (String)session.getAttribute("SAVE_TOKEN");
		String saveToken = boardVO.getSaveToken();
		
		// &두개는 (&&) 는 앞에 조건이 false면 뒤에 조건을 따지러가지않음
		// &한개(&)는 앞에 조건이 true이든 false이든 두 조건을 모두 비교함 
		
//		if(sessionSaveToken != null && !sessionSaveToken.equals("")) { // 두 조건 위치가 중요
//		} 
		
		// 위와 같은 코드
		if(!GenericValidator.isBlankOrNull(sessionSaveToken) ||
				!GenericValidator.isBlankOrNull(saveToken) ||
				sessionSaveToken.equals(saveToken)) {
			
			boardService.create(boardVO);	
			session.removeAttribute("SAVE_TOKEN");
		}else {
			
		}
		

		return "forward:/board/retrievePagingList.do";
	}
	
}

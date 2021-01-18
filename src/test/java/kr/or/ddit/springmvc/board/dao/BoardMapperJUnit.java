package kr.or.ddit.springmvc.board.dao;


import static org.junit.Assert.assertNotNull;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import kr.or.ddit.springmvc.board.vo.BoardVO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations= {
		"/egovframework/spring/context-common.xml"
	  , "/egovframework/spring/context-datasource.xml"
	  , "/egovframework/spring/context-mapper.xml"
})
public class BoardMapperJUnit {
	private static final Logger LOGGER = LoggerFactory.getLogger(BoardMapperJUnit.class);
	@Resource(name="boardMapper")
	private BoardMapper boardMapper;
	
//	@Test
	public void testCreate() throws Exception {
		BoardVO paramBoardVO = new BoardVO();
		for(int i=0;i<300;i++) {
			paramBoardVO.setTitle((i+1)+"번째 제목 입력");
			paramBoardVO.setContents((i+1)+"번째 내용 입력");
			paramBoardVO.setWriter("테스터");
			paramBoardVO.setUserPassword("12345");
			paramBoardVO.setOpenYn("Y");
			boardMapper.create(paramBoardVO);
			LOGGER.info("paramBoardVO.getBoardSn(): "+paramBoardVO.getBoardSn());
		}
		Assert.assertNotNull(paramBoardVO.getBoardSn());
	}
	
//	@Test
	public void testUpdate() throws Exception {
		BoardVO paramBoardVO = new BoardVO();
		
		paramBoardVO.setBoardSn("10");
		paramBoardVO.setTitle("제목 수정");
		paramBoardVO.setContents("내용 수정");
		paramBoardVO.setWriter("관리자");
		paramBoardVO.setUserPassword("111111");
		paramBoardVO.setOpenYn("N");
		int cnt = boardMapper.update(paramBoardVO);
		LOGGER.info("cnt: "+cnt);
		
		Assert.assertTrue(cnt > 0);
	}
	
//	@Test
	public void testDelete() throws Exception {
		BoardVO paramBoardVO = new BoardVO();
		
		paramBoardVO.setBoardSn("12");
		
		int cnt = boardMapper.delete(paramBoardVO);
		LOGGER.info("cnt: "+cnt);
		
		Assert.assertTrue(cnt > 0);
	}
	
	@Test
	public void testRetrieve() throws Exception {
		BoardVO paramBoardVO = new BoardVO();
		paramBoardVO.setBoardSn("10");
		
		BoardVO retrieveBoardVO = boardMapper.retrieve(paramBoardVO);
		LOGGER.info("retrieveBoardVO: "+retrieveBoardVO);
		
		Assert.assertNotNull(retrieveBoardVO);
	}
	
//	@Test
	public void testRetrieveList() throws Exception {
		BoardVO paramBoardVO = new BoardVO();
		List<BoardVO> list = boardMapper.retrieveList(paramBoardVO);
		for(BoardVO boardVO:list) {
			LOGGER.info(boardVO.getTitle());
		}
		
		Assert.assertTrue(list.size()>0);
	}
}

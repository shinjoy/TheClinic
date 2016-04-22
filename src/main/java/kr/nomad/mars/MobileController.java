package kr.nomad.mars;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.nomad.mars.dao.NoticeDao;

import kr.nomad.mars.dao.UserDao;

import kr.nomad.mars.dto.Notice;

import kr.nomad.mars.dto.User;
import kr.nomad.util.F;
import kr.nomad.util.ImageUtil;
import kr.nomad.util.Paging;
import kr.nomad.util.Response;
import kr.nomad.util.T;
import kr.nomad.util.file.UniqFileRenamePolicy;
import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.google.gson.Gson;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

@Controller
public class MobileController {

	@Autowired
	NoticeDao noticeDao;


	@Autowired
	UserDao userDao;
	

	
	// 페이지당 아이템 갯수
	@Value("#{config['page.itemCountPerPage']}")
	int ITEM_COUNT_PER_PAGE;

	// 페이징당 페이지 갯수
	@Value("#{config['page.pageCountPerPaging']}")
	int PAGE_COUNT_PER_PAGING;

	// 파일 루트
	@Value("#{config['file.root']}")
	String FILE_ROOT;

	String FILE_PATH = "";
	String FILE_LOCAL_PATH = "";

	// 파일 최대크기(Mb)
	@Value("#{config['file.maxSize']}")
	int FILE_MAX_SIZE;

	// 이용약관
	@RequestMapping("/m/terms_use.go")
	public String mTermsUseController(
			@RequestParam(value = "seq", required = false, defaultValue = "0") int seq,
			HttpSession session, Model model) {

		return "/m/terms_use";
	}

	// 개인정보보호정책
	@RequestMapping("/m/terms_personal.go")
	public String mTermsPersonalController(
			@RequestParam(value = "seq", required = false, defaultValue = "0") int seq,
			HttpSession session, Model model) {

		return "/m/terms_personal";
	}
	
	/********************************************************************************
	 * 공지사항 공지
	 * 
	 * @param seq
	 * @param model
	 * @return
	 */
	@RequestMapping("/m/notice/notice.go")
	public String mNoticeController(
			@RequestParam(value = "keyword", required = false, defaultValue = "") String keyword,
			@RequestParam(value = "ctrl", required = false, defaultValue = "0") int ctrl,
			HttpSession session, Model model) {
		// User loginUser =
		// userDao.getUser(session.getAttribute("USER_ID").toString());

		// model.addAttribute("loginUser", loginUser);
		model.addAttribute("keyword", keyword);
		model.addAttribute("ctrl", ctrl);
		return "m/notice/notice";
	}

	@RequestMapping("/m/notice/notice_list.go")
	public String mNoticeListController(
			@RequestParam(value = "page", required = false, defaultValue = "1") int page,
			@RequestParam(value = "keyword", required = false, defaultValue = "") String keyword,
			@RequestParam(value = "ctrl", required = false, defaultValue = "0") int ctrl,
			HttpSession session, Model model) {

		List<Notice> list = null;
		int count = 0;
		int notiType = 0;
		list = noticeDao.getNoticeMainList(page, ITEM_COUNT_PER_PAGE);
		count = noticeDao.getNoticeMainCount();

		// 페이징
		String paging = Paging.getPaging2(page, count, ITEM_COUNT_PER_PAGE,PAGE_COUNT_PER_PAGING);

		model.addAttribute("list", list);
		model.addAttribute("paging", paging);
		model.addAttribute("keyword", keyword);
		model.addAttribute("currentPage", page);
		return "m/notice/notice_list";
	}

	// 공지사항 상세보기 페이지
	@RequestMapping("/m/notice/notice_view.go")
	public String mNoticeViewController(
			@RequestParam(value = "seq", required = false, defaultValue = "0") int seq,
			@RequestParam(value = "ctrl", required = false, defaultValue = "0") int ctrl,
			HttpSession session, Model model) {

		Notice notice = null;
		if (seq == 0) {
			notice = new Notice();
		} else {
			notice = noticeDao.getNotice(seq);
		}

		
		model.addAttribute("notice", notice);
		model.addAttribute("ctrl", ctrl);
		return "m/notice/notice_view";
	}

	

	
	
}

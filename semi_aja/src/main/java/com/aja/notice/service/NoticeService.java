package com.aja.notice.service;

import java.sql.Connection;
import java.util.List;

import com.aja.notice.dao.NoticeDao;
import com.aja.notice.dto.Notice;

import static com.aja.common.JDBCTemplate.close;


public class NoticeService {
	
	public List<Notice> selectNotice() {
	NoticeDao dao=new NoticeDao();
		
	//1. 공지사항 목록 조회
	Connection conn=null;
	
	List<Notice> listNotice=dao.selectNotice(conn);
	
	close(conn);
	return listNotice;
	}
}

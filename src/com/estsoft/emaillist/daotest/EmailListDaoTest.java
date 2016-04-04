package com.estsoft.emaillist.daotest;

import java.util.List;

import com.estsoft.DB.MySQLWebDBConnection;
import com.estsoft.emaillist.dao.EmailListDao;
import com.estsoft.emaillist.vo.EmailListVo;

public class EmailListDaoTest {

	public static void main(String[] args) {
//		EmailListDao dao = new EmailListDao(new MySQLWebDBConnection());
//		dao.getList();
		
		getInsertTest();
		getListTest();
	}
	public static void getInsertTest(){
		EmailListVo vo = new EmailListVo();
		vo.setFirstName("둘");
		vo.setLastName("리");
		vo.setEmail("lucklys324");
		EmailListDao dao = new EmailListDao(new MySQLWebDBConnection());

		dao.insert(vo);
	}
	public static void getListTest(){
		EmailListDao dao = new EmailListDao(new MySQLWebDBConnection());
		List<EmailListVo> list = dao.getList();
		for(EmailListVo vo : list){
			System.out.println(vo);
		}
	}

}

package com.spring.shareComm.member.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.spring.shareComm.member.vo.MemberVO;

@Component("memberDAO")
public class MemberDAOImpl implements MemberDAO {
	@Autowired
	SqlSession sqlSession;
	
	@Override
	public List selectAllMembers() {
		List<MemberVO> membersList = new ArrayList<MemberVO>();
		membersList = sqlSession.selectList("mapper.member.selectAllMembers");
		return membersList;
	}
	
	@Override
	public MemberVO select(String id) {
		MemberVO memberVO = sqlSession.selectOne("mapper.member.selectMember", id);
		return memberVO;
	}
	
	@Override
	public MemberVO select(MemberVO memberVO) {
		return sqlSession.selectOne("mapper.member.selectMember2", memberVO);
		
	}

	@Override
	public void insertMember(MemberVO memberVO) {
		sqlSession.insert("mapper.member.insertMember", memberVO);
	}

	@Override
	public void deleteMember(String id) {
		sqlSession.delete("mapper.member.deleteMember", id);
	}

	@Override
	public void updateMember(MemberVO memberVO) {
		sqlSession.update("mapper.member.updateMember", memberVO);
	}

	@Override
	public MemberVO loginById(MemberVO memberVO) {
		return sqlSession.selectOne("mapper.member.loginById", memberVO);
	}
}

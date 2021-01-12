package com.spring.shareComm.member.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.shareComm.member.dao.MemberDAO;
import com.spring.shareComm.member.vo.MemberVO;

@Service("memberService")
public class MemberServiceImpl implements MemberService {
	@Autowired
	MemberDAO memberDAO;
	
	@Override
	public List selectAll() {
		System.out.println("call selectAll from service");
		List membersList = memberDAO.selectAllMembers();
		
		return membersList;
	}

	@Override
	public void addMember(MemberVO memberVO) {
		memberDAO.insertMember(memberVO);
	}

	@Override
	public void removeMember(String id) {
		memberDAO.deleteMember(id);
	}

	@Override
	public void modMember(MemberVO memberVO) {
		memberDAO.updateMember(memberVO);
	}
	
	
	
}

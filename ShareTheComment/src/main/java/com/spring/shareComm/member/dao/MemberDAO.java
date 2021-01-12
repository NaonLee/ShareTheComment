package com.spring.shareComm.member.dao;

import java.util.List;

import com.spring.shareComm.member.vo.MemberVO;

public interface MemberDAO {
	public List<MemberVO> selectAllMembers();
	public void insertMember(MemberVO memberVO);
	public void deleteMember(String id);
	public void updateMember(MemberVO memberVO);
	public MemberVO select(String id);
}

package com.neuralnoise.map.data;

import java.util.List;

import com.neuralnoise.map.model.Member;

public interface MemberDAO {
    
	public Member findById(Long id);
    public Member findByEmail(String email);
    public List<Member> findAllOrderedByName();
    public void register(Member member);
    
}
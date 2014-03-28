package com.neuralnoise.map.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.neuralnoise.map.data.MemberDAO;
import com.neuralnoise.map.model.Member;

@Controller
@RequestMapping("/rest/members")
public class MemberRESTController {
    @Autowired
    private MemberDAO memberDAO;

    @RequestMapping(method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody List<Member> listAllMembers() {
        return memberDAO.findAllOrderedByName();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody Member lookupMemberById(@PathVariable("id") Long id) {
        return memberDAO.findById(id);
    }
}
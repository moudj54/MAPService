package com.neuralnoise.map.web;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.UnexpectedRollbackException;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.neuralnoise.map.data.MemberDAO;
import com.neuralnoise.map.model.Member;

@Controller
@RequestMapping(value = "/")
public class MemberController {
    @Autowired
    private MemberDAO memberDAO;

    @RequestMapping(method = RequestMethod.GET)
    public String displaySortedMembers(Model model) {
        model.addAttribute("newMember", new Member());
        model.addAttribute("members", memberDAO.findAllOrderedByName());
        return "index";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String registerNewMember(@Valid @ModelAttribute("newMember") Member newMember, BindingResult result, Model model) {
        if (!result.hasErrors()) {
            try {
                memberDAO.register(newMember);
                return "redirect:/";
            } catch (UnexpectedRollbackException e) {
                model.addAttribute("members", memberDAO.findAllOrderedByName());
                model.addAttribute("error", e.getCause().getCause());
                return "index";
            }
        } else {
            model.addAttribute("members", memberDAO.findAllOrderedByName());
            return "index";
        }
    }
}

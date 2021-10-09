package hello.hellospring.controller;

import hello.hellospring.domain.Member;
import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class MemberController {
    MemberService memberService;

    @Autowired //Autowired 로  일단 MemberService 에 의존관계를 설정하고 MemberService 에 연결되어 있는 애들과도 상호작용함
    //Controller는 그냥 사용만 하면 되니까 MemberService와 직접적인 의존관계가 있고 나머지는 그냥 MemberService 에 연결이 되어서 저장공간만 내어주면 됨
    public MemberController(MemberService memberService){
        this.memberService = memberService;
    } //Member repository 를 다 MemberService 에 담아서 return 해주었으니 실제로 controller 에서는 memberService 만 받아서 쓰면 됨

    @GetMapping("/members/new")
    public String createForm(){
        return "members/createMemberForm";
    }

    @PostMapping("/members/new")
    public String create(MemberForm form) {
        Member member = new Member();
        member.setName(form.getName());
        //spring bean 이 polymorphism 을 제공해주는 듯
        memberService.join(member); // 실제로 구현 한 것으로 spring Bean 만 바꾸면 다른 소스코드를 바꾸지 않아도 여기에서 다 사용 가능하다.

        return "redirect:/";
    }
    @GetMapping("/members")
    public String post(Model model){
        List<Member> members = memberService.findMembers();
        model.addAttribute("members" , members);
        return "members/memberList";
    }
}

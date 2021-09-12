package hello.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping; //쨋든 이것들을 무조건 import 해주어야 하는 듯
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
//그냥 index가 welcome homepage 인

@Controller
public class HelloController {

    @GetMapping("hello") //해당 html 문서를 찾는 것 같은듯
    public String hello(Model model){ //model 로 받아서 아마 hello 에 있는 data 를 해당 문자열로 바꾸고 hello 에다가 리턴 해주는 것 아닐까?
        model.addAttribute("data" , "fucking");
        return "hello"; //이렇게 하면 hello html 을 찾아서 resource 아래에 거기서 이제 data 라는 것을 해당 내가 넘긴 문자열로 변경해줌
    } // .bat 파일 build 한 뒤에 java -jar 하면 터미널에서 실행시킬수도 있음 그래서 이렇게 만들어서 빌드 파일 배포하면 서버 파일 배포할 수 있는 것

    @GetMapping("fucking") //드디어 된다. GetMapping 이것이라 맵핑 되는 게 맞는 듯
    public String hello1(Model model){
        model.addAttribute("data", "fuck you");
        return "hello";
    }

    @GetMapping("hello-mvc") //server에서 넘겨준 url을 가지고 이것과 맵핑 해서 하는 듯
    public String helloMvc(@RequestParam(value = "name") String name, Model model){ // request param 으로 받으면 server에서 넘어오는 것을 받는 것인 듯
        model.addAttribute("name",name);
        return "hello-template";
    }

    @GetMapping("hello-string")
    @ResponseBody //json 파일로 viewresolver가 받는게 아니라 jsonconverter 로 바로 하는 것 그냥 문자열로 오면 stringconverter로
    public String helloString(@RequestParam("name") String name){
        return "hello " + name;
    }
    @GetMapping("hello-api") //responseBody가 없으면 viewresolver 가 받고 있으면 HttpMessageConverter가 받는 것임
    @ResponseBody //이런식으로 구성하면 default 로 json 으로 넘어감 response body를 사용한 api 방식은 body내용을 그냥 바로 반환을
    //근데 여기서 이제 객체가 넘어오면 jsonconverter 로 넘어가고 문자열이 넘어오면 stringconverter 로 감 stringconverter는 그냥 바로 출력 jsonconverter 는 key 와 value 로 출력하는 것
        public Hello helloApi(@RequestParam("name") String name){ // request로 받는 것 사이트에서
        Hello hello = new Hello(); // hello 라는 객체로 싸서 보내면 다르게 나옴
        hello.setName(name);
        return hello; //json 형식이고 key 와 value 의 형태로 출력이 된다. 객체 반환
    } //그냥 문자열이 넘어오면은 그냥 바로 출력하지만 객체로 넘어오면은 조건에 따라서 key 와 value로 json 스타일로 반환함
    static class Hello{ //static class 로 선언해서 HelloController 안에서 이 객체를 사용할 수 있도록 한다.
        private String name;

        public String getName(){
            return name; //api 에서 알아서 getName 해주나 봄
        }
        public void setName(String name) {
            this.name = name;
        } //api 사용하면 view 파일이 필요 x
    }
}



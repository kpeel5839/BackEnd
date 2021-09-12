package hello1.hellospringparctice.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {
    @GetMapping("/hello1")
    public String hello1(Model model){
        model.addAttribute("data", "fuck you");
        return "hello1";
    }
    @GetMapping("hello-mvc")
    public String hello2(@RequestParam("name") String name, Model model){
        model.addAttribute("name", name);
        return "hello-mvc";
    }
    @GetMapping("hello-test-api")
    @ResponseBody
    public String hello3(@RequestParam("name") String name){
        return "hello " + name;
    }

    static public class Hello{
        private String name;

        public String getName(){
            return this.name;
        }
        public void setName(String name){
            this.name = name;
        }
    }

    @GetMapping("hello-api")
    @ResponseBody
    public Hello hello4(@RequestParam("name") String name){
        Hello hello = new Hello();
        hello.setName(name);
        return hello;
    }
}

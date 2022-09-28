package my.frist.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class helloController {

    // GetMapping()으로 괄호 안의 문자열을 주소와 맵핑해줍니다.(예: localhost:500/hello)
    @GetMapping("hello")
    public String hello(Model model) {
        model.addAttribute("greeting", "hello~!!!");
        return "hello"; // return "hello"가 templates의 hello.html의 파일명과 일치되며 매핑되는 구조입니다.(thymeleaf에서)
    }

    // mvc + template engin은 template engin을 Model, View, Controller방식으로 쪼개서
    // View를 template engin으로 html을 작성하여 랜더링 된 html를 client에게 전달해줍니다.
    @GetMapping("hello-mvc")
    public String helloMvc(@RequestParam("name") String name, Model model) {
        model.addAttribute("name", name);
        return "hello-template";
    }

    // @ResponseBody를 통해 http body에 문자 내용을 직접 반환합니다.(view X)
    @GetMapping("hello-string")
    @ResponseBody
    public String helloString(@RequestParam("name") String name) {
        return "hello " + name;
    }

    // @ResponseBody가 붙어 있으면 HttpMessageConverter가 동작하여 받은 데이터가 Json이면 JsonConverter(MappingJackson2와 같은 내장 라이브러리)가, string이면 StringConverter가 동작하여 obj나 str로 응답합니다.
    // 클라이언트의 HTTP Accept header와 서버의 컨트롤러 반환 타입 정보 둘을 조합하여 HttpMessageConverter가 선택됩니다.
    @GetMapping("hello-api")
    @ResponseBody
    public Hello helloApi(@RequestParam("name") String name) {
        Hello hello = new Hello();
        hello.setName(name);
        return hello;
    }

    // java bin 표준 양식
    // private를 외부에서 바로 접근할 수 없도록 하고 public get/set으로 접근토록 합니다.
    static class Hello {
        private String name;

        public String getName() {
            System.out.println("I'm getter of name obj: " + this.name);
            return name;
        }

        public void setName(String name) {
            this.name = name;
            System.out.println("I'm setter of name obj: " + this.name);
        }
    }
} 
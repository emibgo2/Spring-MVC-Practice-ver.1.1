package hello.springmvc.basic.request;

import hello.springmvc.basic.HelloData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

@Slf4j
@Controller
public class RequestParamController {

    @RequestMapping("/request-param-v1")
    public void requestParamV1(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String username = request.getParameter("username");
        int age = Integer.parseInt(request.getParameter("age"));

        log.info("username = {}, age = {}", username, age);

        response.getWriter().write("ok");
    }

    @ResponseBody // view 리턴이 아니고 기존 String 형태로 반환
    @RequestMapping("/request-param-v2")
    public String requestParamV2(
            @RequestParam("username") String memberName,
            @RequestParam("age") int memberAge) {

        log.info("username={}, age={}", memberName, memberAge);
        return "ok";
    }

    @ResponseBody // view 리턴이 아니고 기존 String 형태로 반환
    @RequestMapping("/request-param-v3")
    public String requestParamV3(
            @RequestParam String username,
            @RequestParam int age) {
        // 필드 이름이랑 파라미터 이름이랑 같으면 가능

        log.info("username={}, age={}", username, age);
        return "ok";
    }

    @ResponseBody // view 리턴이 아니고 기존 String 형태로 반환
    @RequestMapping("/request-param-v4")
    public String requestParamV4(String username, int age) {
        //모두 없앤버전 위와 동일한 원리리
        log.info("username={}, age={}", username, age);
        return "ok";
    }

    @ResponseBody // view 리턴이 아니고 기존 String 형태로 반환
    @RequestMapping("/request-param-required")
    public String requestParamRequired(
            @RequestParam(required = true) String username,
            @RequestParam(required = false) Integer age) {
        // 필수로 받아야 하는값 필수가 아니어도 되는 값 지정

        log.info("username={}, age={}", username, age);
        return "ok";
    }

    @ResponseBody
    @RequestMapping("/request-param-default")
    public String requestParamDefault(
            @RequestParam(required = true, defaultValue = "guest") String username,
            @RequestParam(required = false, defaultValue = "-1") int age) {
        // Default 값 지정

        log.info("username={}, age={}", username, age);
        return "ok";
    }

    @ResponseBody
    @RequestMapping("/request-param-map")
    public String requestParamMap(@RequestParam Map<String, Object> paramMap) {

        log.info("username={}, age={}", paramMap.get("username"), paramMap.get("age"));
        return "ok";
    }

    @ResponseBody
    @RequestMapping("/model-attribute-v1")
    public String modelAttributeV1(@ModelAttribute HelloData helloData) {
//        객체 형식으로 받기
        // 파라미터 이름 username -> HelloData 객체의 필드 중 username을 찾아 주입
        log.info("username={}, age={}", helloData.getUsername(), helloData.getAge());
        log.info("helloData={}",helloData);

        return "Ok";
    }

    @ResponseBody
    @RequestMapping("/model-attribute-v2")
    public String modelAttributeV2(HelloData helloData) {
//        객체 형식으로 받기
        // 파라미터 이름 username -> HelloData 객체의 필드 중 username을 찾아 주입
        // ModelAttribute 생략 가능
        log.info("username={}, age={}", helloData.getUsername(), helloData.getAge());

        return "Ok";
    }
}

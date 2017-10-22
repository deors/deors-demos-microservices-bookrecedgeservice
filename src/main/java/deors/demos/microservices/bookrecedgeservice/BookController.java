package deors.demos.microservices.bookrecedgeservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class BookController {

    @Autowired
    RestTemplate restTemplate;

    @Value("${defaultBook}")
    private String defaultBook;

    @RequestMapping("/bookrecedge")
    @com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand(fallbackMethod = "getDefaultBook")
    public String getBookRecommendation() {
        return restTemplate.getForObject("http://bookrec-service/bookrec", String.class);
    }

    public String getDefaultBook() {
        return defaultBook;
    }
}

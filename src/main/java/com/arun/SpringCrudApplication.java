package com.arun;
import org.slf4j.Logger;
		import org.slf4j.LoggerFactory;
		import org.springframework.boot.SpringApplication;
		import org.springframework.boot.autoconfigure.SpringBootApplication;
		import org.springframework.web.bind.annotation.RequestMapping;
		import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class SpringCrudApplication {

	// @Value("${spring.my.value}")
	// private String name;
	private final Logger LOG = LoggerFactory.getLogger(getClass());

	public static void main(String[] args) {
		SpringApplication.run(SpringCrudApplication.class, args);
	}

	@RequestMapping(value = "/")
	public String hello() {
		return "Hello Rocky";
	}
}



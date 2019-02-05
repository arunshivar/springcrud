package com.arun;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class SpringCrudApplication {

	// @Value("${spring.my.value}")
	// private String name;
	private static Logger logger = LogManager.getLogger();

	public static void main(String[] args) {
		SpringApplication.run(SpringCrudApplication.class, args);
		logger.debug("This is a debug message");
		logger.info("This is an info message");
		logger.warn("This is a warn message");
		logger.error("This is an error message");
		logger.fatal("This is a fatal message");

	}

	@RequestMapping(value = "/")
	public String hello() {
		return "Hello Rocky";
	}
}



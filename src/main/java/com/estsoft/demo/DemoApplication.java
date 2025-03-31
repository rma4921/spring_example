package com.estsoft.demo;

// import org.apache.catalina.core.ApplicationContext;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.ApplicationContext;
import java.util.Arrays;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		// 스프링 컨테이너가 관리하는 빈 조회
		ApplicationContext context = new AnnotationConfigApplicationContext(DemoApplication.class);
		String[] beanList = context.getBeanDefinitionNames();

		System.out.println("===== 스프링 컨테이너가 관리하는 빈 목록 =====");
		Arrays.stream(beanList).forEach(System.out::println);

		SpringApplication.run(DemoApplication.class, args);
	}

}

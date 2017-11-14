package cn.material;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@ServletComponentScan //servlet容器中的类扫描
@SpringBootApplication
public class CommonApplication {

	public static void main(String[] args) {

		SpringApplication.run(CommonApplication.class, args);
	}
}

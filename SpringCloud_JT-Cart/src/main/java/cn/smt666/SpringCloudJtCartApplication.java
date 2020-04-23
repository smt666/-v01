package cn.smt666;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
@MapperScan(basePackages = "cn.smt666.jt.mapper")
public class SpringCloudJtCartApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringCloudJtCartApplication.class, args);
	}

}

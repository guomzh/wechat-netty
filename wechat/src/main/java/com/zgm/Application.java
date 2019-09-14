package com.zgm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import tk.mybatis.spring.annotation.MapperScan;


@SpringBootApplication
//扫描mybatis 的mapper包路径
@MapperScan(basePackages = {"com.zgm.mapper"})
// 扫描mybatis mapper包路径
// 扫描 所有需要的包, 包含一些自用的工具类包 所在的路径
//@EnableAutoConfiguration(exclude={DataSourceAutoConfiguration.class})
@ComponentScan(basePackages = {"com.zgm","org.n3r.idworker"})
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
}

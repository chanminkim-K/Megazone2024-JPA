package com.rubypaper.shopping;

import org.springframework.context.support.GenericXmlApplicationContext;

public class RubbyShoppingClient {

	public static void main(String[] args) {
		// 스프링 컨테이너 생성
		String configLocation = "classpath:spring/business-layer.xml";
		GenericXmlApplicationContext container = new GenericXmlApplicationContext(configLocation);

		// 스프링 컨테이너 종료
		container.close();

	}
}

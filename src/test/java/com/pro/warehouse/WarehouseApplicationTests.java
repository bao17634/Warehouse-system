package com.pro.warehouse;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

public class WarehouseApplicationTests {

	@Test
	public void contextLoads() {
		System.out.println( getClass().toString().substring(getClass().toString().lastIndexOf(".")+1));
	}

}

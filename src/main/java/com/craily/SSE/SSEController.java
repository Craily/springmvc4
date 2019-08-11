package com.craily.SSE;

import java.util.Random;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class SSEController {

	@RequestMapping(value = "/push", produces = "text/event-stream;charset=UTF-8")
	public @ResponseBody String push() {
		Random random = new Random();
		try {
			Thread.sleep(5000);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "data:Testing 1,2,3" + random.nextInt() + "\n\n";
	}
}

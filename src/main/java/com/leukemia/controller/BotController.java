package com.leukemia.controller;

import java.io.File;

import org.alicebot.ab.Bot;
import org.alicebot.ab.Chat;
import org.alicebot.ab.MagicBooleans;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.leukemia.model.ChatResponse;

/**
 * https://howtodoinjava.com/ai/java-aiml-chatbot-example/#custom-rules
 *
 */
@RestController
public class BotController {

	private static final boolean TRACE_MODE = false;

	@RequestMapping(value = "/bot", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ChatResponse> getBotResponse(@RequestParam(name = "query") final String request) {

		String resourcesPath = getResourcesPath();
		System.out.println(resourcesPath);
		MagicBooleans.trace_mode = TRACE_MODE;
		Bot bot = new Bot("conQuerBot", resourcesPath);
		Chat chatSession = new Chat(bot);
		bot.brain.nodeStats();
		String response = chatSession.multisentenceRespond(request) == "" ? "sorry i dont know that one"
				: chatSession.multisentenceRespond(request);

		while (response.contains("&lt;"))
			response = response.replace("&lt;", "<");
		while (response.contains("&gt;"))
			response = response.replace("&gt;", ">");

		System.out.println("Robot : " + response);

		return new ResponseEntity<ChatResponse>(new ChatResponse(response), HttpStatus.OK);
	}

	private static String getResourcesPath() {
		File currDir = new File(".");
		String path = currDir.getAbsolutePath();
		path = path.substring(0, path.length() - 2);
		System.out.println(path);
		String resourcesPath = path + File.separator + "src" + File.separator + "main" + File.separator + "resources";
		return resourcesPath;
	}
}
package com.tgb.SpringActivemq.controller;

import javax.annotation.Resource;

import com.google.gson.Gson;
import com.tgb.SpringActivemq.redis.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tgb.SpringActivemq.mq.producer.queue.QueueSender;
import com.tgb.SpringActivemq.mq.producer.topic.TopicSender;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * 
 * @author liang
 * @description controller测试
 */
@Controller
@RequestMapping("/activemq")
public class ActivemqController {
	
	@Resource 
	QueueSender queueSender;
	@Resource 
	TopicSender topicSender;
	@Autowired
	private BaseService baseService;

	Gson gson = new Gson();
	/**
	 * 发送消息到队列
	 * Queue队列：仅有一个订阅者会收到消息，消息一旦被处理就不会存在队列中
	 * @param message
	 * @return String
	 */
	@ResponseBody
	@RequestMapping("queueSender")
	public String queueSender(@RequestParam("message")String message){

		Map map = new HashMap();
		String opt="suc";
		try {
			String uuid = message;

			String msg = (String) baseService.get("queue",uuid);
			if(StringUtils.isEmpty(msg)){
				queueSender.send("queue", message, uuid);
				map.put("flog","0");
			}else{
				map.put("flog","1");
				map.put("message",msg);
			}

			map.put("opt",opt);
			map.put("uuid",uuid);
			map.put("typeName","queue");
			return gson.toJson(map);
		} catch (Exception e) {
			opt = e.getCause().toString();
			map.put("opt",opt);
			map.put("flag","1");
			return gson.toJson(map);
		}
	}
	
	/**
	 * 发送消息到主题
	 * Topic主题 ：放入一个消息，所有订阅者都会收到 
	 * 这个是主题目的地是一对多的
	 * @param message
	 * @return String
	 */
	@ResponseBody
	@RequestMapping("topicSender")
	public String topicSender(@RequestParam("message")String message){
		String opt = "";
		try {
			topicSender.send("test.topic", message,"123");
			opt = "suc";
		} catch (Exception e) {
			opt = e.getCause().toString();
		}
		return opt;
	}


	@ResponseBody
	@RequestMapping("getData")
	public String getData(String uuid,String typeName){

		Map map = new HashMap();

		String msg = (String) baseService.get(typeName,uuid);
		if(StringUtils.isEmpty(msg)){
			map.put("flog","0");
		}else{
			map.put("flog","1");
			map.put("message",msg);
		}
		return gson.toJson(map);
	}
	
}

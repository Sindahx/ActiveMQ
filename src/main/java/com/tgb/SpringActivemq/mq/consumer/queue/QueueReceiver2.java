
package com.tgb.SpringActivemq.mq.consumer.queue;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

import com.google.gson.Gson;
import com.tgb.SpringActivemq.dao.ApplyDao;
import com.tgb.SpringActivemq.redis.service.BaseService;
import javafx.scene.chart.PieChart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Map;

/**
 * 
 * @author liang
 * @description  队列消息监听器
 * 
 */
@Component
public class QueueReceiver2 implements MessageListener {

	@Autowired
	private BaseService baseService;

	@Autowired
	private ApplyDao applyDao;

	public void onMessage(Message message) {
		try {
			String msg = "receiver2:"+((TextMessage)message).getText();
			String phyName = (message.getJMSDestination().toString()).split(":")[0];
			long start = new Date().getTime();
			Map map =  applyDao.getSimpleInfo(((TextMessage)message).getText());
			long end = new Date().getTime();
			System.out.println(end-start);
			Gson gson = new Gson();

			baseService.put(phyName,message.getJMSCorrelationID(),gson.toJson(map),180);
		} catch (JMSException e) {
			e.printStackTrace();
		}
	}

}

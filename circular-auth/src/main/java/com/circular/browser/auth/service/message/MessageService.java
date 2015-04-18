package com.circular.browser.auth.service.message;

import com.circular.brower.common.util.ThreadPoolManager;
import com.circular.browser.auth.util.Constants;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Created by senlin.xsl on 2015/4/17.
 */
@Service("messageService")
public class MessageService {
    private static Logger logger = LogManager.getLogger(MessageService.class);

    @Autowired
    private MessageSender messageSender;
    private BlockingQueue<MessageItem> sendQueue;

    public MessageService() {
        this.sendQueue = new LinkedBlockingQueue<MessageItem>();
        ThreadPoolManager.getInstance().getCachedThreadPool().execute(new MessageSendRunner());
    }

    public synchronized void sendMessage(List<MessageItem> items) {
        this.sendQueue.addAll(items);
    }

    public synchronized void sendMessage(MessageItem item) {
        this.sendQueue.add(item);
    }

    private class MessageSendRunner implements Runnable {
        @Override
        public void run() {
            MessageItem messageItem = null;
            while (true) {
                try {
                    messageItem = sendQueue.take();
                    if (messageItem.getRetryTimes() > Constants.FAILED_RETRY_TIMES) {
                        logger.error(messageItem.getErrorMsg());
                        continue;
                    }

                    String result = messageSender.sendMessage(messageItem);
                    if (result != null && result.length() > 0) {
                        messageItem.setRetryTimes(messageItem.getRetryTimes() + 1);
                        messageItem.setErrorMsg(result);
                    }
                } catch (Exception e) {
                    messageItem.setRetryTimes(messageItem.getRetryTimes() + 1);
                    messageItem.setErrorMsg("send phone message error, details: " + e.getMessage());
                }
            }
        }

    }
}

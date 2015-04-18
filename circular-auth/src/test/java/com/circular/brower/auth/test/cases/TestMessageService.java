package com.circular.brower.auth.test.cases;

import com.circular.brower.auth.test.base.TestBase;
import com.circular.browser.auth.service.message.MessageItem;
import com.circular.browser.auth.service.message.MessageService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by senlin.xsl on 2015/4/17.
 */
public class TestMessageService extends TestBase {
    @Autowired
    private MessageService messageService;

    @Test
    public void testSend() {
        MessageItem messageItem = new MessageItem();
        messageItem.setRetryTimes(0);
        messageItem.setPhoneNumber("18515687350");
        messageItem.setContent("test");
        this.messageService.sendMessage(messageItem);

        while (true) {
            try {
                Thread.sleep(100000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

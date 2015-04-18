package com.circular.browser.auth.service.message;

import com.bcloud.msg.http.HttpSender;
import com.circular.browser.auth.util.Constants;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.HashMap;

/**
 * Created by senlin.xsl on 2015/4/17.
 */
@Service("messageSender")
public class MessageSender {
    private static Logger logger = LogManager.getLogger(MessageSender.class);


    @Value("#{fieldPlaceHolder['phonemsg.uri']}")
    private String url;
    @Value("#{fieldPlaceHolder['phonemsg.account']}")
    private String account;
    @Value("#{fieldPlaceHolder['phonemsg.pwd']}")
    private String pwd;
    @Value("#{fieldPlaceHolder['phonemsg.needStatus']}")
    private boolean needStatus;

    @Autowired
    private HashMap<String, String> phoneMsgErrerCode;

    public String sendMessage(MessageItem messageItem) {

        String returnString;
        try {
            returnString = HttpSender.send(this.url, this.account, this.pwd, messageItem.getPhoneNumber(),
                    messageItem.getContent(), this.needStatus, null, null);

            String [] resItems = returnString.split(",");
            String code = resItems[1];
            if (this.phoneMsgErrerCode.containsKey(code)) {
                return this.phoneMsgErrerCode.get(code);
            }
            else {
                return this.phoneMsgErrerCode.get(Constants.DEFAULT_MAP_KEY);
            }

        } catch (Exception e) {
            String msg = "send msg error, content: [" + messageItem.toString() + "]" + ", details: " + e.getMessage();
            logger.error(msg, e);
            return msg;
        }
    }

}

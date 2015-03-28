package com.circular.browser.auth.thrift.impl;

import com.circular.browser.auth.thrift.HelloWorldService;
import org.apache.thrift.TException;

/**
 * Created by senlin.xsl on 2015/3/28.
 */
public class HelloWorldImpl implements HelloWorldService.Iface {
    @Override
    public String sayHello(String username) throws TException {
        return "Hi, " + username + " welcome to my blog www.micmiu.com";
    }
}

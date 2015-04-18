package com.circular.browser.common.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * Created by senlin.xsl on 2015/4/16.
 */
public class SerializeUtil {
    /**
     * 序列化java对象
     * @param object
     * @return
     * @throws Exception
     */
    public static byte[] serialize(Object object) throws Exception {
        if (object == null) {
            return null;
        }
        else {
            ByteArrayOutputStream bas = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(bas);
            oos.writeObject(object);
            return bas.toByteArray();
        }
    }

    /**
     * 反序列化java对象
     * @param bytes
     * @return
     * @throws Exception
     */
    public static Object unserialize(byte[] bytes) throws Exception {
        if (bytes == null) {
            return null;
        }
        else {
            ByteArrayInputStream bis = new ByteArrayInputStream(bytes);
            ObjectInputStream ois = new ObjectInputStream(bis);
            return ois.readObject();
        }
    }
}

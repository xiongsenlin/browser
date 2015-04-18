package com.circular.brower.common.util;

import com.google.gson.Gson;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by senlin.xsl on 2015/3/12.
 */
public class StringUtils {
    /**
     * 占位符替换
     * @param orgStr
     * @param args
     * @return
     */
    public static String stringFormat(String orgStr, Object... args) {
        return MessageFormat.format(orgStr, args);
    }

    /**
     * 将列表拼成字符串
     * @param collections
     * @param separator
     * @return
     */
    public static String collectionJoin(Collection<?> collections, String separator) {
        if (collections == null || collections.size() == 0) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        for (Object item : collections) {
            if (sb.toString().length() > 0) {
                sb.append(separator);
            }
            sb.append(item.toString());
        }

        return sb.toString();
    }

    /**
     * 正则表达式匹配，返回特定的分组
     * @param content         待匹配的内容
     * @param patternStr      匹配模板
     * @param group           返回匹配中的特定分组，0表示所有匹配的内容
     * @return
     */
    public static List<String> getMatchGroup(String content, String patternStr, int group) {
        List<String> result = new ArrayList<String>();

        Pattern pattern = Pattern.compile(patternStr);
        Matcher matcher = pattern.matcher(content);
        while (matcher.find()) {
            String tmp = matcher.group(group);
            if (tmp != null && !tmp.isEmpty()) {
                result.add(tmp);
            }
        }
        return result;
    }

    /**
     * 判断在content中是否能找到与patternStr匹配的内容
     * @param content
     * @param patternStr
     * @return
     */
    public static boolean isMatch(String content, String patternStr) {
        Pattern pattern = Pattern.compile(patternStr);
        Matcher matcher = pattern.matcher(content);
        return matcher.find();
    }

    /**
     * 将map转换成字符串，元素之间用separator分离开
     * @param map
     * @param separator
     * @return
     */
    public static String mapToString(Map<String, ?> map, String separator) {
        if (map == null) {
            return "";
        }
        else {
            StringBuilder sb = new StringBuilder();
            for (Map.Entry<String, ?> entry : map.entrySet()) {
                if (sb.length() > 0) {
                    sb.append(separator);
                }
                sb.append("[ " + entry.getKey() + " : " + entry.getValue() + " ]");
            }
            return sb.toString();
        }
    }

    /**
     * 将对象转换为json格式
     * @param obj
     * @return
     */
    public static String toJson(Object obj) {
        Gson gson = new Gson();
        return gson.toJson(obj);
    }
}

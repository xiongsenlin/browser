package com.circular.browser.common.util;

import sun.misc.URLClassPath;

import java.io.*;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Stack;

/**
 * Created by senlin.xsl on 2015/3/19.
 */
public class FileUtils {
    /**
     * 按行读取一个文件的所有类容，文件中的每一行对应返回结果list中的一条数据
     * @param fileName
     * @return
     */
    public static List<String> getFileContent(String fileName) {
        File file = new File(fileName);
        if (!file.exists()) {
            return new ArrayList<String>();
        }

        BufferedReader br = null;
        try {
            br = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
            List<String> result = new ArrayList<String>();
            while (true) {
                String line = br.readLine();
                if (line == null) {
                    break;
                }
                line = line.trim();
                if (!line.isEmpty()) {
                    result.add(line);
                }
            }
            return result;
        } catch (Exception e) {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
            return new ArrayList<String>();
        }
    }

    /**
     * 返回一个路径下所有能正则表达式pattern匹配的文件名，由于所有文件都在同一个
     * 目录下，不含嵌套结构，所以只返回文件名，不返回全路径名
     * @param path
     * @param pattern
     * @return
     */
    public static List<String> getFileList(String path, String pattern) {
        File file = new File(path);
        if (!file.exists() || !file.isDirectory()) {
            return new ArrayList<String>();
        }
        else {
            List<String> result = new ArrayList<String>();

            String [] files = file.list();
            for (String item : files) {
                if (!isDirectory(file.getAbsolutePath() + File.separator + item)) {
                    if (pattern == null || StringUtils.getMatchGroup(item, pattern, 0).size() > 0) {
                        result.add(item);
                    }
                }
            }
            return result;
        }
    }


    /**
     * 返回一个路径下所有能正则表达式pattern匹配的文件名，会递归查找各子目录
     * 由于会存在子目录的问题，所以为了能定位文件的位置，返回的是文件的全路径名
     * @param path
     * @param pattern
     * @return
     */
    public static List<String> getFileListRecursive(String path, String pattern) {
        File file = new File(path);
        if (!file.exists() || !file.isDirectory()) {
            return new ArrayList<String>();
        }
        else {
            List<String> result = new ArrayList<String>();

            String [] files = file.list();
            for (String item : files) {
                String tmpPath = file.getAbsolutePath() + File.separator + item;
                if (!isDirectory(tmpPath)) {
                    if (pattern == null || StringUtils.isMatch(item, pattern)) {
                        result.add(tmpPath);
                    }
                }
                else {
                    result.addAll(getFileListRecursive(tmpPath, pattern));
                }
            }
            return result;
        }
    }

    /**
     * 获取一个文件夹下所有匹配正则表达式pattern的目录名
     * @param path
     * @param pattern
     * @return
     */
    public static List<String> getDirectoryList(String path, String pattern) {
        File file = new File(path);
        if (!file.exists() || !file.isDirectory()) {
            return new ArrayList<String>();
        }
        else {
            List<String> result = new ArrayList<String>();
            String [] files = file.list();
            for (String item : files) {
                if (isDirectory(file.getAbsolutePath() + File.separator + item)) {
                    if (pattern == null || StringUtils.isMatch(item, pattern)) {
                        result.add(item);
                    }
                }
            }
            return result;
        }
    }

    /**
     * 判断一个路径是否是目录
     * @param fileName
     * @return
     */
    public static boolean isDirectory(String fileName) {
        File file = new File(fileName);
        if (!file.exists()) {
            return false;
        }
        else {
            return file.isDirectory();
        }
    }

    /**
     * 动态添加一个目录到classpath
     * @param path
     * @throws Exception
     */
    public static void addClassPath(String path) throws Exception {
        File programRootDir = new File(path);
        URLClassLoader classLoader = (URLClassLoader) ClassLoader.getSystemClassLoader();
        Method add = URLClassLoader.class.getDeclaredMethod("addURL", new Class[]{URL.class});
        add.setAccessible(true);
        add.invoke(classLoader, programRootDir.toURI().toURL());
    }

    /**
     * 动态添加多个目录到classpath
     * @param pathes
     * @throws Exception
     */
    public static void addClassPathes(List<String> pathes) throws Exception {
        for (String path : pathes) {
            File programRootDir = new File(path);
            URLClassLoader classLoader = (URLClassLoader) ClassLoader.getSystemClassLoader();
            Method add = URLClassLoader.class.getDeclaredMethod("addURL", new Class[]{URL.class});
            add.setAccessible(true);
            add.invoke(classLoader, programRootDir.toURI().toURL());
        }
    }

    /**
     * 返回一个目录下或者某个具体的属性文件所包含的所有属性信息
     * @param filePath
     * @return
     * @throws Exception
     */
    public static Properties getProperties(String filePath) throws Exception {
        List<String> fileList;
        if (isDirectory(filePath)) {
            fileList = getFileListRecursive(filePath, null);
        }
        else {
            fileList = new ArrayList<String>();
            fileList.add(filePath);
        }
        Properties properties = new Properties();
        for (String file : fileList) {
            FileInputStream configInputStream = new FileInputStream(file);
            properties.load(configInputStream);
        }
        return properties;
    }

    /**
     * 移除最近添加到classpath中的URL
     * @param pattern
     */
    public static void removeClasspath(String pattern) throws Exception {
        if (pattern == null) {
            return;
        }

        URLClassLoader classLoader = (URLClassLoader) ClassLoader.getSystemClassLoader();
        Field ucpField = URLClassLoader.class.getDeclaredField("ucp");
        ucpField.setAccessible(true);
        URLClassPath urlClassPath = (URLClassPath) ucpField.get(classLoader);

        Field pathField = URLClassPath.class.getDeclaredField("path");
        Field urlField = URLClassPath.class.getDeclaredField("urls");

        pathField.setAccessible(true);
        urlField.setAccessible(true);

        ArrayList path = (ArrayList) pathField.get(urlClassPath);
        Stack urls = (Stack) urlField.get(urlClassPath);

        popArrayList(path, pattern);
        popStack(urls, pattern);
    }

    /**
     * 从classpath中移除多个路径，移除的顺序应该与添加的顺序相反
     * @param pathes
     * @throws Exception
     */
    public static void removeClassPathes(List<String> pathes) throws Exception {
        for (String path : pathes) {
            removeClasspath(path);
        }
    }

    /**
     * 如果list中最后一个或多个元素元素匹配pattern，则
     * 从classpath中移除
     * @param path
     * @param pattern
     * @throws Exception
     */
    private static void popArrayList(ArrayList path, String pattern) throws Exception {
        pattern = pattern.replace("/", ".");
        pattern = pattern.replace("\\", ".");

        for (int i = path.size() - 1; i >= 0; i--) {
            URL tmpUrl = (URL) path.get(i);

            String tmpUrlPath = tmpUrl.toURI().toString();
            tmpUrlPath = tmpUrlPath.replace("/", ".");
            tmpUrlPath = tmpUrlPath.replace("\\", ".");
            if (StringUtils.isMatch(tmpUrlPath, pattern)) {
                path.remove(i);
            }
            else {
                return;
            }
        }
    }

    /**
     * 如果stack中最后一个或多个元素元素匹配pattern，则
     * 从classpath中移除
     * @param stack
     * @param pattern
     * @throws Exception
     */
    private static void popStack(Stack stack, String pattern) throws Exception {
        pattern = pattern.replace("/", ".");
        pattern = pattern.replace("\\", ".");
        while (!stack.isEmpty()) {
            URL tmpUrl = (URL) stack.peek();

            String tmpUrlPath = tmpUrl.toURI().toString();
            tmpUrlPath = tmpUrlPath.replace("/", ".");
            tmpUrlPath = tmpUrlPath.replace("\\", ".");
            if (StringUtils.isMatch(tmpUrlPath, pattern)) {
                stack.pop();
            }
            else {
                break;
            }
        }
    }
}

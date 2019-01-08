package com.delicacy.oatmeal.common.util.io;

import org.apache.commons.lang3.StringUtils;

import java.io.File;

/**
 * 关于文件路径的工具集
 *
 * @author calvin
 */
public class FilePathUtil {

    // 文件路径分隔符
    public static final String FILE_PATH_SEPARATOR = File.separator;
    public static final char FILE_PATH_SEPARATOR_CHAR = File.separatorChar;
    public static final char WINDOWS_FILE_PATH_SEPARATOR_CHAR = '\\';
    public static final char LINUX_FILE_PATH_SEPARATOR_CHAR = '/';

    /**
     * 在Windows环境里，兼容Windows上的路径分割符，将 '/' 转回 '\'
     */
    public static String normalizePath(String path) {
        if (FILE_PATH_SEPARATOR_CHAR == WINDOWS_FILE_PATH_SEPARATOR_CHAR
                && StringUtils.indexOf(path, LINUX_FILE_PATH_SEPARATOR_CHAR) != -1) {
            return StringUtils.replaceChars(path, LINUX_FILE_PATH_SEPARATOR_CHAR, WINDOWS_FILE_PATH_SEPARATOR_CHAR);
        }
        return path;

    }

    /**
     * 以拼接路径名
     */
    public static String contact(String baseName, String... appendName) {
        if (appendName.length == 0) {
            return baseName;
        }

        String contactName;
        if (endWith(baseName, FILE_PATH_SEPARATOR_CHAR)) {
            contactName = baseName + appendName[0];
        } else {
            contactName = baseName + FILE_PATH_SEPARATOR_CHAR + appendName[0];
        }

        if (appendName.length > 1) {
            for (int i = 1; i < appendName.length; i++) {
                contactName += FILE_PATH_SEPARATOR_CHAR + appendName[i];
            }
        }

        return contactName;
    }

     static boolean endWith( CharSequence s, char c) {
        if (StringUtils.isEmpty(s)) {
            return false;
        }
        return s.charAt(s.length() - 1) == c;
    }

    /**
     * 获得上层目录的路径
     */
    public static String getParentPath(String path) {
        String parentPath = path;

        if (FILE_PATH_SEPARATOR.equals(parentPath)) {
            return parentPath;
        }

        parentPath = removeEnd(parentPath, FILE_PATH_SEPARATOR_CHAR);

        int idx = parentPath.lastIndexOf(FILE_PATH_SEPARATOR_CHAR);
        if (idx >= 0) {
            parentPath = parentPath.substring(0, idx + 1);
        } else {
            parentPath = FILE_PATH_SEPARATOR;
        }

        return parentPath;
    }

     static String removeEnd(final String s, final char c) {
        if (endWith(s, c)) {
            return s.substring(0, s.length() - 1);
        }
        return s;
    }

    /**
     * 获得参数clazz所在的Jar文件的绝对路径
     */
    public static String getJarPath(Class<?> clazz) {
        return clazz.getProtectionDomain().getCodeSource().getLocation().getFile();
    }

    public static void main(String[] args) {
        System.out.println(getJarPath(FilePathUtil.class));
        System.out.println(getParentPath("."));
        System.out.println(contact("a", "b", "c.txt"));
        System.out.println(normalizePath("f://software"));

    }
}

package cn.smartrick.metaverse.utils;

import org.apache.commons.io.FileUtils;

import java.io.*;
import java.nio.charset.Charset;

/**
 * @Date: 2021年11月24日09:52:43
 * @Author: SmartRick
 * @Description: 文件处理工具类
 */
public class SmartFileUtil extends FileUtils {
    /**
     * 判断是否为xml文件
     *
     * @param file
     * @return boolean
     */
    public static boolean isXmlFile(File file) {
        return "xml".equalsIgnoreCase(getFileExtension(file.getName()));
    }

    /**
     * 文件后缀名
     *
     * @param fullName
     * @return
     */
    public static String getFileExtension(String fullName) {
        String fileName = new File(fullName).getName();
        int dotIndex = fileName.lastIndexOf('.');
        return (dotIndex == -1) ? "" : fileName.substring(dotIndex + 1);
    }

    /**
     * 不带后缀名的文件名
     *
     * @param file
     * @return
     */
    public static String getNameWithoutExtension(String file) {
        String fileName = new File(file).getName();
        int dotIndex = fileName.lastIndexOf('.');
        return (dotIndex == -1) ? fileName : fileName.substring(0, dotIndex);
    }

    /**
     * 验证文件是否存在
     *
     * @param filePath
     * @return boolean
     */
    public static boolean isFileExist(String filePath) {
        File file = new File(filePath);
        return file.exists();
    }

    /**
     * 验证文件是否存在，如果不存在则抛出异常
     *
     * @param filePath
     * @throws IOException
     */
    public static void isFileExistThrowException(String filePath) throws IOException {
        if (!isFileExist(filePath)) {
            throw new FileNotFoundException(filePath);
        }
    }

    /**
     * 通过file创建缓冲字符流，用于读取指定编码字符文本文件
     *
     * @param file 文件
     * @param charset 字符集
     * @return BufferReader 字符读取缓冲流
     * @throws FileNotFoundException
     */
    public static BufferedReader newBufferedReader(File file, Charset charset) throws FileNotFoundException {
        return new BufferedReader(new InputStreamReader(new FileInputStream(file), charset));
    }

    /**
     * 通过file创建缓冲字符流，用于按照指定编码方式输出文本至文件
     *
     * @param file 文件
     * @param charset 字符集
     * @return BufferedWriter 字符输出缓冲流
     * @throws FileNotFoundException
     */
    public static BufferedWriter newBufferedWriter(File file, Charset charset) throws FileNotFoundException {
        return new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file), charset));
    }

    /**
     * 创建文件父级文件夹
     *
     * @param file 文件
     * @return boolean 是否创建成功
     * @throws IOException
     */
    public static boolean createParentDirs(File file) throws IOException {
        File parent = file.getCanonicalFile().getParentFile();
        if (parent == null) {
            return false;
        }
        return parent.mkdirs();
    }

    /**
     * 先创建文件父级文件夹，再创建文件
     *
     * @param file 文件
     * @return boolean 是否创建成功
     * @throws IOException
     */
    public static boolean createNotExistParentDirFile(File file) throws IOException {
        boolean createParentDirsRes = createParentDirs(file);
        if (!createParentDirsRes) {
            throw new IOException("cannot create parent Directory of " + file.getName());
        }
        return file.createNewFile();
    }

}

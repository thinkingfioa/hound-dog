package org.vlis.dog.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;

/**
 * @author thinking_fioa
 * @createTime 2017/11/17
 * @description
 */


public final class FileReadWriteUtil {

    private static final Logger LOGGER = LoggerFactory.getLogger(FileReadWriteUtil.class.getName());

    private FileReadWriteUtil() {}

    /**
     * 获得文件存储实例
     * @return .
     */
    public static FileReadWriteUtil getInstance() {
        return FileReadWriteUtilHolder.INSTANCE;
    }

    /**
     * 通过BufferedWriter向文件中写数据
     * @param dataContent 待写入的文件内容
     * @param filePath 写入文件的路径
     * @return true is success.
     */
    public boolean writeByBufferedWriter(String dataContent, String filePath) {
        if(null == dataContent || null == filePath) {
            throw new NullPointerException("parameters is null.");
        }

        try {
            File file = new File(filePath);
            if(!file.exists()) {
                file.createNewFile();
            }
            FileWriter fw = new FileWriter(file, false);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(dataContent);
            bw.flush();
            bw.close();
            return true;
        } catch (IOException ex) {
            LOGGER.error("write [filePath]{}, [dataLength]{}", filePath, dataContent.length(), ex);
            return false;
        }

    }

    /**
     * 通过BufferedReader从文件中读取文件
     * @param filePath 读取文件的路径
     * @return 文件内容
     */
    public String readByBuffeeredReader(String filePath) {
        try {
            File file = new File(filePath);
            // 读取文件，并且以UTF-8的形式写
            BufferedReader bufferedReader =  new BufferedReader(new FileReader(file));
            StringBuilder readFileContent = new StringBuilder();
            String readContentLine;
            while((readContentLine = bufferedReader.readLine()) != null) {
                readFileContent.append(readContentLine);
            }
            bufferedReader.close();

            return readFileContent.toString();
        } catch (FileNotFoundException ex) {
            LOGGER.error("read [filePath]{}", filePath, ex);
        } catch (IOException ex) {
            LOGGER.error("read [filePath]{}", filePath, ex);
        }
        return null;
    }

    private static class FileReadWriteUtilHolder {
        private static final FileReadWriteUtil INSTANCE = new FileReadWriteUtil();
    }

}

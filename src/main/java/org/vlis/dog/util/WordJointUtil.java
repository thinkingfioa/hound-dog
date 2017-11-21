package org.vlis.dog.util;

import org.vlis.dog.config.Config;
import org.vlis.dog.constant.WarningEnum;

import java.util.List;

/**
 * @author thinking_fioa
 * @createTime 2017/11/21
 * @description 多个单词拼接成一个字符串
 */


public final class WordJointUtil {

    /**
     * WarningEnum和一个WarningBean某个属性拼接
     * @param warningEnum {@see org.vlis.dog.constant.WarningEnum}
     * @param word String字符串
     * @return 拼接后字符串
     */
    public static String warningEnumJointWord(WarningEnum warningEnum, String word) {
        if( null ==  warningEnum || null == word) {
            throw new NullPointerException("parameters is null.");
        }

        return warningEnum.getAlarmType() + Config.getWordJointSign() + word;
    }

    /**
     * 拼接给定的List字符串，分隔符是：{@see org.vlis.dog.config.WORD_JOINT_SIGN}
     * @param wordList 需要拼接的单词
     * @return 拼接后的单词，
     *         格式: word1+{@code WORD_JOIN_SIGN}+word2+{@code WORD_JOIN_SIGN}+...+wordn+{@code WORD_JOIN_SIGN}
     */
    public static String joinWordsOfList(List<String> wordList) {
        if( null == wordList || wordList.isEmpty()) {
            return "";
        }

        StringBuilder stringBuilder = new StringBuilder();
        for(String oneWord : wordList) {
            stringBuilder.append(oneWord).append(Config.getWordJointSign());
        }

        return stringBuilder.toString();
    }

    /**
     * 可变参数方法。
     * @param wordsArray 传入的可变参数
     * @return 拼接后的单词
     *         格式: word1+{@code WORD_JOIN_SIGN}+word2+{@code WORD_JOIN_SIGN}+...+wordn+{@code WORD_JOIN_SIGN}
     */
    public static String joinWords(String ... wordsArray) {
        StringBuilder stringBuilder = new StringBuilder();
        for(String oneWord: wordsArray) {
            stringBuilder.append(oneWord).append(Config.getWordJointSign());
        }

        return stringBuilder.toString();
    }
}

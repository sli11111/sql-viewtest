package com.ysmjjsy.goya.util;

import java.time.LocalDate;

/**
 * 范围工具类
 *
 * @author xudongmaster
 */
public class ScopeUtil {

    /**
     * 两个范围是否有交集
     *
     * @param beginNum1 范围1开始值
     * @param endNum1   范围1结束值
     * @param beginNum2 范围2开始值
     * @param endNum2   范围2结束值
     * @return boolean
     */
    public static boolean isIntersection(int beginNum1, int endNum1, int beginNum2, int endNum2) {
        return isBetween(beginNum1, beginNum2, endNum2) || isBetween(endNum1, beginNum2, endNum2)
                || isBetween(beginNum2, beginNum1, endNum1) || isBetween(endNum2, beginNum1, endNum1);
    }

    /**
     * 两个范围是否有交集
     *
     * @param beginDate1 范围1开始时间
     * @param endDate1 范围1结束时间
     * @param beginDate2 范围2开始时间
     * @param endDate2 范围2结束时间
     * @return boolean
     */
    public static boolean isIntersection(LocalDate beginDate1, LocalDate endDate1, LocalDate beginDate2, LocalDate endDate2) {
        return isBetween(beginDate1, beginDate2, endDate2) || isBetween(endDate1, beginDate2, endDate2)
                || isBetween(beginDate2, beginDate1, endDate1) || isBetween(endDate2, beginDate1, endDate1);
    }

    /**
     * 是否介于范围内
     *
     * @param num 值
     * @param beginNum 范围开始值
     * @param endNum 范围结束值
     * @return boolean
     */
    public static boolean isBetween(int num, int beginNum, int endNum) {
        return num >= beginNum && num <= endNum;
    }

    /**
     * 是否介于范围内
     *
     * @param date 日期
     * @param beginDate 范围开始日期
     * @param endDate 范围结束日期
     * @return boolean
     */
    public static boolean isBetween(LocalDate date, LocalDate beginDate, LocalDate endDate) {
        return date.compareTo(beginDate) >= 0 && date.compareTo(endDate) <= 0;
    }
}

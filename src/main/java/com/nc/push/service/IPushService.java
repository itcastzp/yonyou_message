package com.nc.push.service;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/7/28.
 */
public abstract interface IPushService {
    public abstract Map<String, Object> pushMessage(String paramString, Map<String, String> paramMap, List<String> paramList);

    public abstract Map<String, Object> broadcast(String paramString, Map<String, String> paramMap);

    public abstract Map<String, Object> pushMessageToPhoneNums(String paramString, Map<String, String> paramMap, List<String> paramList);

    public abstract Map<String, Object> pushMessageToUserIds(String paramString, Map<String, String> paramMap, List<String> paramList);

    public abstract String getHistoryMessage(String paramString1, String paramString2, long paramLong1, long paramLong2, int paramInt1, int paramInt2);
}

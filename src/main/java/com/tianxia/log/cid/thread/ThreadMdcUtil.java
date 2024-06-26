package com.tianxia.log.cid.thread;

import com.tianxia.log.cid.utils.MDCIdUtil;
import org.slf4j.MDC;

import java.util.Map;
import java.util.concurrent.Callable;

/**
 * @ClassName: ThreadMdcUtil
 * @Description:
 * @Author: dream
 * @Date: 2024/6/26 16:21
 */
public class ThreadMdcUtil {

    public static void setTraceIdIfAbsent() {
        if (MDC.get(MDCIdUtil.CID) == null) {
            //插入唯一日志ID
            MDC.put(MDCIdUtil.CID, MDCIdUtil.get());
        }
    }

    public static <T> Callable<T> wrap(final Callable<T> callable, final Map<String, String> context) {
        return () -> {
            if (context == null) {
                MDC.clear();
            } else {
                MDC.setContextMap(context);
            }
            setTraceIdIfAbsent();
            try {
                return callable.call();
            } finally {
                MDC.clear();
            }
        };
    }

    public static Runnable wrap(final Runnable runnable, final Map<String, String> context) {
        return () -> {
            if (context == null) {
                MDC.clear();
            } else {
                MDC.setContextMap(context);
            }
            setTraceIdIfAbsent();
            try {
                runnable.run();
            } finally {
                MDC.clear();
            }
        };
    }

}


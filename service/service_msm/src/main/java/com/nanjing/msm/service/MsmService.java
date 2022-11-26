package com.nanjing.msm.service;

import java.util.Map;

/**
 * @author yimo
 * @version 1.0
 * @date 2022/3/27 17:20
 */
public interface MsmService {
    boolean send(Map<String, Object> param, String phone);
}

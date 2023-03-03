package com.blogs.service;

import com.blogs.model.expressTools.StopTyTime;

import java.util.Map;

public interface TyGameService {

    /**
     * 通过ID暂停加速
     */
    public Map<String, Object> stopTyTime(StopTyTime stopTyTime);


}

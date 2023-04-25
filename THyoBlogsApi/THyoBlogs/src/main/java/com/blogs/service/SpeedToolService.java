package com.blogs.service;

import com.blogs.model.speed.SpeedInfo;
import okhttp3.Response;

public interface SpeedToolService {
 public Response getUserBoxItemInfoV2() throws Exception;
 public Response getUserBagInfo() throws Exception;

 public Response openBoxByKey(Integer keyId1,Integer keyNum1,Integer boxId) throws Exception;

 public Response openBox(Integer boxId) throws Exception;

 public  void  savaInfo(SpeedInfo info);

 public SpeedInfo SpeedInfoByUserId(Integer userId);

 public SpeedInfo SpeedInfoById(Integer id);

 public SpeedInfo LoginChek(String account,String password);
}

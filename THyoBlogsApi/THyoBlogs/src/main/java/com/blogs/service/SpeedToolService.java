package com.blogs.service;

import okhttp3.Response;

import java.io.IOException;

public interface SpeedToolService {
 public Response getUserBoxItemInfoV2() throws IOException;
 public Response getUserBagInfo() throws IOException;

 public Response openBoxByKey(Integer keyId1,Integer keyNum1,Integer boxId) throws  IOException;

 public Response openBox() throws IOException;
}

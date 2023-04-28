package com.blogs.service.imlp;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.blogs.mapper.speed.SpeedInfoMapper;
import com.blogs.model.speed.SpeedInfo;
import com.blogs.service.SpeedToolService;
import com.blogs.util.CurrentUserUtil;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;


@Service
public class SpeedToolServiceImpl implements SpeedToolService {

    @Autowired
    private OkHttpClient okHttpClient;

    @Autowired
    private SpeedInfoMapper speedInfoMapper;

    @Override
    public Response getUserBoxItemInfoV2() throws Exception {
        Integer userId = CurrentUserUtil.getUserId();
        SpeedInfo speedInfo = this.SpeedInfoByUserId(userId);
        MediaType mediaType = MediaType.parse("application/x-www-form-urlencoded");
        okhttp3.RequestBody body = okhttp3.RequestBody.create(mediaType, "game=speed&serverId="+speedInfo.getServerId()+"&areaId="+speedInfo.getAreaId()+"&roleId="+speedInfo.getRoleId()+"&userId="+speedInfo.getUserId()+"&appOpenid="+speedInfo.getAppOpenid()+"&uin="+speedInfo.getUin()+"&token="+speedInfo.getToken());
        Request request = new Request.Builder()
                .url("https://bang.qq.com/app/speed/chest/ajax/getUserBoxItemInfoV2")
                .method("POST", body)
                .addHeader("Referer", speedInfo.getReferer())
                .addHeader("Content-Type", "Mozilla/5.0 (iPhone; CPU iPhone OS 13_2_3 like Mac OS X) AppleWebKit/605.1.15 (KHTML, like Gecko) Version/13.0.3 Mobile/15E148 Safari/604.1")
                .build();
        Response response = okHttpClient.newCall(request).execute();
        return response;
    }

    @Override
    public Response getUserBagInfo() throws Exception {

        Integer userId = CurrentUserUtil.getUserId();
        SpeedInfo speedInfo = this.SpeedInfoByUserId(userId);

        MediaType mediaType = MediaType.parse("application/x-www-form-urlencoded");
        okhttp3.RequestBody body = okhttp3.RequestBody.create(mediaType, "uin="+speedInfo.getUin()+"&areaId="+speedInfo.getAreaId()+"&toUin="+speedInfo.getToUin()+"&userId="+speedInfo.getUserId()+"&token="+speedInfo.getToken());
        Request request = new Request.Builder()
                .url("https://bang.qq.com//app/speed/card/ajax/getUserBagInfo")
                .method("POST", body)
                .addHeader("Referer", speedInfo.getReferer())
                .addHeader("Content-Type", "Mozilla/5.0 (iPhone; CPU iPhone OS 13_2_3 like Mac OS X) AppleWebKit/605.1.15 (KHTML, like Gecko) Version/13.0.3 Mobile/15E148 Safari/604.1")
                .build();
        Response response = okHttpClient.newCall(request).execute();
        return response;

    }

    @Override
    public Response openBoxByKey(Integer keyId1,Integer keyNum1,Integer boxId) throws Exception {


        Integer userId = CurrentUserUtil.getUserId();
        SpeedInfo speedInfo = this.SpeedInfoByUserId(userId);

        MediaType mediaType = MediaType.parse("application/x-www-form-urlencoded");
        RequestBody body = RequestBody.create(mediaType, "game=speed&openid=&serverId="+speedInfo.getServerId()+"&areaId="+speedInfo.getAreaId()+"&roleId="+speedInfo.getRoleId()+"&userId="+speedInfo.getUserId()+"&appOpenid="+speedInfo.getAppOpenid()+"&keyId1="+keyId1+"&keyNum1="+keyNum1+"&keyId2=&keyNum2=&uin="+speedInfo.getUin()+"&token="+speedInfo.getToken()+"&boxId="+boxId+"&openNum=1");
        Request request = new Request.Builder()
                .url("https://bang.qq.com/app/speed/chest/ajax/openBoxByKey")
                .method("POST", body)
                .addHeader("Referer", speedInfo.getReferer())
                .addHeader("Content-Type", "Mozilla/5.0 (iPhone; CPU iPhone OS 13_2_3 like Mac OS X) AppleWebKit/605.1.15 (KHTML, like Gecko) Version/13.0.3 Mobile/15E148 Safari/604.1")
                .build();
        Response response = okHttpClient.newCall(request).execute();


        return response;
    }

    @Override
    public Response openBox(Integer boxId) throws Exception {



        Integer userId = CurrentUserUtil.getUserId();
        SpeedInfo speedInfo = this.SpeedInfoByUserId(userId);

        MediaType mediaType = MediaType.parse("application/x-www-form-urlencoded");
        RequestBody body = RequestBody.create(mediaType, "game=speed&openid=&serverId="+speedInfo.getServerId()+"&areaId="+speedInfo.getAreaId()+"&roleId="+speedInfo.getRoleId()+"&userId="+speedInfo.getUserId()+"&appOpenid="+speedInfo.getAppOpenid()+"&uin="+speedInfo.getUin()+"&token="+speedInfo.getToken()+"&boxId="+boxId+"&openNum=1");
        Request request = new Request.Builder()
                .url("https://bang.qq.com/app/speed/chest/ajax/openBox")
                .method("POST", body)
                .addHeader("Referer", speedInfo.getReferer())
                .addHeader("Content-Type", "Mozilla/5.0 (iPhone; CPU iPhone OS 13_2_3 like Mac OS X) AppleWebKit/605.1.15 (KHTML, like Gecko) Version/13.0.3 Mobile/15E148 Safari/604.1")
                .build();
        Response response = okHttpClient.newCall(request).execute();
        return response;
    }

    @Override
    public void savaInfo(SpeedInfo info) {
           if (info.getId() != null){
               speedInfoMapper.updateById(info);
           }else {
               speedInfoMapper.insert(info);
           }
    }

    @Override
    public SpeedInfo SpeedInfoByUserId(Integer userId) {
        QueryWrapper<SpeedInfo> speedInfoQueryWrapper = new QueryWrapper<>();
        speedInfoQueryWrapper.eq("speed_user_id",userId);
        SpeedInfo speedInfo = speedInfoMapper.selectOne(speedInfoQueryWrapper);
        return speedInfo;
    }

    @Override
    public SpeedInfo SpeedInfoById(Integer id) {
        SpeedInfo speedInfo = speedInfoMapper.selectById(id);
        return speedInfo;
    }

    @Override
    public SpeedInfo LoginChek(String account, String password) {

        QueryWrapper<SpeedInfo> speedInfoQueryWrapper = new QueryWrapper<>();
        speedInfoQueryWrapper.eq("account",account).eq("password",password);
        SpeedInfo speedInfo = speedInfoMapper.selectOne(speedInfoQueryWrapper);
        return speedInfo;
    }






    @Override
    public  List<Map<String, Object>> asyncOpenBoxByKey(SpeedInfo speedInfo, Integer openNum, Integer keyId1, Integer keyNum1, Integer boxId) throws Exception {
        MediaType mediaType = MediaType.parse("application/x-www-form-urlencoded");
        RequestBody body = RequestBody.create(mediaType, "game=speed&openid=&serverId="+speedInfo.getServerId()+"&areaId="+speedInfo.getAreaId()+"&roleId="+speedInfo.getRoleId()+"&userId="+speedInfo.getUserId()+"&appOpenid="+speedInfo.getAppOpenid()+"&keyId1="+keyId1+"&keyNum1="+keyNum1+"&keyId2=&keyNum2=&uin="+speedInfo.getUin()+"&token="+speedInfo.getToken()+"&boxId="+boxId+"&openNum=1");

        Request request = new Request.Builder()
                .url("https://bang.qq.com/app/speed/chest/ajax/openBoxByKey")
                .method("POST", body)
                .addHeader("Referer", speedInfo.getReferer())
                .addHeader("Content-Type", "Mozilla/5.0 (iPhone; CPU iPhone OS 13_2_3 like Mac OS X) AppleWebKit/605.1.15 (KHTML, like Gecko) Version/13.0.3 Mobile/15E148 Safari/604.1")
                .build();

        List<CompletableFuture<Map<String, Object>>> futures = new ArrayList<>();

        for (int i = 0; i < openNum; i++) {
            futures.add(fetchrequestAsync(request));
        }

        CompletableFuture<Void> allFutures = CompletableFuture.allOf(
                futures.toArray(new CompletableFuture[futures.size()])
        );
        allFutures.join();
        List<Map<String, Object>> responses = futures.stream()
                .map(CompletableFuture::join)
                .collect(Collectors.toList());

        return responses;
    }


    // 发送异步请求，完成后统一处理
    private CompletableFuture<Map<String, Object>> fetchrequestAsync(Request request) {
        CompletableFuture<Map<String, Object>> future = new CompletableFuture<>();
        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String responseBody = response.body().string();
                ObjectMapper objectMapper = new ObjectMapper();
                Map<String, Object> map = objectMapper.readValue(responseBody, new TypeReference<Map<String, Object>>() {});
                future.complete(map);
            }

            @Override
            public void onFailure(Call call, IOException e) {
                future.completeExceptionally(e);
            }
        });
        return future;
    }
}

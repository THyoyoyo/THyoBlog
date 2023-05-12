package com.blogs.service.imlp;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.blogs.mapper.speed.SpeedBoxLogMapper;
import com.blogs.mapper.speed.SpeedInfoMapper;
import com.blogs.model.speed.SpeedBoxLog;
import com.blogs.model.speed.SpeedInfo;
import com.blogs.service.SpeedToolService;
import com.blogs.util.CurrentUserUtil;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import okhttp3.*;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;


@Service
@Slf4j
public class SpeedToolServiceImpl implements SpeedToolService {

    @Autowired
    private OkHttpClient okHttpClient;

    @Autowired
    private SpeedInfoMapper speedInfoMapper;

    @Autowired
    private SpeedBoxLogMapper speedBoxLogMapper;

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
            Thread.sleep(400);
            futures.add(fetchrequestAsync(request));
        }

        CompletableFuture<Void> allFutures = CompletableFuture.allOf(
                futures.toArray(new CompletableFuture[futures.size()])
        );
        allFutures.join();
        List<Map<String, Object>> responses = futures.stream()
                .map(CompletableFuture::join)
                .collect(Collectors.toList());


        //日志
        for (Map<String, Object> respons : responses) {

//            Map<String, Object> data =(Map<String, Object>) respons.get("data");
//            List<Map<String, Object>> itemList =(List<Map<String, Object>>) data.get("itemList");
//            if(itemList !=null){     }
                SpeedBoxLog speedBoxLog = new SpeedBoxLog();
                speedBoxLog.setSpeedId(speedInfo.getId());
                speedBoxLog.setJson(JSON.toJSONString(respons));
                speedBoxLog.setCreated(new Date());
                speedBoxLogMapper.insert(speedBoxLog);

        }


        return responses;
    }

    @Override
    public List<String> getGiftid(Integer userId) throws Exception {

        Integer speedUserId = userId == null ? CurrentUserUtil.getUserId() :userId;

        QueryWrapper<SpeedInfo> speedInfoQueryWrapper = new QueryWrapper<>();
        speedInfoQueryWrapper.eq("speed_user_id",speedUserId);
        SpeedInfo speedInfo = speedInfoMapper.selectOne(speedInfoQueryWrapper);

        Request request = new Request.Builder()
                .url(getMwegameUrl(speedInfo.getReferer()))
                .build();

        try (Response response = okHttpClient.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                throw new IOException("Unexpected code " + response);
            }

            String html = response.body().string();
            Document document = Jsoup.parse(html);

            // 获取所有具有 giftid 属性的标签
            Elements elementsWithGiftId = document.select("[giftid]");


            List<String> giftids = new ArrayList<>();
            // 遍历每个标签，并打印 giftid 属性的值
            for (Element element : elementsWithGiftId) {
                giftids.add(element.attr("giftid"));
            }

            return giftids;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }



    //每日签到领取
    @Override
    public Object dailyCheckIn(Integer type,String giftid,Integer speedUserId) throws Exception {

        String url = type == 0 ? "https://mwegame.qq.com/ams/sign/doSign/month":"https://mwegame.qq.com/ams/send/handle";

        Integer userId = speedUserId == null ? CurrentUserUtil.getUserId() : speedUserId;

        QueryWrapper<SpeedInfo> speedInfoQueryWrapper = new QueryWrapper<>();
        speedInfoQueryWrapper.eq("speed_user_id",userId);
        SpeedInfo speedInfo = speedInfoMapper.selectOne(speedInfoQueryWrapper);

        Map<String, String> parameters = new HashMap<>();
        parameters.put("userId", speedInfo.getUserId());
        parameters.put("openid", "");
        parameters.put("appOpenid", speedInfo.getAppOpenid());
        parameters.put("areaId", speedInfo.getAreaId());
        parameters.put("serverId", speedInfo.getServerId());
        parameters.put("gameId", speedInfo.getGameId());
        parameters.put("cGameId", "0");
        parameters.put("subGameId", "0");
        parameters.put("roleId", speedInfo.getRoleId());
        parameters.put("uniqueRoleId", speedInfo.getUniqueRoleId());
        parameters.put("token", speedInfo.getToken());
        parameters.put("uin", speedInfo.getUin());
        parameters.put("toUin", speedInfo.getToUin());
        parameters.put("nickname", speedInfo.getNickname());
        parameters.put("roleName", speedInfo.getRoleName());
        parameters.put("areaName", speedInfo.getAreaName());
        parameters.put("serverName", speedInfo.getServerName());
        parameters.put("page", "0");
        parameters.put("isother", "0");
        parameters.put("env", "");
        parameters.put("accessToken", speedInfo.getAccessToken());
        parameters.put("game", "speed");
        parameters.put("gift_id", giftid);

        // 用于构建查询参数
        StringBuilder queryBuilder = new StringBuilder();

        // 将参数编码并添加到查询参数中
        for (Map.Entry<String, String> entry : parameters.entrySet()) {
            if (queryBuilder.length() > 0) {
                queryBuilder.append('&');
            }
            queryBuilder.append(URLEncoder.encode(entry.getKey(), StandardCharsets.UTF_8.toString()));
            queryBuilder.append('=');
            queryBuilder.append(URLEncoder.encode(entry.getValue(), StandardCharsets.UTF_8.toString()));
        }

        // 将查询参数附加到 URL 的末尾
        String fullUrl = url + "?" + queryBuilder.toString();

        Request request = new Request.Builder()
                .url(fullUrl)
                .addHeader("Referer",getMwegameUrl(speedInfo.getReferer()))
                .get()
                .build();

        Response response = okHttpClient.newCall(request).execute();
        Object parse = JSON.parse(response.body().string());

        return  parse;
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



    //获取当前用户签到URL
    private String getMwegameUrl(String referer){
        //签到网页链接
        String replaceUrl = referer.replace("https://bang.qq.com/app/speed/card/userbag", "https://mwegame.qq.com/ams/sign/month/speed");
        return replaceUrl;
    }
}

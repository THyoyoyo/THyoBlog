package com.blogs.service.imlp;

import com.blogs.service.SpeedToolService;
import okhttp3.*;
import org.springframework.stereotype.Service;

import java.io.IOException;


@Service
public class SpeedToolServiceImpl implements SpeedToolService {
    @Override
    public Response getUserBoxItemInfoV2() throws IOException {
        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        MediaType mediaType = MediaType.parse("application/x-www-form-urlencoded");
        okhttp3.RequestBody body = okhttp3.RequestBody.create(mediaType, "game=speed&serverId=0&areaId=1&roleId=172012672&userId=77634875&appOpenid=E985DFB4B43FE1956B6EFB162DFA88D1&uin=522307026&token=wrMCRZSN");
        Request request = new Request.Builder()
                .url("https://bang.qq.com/app/speed/chest/ajax/getUserBoxItemInfoV2")
                .method("POST", body)
                .addHeader("Referer", "https://bang.qq.com/app/speed/card/userbag?serverName=&areaName=%E7%94%B5%E4%BF%A1%E5%8C%BA&roleName=THyo&appid=1105330667&nickname=THyo&isMainRole=1&appOpenid=E985DFB4B43FE1956B6EFB162DFA88D1&areaId=1&roleId=522307026&gameId=10013&toUin=522307026&serverId=0&accessToken=5A1BAE087966B0C48B8BF28754B13AD0&roleJob=&token=wrMCRZSN&uniqueRoleId=172012672&toOpenid=E985DFB4B43FE1956B6EFB162DFA88D1&acctype=qc&accType=qc&uin=522307026&roleLevel=131&userId=77634875")
                .addHeader("User-Agent", "Apifox/1.0.0 (https://www.apifox.cn)")
                .addHeader("Content-Type", "Mozilla/5.0 (iPhone; CPU iPhone OS 13_2_3 like Mac OS X) AppleWebKit/605.1.15 (KHTML, like Gecko) Version/13.0.3 Mobile/15E148 Safari/604.1")
                .build();
        Response response = client.newCall(request).execute();
        return response;
    }

    @Override
    public Response getUserBagInfo() throws IOException {
        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        MediaType mediaType = MediaType.parse("application/x-www-form-urlencoded");
        okhttp3.RequestBody body = okhttp3.RequestBody.create(mediaType, "uin=522307026&areaId=1&toUin=0522307026&userId=77634875&token=wrMCRZSN");
        Request request = new Request.Builder()
                .url("https://bang.qq.com//app/speed/card/ajax/getUserBagInfo")
                .method("POST", body)
                .addHeader("Referer", "https://bang.qq.com/app/speed/card/userbag?serverName=&areaName=%E7%94%B5%E4%BF%A1%E5%8C%BA&roleName=THyo&appid=1105330667&nickname=THyo&isMainRole=1&appOpenid=E985DFB4B43FE1956B6EFB162DFA88D1&areaId=1&roleId=522307026&gameId=10013&toUin=522307026&serverId=0&accessToken=5A1BAE087966B0C48B8BF28754B13AD0&roleJob=&token=wrMCRZSN&uniqueRoleId=172012672&toOpenid=E985DFB4B43FE1956B6EFB162DFA88D1&acctype=qc&accType=qc&uin=522307026&roleLevel=131&userId=77634875")
                .addHeader("Content-Type", "Mozilla/5.0 (iPhone; CPU iPhone OS 13_2_3 like Mac OS X) AppleWebKit/605.1.15 (KHTML, like Gecko) Version/13.0.3 Mobile/15E148 Safari/604.1")
                .build();
        Response response = client.newCall(request).execute();
        return response;

    }

    @Override
    public Response openBoxByKey() throws IOException {
        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        MediaType mediaType = MediaType.parse("application/x-www-form-urlencoded");
        RequestBody body = RequestBody.create(mediaType, "game=speed&openid=&serverId=0&areaId=1&roleId=172012672&userId=77634875&appOpenid=E985DFB4B43FE1956B6EFB162DFA88D1&keyId1=17456&keyNum1=2&keyId2=&keyNum2=&uin=522307026&token=wrMCRZSN&boxId=17455&openNum=1");
        Request request = new Request.Builder()
                .url("https://bang.qq.com/app/speed/chest/ajax/openBoxByKey")
                .method("POST", body)
                .addHeader("Referer", "https://bang.qq.com/app/speed/card/userbag?serverName=&areaName=%E7%94%B5%E4%BF%A1%E5%8C%BA&roleName=THyo&appid=1105330667&nickname=THyo&isMainRole=1&appOpenid=E985DFB4B43FE1956B6EFB162DFA88D1&areaId=1&roleId=522307026&gameId=10013&toUin=522307026&serverId=0&accessToken=5A1BAE087966B0C48B8BF28754B13AD0&roleJob=&token=wrMCRZSN&uniqueRoleId=172012672&toOpenid=E985DFB4B43FE1956B6EFB162DFA88D1&acctype=qc&accType=qc&uin=522307026&roleLevel=131&userId=77634875")
                .addHeader("Content-Type", "Mozilla/5.0 (iPhone; CPU iPhone OS 13_2_3 like Mac OS X) AppleWebKit/605.1.15 (KHTML, like Gecko) Version/13.0.3 Mobile/15E148 Safari/604.1")
                .build();
        Response response = client.newCall(request).execute();
        return response;
    }

    @Override
    public Response openBox() throws IOException {

        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        MediaType mediaType = MediaType.parse("application/x-www-form-urlencoded");
        RequestBody body = RequestBody.create(mediaType, "game=speed&openid=&serverId=0&areaId=1&roleId=172012672&userId=77634875&appOpenid=E985DFB4B43FE1956B6EFB162DFA88D1&uin=522307026&token=wrMCRZSN&boxId=124733&openNum=1");
        Request request = new Request.Builder()
                .url("https://bang.qq.com/app/speed/chest/ajax/openBox")
                .method("POST", body)
                .addHeader("Referer", "https://bang.qq.com/app/speed/card/userbag?serverName=&areaName=%E7%94%B5%E4%BF%A1%E5%8C%BA&roleName=THyo&appid=1105330667&nickname=THyo&isMainRole=1&appOpenid=E985DFB4B43FE1956B6EFB162DFA88D1&areaId=1&roleId=522307026&gameId=10013&toUin=522307026&serverId=0&accessToken=5A1BAE087966B0C48B8BF28754B13AD0&roleJob=&token=wrMCRZSN&uniqueRoleId=172012672&toOpenid=E985DFB4B43FE1956B6EFB162DFA88D1&acctype=qc&accType=qc&uin=522307026&roleLevel=131&userId=77634875")
                .addHeader("Content-Type", "Mozilla/5.0 (iPhone; CPU iPhone OS 13_2_3 like Mac OS X) AppleWebKit/605.1.15 (KHTML, like Gecko) Version/13.0.3 Mobile/15E148 Safari/604.1")
                .build();
        Response response = client.newCall(request).execute();
        return response;
    }
}

package com.blogs.service.imlp;


import com.alibaba.fastjson.JSON;
import com.blogs.service.LolToolsService;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class LolToolsServiceImpl implements LolToolsService {



    @Autowired
    private OkHttpClient okHttpClient;

    @Override
    public Response getBattleList() throws IOException {
        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("self_uuid","91c51f8c-81ab-4d75-a09c-011a7fef26c4");
        requestBody.put("area_id",3);
        requestBody.put("search_type",0);
        requestBody.put("battle_types",new ArrayList());
        requestBody.put("target_token","");
        requestBody.put("target_uuid","b1559b3c-1b2c-45d0-ad45-20fdf25f457a");
        requestBody.put("start_idx",0);

        MediaType mediaType = MediaType.parse("application/json");

        okhttp3.RequestBody body = okhttp3.RequestBody.create(mediaType, JSON.toJSONString(requestBody));
        Request request = new Request.Builder()
                .url("https://mlol.qt.qq.com/go/battle_info/get_battle_list?plat=ios&version=10000")
                .method("POST", body)
                .addHeader("GH-HEADER", "1-2-105-925-522307026")
                .addHeader("User-Agent", "QTL/9.2.5 (iPhone; IOS 16.1.2; Scale/3.00)")
                .addHeader("Cookie", "p_skey=JQ4PGKt3q1UaawZb0P7HBqAxKoZWd09MpiFpSZdrqLc_; p_skey=Cb3C4WpcnE-B**Upofu9fzkMc4Cv1hLra-gXSXUr5Lk_; tgw_l7_route=ced3f72ee0d845ca4b7eb984a1998edd; p_uin=o522307026; uin=o522307026; skey=MfP90ZXulk; acctype=; userId=91c51f8c-81ab-4d75-a09c-011a7fef26c4; tid=9DDD9C8D9572DAAAA47B76A09FCF6C68393BC82159ABABAF9733D2D836261B9E27DE7711B59F80225125ADFF334B6D674AF481F88B5953C87F36EBFB7EC14D8466F590468CD769AF923BD2E2A438BCE46108021931773A08355693CAFC73224BFF8581A301517A1CFB1E5FFB2F0456CD7B49DEB0CF1AD4F9E05073ADDE55D324482312151500AC86D16CE47FC21750CCB45B2A9BD666C7321F6674CC663292BA; clientType=10; accountType=1")
                .build();
        Response response = okHttpClient.newCall(request).execute();

        return  response;
    }

    @Override
    public Response getOnlineStatus() throws IOException {
        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("scene","v3_QdZUZ-XOZIKC0yWq8kDYTndrBYDyeI_HDrhKQVuXkazHvln8EhvJY9-H1TMt6dBj33iKKM8Kl6PC6Aek7trTNC17UG1eeiHycxApezjjD4s=");

        MediaType mediaType = MediaType.parse("application/json");

        okhttp3.RequestBody body = okhttp3.RequestBody.create(mediaType, JSON.toJSONString(requestBody));
        Request request = new Request.Builder()
                .url("https://mlol.qt.qq.com/go/battle_info/get_online_status?plat=ios&version=10000")
                .method("POST", body)
                .addHeader("GH-HEADER", "1-2-105-961-522307026")
                .addHeader("User-Agent", "QTL/9.6.1 (iPhone; IOS 16.1.2; Scale/3.00)")
                .addHeader("Cookie", "tgw_l7_route=8dad33cd14f6475f74b56fa7a6119541; uin=o522307026; skey=MFH2nwa4r1; acctype=; userId=91c51f8c-81ab-4d75-a09c-011a7fef26c4; tid=B10AEB33C255398DBE86CC332E06606D2C0DA5BED781E83F8C7C2F754DA7ACA19B086BF9CEA1447853EA9BF1476EAB29CCCCE0867672F95D969EABA773E8F9AC3609BA67A2BFF79910A31782C8ECC80357FE9664114241C44ADEBAA98372DDC27231C2E9568B01974D9C56D7852B8EFAF6819A15D4B649942F038579AC23E4026F7ED96A4A621342F0C72488FAEB1B24A4C4BD8B190B5091022FD7F53F8CD7D1; clientType=10; accountType=1")
                .build();
        Response response = okHttpClient.newCall(request).execute();

        return  response;
    }

    @Override
    public Response userProfileInfo() throws IOException {
        Map<String, Object> requestBody = new HashMap<>();
        List<Map<String,Object>> uuidSceneList = new ArrayList<>();




        requestBody.put("clientType",10);

        HashMap<String, Object> uuidSceneListItem = new HashMap<>();

        uuidSceneListItem.put("uuid","b1559b3c-1b2c-45d0-ad45-20fdf25f457a");
        uuidSceneList.add(uuidSceneListItem);
        requestBody.put("uuidSceneList",uuidSceneList);

        requestBody.put("isNeedMedal",1);
        requestBody.put("opUuid","91c51f8c-81ab-4d75-a09c-011a7fef26c4");
        requestBody.put("isNeedCommunityInfo",1);
        requestBody.put("isNeedGameInfo",1);



        MediaType mediaType = MediaType.parse("application/json");

        okhttp3.RequestBody body = okhttp3.RequestBody.create(mediaType, JSON.toJSONString(requestBody));
        Request request = new Request.Builder()
                .url("https://mlol.qt.qq.com/go/user_profile/query/user?plat=ios&version=10000")
                .method("POST", body)
                .addHeader("GH-HEADER", "1-2-105-925-522307026")
                .addHeader("User-Agent", "QTL/9.2.5 (iPhone; IOS 16.1.2; Scale/3.00)")
                .addHeader("Cookie", "p_skey=JQ4PGKt3q1UaawZb0P7HBqAxKoZWd09MpiFpSZdrqLc_; p_skey=Cb3C4WpcnE-B**Upofu9fzkMc4Cv1hLra-gXSXUr5Lk_; tgw_l7_route=ced3f72ee0d845ca4b7eb984a1998edd; p_uin=o522307026; uin=o522307026; skey=MfP90ZXulk; acctype=; userId=91c51f8c-81ab-4d75-a09c-011a7fef26c4; tid=9DDD9C8D9572DAAAA47B76A09FCF6C68393BC82159ABABAF9733D2D836261B9E27DE7711B59F80225125ADFF334B6D674AF481F88B5953C87F36EBFB7EC14D8466F590468CD769AF923BD2E2A438BCE46108021931773A08355693CAFC73224BFF8581A301517A1CFB1E5FFB2F0456CD7B49DEB0CF1AD4F9E05073ADDE55D324482312151500AC86D16CE47FC21750CCB45B2A9BD666C7321F6674CC663292BA; clientType=10; accountType=1")
                .build();
        Response response = okHttpClient.newCall(request).execute();

        return  response;
    }

    @Override
    public Response getExploitBattleList() throws IOException {

        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("scene","v3_QdZUZ-XOZIKC0yWq8kDYTndrBYDyeI_HDrhKQVuXkazHvln8EhvJY9-H1TMt6dBj33iKKM8Kl6PC6Aek7trTNC17UG1eeiHycxApezjjD4s=");
        requestBody.put("filter","all");
        MediaType mediaType = MediaType.parse("application/json");

        okhttp3.RequestBody body = okhttp3.RequestBody.create(mediaType, JSON.toJSONString(requestBody));
        Request request = new Request.Builder()
                .url("https://mlol.qt.qq.com/go/exploit/get_battle_list?plat=ios&version=10000")
                .method("POST", body)
                .addHeader("GH-HEADER", "1-2-105-961-522307026")
                .addHeader("User-Agent", "QTL/9.6.1 (iPhone; IOS 16.1.2; Scale/3.00)")
                .addHeader("Cookie", "tgw_l7_route=8dad33cd14f6475f74b56fa7a6119541; uin=o522307026; skey=MFH2nwa4r1; acctype=; userId=91c51f8c-81ab-4d75-a09c-011a7fef26c4; tid=B10AEB33C255398DBE86CC332E06606D2C0DA5BED781E83F8C7C2F754DA7ACA19B086BF9CEA1447853EA9BF1476EAB29CCCCE0867672F95D969EABA773E8F9AC3609BA67A2BFF79910A31782C8ECC80357FE9664114241C44ADEBAA98372DDC27231C2E9568B01974D9C56D7852B8EFAF6819A15D4B649942F038579AC23E4026F7ED96A4A621342F0C72488FAEB1B24A4C4BD8B190B5091022FD7F53F8CD7D1; clientType=10; accountType=1")
                .build();
        Response response = okHttpClient.newCall(request).execute();

        return  response;
    }
}

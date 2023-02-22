package com.blogs.service.imlp;

import com.alibaba.fastjson.JSON;
import com.blogs.model.expressTools.Botany;
import com.blogs.model.expressTools.Translate;
import com.blogs.model.expressTools.UpMail;
import com.blogs.service.ExpressToolsService;
import com.blogs.util.MD5;
import com.blogs.vo.common.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.Map;


@Service
public class ExpressToolsServiceImpl implements ExpressToolsService {

    @Autowired
    private JavaMailSender mailSender;

    @Value("${spring.mail.username}")
    private String from;


    @Override
    public Map<String, Object> getTranslate(Translate translate) {
        String text = translate.getText();
        //公共值
        String APP_ID = "20221222001504909";
        String KEY = "hsE0gwQfmF3YRGMZ07VB";
        String URL = "http://api.fanyi.baidu.com/api/trans/vip/translate";
        // 随机数
        String salt = String.valueOf(System.currentTimeMillis());
        // 签名
        String src = APP_ID + text + salt + KEY; // 加密前的原文

        try {
            //Post-提交
            RestTemplate restTemplate = new RestTemplate();
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
            MultiValueMap<String, String> multiValueMap = new LinkedMultiValueMap<>();
            multiValueMap.add("q", translate.getText());
            multiValueMap.add("from", translate.getFrom());
            multiValueMap.add("to", translate.getTo());
            multiValueMap.add("appid", APP_ID);
            multiValueMap.add("salt", salt);
            multiValueMap.add("sign", MD5.md5(src));
            HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(multiValueMap, headers);
            ResponseEntity<String> response = restTemplate.postForEntity(URL, request, String.class);
            Map<String, Object> parse = (Map<String, Object>) JSON.parse(response.getBody());
            return parse;
        }catch (Throwable e){
            return null;
        }
    }

    @Override
    public Map<String, Object> getBotany(Botany botany) {
        String API_Key = "FHSV4NooLfsVTm8sKxjc3Zso";
        String Secret_Key = "zsvQGcqdAMFbWXPH5dX1jGpEf0XoZLRU";
        String AccessTokenUrl = "https://aip.baidubce.com/oauth/2.0/token?grant_type=client_credentials" + "&client_id=" + API_Key + "&client_secret=" + Secret_Key;

        String imgUrl = botany.getImgUrl();
        String imgBase64 = botany.getImgBase64();
        //Post-AccessToken
        RestTemplate tokenRestTemplate = new RestTemplate();
        HttpHeaders tokenHeaders = new HttpHeaders();
        tokenHeaders.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<MultiValueMap<String, String>> tokenRequest = new HttpEntity<>(null, tokenHeaders);
        ResponseEntity<String> response = tokenRestTemplate.postForEntity(AccessTokenUrl, tokenRequest, String.class);
        Map<String, Object> tokenParse = (Map<String, Object>) JSON.parse(response.getBody());

        //权限token
        String accessToken = tokenParse.get("access_token").toString();
        String plantUrl = "https://aip.baidubce.com/rest/2.0/image-classify/v1/plant?access_token=" + accessToken;

        RestTemplate plantRestTemplate = new RestTemplate();
        HttpHeaders plantHeaders = new HttpHeaders();
        plantHeaders.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        MultiValueMap<String, String> multiValueMap = new LinkedMultiValueMap<>();

        if(botany.getImgUrl() != null){
            multiValueMap.add("url", imgUrl);
        }

        if(multiValueMap.get("url") == null && imgBase64 !=null){
            multiValueMap.add("image", imgBase64);
        }

        if(botany.getBaikeNum() != null ){
            multiValueMap.add("baike_num", "1");
        }

        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(multiValueMap, plantHeaders);
        ResponseEntity<String> plantResponse = plantRestTemplate.postForEntity(plantUrl, request, String.class);
        Map<String, Object> parse = (Map<String, Object>) JSON.parse(plantResponse.getBody());
        return parse;
    }

    @Override
    public void upMail(UpMail upMail) {
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setTo(upMail.getToEmail());//目标邮箱
        simpleMailMessage.setSubject(upMail.getUbject());
        simpleMailMessage.setText(upMail.getText());//正文
        simpleMailMessage.setFrom(from);//发送的邮箱
        mailSender.send(simpleMailMessage);
    }

    @Override
    public void upMailHtml(UpMail upMail) throws MessagingException {
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        //是否发送的邮件是富文本（附件，图片，html等）
        MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage,true);
        messageHelper.setTo(upMail.getToEmail());//目标邮箱
        messageHelper.setSubject(upMail.getUbject());
        messageHelper.setText(upMail.getText(),true);//正文
        messageHelper.setFrom(from);//发送的邮箱
        mailSender.send(mimeMessage);
    }
}

package com.blogs.controller;


import com.alibaba.fastjson.JSON;
import com.blogs.annotation.Token;
import com.blogs.model.expressTools.Botany;
import com.blogs.model.expressTools.Translate;
import com.blogs.model.expressTools.UpMail;
import com.blogs.service.ExpressToolsService;
import com.blogs.util.MD5;
import com.blogs.vo.common.R;
import com.sun.xml.internal.ws.api.model.ExceptionType;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.Map;

@RestController
@RequestMapping("/api/expressTools")
@Api(tags = "扩展工具")
public class ExpressToolsController {


    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private ExpressToolsService expressToolsService;

    @Value("${spring.mail.username}")
    private String from;

    @ApiOperation(value = "百度翻译")
    @PostMapping("/getTranslate")
    public R getTranslate(@RequestBody Translate dto) {
        try {
            Map<String, Object> translate = expressToolsService.getTranslate(dto);
            if (translate.get("error_code") == null) {
                return R.succeed(translate);
            } else {
                return R.failed(402, "翻译繁忙,请稍后再试");
            }
        }catch (Exception e){
            return R.failed(403, "翻译错误,请稍后再试");
        }
    }


    @ApiOperation(value = "植物图片识别")
    @PostMapping("/getBotany")
    public R getBotany(@RequestBody Botany dto) {
        try {
            Map<String, Object> botany = expressToolsService.getBotany(dto);
            return R.succeed(botany);
        } catch (Throwable e) {
            return R.failed(403, "发生错误,请稍后再试");
        }
    }

    @ApiOperation(value = "发送邮件(文字版)")
    @PostMapping("/upMail")
    public R upMail(@RequestBody UpMail dto) throws MessagingException {
        try {
            expressToolsService.upMail(dto);
            return  R.succeed();
        }catch (Exception e){
            System.out.println(e);
            return  R.failed(404,"邮件发送失败");
        }
    }


    @ApiOperation(value = "发送邮件（富文本）")
    @PostMapping("upMailHtml")
    public R upMailHtml(@RequestBody UpMail dto) throws MessagingException {
        try {
            expressToolsService.upMailHtml(dto);
            return R.succeed();
        }catch (Exception e){
            System.out.println(e);
            return  R.failed(404,"邮件发送失败");
        }
    }
}

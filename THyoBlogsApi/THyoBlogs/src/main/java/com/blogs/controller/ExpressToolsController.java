package com.blogs.controller;


import com.blogs.model.expressTools.Botany;
import com.blogs.model.expressTools.OkHttpMethod;
import com.blogs.model.expressTools.Translate;
import com.blogs.model.expressTools.UpMail;
import com.blogs.service.ExpressToolsService;
import com.blogs.vo.common.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/expressTools")
@Api(tags = "扩展工具")
public class ExpressToolsController {

    @Autowired
    private ExpressToolsService expressToolsService;


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
    public R upMail(@RequestBody UpMail dto) {
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
    public R upMailHtml(@RequestBody UpMail dto) {
        try {
            expressToolsService.upMailHtml(dto);
            return R.succeed();
        }catch (Exception e){
            System.out.println(e);
            return  R.failed(404,"邮件发送失败");
        }
    }

    @ApiOperation(value = "OkHttp发送请求get/post(value必须是字符串)")
    @PostMapping("/okHttpGetMethod")
    public Object okHttpGetMethod(@RequestBody OkHttpMethod dto){
        Object  data = null;
          try {
              data = expressToolsService.okHttpMethod(dto);
              if(data ==null){
                  return R.succeed(200,"没有获取到任何数据");
              }
              return data;
          }catch (Exception e){
             return R.failed(404,"请重新尝试");
          }

    }

    @ApiOperation(value = "获取QQ头像昵称")
    @GetMapping("/getQinfo")
    public R getQinfo(@RequestParam String qq){
        String KEY = "VFR6AzQbrG9eyAbFqt5wDUfHV9";
        OkHttpMethod okHttpMethod = new OkHttpMethod();
        okHttpMethod.setUrl("https://qqlykm.cn/api/qqobtain/get");
        okHttpMethod.setMethod("get");
        HashMap<String, String> param = new HashMap<>();
        param.put("key",KEY);
        param.put("qq",qq);
        okHttpMethod.setParam(param);
        try {
            Object o = expressToolsService.okHttpMethod(okHttpMethod);
            return  R.succeed(o);
        }catch (Exception e){
            return R.failed(404,"接口错误，请稍后再试");
        }
    }
}

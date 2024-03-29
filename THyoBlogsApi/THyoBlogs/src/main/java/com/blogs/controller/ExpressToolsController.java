package com.blogs.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.blogs.mapper.takeawayEvaluation.TakeawayEvaluationMapper;
import com.blogs.model.expressTools.*;
import com.blogs.model.takeawayEvaluation.TakeawayEvaluation;
import com.blogs.service.ExpressToolsService;
import com.blogs.vo.common.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/expressTools")
@Api(tags = "扩展工具")
public class ExpressToolsController {

    @Autowired
    private ExpressToolsService expressToolsService;

    @Autowired
    private TakeawayEvaluationMapper takeawayEvaluationMapper;


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


    @ApiOperation(value = "图片识别")
    @PostMapping("/getImageScan")
    public R getBotany(@RequestBody ImageScan dto) {
        try {
            Map<String, Object> imageScan = expressToolsService.getImageScan(dto);
            if (imageScan !=null){
                return R.succeed(imageScan);
            }else {
                return R.succeed(201,"识别失败,请检查图片或参数再进行尝试");
            }

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
        try {
            Object o = expressToolsService.getQqInfo(qq);
            return  R.succeed(o);
        }catch (Exception e){
            return R.failed(404,"接口错误，请稍后再试");
        }
    }




    @ApiOperation(value = "腾游加速器通过token获取用户信息")
    @PostMapping("/getTyUserInfoByToken")
    public R getTyUserInfoByToken(@RequestParam("token") String token){

        Object tyUserInfoByToken = expressToolsService.getTyUserInfoByToken(token);
        return R.succeed(tyUserInfoByToken);
    }

    @ApiOperation(value = "外卖评价获取")
    @GetMapping("/getWmEvaluate")
    public R getWmEvaluate(@RequestParam("length") Integer  length){
        QueryWrapper<TakeawayEvaluation> takeawayEvaluationQueryWrapper = new QueryWrapper<>();
        List<TakeawayEvaluation> takeawayEvaluations = takeawayEvaluationMapper.selectList(takeawayEvaluationQueryWrapper);

        List<String> shortContents = new ArrayList<>();

        for (TakeawayEvaluation evaluation : takeawayEvaluations) {
            String content = evaluation.getContent();
            String cleanedText = content.replaceAll("[\\p{P}+~$`^=|<>～｀＄＾＋＝｜＜＞￥×]", "");
            if (cleanedText.length() < length) {
                shortContents.add(cleanedText);
            }
        }
        Random random = new Random();
        int randomIndex = random.nextInt(shortContents.size());

        return  R.succeed(shortContents.get(randomIndex));
    }




    @ApiOperation(value = "添加外卖评价")
    @PostMapping("/addWmEvaluate")
    public R addWmEvaluate(@RequestBody Map<String,Object> dto){

        List<String> list = (List<String>) dto.get("list");
        for (String item : list) {
            TakeawayEvaluation takeawayEvaluation = new TakeawayEvaluation();
            takeawayEvaluation.setContent(item);
            takeawayEvaluationMapper.insert(takeawayEvaluation);
        }

        return  R.succeed();
    }

















}

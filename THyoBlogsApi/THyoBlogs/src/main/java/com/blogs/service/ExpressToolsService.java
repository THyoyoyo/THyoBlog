package com.blogs.service;

import com.blogs.model.expressTools.*;

import javax.mail.MessagingException;
import java.util.Map;

public interface ExpressToolsService {
    public Map<String, Object> getTranslate(Translate translate);

    public Map<String, Object> getImageScan(ImageScan imageScan);

    public void upMail(UpMail upMail);

    public void upMailHtml(UpMail upMail) throws MessagingException;


    public Object okHttpMethod(OkHttpMethod okHttpMethod);

    public Map<String, Object> getQqInfo(String qq);

    public Map<String, Object> stopTyTime(StopTyTime stopTyTime);

    public Object getTyUserInfoByToken(String token);
}

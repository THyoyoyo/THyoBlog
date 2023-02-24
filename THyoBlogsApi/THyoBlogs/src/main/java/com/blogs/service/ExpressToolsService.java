package com.blogs.service;

import com.blogs.model.expressTools.ImageScan;
import com.blogs.model.expressTools.OkHttpMethod;
import com.blogs.model.expressTools.Translate;
import com.blogs.model.expressTools.UpMail;

import javax.mail.MessagingException;
import java.util.Map;

public interface ExpressToolsService {
    public Map<String, Object> getTranslate(Translate translate);

    public Map<String, Object> getImageScan(ImageScan imageScan);

    public void upMail(UpMail upMail);

    public void upMailHtml(UpMail upMail) throws MessagingException;


    public Object okHttpMethod(OkHttpMethod okHttpMethod);

    public Object getQqInfo(String qq);
}

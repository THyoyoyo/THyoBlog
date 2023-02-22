package com.blogs.service;

import com.blogs.model.expressTools.Botany;
import com.blogs.model.expressTools.Translate;
import com.blogs.model.expressTools.UpMail;

import javax.mail.MessagingException;
import java.util.Map;

public interface ExpressToolsService {
    public Map<String, Object> getTranslate(Translate translate);

    public Map<String, Object> getBotany(Botany botany);

    public void upMail(UpMail upMail);

    public void upMailHtml(UpMail upMail) throws MessagingException;
}

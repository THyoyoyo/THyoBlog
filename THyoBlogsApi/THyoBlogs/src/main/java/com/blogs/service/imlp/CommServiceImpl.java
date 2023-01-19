package com.blogs.service.imlp;

import com.blogs.service.CommService;
import com.qiniu.util.Auth;
import org.springframework.stereotype.Service;

@Service
public class CommServiceImpl implements CommService {


    @Override
    public String getUploadFileToken() {
        String accessKey = "ECUNRvAx5D7SYZGXaH-8tL10DOXx3KBBExj-bvil";
        String secretKey = "31C9FcvmDRp-2GyTGRRIRR0OxzKW22S_zIfx41RL";
        String bucket = "thyo";
        Auth auth = Auth.create(accessKey, secretKey);
        String upToken = auth.uploadToken(bucket);
        return upToken;
    }
}

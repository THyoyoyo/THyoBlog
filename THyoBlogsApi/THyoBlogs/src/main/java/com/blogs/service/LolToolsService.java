package com.blogs.service;

import okhttp3.Response;

import java.io.IOException;

public interface LolToolsService {
    public Response getBattleList() throws IOException;

    public Response getOnlineStatus() throws IOException;

    public Response userProfileInfo() throws IOException;

    public Response getExploitBattleList() throws IOException;
}

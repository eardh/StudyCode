package com.dahuang;


import javax.servlet.*;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class SSE extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        resp.setContentType("text/event-stream");
        resp.setCharacterEncoding("UTF-8");

        for ( int i = 0;i < 5;i++) {
            //指定事件标识
            resp.getWriter().write("event:me\n");

            // 格式：data：+ 数据 + 2个回车
            resp.getWriter().write("data:"+i+"\n\n");
            resp.getWriter().flush();

            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req,resp);
    }
}

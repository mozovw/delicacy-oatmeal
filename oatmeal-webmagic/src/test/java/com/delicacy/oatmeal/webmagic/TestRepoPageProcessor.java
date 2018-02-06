package com.delicacy.oatmeal.webmagic;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.http.HttpHost;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;
import us.codecraft.webmagic.*;
import us.codecraft.webmagic.pipeline.ConsolePipeline;
import us.codecraft.webmagic.pipeline.JsonFilePipeline;
import us.codecraft.webmagic.pipeline.Pipeline;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.selector.Json;
import us.codecraft.webmagic.utils.FilePersistentBase;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class TestRepoPageProcessor implements PageProcessor {

    // 部分一：抓取网站的相关配置，包括编码、抓取间隔、重试次数等
    private Site site = Site.me().setRetryTimes(3).setSleepTime(1000);

    //WebMagic里主要使用了三种抽取技术：XPath、正则表达式和CSS选择器。
    @Override
    // process是定制爬虫逻辑的核心接口，在这里编写抽取逻辑
    public void process(Page page) {
        // 部分二：定义如何抽取页面信息，并保存下来
//        page.putField("url", page.getHtml().xpath("").all());
//        page.putField("url", page.getHtml().links().regex("[^\\s]*/search/detail/+[^\\s]*").all());
//        page.putField("url", page.getHtml().links().all());
        //*[@id="imgid"]/div/ul/li[13]/div[1]/a
//        page.putField("url", page.getHtml().xpath("//*[@id='imgid']/div/ul/li/div/a").all());
        List<String> links = page.getHtml().regex("[^\\s]*//img\\d+\\.sogoucdn\\.com/app/a/\\S+\\.jpg").all();

        if (page.getResultItems().get("url") == null) {
            //skip this page
            return;
            //page.setSkip(true);

        }
//        page.putField("status", page.getHtml().xpath("//*[@id=\"instances\"]/tbody/tr/td[4]/a/text()").all());
        // 部分三：从页面发现后续的url地址来抓取
//        page.addTargetRequests(page.getHtml().links().regex("(https://github\\.com/[\\w\\-]+/[\\w\\-]+)").all());
    }

    @Override
    public Site getSite() {
        return site;
    }




    public static void main(String[] args) {

        JsonPipeline pipeline = new JsonPipeline();
        Spider.create(new TestRepoPageProcessor())
                //从"https://github.com/code4craft"开始抓
                .addUrl("http://pic.sogou.com/pics?query=%E7%BE%8E%E5%A5%B3%E5%9B%BE%E7%89%87&ie=utf-8&bh=1&w=05002600")

                .addPipeline(pipeline)
                //.addPipeline(new JsonFilePipeline("D:\\webmagic\\"))

                //开启5个线程抓取
                .thread(5)
                //启动爬虫
                .run();
        System.out.println(pipeline.getJson());
        JSONObject object = JSON.parseObject(pipeline.getJson());
        Object obj = object.get("url");
        List<String> list = new ArrayList<>();
        if (obj instanceof JSONObject){
            JSON jsonObject =   (JSONObject) obj;
        }else{
            JSONArray jsonArray = (JSONArray) obj;
            Iterator<Object> iterator = jsonArray.iterator();
            while (iterator.hasNext()){
                String next = (String)iterator.next();
                System.out.println(next);
                list.add(next);
            }

            //downloadFile(o.toString(),"meimei.jpg");

        }
    }

    static class JsonPipeline implements Pipeline {

        private String json;

        public String getJson(){
            return json;
        }
        public void process(ResultItems resultItems, Task task) {
            json = JSON.toJSONString(resultItems.getAll());
        }
    }

    public static void downloadFile(String strUrl, String fileName) {
        downloadFile(strUrl,null,fileName);
    }


    public static void downloadFile(String strUrl, String dir, String fileName) {
        URL url = null;
        try {
            url = new URL(strUrl);
        } catch (MalformedURLException e2) {
            e2.printStackTrace();
        }
        //打开链接
        HttpURLConnection conn = null;
        try {
            conn = (HttpURLConnection)url.openConnection();
        } catch (IOException e) {
            e.printStackTrace();
        }
        //设置请求方式为"GET"
        try {
            conn.setRequestMethod("GET");
        } catch (ProtocolException e) {
            e.printStackTrace();
        }
        //超时响应时间为5秒
        conn.setConnectTimeout(5 * 1000);
        //告诉服务器我是什么浏览器 CHROME
        conn.setRequestProperty("User-Agent", "Mozilla/5.0(Macintosh;IntelMacOSX10_7_0)AppleWebKit/535.11(KHTML,likeGecko)Chrome/17.0.963.56Safari/535");
        InputStream is = null;
        try {
            is = conn.getInputStream();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        if (StringUtils.isEmpty(dir)) {
            String oss = System.getProperties().getProperty("os.name");
            if (oss != null && oss.toLowerCase().indexOf("linux") > -1) {
                dir = "/data/webpath/";
            } else {
                dir = "d:\\webpath\\";
            }
        }
        OutputStream os = null;
        File f = new File(dir);
        if (!f.exists()) {
            f.mkdirs();
        }
        try {
            os = new FileOutputStream(dir + fileName);
            int bytesRead = 0;
            byte[] buffer = new byte[8192];
            while ((bytesRead = is.read(buffer, 0, 8192)) != -1) {
                os.write(buffer, 0, bytesRead);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (os != null) os.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                if (is != null) is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
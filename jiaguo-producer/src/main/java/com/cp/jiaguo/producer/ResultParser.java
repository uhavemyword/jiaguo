package com.cp.jiaguo.producer;

import com.alibaba.fastjson.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ResultParser {
    private static final Logger log = LoggerFactory.getLogger(ResultParser.class);
    private static final String PATTERN = "id=\"r-props-J-gallery\"><!--(.+)-->";

    public ResultModel parse(String html) {
        Pattern pattern = Pattern.compile(PATTERN);
        Matcher matcher = pattern.matcher(html);
        if (matcher.find()) {
            String jsonString = matcher.group(1);
            ResultModel resultModel = JSON.parseObject(jsonString, ResultModel.class);
            return resultModel;
        }
        return null;
    }
}

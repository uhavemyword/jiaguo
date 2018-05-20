package com.cp.jiaguo.producer;

import com.alibaba.fastjson.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class ResultParser {
    private static final Logger log = LoggerFactory.getLogger(ResultParser.class);
    private static final String REGEX = "id=\"r-props-J-gallery\"><!--(.+)-->";
    private static final Pattern pattern = Pattern.compile(REGEX);

    public ResultModel parse(String html) {
        Matcher matcher = pattern.matcher(html);
        if (matcher.find()) {
            String jsonString = matcher.group(1);
            ResultModel resultModel = JSON.parseObject(jsonString, ResultModel.class);
            resultModel.plainJson = jsonString;
            return resultModel;
        }
        return null;
    }
}

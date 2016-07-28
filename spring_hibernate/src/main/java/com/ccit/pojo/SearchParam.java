package com.ccit.pojo;

import com.ccit.utils.Strings;
import com.google.common.collect.Lists;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;
import java.util.List;

public class SearchParam {
    private String type;
    private String propertyName;
    private Object value;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPropertyName() {
        return propertyName;
    }

    public void setPropertyName(String propertyName) {
        this.propertyName = propertyName;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }


    public static List<SearchParam> getParam(HttpServletRequest request){
        Enumeration enumeration = request.getParameterNames();
        List<SearchParam> paramList = Lists.newArrayList();
        while (enumeration.hasMoreElements()){
            String param = (String) enumeration.nextElement();
            String value = request.getParameter(param);
            if(param.startsWith("q_")&& StringUtils.isNotEmpty(value)){
                String[] params = param.split("_");
                if(params.length != 3){
                    throw new RuntimeException("查询格式错误!");
                }
                String type = params[1];
                String propertyName = params[2];
                SearchParam searchParam = new SearchParam();
                searchParam.setType(type);
                searchParam.setPropertyName(propertyName);
                searchParam.setValue(Strings.toUTF8(value));
                paramList.add(searchParam);
            }
        }
        return paramList;
    }
}

package com.ccit.utils;

import com.google.common.collect.Lists;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;
import java.util.List;

public class QueryParam {
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

    public static List<QueryParam> getParam(HttpServletRequest request){
        Enumeration enumation = request.getParameterNames();
        List<QueryParam> paramList = Lists.newArrayList();
       while(enumation.hasMoreElements()){
            String params = (String) enumation.nextElement();
            Object value = request.getParameter(params);
            if(params.startsWith("q_") && value != null && StringUtils.isNotEmpty(value.toString())){
                String[] param = params.split("_",4);
                String valueType = param[1];
                String type = param[2];
                String propertyName = param[3];

                if("s".equals(valueType)){
                    value = Strings.toUTF8(value.toString());
                }else if("i".equals(valueType)){
                    value = Integer.valueOf(value.toString());
                }else if("f".equals(valueType)){
                    value = Float.valueOf(value.toString());
                }

                request.setAttribute(params,value);

                QueryParam queryParam = new QueryParam();
                queryParam.setValue(value);
                queryParam.setPropertyName(propertyName);
                queryParam.setType(type);
                paramList.add(queryParam);
            }
        }
        return paramList;
    }
}

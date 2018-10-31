package com.java.summary.web.filter.encoding;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.io.UnsupportedEncodingException;
import java.util.Iterator;
import java.util.Map;

/**
 * 自定义HttpServletRequest增强类
 */
public class EncodingRequest extends HttpServletRequestWrapper {

    private HttpServletRequest request;
    private String charset;
    private Map<String, String[]> map = null;

    /**
     * Constructs a request object wrapping the given request.
     *
     * @param request
     * @throws IllegalArgumentException if the request is null
     */
    public EncodingRequest(HttpServletRequest request, String charset) {
        super(request);
        this.charset = charset;
        this.request = request;
    }

    @Override
    public Map<String, String[]> getParameterMap() {

        if (null == this.map) {
            synchronized (EncodingRequest.class) {
                if (null == this.map) {
                    this.map = this.request.getParameterMap();
                    // 遍历map，对每个String[]进行编码
                    Iterator<Map.Entry<String, String[]>> iterable = map.entrySet().iterator();
                    while (iterable.hasNext()) {
                        Map.Entry<String, String[]> entry = iterable.next();
                        String[] values = entry.getValue();
                        for (int i = 0; i < values.length; i++) {
                            try {
                                values[i] = new String(values[i].getBytes("ISO-8859-1"), this.charset);
                            } catch (UnsupportedEncodingException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }
            }
        }
        return this.map;
    }

    @Override
    public String[] getParameterValues(String name) {

        return this.getParameterMap().get(name);
    }

    @Override
    public String getParameter(String name) {

        String[] values = this.getParameterValues(name);
        if (null != values && values.length > 0) {
            return values[0];
        }
        return null;
    }
}

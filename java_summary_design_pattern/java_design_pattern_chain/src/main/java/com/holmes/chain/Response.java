package com.holmes.chain;

/**
 * @Description:    响应信息
 * @Author:         holmes
 * @CreateDate:     2018/12/14 14:28
 * @Version:        1.0.0
*/
public class Response {

    private String content;

    private String remark;

    public Response(String content, String remark) {
        this.content = content;
        this.remark = remark;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Response{");
        sb.append("content='").append(content).append('\'');
        sb.append(", remark='").append(remark).append('\'');
        sb.append('}');
        return sb.toString();
    }
}

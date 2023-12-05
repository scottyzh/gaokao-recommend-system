package com.ruoyi.project.system.mainpage.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.framework.aspectj.lang.annotation.Excel;
import com.ruoyi.framework.web.domain.BaseEntity;

/**
 * 首页对象 mainpage
 * 
 * @author ruoyi
 * @date 2022-03-19
 */
public class Mainpage extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 图片链接 */
    @Excel(name = "图片链接")
    private String imageHref;

    /** 图片id */
    @Excel(name = "图片id")
    private Integer imageId;

    public void setImageHref(String imageHref)
    {
        this.imageHref = imageHref;
    }

    public String getImageHref()
    {
        return imageHref;
    }
    public void setImageId(Integer imageId)
    {
        this.imageId = imageId;
    }

    public Integer getImageId()
    {
        return imageId;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("imageHref", getImageHref())
            .append("imageId", getImageId())
            .toString();
    }
}

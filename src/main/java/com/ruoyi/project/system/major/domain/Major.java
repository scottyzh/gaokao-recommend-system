package com.ruoyi.project.system.major.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.framework.aspectj.lang.annotation.Excel;
import com.ruoyi.framework.web.domain.BaseEntity;

/**
 * 专业查询对象 major
 * 
 * @author ruoyi
 * @date 2022-03-27
 */
public class Major extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 学校名称 */
    @Excel(name = "学校名称")
    private String uniName;

    /** 学校代码 */
    @Excel(name = "学校代码")
    private String uniCode;

    /** 方向 */
    @Excel(name = "方向")
    private String uniDirection;

    /** 专业名称 */
    @Excel(name = "专业名称")
    private String majorName;

    /** 专业代码 */
    @Excel(name = "专业代码")
    private String majorCode;

    /** 首选科目:物理/历史 */
    @Excel(name = "首选科目:物理/历史")
    private String firstRequest;

    /** 次选科目：除物历外的科目。若无要求，则写任选 */
    @Excel(name = "次选科目：除物历外的科目。若无要求，则写任选")
    private String secondRequest;

    /** 最低分数 */
    @Excel(name = "最低分数")
    private Long majorLowScore;

    /** 最低分排名 */
    @Excel(name = "最低分排名")
    private Long majorLowRank;

    public void setUniName(String uniName)
    {
        this.uniName = uniName;
    }

    public String getUniName()
    {
        return uniName;
    }
    public void setUniCode(String uniCode)
    {
        this.uniCode = uniCode;
    }

    public String getUniCode()
    {
        return uniCode;
    }
    public void setUniDirection(String uniDirection)
    {
        this.uniDirection = uniDirection;
    }

    public String getUniDirection()
    {
        return uniDirection;
    }
    public void setMajorName(String majorName)
    {
        this.majorName = majorName;
    }

    public String getMajorName()
    {
        return majorName;
    }
    public void setMajorCode(String majorCode)
    {
        this.majorCode = majorCode;
    }

    public String getMajorCode()
    {
        return majorCode;
    }
    public void setFirstRequest(String firstRequest)
    {
        this.firstRequest = firstRequest;
    }

    public String getFirstRequest()
    {
        return firstRequest;
    }
    public void setSecondRequest(String secondRequest)
    {
        this.secondRequest = secondRequest;
    }

    public String getSecondRequest()
    {
        return secondRequest;
    }
    public void setMajorLowScore(Long majorLowScore)
    {
        this.majorLowScore = majorLowScore;
    }

    public Long getMajorLowScore()
    {
        return majorLowScore;
    }
    public void setMajorLowRank(Long majorLowRank)
    {
        this.majorLowRank = majorLowRank;
    }

    public Long getMajorLowRank()
    {
        return majorLowRank;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("uniName", getUniName())
            .append("uniCode", getUniCode())
            .append("uniDirection", getUniDirection())
            .append("majorName", getMajorName())
            .append("majorCode", getMajorCode())
            .append("firstRequest", getFirstRequest())
            .append("secondRequest", getSecondRequest())
            .append("majorLowScore", getMajorLowScore())
            .append("majorLowRank", getMajorLowRank())
            .toString();
    }
}

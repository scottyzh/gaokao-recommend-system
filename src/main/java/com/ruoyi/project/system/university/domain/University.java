package com.ruoyi.project.system.university.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.framework.aspectj.lang.annotation.Excel;
import com.ruoyi.framework.web.domain.BaseEntity;

/**
 * 院校查询对象 university
 *
 * @author ruoyi
 * @date 2022-04-07
 */
public class University extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 学校名称 */
    @Excel(name = "学校名称")
    private String uniName;

    /** 省份 */
    @Excel(name = "省份")
    private String uniProvince;

    /** 城市 */
    @Excel(name = "城市")
    private String uniCity;

    /** 学校类型 */
    @Excel(name = "学校类型")
    private String uniType;

    /** 学校代码 */
    @Excel(name = "学校代码")
    private String uniCode;

    /** 方向 */
    @Excel(name = "方向")
    private String uniDirection;

    /** 批次 */
    @Excel(name = "批次")
    private String level;

    /** 物理最低分 */
    @Excel(name = "物理最低分")
    private Long physisLowScore;

    /** 历史最低分 */
    @Excel(name = "历史最低分")
    private Long historyLowScore;

    /** 物理最低分排名 */
    @Excel(name = "物理最低分排名")
    private Long physisLowScoreRank;

    /** 历史最低分排名 */
    @Excel(name = "历史最低分排名")
    private Long historyLowScoreRank;

    /** 物理类录取人数 */
    @Excel(name = "物理类录取人数")
    private Long physisMatriculateNumber;

    /** 历史类录取人数 */
    @Excel(name = "历史类录取人数")
    private Long historyMatriculateNumber;

    /** 物理类招生计划 */
    @Excel(name = "物理类招生计划")
    private Long physisTotal;

    /** 历史类招生计划 */
    @Excel(name = "历史类招生计划")
    private Long historyTotal;

    public void setUniName(String uniName)
    {
        this.uniName = uniName;
    }

    public String getUniName()
    {
        return uniName;
    }
    public void setUniProvince(String uniProvince)
    {
        this.uniProvince = uniProvince;
    }

    public String getUniProvince()
    {
        return uniProvince;
    }
    public void setUniCity(String uniCity)
    {
        this.uniCity = uniCity;
    }

    public String getUniCity()
    {
        return uniCity;
    }
    public void setUniType(String uniType)
    {
        this.uniType = uniType;
    }

    public String getUniType()
    {
        return uniType;
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
    public void setLevel(String level)
    {
        this.level = level;
    }

    public String getLevel()
    {
        return level;
    }
    public void setPhysisLowScore(Long physisLowScore)
    {
        this.physisLowScore = physisLowScore;
    }

    public Long getPhysisLowScore()
    {
        return physisLowScore;
    }
    public void setHistoryLowScore(Long historyLowScore)
    {
        this.historyLowScore = historyLowScore;
    }

    public Long getHistoryLowScore()
    {
        return historyLowScore;
    }
    public void setPhysisLowScoreRank(Long physisLowScoreRank)
    {
        this.physisLowScoreRank = physisLowScoreRank;
    }

    public Long getPhysisLowScoreRank()
    {
        return physisLowScoreRank;
    }
    public void setHistoryLowScoreRank(Long historyLowScoreRank)
    {
        this.historyLowScoreRank = historyLowScoreRank;
    }

    public Long getHistoryLowScoreRank()
    {
        return historyLowScoreRank;
    }
    public void setPhysisMatriculateNumber(Long physisMatriculateNumber)
    {
        this.physisMatriculateNumber = physisMatriculateNumber;
    }

    public Long getPhysisMatriculateNumber()
    {
        return physisMatriculateNumber;
    }
    public void setHistoryMatriculateNumber(Long historyMatriculateNumber)
    {
        this.historyMatriculateNumber = historyMatriculateNumber;
    }

    public Long getHistoryMatriculateNumber()
    {
        return historyMatriculateNumber;
    }
    public void setPhysisTotal(Long physisTotal)
    {
        this.physisTotal = physisTotal;
    }

    public Long getPhysisTotal()
    {
        return physisTotal;
    }
    public void setHistoryTotal(Long historyTotal)
    {
        this.historyTotal = historyTotal;
    }

    public Long getHistoryTotal()
    {
        return historyTotal;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
                .append("uniName", getUniName())
                .append("uniProvince", getUniProvince())
                .append("uniCity", getUniCity())
                .append("uniType", getUniType())
                .append("uniCode", getUniCode())
                .append("uniDirection", getUniDirection())
                .append("level", getLevel())
                .append("physisLowScore", getPhysisLowScore())
                .append("historyLowScore", getHistoryLowScore())
                .append("physisLowScoreRank", getPhysisLowScoreRank())
                .append("historyLowScoreRank", getHistoryLowScoreRank())
                .append("physisMatriculateNumber", getPhysisMatriculateNumber())
                .append("historyMatriculateNumber", getHistoryMatriculateNumber())
                .append("physisTotal", getPhysisTotal())
                .append("historyTotal", getHistoryTotal())
                .toString();
    }
}
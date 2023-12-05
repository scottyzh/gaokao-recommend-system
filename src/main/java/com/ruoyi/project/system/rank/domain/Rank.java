package com.ruoyi.project.system.rank.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.framework.aspectj.lang.annotation.Excel;
import com.ruoyi.framework.web.domain.BaseEntity;

/**
 * 排位查询对象 rank
 * 
 * @author ruoyi
 * @date 2022-04-01
 */
public class Rank extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 物理分数 */
    @Excel(name = "物理分数")
    private Long physisScore;

    /** 物理排位 */
    @Excel(name = "物理排位")
    private Long physisRank;

    /** 历史分数 */
    @Excel(name = "历史分数")
    private Long historyScore;

    /** 历史排位 */
    @Excel(name = "历史排位")
    private Long historyRank;

    public void setPhysisScore(Long physisScore)
    {
        this.physisScore = physisScore;
    }

    public Long getPhysisScore()
    {
        return physisScore;
    }
    public void setPhysisRank(Long physisRank)
    {
        this.physisRank = physisRank;
    }

    public Long getPhysisRank()
    {
        return physisRank;
    }
    public void setHistoryScore(Long historyScore)
    {
        this.historyScore = historyScore;
    }

    public Long getHistoryScore()
    {
        return historyScore;
    }
    public void setHistoryRank(Long historyRank)
    {
        this.historyRank = historyRank;
    }

    public Long getHistoryRank()
    {
        return historyRank;
    }
    public Rank(Long physisScore,Long historyScore) {
        this.physisScore = physisScore;
        this.historyScore = historyScore;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("physisScore", getPhysisScore())
            .append("physisRank", getPhysisRank())
            .append("historyScore", getHistoryScore())
            .append("historyRank", getHistoryRank())
            .toString();
    }
}

package com.ruoyi.project.system.score.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.framework.aspectj.lang.annotation.Excel;
import com.ruoyi.framework.web.domain.BaseEntity;

/**
 * 志愿推荐对象 score
 *
 * @author ruoyi
 * @date 2022-03-28
 */
public class Score extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 报考风险
     */
    @Excel(name = "报考风险")
    private String riskRate;

    private double index;

    public double getIndex() {
        return index;
    }

    public void setIndex(double index) {
        this.index = index;
    }

    /**
     * 学校名称
     */
    @Excel(name = "学校名称")
    private String uniName;

    /**
     * 方向
     */
    @Excel(name = "方向")
    private String uniDirection;

    /**
     * 学校代码
     */
    private String uniCode;

    /**
     * 省份
     */
    @Excel(name = "省份")
    private String uniProvince;

    /**
     * 城市
     */
    @Excel(name = "城市")
    private String uniCity;

    /**
     * 学校类型
     */
    @Excel(name = "学校类型")
    private String uniType;

    /**
     * 批次
     */
    @Excel(name = "批次")
    private String level;

    /**
     * 首选科目
     */
    @Excel(name = "首选科目")
    private String firstRequest;

    /**
     * 最低投档线
     */
    @Excel(name = "最低投档线")
    private Long lowestScore;

    /**
     * 最低分排名
     */
    @Excel(name = "最低分排名")
    private Long lowestRank;

    /**
     * 录取人数
     */
    @Excel(name = "录取人数")
    private Long matriculateNumber;


    public void setRiskRate(String riskRate) {
        this.riskRate = riskRate;
    }

    public String getRiskRate() {
        return riskRate;
    }

    public void setUniName(String uniName) {
        this.uniName = uniName;
    }

    public String getUniName() {
        return uniName;
    }

    public void setUniDirection(String uniDirection) {
        this.uniDirection = uniDirection;
    }

    public String getUniDirection() {
        return uniDirection;
    }

    public void setUniCode(String uniCode) {
        this.uniCode = uniCode;
    }

    public String getUniCode() {
        return uniCode;
    }

    public void setUniProvince(String uniProvince) {
        this.uniProvince = uniProvince;
    }

    public String getUniProvince() {
        return uniProvince;
    }

    public void setUniCity(String uniCity) {
        this.uniCity = uniCity;
    }

    public String getUniCity() {
        return uniCity;
    }

    public void setUniType(String uniType) {
        this.uniType = uniType;
    }

    public String getUniType() {
        return uniType;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getLevel() {
        return level;
    }

    public void setFirstRequest(String firstRequest) {
        this.firstRequest = firstRequest;
    }

    public String getFirstRequest() {
        return firstRequest;
    }

    public void setLowestScore(Long lowestScore) {
        this.lowestScore = lowestScore;
    }

    public Long getLowestScore() {
        return lowestScore;
    }

    public void setLowestRank(Long lowestRank) {
        this.lowestRank = lowestRank;
    }

    public Long getLowestRank() {
        return lowestRank;
    }

    public void setMatriculateNumber(Long matriculateNumber) {
        this.matriculateNumber = matriculateNumber;
    }

    public Long getMatriculateNumber() {
        return matriculateNumber;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("riskRate", getRiskRate())
                .append("index", getIndex())
                .append("uniName", getUniName())
                .append("uniDirection", getUniDirection())
                .append("uniCode", getUniCode())
                .append("uniProvince", getUniProvince())
                .append("uniCity", getUniCity())
                .append("uniType", getUniType())
                .append("level", getLevel())
                .append("firstRequest", getFirstRequest())
                .append("lowestScore", getLowestScore())
                .append("lowestRank", getLowestRank())
                .append("matriculateNumber", getMatriculateNumber())
                .toString();
    }
}

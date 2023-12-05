package com.ruoyi.project.system.score.controller;

import com.github.pagehelper.PageInfo;
import com.ruoyi.common.utils.PageByList;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.aspectj.lang.annotation.Log;
import com.ruoyi.framework.aspectj.lang.enums.BusinessType;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.framework.web.page.PageDomain;
import com.ruoyi.framework.web.page.TableDataInfo;
import com.ruoyi.framework.web.page.TableSupport;
import com.ruoyi.project.system.rank.domain.Rank;
import com.ruoyi.project.system.rank.service.IRankService;
import com.ruoyi.project.system.score.domain.Score;
import com.ruoyi.project.system.score.service.IScoreService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 志愿推荐Controller
 * 
 * @author ruoyi
 * @date 2022-03-28
 */
@Controller
@RequestMapping("/system/score")
public class ScoreController extends BaseController
{
    private String prefix = "system/score";

    @Autowired
    private IScoreService scoreService;
    @Autowired
    private IRankService rankService;

    @RequiresPermissions("system:score:view")
    @GetMapping()
    public String score()
    {
        return prefix + "/score";
    }

    /**
     * 查询志愿推荐列表
     */
    @RequiresPermissions("system:score:list")
    @RequestMapping("/list")
    @ResponseBody
    public TableDataInfo list(Score score,HttpServletRequest request)
    {
        List<Score> list = null;
        if(score.getLowestScore() != null && score.getLowestRank() != null  && !"".equals(score.getFirstRequest()) && ("物理".equals(score.getFirstRequest()) || "历史".equals(score.getFirstRequest()))){
            list = scoreService.selectScoreList(score,score.getFirstRequest());
        }else{
            throw new RuntimeException("请在上方输入分数与首选科目！");
        }
        PageDomain pageDomain = TableSupport.buildPageRequest();
        Integer pageNum = pageDomain.getPageNum();
        Integer pageSize = pageDomain.getPageSize();
        TableDataInfo rspData = new TableDataInfo();
        rspData.setCode(0);
        rspData.setRows(PageByList.startPage(list, pageNum, pageSize));
        rspData.setTotal(new PageInfo(list).getTotal());
        return rspData;
    }

    /**
     * 开始填报去查询志愿推荐列表
     */
    @GetMapping("/toScore")
    public String toScorePage(@RequestParam("score")String score,
                              @RequestParam("subject")String subject,
                              @RequestParam("ranking")String ranking,
                              HttpServletRequest request){
        Rank rank = null;
        if("物理".equals(subject)){
             rank = rankService.selectPhysisRankByPhysisScore(Long.parseLong(score));
             ranking = rank.getPhysisRank().toString();
        }else{
             rank = rankService.selectHistoryRankByHistoryScore(Long.parseLong(score));
             ranking = rank.getHistoryRank().toString();
        }
        request.getSession().setAttribute("score",score);
        request.getSession().setAttribute("subject",subject);
        request.getSession().setAttribute("ranking",ranking);
        System.out.println(score);
        System.out.println(subject);
        System.out.println(ranking);
        return  prefix+"/score";
    }



    @RequestMapping("/getScoreAndSubject")
    @RequiresPermissions("system:score:getScoreAndSubject")
    @ResponseBody
    public void getScoreAndSubject(Long score ,String subject) {
        int i = 0;
    }
    /**
     * 导出志愿推荐列表
     */
    @RequiresPermissions("system:score:export")
    @Log(title = "志愿推荐", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(Score score)
    {
        List<Score> list = scoreService.selectScoreList(score,"");
        ExcelUtil<Score> util = new ExcelUtil<Score>(Score.class);
        return util.exportExcel(list, "志愿推荐数据");
    }

    /**
     * 新增志愿推荐
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存志愿推荐
     */
    @RequiresPermissions("system:score:add")
    @Log(title = "志愿推荐", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(Score score)
    {
        return toAjax(scoreService.insertScore(score));
    }

    /**
     * 修改志愿推荐
     */
    @RequiresPermissions("system:score:edit")
    @GetMapping("/edit/{uniCode}")
    public String edit(@PathVariable("uniCode") String uniCode, ModelMap mmap)
    {
        Score score = scoreService.selectScoreByUniCode(uniCode);
        mmap.put("score", score);
        return prefix + "/edit";
    }

    /**
     * 修改保存志愿推荐
     */
    @RequiresPermissions("system:score:edit")
    @Log(title = "志愿推荐", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(Score score)
    {
        return toAjax(scoreService.updateScore(score));
    }

    /**
     * 删除志愿推荐
     */
    @RequiresPermissions("system:score:remove")
    @Log(title = "志愿推荐", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(scoreService.deleteScoreByUniCodes(ids));
    }

}


package com.ruoyi.project.system.rank.controller;

import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.aspectj.lang.annotation.Log;
import com.ruoyi.framework.aspectj.lang.enums.BusinessType;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.framework.web.page.TableDataInfo;
import com.ruoyi.project.system.rank.domain.Rank;
import com.ruoyi.project.system.rank.service.IRankService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 排位查询Controller
 * 
 * @author ruoyi
 * @date 2022-04-01
 */
@Controller
@RequestMapping("/system/rank")
public class RankController extends BaseController
{
    private String prefix = "system/rank";

    @Autowired
    private IRankService rankService;

    @RequiresPermissions("system:rank:view")
    @GetMapping()
    public String rank()
    {
        return prefix + "/rank";
    }

    /**
     * 查询排位查询列表
     */
    @RequiresPermissions("system:rank:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(Rank rank)
    {
        startPage();
        List<Rank> list = rankService.selectRankList(rank);
        return getDataTable(list);
    }

    /**
     * 导出排位查询列表
     */
    @RequiresPermissions("system:rank:export")
    @Log(title = "排位查询", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(Rank rank)
    {
        List<Rank> list = rankService.selectRankList(rank);
        ExcelUtil<Rank> util = new ExcelUtil<Rank>(Rank.class);
        return util.exportExcel(list, "排位查询数据");
    }

    /**
     * 新增排位查询
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存排位查询
     */
    @RequiresPermissions("system:rank:add")
    @Log(title = "排位查询", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(Rank rank)
    {
        return toAjax(rankService.insertRank(rank));
    }

    /**
     * 修改排位查询
     */
    @RequiresPermissions("system:rank:edit")
    @GetMapping("/edit/{physisScore}")
    public String edit(@PathVariable("physisScore") Long physisScore, ModelMap mmap)
    {
        Rank rank = rankService.selectRankByPhysisScore(physisScore);
        mmap.put("rank", rank);
        return prefix + "/edit";
    }

    /**
     * 修改保存排位查询
     */
    @RequiresPermissions("system:rank:edit")
    @Log(title = "排位查询", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(Rank rank)
    {
        return toAjax(rankService.updateRank(rank));
    }

    /**
     * 删除排位查询
     */
    @RequiresPermissions("system:rank:remove")
    @Log(title = "排位查询", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(rankService.deleteRankByPhysisScores(ids));
    }


    /**
     * 首页输入分数，自动获得排名
     */
    @GetMapping("/getRank")
    @ResponseBody
    public List<Rank> getRank(@RequestParam("score") Long score,@RequestParam("subject") String subject)
    {
        Long physisScore = score;
        Long historyScore = score;
        startPage();
        System.out.println(subject);
        if("物理".equals(subject)){
            Rank rank = new Rank(physisScore,null);
            System.out.println("============>"+rank);
            List<Rank> result = rankService.selectRankList(rank);
//            List<Rank> result = Collections.singletonList(rankService.selectPhysisRankByPhysisScore(physisScore));
            System.out.println("============>"+result);
            return result;
        }else{
            Rank rank = new Rank(null,historyScore);
            System.out.println("============>"+rank);
            List<Rank> result = rankService.selectRankList(rank);
//            List<Rank> result = Collections.singletonList(rankService.selectHistoryRankByHistoryScore(historyScore));
            System.out.println("============>"+result);
            return result;
        }
    }
}

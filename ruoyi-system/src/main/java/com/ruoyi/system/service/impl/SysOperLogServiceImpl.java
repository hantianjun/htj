package com.ruoyi.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.domain.SysOperLog;
import com.ruoyi.system.mapper.SysOperLogMapper;
import com.ruoyi.system.service.ISysOperLogService;

/**
 * 操作日志 服务层处理
 * 
 * @author ruoyi
 */
@Service
public class SysOperLogServiceImpl implements ISysOperLogService
{
    @Autowired
    private SysOperLogMapper operLogMapper;

    /**
     * 新增操作日志
     * 
     * @param operLog 操作日志对象
     */
    @Override
    public void insertOperlog(SysOperLog operLog)
    {
        operLogMapper.insertOperlog(operLog);
    }

    /**
     * 查询系统操作日志集合
     * 
     * @param operLog 操作日志对象
     * @return 操作日志集合
     */
    @Override
    public List<SysOperLog> selectOperLogList(SysOperLog operLog)
    {
        return operLogMapper.selectOperLogList(operLog);
    }

    /**
     * 批量删除系统操作日志
     * 
     * @param operIds 需要删除的操作日志ID
     * @return 结果
     */
    @Override
    public int deleteOperLogByIds(Long[] operIds)
    {
        return operLogMapper.deleteOperLogByIds(operIds);
    }

    /**
     * 查询操作日志详细
     * 
     * @param operId 操作ID
     * @return 操作日志对象
     */
    @Override
    public SysOperLog selectOperLogById(Long operId)
    {
        return operLogMapper.selectOperLogById(operId);
    }

    /**
     * 清空操作日志
     */
    @Override
    public void cleanOperLog()
    {
        operLogMapper.cleanOperLog();
    }

    /**
     * 导入操作日志
     *
     * @param operLogList 操作日志列表
     * @param operName 操作人
     * @return 结果消息
     */
    @Override
    public String importOperLog(List<SysOperLog> operLogList, String operName)
    {
        int successNum = 0;
        int failureNum = 0;
        StringBuilder successMsg = new StringBuilder();
        StringBuilder failureMsg = new StringBuilder();
        for (SysOperLog operLog : operLogList)
        {
            try
            {
                operLog.setOperName(operName);
                this.insertOperlog(operLog);
                successNum++;
            }
            catch (Exception e)
            {
                failureNum++;
                String msg = "<br/>" + failureNum + "、日志 " + operLog.getTitle() + " 导入失败：" + e.getMessage();
                failureMsg.append(msg);
            }
        }
        if (failureNum > 0)
        {
            failureMsg.insert(0, "共成功导入 " + successNum + " 条，失败 " + failureNum + " 条，失败数据如下：");
            return failureMsg.toString();
        }
        else
        {
            successMsg.insert(0, "恭喜您，数据已全部导入成功！共 " + successNum + " 条");
            return successMsg.toString();
        }
    }
}

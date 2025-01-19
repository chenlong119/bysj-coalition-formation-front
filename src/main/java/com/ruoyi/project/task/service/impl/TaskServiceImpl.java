package com.ruoyi.project.task.service.impl;

import java.util.*;

import com.ruoyi.project.company.domain.Company;
import com.ruoyi.project.company.domain.CompanyResource;
import com.ruoyi.project.company.domain.Resource;
import com.ruoyi.project.company.mapper.CompanyMapper;
import com.ruoyi.project.company.mapper.CompanyResourceMapper;
import com.ruoyi.project.company.mapper.ResourceMapper;
import com.ruoyi.project.task.domain.*;
import io.swagger.models.auth.In;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.project.task.mapper.TaskMapper;
import com.ruoyi.project.task.service.ITaskService;
import org.springframework.transaction.annotation.Transactional;
import com.ruoyi.project.task.mapper.CoalitionMapper;

/**
 * taskService业务层处理
 * 
 * @author ruoyi
 * @date 2025-01-15
 */
@Slf4j
@Service
public class TaskServiceImpl implements ITaskService {
    @Autowired
    private TaskMapper taskMapper;

    @Autowired
    private CompanyMapper companyMapper;

    @Autowired
    private ResourceMapper resourceMapper;

    @Autowired
    private CompanyResourceMapper companyResourceMapper;

    @Autowired
    private CoalitionMapper coalitionMapper;
    /**
     * 查询task
     * 
     * @param id task主键
     * @return task
     */
    @Override
    public Task selectTaskById(Long id) {
        return taskMapper.selectTaskById(id);
    }

    /**
     * 查询task列表
     * 
     * @param task task
     * @return task
     */
    @Override
    public List<Task> selectTaskList(Task task) {
        return taskMapper.selectTaskList(task);
    }

    /**
     * 新增task
     * 
     * @param task task
     * @return 结果
     */
    @Override
    public int insertTask(Task task) {
        return taskMapper.insertTask(task);
    }

    /**
     * 修改task
     * 
     * @param task task
     * @return 结果
     */
    @Override
    public int updateTask(Task task) {
        return taskMapper.updateTask(task);
    }

    /**
     * 批量删除task
     * 
     * @param ids 需要删除的task主键
     * @return 结果
     */
    @Override
    public int deleteTaskByIds(Long[] ids) {
        return taskMapper.deleteTaskByIds(ids);
    }

    /**
     * 删除task信息
     * 
     * @param id task主键
     * @return 结果
     */
    @Override
    public int deleteTaskById(Long id) {
        return taskMapper.deleteTaskById(id);
    }

    /**
     * 获取异常任务所属企业信息
     * 
     * @param companyId 企业ID
     * @return 企业信息
     */
    @Override
    public Company selectCompanyByTaskAbnormal(Long companyId) {
        return companyMapper.selectCompanyById(companyId);
    }

    @Override
    public List<TaskResource> selectResourcesByTaskId(Long taskId) {
        List<TaskResource> taskResources = taskMapper.selectResourcesByTaskId(taskId);
//        填充资源信息
        taskResources.forEach(taskResource -> {
            taskResource.setResource(resourceMapper.selectById(taskResource.getResourceId()));
        });
        return taskResources;
    }

    @Override
    public int insertTaskResource(TaskResource taskResource) {
        return taskMapper.insertTaskResource(taskResource);
    }

    @Override
    public int updateTaskResource(TaskResource taskResource) {
        return taskMapper.updateTaskResource(taskResource);
    }

    @Override
    public int deleteTaskResource(Long taskId, Long resourceId) {
        return taskMapper.deleteTaskResource(taskId, resourceId);
    }
    public  Map<Long,Integer> getTaskResourceInfo(Long taskId){
        List<TaskResource> taskResources = taskMapper.selectResourcesByTaskId(taskId);
        Map<Long,Integer> resourceInfo = new HashMap<>();
        for(TaskResource taskResource:taskResources){
            resourceInfo.put(taskResource.getResourceId(),taskResource.getNumber());
        }
        return resourceInfo;
    }

    public List<Resource> getAllResource(){
        return resourceMapper.selectResourceList("");
    }

    public Map<Long,Double> calculateImpWeights(Map<Long,Integer> taskResourceInfo,List<Resource> allResource){
        Map<Long,Double> imp = new HashMap<>();
        for(Resource resource:allResource){
            Integer taskResourceNumber=0;
            if(taskResourceInfo.get(resource.getId())!=null){
              taskResourceNumber=taskResourceInfo.get(resource.getId());
            }
            long resourceSum = companyResourceMapper.getResourceSum(resource.getId());
            if(resourceSum<taskResourceNumber)
                return null;
            long companyCount = companyResourceMapper.getCompanyCount(resource.getId());
            double avg_count=Math.ceil((double)resourceSum/companyCount);
            imp.put(resource.getId(),taskResourceNumber/avg_count);
        }
        Double sum_imp=0.0;
        for(Long key:imp.keySet()){
            sum_imp+=imp.get(key);
        }
        Map<Long,Double> imp_weight=new HashMap<>();
        for(Long key:imp.keySet()){
            imp_weight.put(key,imp.get(key)/sum_imp);
        }
        return imp_weight;
    }

    public List<Company> getAllCompany(){
        return companyMapper.getAllCompany();
    }

    public boolean isSatisfy(Map<Long,Integer> taskResourceInfo,Set<Long> ca,List<Resource> allResource,Map<Long,Map<Long,Integer>> allCompanyResourceInfo)
    {
        for(Resource r: allResource)
        {
            Integer taskResourceNumber = taskResourceInfo.get(r.getId());
            if(taskResourceNumber==null)
                taskResourceNumber=0;
            Integer sum=0;
            for(Long c: ca)
            {
                sum+=allCompanyResourceInfo.get(c).get(r.getId());
            }
            if(sum<taskResourceNumber)
                return false;
        }
        return true;
    }

    public Integer calcAssistTransportCost(Set<Long> ca, Long cid, Map<Long,Map<Long,Integer>> allTransportCosts)
    {
        Integer sum=0;
        for(Long c:ca)
        {
            if(c>cid)
            {
                Long temp=c;
                c=cid;
                cid=temp;
            }
            sum+=allTransportCosts.get(c).get(cid);
        }
        return sum;
    }

    public  Long calcTransportCost(Set<Long> ca,Map<Long,Map<Long,Integer>> allTransportCosts){
        Long sum=0L;
        for(Long c1: ca)
        {
            for(Long c2: ca)
            {
                if(c1<c2)
                {
                    sum+=allTransportCosts.get(c1).get(c2);
                }
            }
        }
        sum/=2;
        return sum;
    }

    public Map<Long, Map<Long, Integer>> getAllCompanyResourceInfo(List<Company> allCompany, List<Resource> allResource) {
        Map<Long, Map<Long, Integer>> companyResourceInfo = new HashMap<>();
        
        // 初始化结果map，所有资源数量默认为0
        for (Company company : allCompany) {
            Map<Long, Integer> resourceInfo = new HashMap<>();
            for (Resource resource : allResource) {
                resourceInfo.put(resource.getId(), 0);
            }
            companyResourceInfo.put(company.getId(), resourceInfo);
        }
        
        // 批量查询所有公司的所有资源
        List<CompanyResource> allCompanyResources = companyResourceMapper.selectBatchCompanyResources();
        
        // 填充实际的资源数量
        for (CompanyResource cr : allCompanyResources) {
            if (companyResourceInfo.containsKey(cr.getCompanyId())) {
                companyResourceInfo.get(cr.getCompanyId()).put(cr.getResourceId(), cr.getNumber());
            }
        }
        
        return companyResourceInfo;
    }

    public Map<Long,Map<Long,Integer>> allocateResource( Map<Long, Integer> taskResourceInfo,Set<Long> ca,Map<Long,Map<Long,Integer>> allCompanyResourceInfo){
        Map<Long,Map<Long,Integer>> resourceAllocation = new HashMap<>();
        for(Long rid: taskResourceInfo.keySet()){
            Integer resourceNumNeed = taskResourceInfo.get(rid);
            Integer resourceHaving=0;
            for(Long cid: ca)
            {
                resourceHaving+=allCompanyResourceInfo.get(cid).get(rid);
            }
            for(Long cid: ca)
            {
                resourceAllocation.putIfAbsent(cid,new HashMap<>());
                double contributionCurrentCompany=Math.ceil(allCompanyResourceInfo.get(cid).get(rid)/(double)resourceHaving*resourceNumNeed);
                resourceAllocation.get(cid).put(rid,(int)contributionCurrentCompany);
            }
        }
        return resourceAllocation;
    }


    public Map<Long,Map<Long,Integer>> getAllTransportCosts() {
        Map<Long,Map<Long,Integer>> res=new HashMap<>();
        List<TransPortCost> allTransportCosts = companyMapper.getAllTransportCosts();
        for(TransPortCost tc: allTransportCosts)
        {
            res.putIfAbsent(tc.getFromCompanyId(),new HashMap<>());
            res.get(tc.getFromCompanyId()).put(tc.getToCompanyId(),tc.getCost());
        }
        return res;
    }

    public CoalitionFormationResults RFCF(Long taskId){
        Map<Long, Integer> taskResourceInfo = getTaskResourceInfo(taskId);
        List<Resource> allResource = getAllResource();
        Map<Long, Double> impWeights = calculateImpWeights(taskResourceInfo,allResource);
        if(impWeights==null)
            return null;
        Map<Long, Map<Long, Integer>> allTransportCosts = getAllTransportCosts();
        CoalitionFormationResults res=new CoalitionFormationResults();
        Set<Long> ca=new HashSet<>();
        Long max_ci=-1L;
        double max_rf=0.0;
        List<Company> allCompany = getAllCompany();
        Map<Long,Double> companyResourceFitness=new HashMap<>();
        Map<Long, Map<Long, Integer>> allCompanyResourceInfo = getAllCompanyResourceInfo(allCompany, allResource);
        for(Company c: allCompany)
        {
            double rf=0;
            for(Resource r: allResource)
            {
                rf+=impWeights.get(r.getId())*allCompanyResourceInfo.get(c.getId()).get(r.getId());
            }
            companyResourceFitness.put(c.getId(),rf);
            if(rf>max_rf)
            {
                max_rf=rf;
                max_ci=c.getId();
            }
        }
        ca.add(max_ci);
        while(!isSatisfy(taskResourceInfo,ca,allResource,allCompanyResourceInfo))
        {
            double max_assist_rf=0.0;
            long max_assist_rf_ci=-1L;
            for(Company c: allCompany)
            {
                if(!ca.contains(c.getId())) {
                    Integer calcTransportCost = calcAssistTransportCost(ca, c.getId(),allTransportCosts);
                    double assist_rf = companyResourceFitness.get(c.getId())/calcTransportCost;
                    if(assist_rf>max_assist_rf)
                    {
                        max_assist_rf=assist_rf;
                        max_assist_rf_ci=c.getId();
                    }
                }
            }
            ca.add(max_assist_rf_ci);
        }
        Map<Long, Map<Long, Integer>> allocatedResource = allocateResource(taskResourceInfo, ca, allCompanyResourceInfo);
        Long transportCost = calcTransportCost(ca,allTransportCosts);
        res.setCoalition(ca);
        res.setTransportCost(transportCost);
        res.setResourceAllocation(allocatedResource);
        res.setTaskResourceDemand(taskResourceInfo);
        return res;
    }

    @Override
    public CoalitionFormationResults coalitionFormation(Long taskId) {
        return RFCF(taskId);
    }

    @Transactional
    public int executeCoalitionAllocation(CoalitionExecuteParams params) {
        try {
            // 1. 插入联盟记录
            Coalition coalitionEntity = new Coalition();
            coalitionEntity.setTaskId(params.getTaskId());
            coalitionEntity.setName("Task_" + params.getTaskId() + "_Coalition");
            coalitionEntity.setCreateTime(new Date());
            coalitionEntity.setCoalitionStatus(0);
            coalitionEntity.setTransportCost(params.getTransportCost());
            coalitionEntity.setCoalitionReputation(params.getCoalitionReputation());
            coalitionMapper.insertCoalition(coalitionEntity);
            Long coalitionId = coalitionEntity.getId();
            coalitionMapper.insertCoalitionCompany(params.getCoalition(), coalitionId);
            // 2. 更新企业资源
            for (Map.Entry<Long, Map<Long, Integer>> entry : params.getResourceAllocation().entrySet()) {
                Long companyId = entry.getKey();
                Map<Long, Integer> resources = entry.getValue();
                
                for (Map.Entry<Long, Integer> resource : resources.entrySet()) {
                    Long resourceId = resource.getKey();
                    Integer amount = resource.getValue();
                    if(amount>0)
                    // 扣减企业资源
                    companyResourceMapper.updateResourceAmount(companyId, resourceId, -amount);
                }
                Company companyById = companyMapper.selectCompanyById(companyId);
                companyById.setStatus(1L);
                double new_rep=companyById.getReputation()+Math.random();
                new_rep=new_rep>5?5:new_rep;
                companyById.setReputation(new_rep);
                // 更新企业状态为已分配任务
                companyMapper.updateCompany(companyById);
            }
            
            // 3. 更新任务状态为已分配未执行
            Task task = new Task();
            task.setId(params.getTaskId());
            task.setTaskStatus(1L);
            taskMapper.updateTask(task);
            
            return 1;
        } catch (Exception e) {
            throw new RuntimeException("执行联盟资源分配失败", e);
        }
    }

    @Override
    public Map<String, Object> getCoalitionDetails(Long taskId) {
        Map<String, Object> result = new HashMap<>();
        
        // 获取任务对应的联盟信息
        Coalition coalition = coalitionMapper.selectCoalitionByTaskId(taskId);
        if (coalition == null) {
            return null;
        }
        
        // 获取联盟内的企业信息
        List<Company> companies = coalitionMapper.selectCoalitionCompanies(coalition.getId());
        
        result.put("coalition", coalition);
        result.put("companies", companies);
        
        return result;
    }
}

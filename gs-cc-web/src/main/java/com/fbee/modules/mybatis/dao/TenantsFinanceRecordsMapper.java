package com.fbee.modules.mybatis.dao;

import java.util.List;
import java.util.Map;

import com.fbee.modules.core.persistence.CrudDao;
import com.fbee.modules.core.persistence.annotation.MyBatisDao;
import com.fbee.modules.mybatis.entity.TenantsFinanceRecordsEntity;
import com.fbee.modules.mybatis.entity.TenantsTradeRecordsEntity;
import com.fbee.modules.mybatis.model.TenantsFinanceRecords;

@MyBatisDao
public interface TenantsFinanceRecordsMapper extends CrudDao<TenantsFinanceRecordsEntity> {


    /**
     * 根据租户id查询财务总收入信息
     *
     * @param id
     * @return
     */
    public String getByTenantsIncomeId(int id);

    /**
     * 根据id返回流水账总条数
     *
     * @param id
     * @return
     */
    public int getCountById(TenantsFinanceRecordsEntity tenantsFinanceRecordsEntity);

    /**
     * 根据租户id查询财务总支出信息
     *
     * @param id
     * @return
     */
    public String getByTenantsExpenseId(int id);

    /**
     * 根据租户id查询财务流水信息
     *
     * @param tenantsFinanceRecordsEntity
     * @return
     */
    public List<Map> getByTenantsId(TenantsFinanceRecordsEntity tenantsFinanceRecordsEntity);

    /**
     * @param staffId
     * @return
     */
    List<TenantsFinanceRecordsEntity> getStaffFinances(Integer staffId);


    int updateByPrimaryKeySelective(TenantsFinanceRecords record);

    TenantsFinanceRecords selectByPrimaryKey(String relatedTrans);


    /** 总控清算总数   优化   @zsq 2017-07-01*/
    int getClearCount(Map<Object, Object> map);

    /**总控清算列表  优化  @zsq 2017-07-01*/
    List<Map> getClearList(Map<Object, Object> map);

    /**总控财务流水优化 @zsq 2017-07-03*/
    int getCount(TenantsFinanceRecords tenantsFinanceRecords);

    /**总控财务流水优化 @zsq 2017-07-03*/
    List<Map> getTenantsRecordList(TenantsFinanceRecords tenantsFinanceRecords);

    /** 查询 relatedTrans 关联的记录：手续费 */
    TenantsFinanceRecordsEntity selectByRelatedTrans(String relatedTrans);
}
package com.myoa.oa.biz.Impl;

import com.myoa.oa.biz.ClaimVoucherBiz;
import com.myoa.oa.dao.ClaimVoucherDao;
import com.myoa.oa.dao.ClaimVoucherItemDao;
import com.myoa.oa.dao.DealRecordDao;
import com.myoa.oa.dao.EmployeeDao;
import com.myoa.oa.entity.ClaimVoucher;
import com.myoa.oa.entity.ClaimVoucherItem;
import com.myoa.oa.entity.DealRecord;
import com.myoa.oa.global.Contant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service("cliamVoucherBiz")
public class ClaimVoucherBizImpl implements ClaimVoucherBiz {
    @Autowired
    @Qualifier("claimVoucherDao")
    private ClaimVoucherDao claimVoucherDao;
    @Autowired
    @Qualifier("claimVoucherItemDao")
    private ClaimVoucherItemDao claimVoucherItemDao;
    @Autowired
    @Qualifier("dealRecordDao")
    private DealRecordDao dealRecordDao;
    @Autowired
    @Qualifier("employeeDao")
    private EmployeeDao employeeDao;

    /**
     * 由于是在业务层Service，每个方法都会被封装为一个事务，具有原子性
     * @param claimVoucher
     * @param items
     */
    public void save(ClaimVoucher claimVoucher, List<ClaimVoucherItem> items) {
        claimVoucher.setCreateTime(new Date());
        //创建完后，自己修改或者提交，所以填自己
        claimVoucher.setNextDealSn(claimVoucher.getCreateSn());
        claimVoucher.setStatus(Contant.CLAIMVOUCHER_CREATED);
        claimVoucherDao.insert(claimVoucher);

        //报销项目的 其他属性都是在页面上获取的
        for(ClaimVoucherItem item:items){
            item.setClaimVoucherId(claimVoucher.getId());
            claimVoucherItemDao.insert(item);
        }
    }

    public ClaimVoucher get(int id) {
        return claimVoucherDao.select(id);
    }

    public List<ClaimVoucherItem> getItems(int cvid) {
        return claimVoucherItemDao.selectByClaimVoucher(cvid);
    }

    public List<DealRecord> getRecords(int cvid) {
        return dealRecordDao.selectByClaimVoucher(cvid);
    }

    public List<ClaimVoucher> getForSelf(String sn) {
        return null;
    }

    public List<ClaimVoucher> getForDeal(String sn) {
        return null;
    }

    public void update(ClaimVoucher claimVoucher, List<ClaimVoucherItem> items) {

    }

    public void submit(int id) {

    }

    public void deal(DealRecord dealRecord) {

    }
}

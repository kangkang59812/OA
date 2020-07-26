package com.myoa.oa.dao;

import com.myoa.oa.entity.ClaimVoucher;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("claimVoucherDao")
public interface ClaimVoucherDao {
    void insert(ClaimVoucher claimVoucher);
    void update(ClaimVoucher claimVoucher);
    void delete(int id);
    ClaimVoucher select(int id);
    //报销单编号
    List<ClaimVoucher> selectByCreateSn(String csn);
    //待处理人编号
    List<ClaimVoucher> selectByNextDealSn(String ndsn);
}

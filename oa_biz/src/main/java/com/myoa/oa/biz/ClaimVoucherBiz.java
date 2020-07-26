package com.myoa.oa.biz;

import com.myoa.oa.entity.ClaimVoucher;
import com.myoa.oa.entity.ClaimVoucherItem;
import com.myoa.oa.entity.DealRecord;

import java.util.List;

public interface ClaimVoucherBiz {
    void save(ClaimVoucher claimVoucher, List<ClaimVoucherItem> items);

    ClaimVoucher get(int id);
    //报销条目
    List<ClaimVoucherItem> getItems(int cvid);
    //处理记录
    List<DealRecord> getRecords(int cvid);

    List<ClaimVoucher> getForSelf(String sn);
    List<ClaimVoucher> getForDeal(String sn);

    void update(ClaimVoucher claimVoucher, List<ClaimVoucherItem> items);
    void submit(int id);
    void deal(DealRecord dealRecord);
}

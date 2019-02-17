package com.leroy.smsf.process;

import com.leroy.smsf.model.Fund;
import com.leroy.smsf.util.RoundUtil;

public class Calculation {
    public int calculateProfit(Fund fund){
        return fund.getIncome() - fund.getExpense();
    }

    public int calculateTaxPayable(Fund fund){
        int profit = calculateProfit(fund);
        return RoundUtil.roundResult(profit * fund.getTaxRate());
    }
}

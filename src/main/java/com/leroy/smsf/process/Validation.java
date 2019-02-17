package com.leroy.smsf.process;

import com.leroy.smsf.model.Fund;
import com.leroy.smsf.model.Member;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;


public class Validation {
    Logger log = LoggerFactory.getLogger("Log");

    public boolean validateFund(Fund fund){
        int income = fund.getIncome();
        int expense = fund.getExpense();
        double taxRate = fund.getTaxRate();
        if (income < 0){
            log.error("Invalid income. Income must be larger than 0");
            return false;
        }

        if (expense < 0){
            log.error("Invalid expense. Expense must be larger than 0");
            return false;
        }
        if (expense > income){
            log.error("Expense cannot be greater than income");
            return false;
        }
        if (taxRate < 0.1 || taxRate > 0.15){
            log.error("Tax rate should be within 10% and 15% (inclusive)");
            return false;
        }

        return true;
    }

    public boolean validateMembers(List<Member> members){
        if (members.size() == 0){
            log.error("There must be at least one member");
            return false;
        }

        double totalProportion = 0;
        double threshold = 0.0001;
        double proportion;
        for (Member member: members){
            if (!validateMember(member)){
                return false;
            }else {
                proportion = member.getProportion();
                totalProportion += proportion;
            }
        }
        if (Math.abs(totalProportion - 1) >= threshold ) {
            log.error("The total proportion of members is not 100%");
            return false;
        }
        for (Member member: members){
            if (!member.isPension()){
                return true;
            }
        }

        log.error("The situation that everyone is pension is not allowed");
        return false;
    }

    private boolean validateMember(Member member){
        double proportion = member.getProportion();
        if (proportion < 0 || proportion > 1){
            log.error("Invalid proportion. Member's proportion must be between 0 and 100%");
            return false;
        }else {
            return true;
        }
    }

}

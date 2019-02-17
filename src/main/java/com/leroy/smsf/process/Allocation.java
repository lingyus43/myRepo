package com.leroy.smsf.process;

import com.leroy.smsf.model.Member;
import com.leroy.smsf.util.RoundUtil;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Allocation {
    public Map<String, Integer> allocateProfit(int profitTotal, List<Member> members){
        int personalProfit;
        Map<String, Integer> profitMap = new HashMap<>();

        for (Member member: members){
            personalProfit = RoundUtil.roundResult(member.getProportion() * profitTotal);
            profitMap.put(member.getName(), personalProfit);
        }

        return profitMap;
    }

    public Map<String, Integer> allocateTaxPayable(List<Member> members, int totalTax){
        Map<String, Integer> taxPayableMap = new HashMap<>();
        double totalProportion = getTotalProportion(members);
        for (Member member: members){
            taxPayableMap.put(member.getName(), member.isPension() ? 0 : RoundUtil.roundResult(member.getProportion() / totalProportion * totalTax));
        }

        int memberTotalTax = taxPayableMap.values().stream().reduce(0, Integer::sum);
        if (memberTotalTax != totalTax){
            String member = getMemberWithLargestProportion(taxPayableMap);
            taxPayableMap.put(member, taxPayableMap.get(member) + (totalTax - memberTotalTax));
        }
        return taxPayableMap;
    }

    private String getMemberWithLargestProportion(Map<String, Integer> taxPayableMap){
        int max = 0;
        String memberNameWithMaxProportion = new String();
        for (Map.Entry<String, Integer> entry: taxPayableMap.entrySet()){
            if (entry.getValue() > max){
                memberNameWithMaxProportion = entry.getKey();
            }
        }
        return memberNameWithMaxProportion;
    }

    private double getTotalProportion(List<Member> members){
        double totalProportion = 0;
        for (Member member: members){
            if (!member.isPension()){
                totalProportion += member.getProportion();
            }
        }
        return totalProportion;
    }
}

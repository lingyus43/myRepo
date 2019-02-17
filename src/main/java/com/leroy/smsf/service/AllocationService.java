package com.leroy.smsf.service;

import com.leroy.smsf.exception.FileReadException;
import com.leroy.smsf.exception.FormattingException;
import com.leroy.smsf.model.Fund;
import com.leroy.smsf.model.Member;
import com.leroy.smsf.process.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Map;

public class AllocationService {
    private String path = "src/main/resources/SMSFFile.txt";
    FileReader fileReader = new FileReader();
    Formater formater = new Formater();
    Validation validation = new Validation();
    Calculation calculation = new Calculation();
    Allocation allocation = new Allocation();
    Logger log = LoggerFactory.getLogger("Log");

    public void allocateProfitAndTaxPayable(){
        try {
            String fundReadFromFile = fileReader.readFund(path);
            Fund fund = formater.formatFund(fundReadFromFile);
            List<String> membersReadFromFile = fileReader.readMembers(path);
            List<Member> members = formater.formatMember(membersReadFromFile);

            boolean isValid = validation.validateFund(fund) && validation.validateMembers(members);
            if (isValid) {
                int profit = calculation.calculateProfit(fund);
                int taxPayable = calculation.calculateTaxPayable(fund);
                Map<String, Integer> profitAllocation = allocation.allocateProfit(profit, members);
                Map<String, Integer> taxPayableAllocation = allocation.allocateTaxPayable(members, taxPayable);
                print(profit, taxPayable, profitAllocation, taxPayableAllocation);
            } else {
                System.exit(1);
            }
        }catch (FileReadException e){
            log.error(e.getMsg());
            System.exit(1);
        }catch (FormattingException e){
            log.error(e.getMsg());
            System.exit(1);
        }catch (Exception e){
            log.error(e.getMessage());
            System.exit(1);
        }
    }

    private void print(int totalProfit, int totalTaxPayable, Map<String, Integer> profitAllocation, Map<String, Integer> taxPayableAllocation){
        System.out.println("Profit: " + totalProfit);
        System.out.println("Tax payable: " + totalTaxPayable + "\n");
        profitAllocation.forEach((member, profit) -> System.out.println(member + "\'s profit : " + profit));
        System.out.println();
        taxPayableAllocation.forEach((member, tax) -> System.out.println(member + "\'s tax allocation: " + tax));
    }

}

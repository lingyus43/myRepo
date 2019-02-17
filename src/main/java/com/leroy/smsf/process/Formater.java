package com.leroy.smsf.process;

import com.leroy.smsf.exception.FileReadException;
import com.leroy.smsf.exception.FormattingException;
import com.leroy.smsf.model.Fund;
import com.leroy.smsf.model.Member;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.LinkedList;
import java.util.List;

public class Formater {
    Logger log = LoggerFactory.getLogger("Log");
    private final int NUMBER_OF_FUND_ELEMENTS = 3;
    private final int NUMBER_OF_MEMBER_ELEMENT = 3;
    private final int LOCATION_OF_INCOME = 0;
    private final int LOCATION_OF_EXPENSE = 1;
    private final int LOCATION_OF_TAX_RATE = 2;
    private final int LOCATION_OF_MEMBER_NAME = 0;
    private final int LOCATION_OF_MEMBER_PROPORTION = 1;
    private final int LOCATION_OF_MEMBER_IS_PENSION = 2;

    public Fund formatFund(String fundDetail) throws FormattingException {
        String[] fund = fundDetail.split(",");
        if (fund.length != NUMBER_OF_FUND_ELEMENTS){
            log.error("Invalid fund input. Element(s) missing");
            throw new FormattingException("At least one of the elements(income, expense, tax rate) is missing");
        }
        try {
            int income = Integer.parseInt(fund[LOCATION_OF_INCOME].trim());
            int expense = Integer.parseInt(fund[LOCATION_OF_EXPENSE].trim());
            double taxRate = Double.parseDouble(fund[LOCATION_OF_TAX_RATE].substring(0, fund[LOCATION_OF_TAX_RATE].length()-1).trim())/100;
            if (fund[LOCATION_OF_TAX_RATE].charAt(fund[LOCATION_OF_TAX_RATE].length() - 1) == '%'){
                return new Fund(income, expense, taxRate);
            }else {
                log.error("Invalid tax rate input");
                throw new FormattingException("Invalid tax rate input. Tax rate must be end with %");
            }
        }catch (NumberFormatException e){
            log.error("Invalid fund input. Income and expense must be integer.\nTax rate must be in format of \"x%\"");
            throw new FormattingException("Invalid fund input. Income and expense must be integer.\nTax rate must be in format of \"x%\"");
        }
    }

    public List<Member> formatMember(List<String> membersDetail) throws FormattingException {
        List<Member> members = new LinkedList<>();
        for (String memberDetail: membersDetail) {
            String[] member = memberDetail.split(",");
            if (member.length != NUMBER_OF_MEMBER_ELEMENT){
                log.error(String.format("At least one element is missing for the No.%s member", membersDetail.indexOf(memberDetail) + 1));
                throw new FormattingException("Missing element for member");
            }
            try {
                String name = member[LOCATION_OF_MEMBER_NAME].trim();
                double proportion = Double.parseDouble(member[LOCATION_OF_MEMBER_PROPORTION].substring(0, member[LOCATION_OF_MEMBER_PROPORTION].length()-1).trim())/100;
                if (member[LOCATION_OF_MEMBER_PROPORTION].charAt(member[LOCATION_OF_MEMBER_PROPORTION].length() - 1) != '%'){
                    log.error("Invalid member proportion input");
                    throw new FormattingException("Invalid member proportion input. Proportion must be end with %");
                }
                String isPension = member[LOCATION_OF_MEMBER_IS_PENSION].trim();
                if (!isPension.toLowerCase().equals("true") && !isPension.toLowerCase().equals("false")){
                    log.error(String.format("The pension status of the No.%s member is incorrect"));
                    throw new FormattingException("Invalid status of pension. Pension status can only be true or false");
                }
                boolean pensionStatus = Boolean.parseBoolean(isPension);
                members.add(new Member(name, proportion, pensionStatus));
            } catch (NumberFormatException e) {
                log.error("Invalid member input. The proportion of each member must be in format of \"x%\" where x is a number between 0 and 100");
                throw new FormattingException("Invalid member input proportion");
            }
        }
        return members;
    }
}

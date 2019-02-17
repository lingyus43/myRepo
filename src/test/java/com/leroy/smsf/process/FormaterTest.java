package com.leroy.smsf.process;

import com.leroy.smsf.exception.FormattingException;
import com.leroy.smsf.model.Fund;
import com.leroy.smsf.model.Member;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.theInstance;
import static org.junit.Assert.*;

public class FormaterTest {
    @Test
    public void formatFundTest() throws FormattingException {
        Formater formater = new Formater();
        String fundDetail = "2, 1, 10%";
        Fund fund = formater.formatFund(fundDetail);
        assertThat(fund.getIncome(), is(2));
        assertThat(fund.getExpense(), is(1));
        assertThat(fund.getTaxRate(), is(0.1));
    }

    @Test(expected = FormattingException.class)
    public void formatFundMissingElementTest() throws FormattingException{
        Formater formater = new Formater();
        String fundDetail = "2, 1";
        formater.formatFund(fundDetail);
    }

    @Test(expected = FormattingException.class)
    public void formatFundMissingElementTest2() throws FormattingException{
        Formater formater = new Formater();
        String fundDetail = "2, 10%";
        formater.formatFund(fundDetail);
    }

    @Test(expected = FormattingException.class)
    public void formatFundInvalidIncomeTest() throws FormattingException{
        Formater formater = new Formater();
        String fundDetail = "2.5, 1, 10%";
        formater.formatFund(fundDetail);
    }

    @Test(expected = FormattingException.class)
    public void formatFundInvalidExpenseTest() throws FormattingException{
        Formater formater = new Formater();
        String fundDetail = "2, 1.0, 10%";
        formater.formatFund(fundDetail);
    }

    @Test(expected = FormattingException.class)
    public void formatFundInvalidTaxRateTest() throws FormattingException{
        Formater formater = new Formater();
        String fundDetail = "2.5, 1, 101%";
        formater.formatFund(fundDetail);
    }

    @Test(expected = FormattingException.class)
    public void formatFundInvalidTaxRateTest2() throws FormattingException{
        Formater formater = new Formater();
        String fundDetail = "2, 1, 10";
        formater.formatFund(fundDetail);
    }

    @Test(expected = FormattingException.class)
    public void formatFundInvalidTaxRateTest3() throws FormattingException{
        Formater formater = new Formater();
        String fundDetail = "2, 1, 0.1";
        formater.formatFund(fundDetail);
    }

    @Test
    public void formatValidMemberTest() throws FormattingException {
        Formater formater = new Formater();
        List<String> membersInput = new ArrayList<>();
        String memberOne = "Jack, 30%, true";
        String memberTwo = "John Doe, 70%, false";
        membersInput.add(memberOne);
        membersInput.add(memberTwo);
        List<Member> members = formater.formatMember(membersInput);
        assertThat(members.get(0).getName(), is("Jack"));
        assertThat(members.get(0).getProportion(), is(0.3));
        assertThat(members.get(0).isPension(), is(true));
        assertThat(members.get(1).getName(), is("John Doe"));
        assertThat(members.get(1).getProportion(), is(0.7));
        assertThat(members.get(1).isPension(), is(false));
    }

}
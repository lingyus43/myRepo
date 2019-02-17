package com.leroy.smsf.process;

import com.leroy.smsf.model.Fund;
import com.leroy.smsf.model.Member;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class ValidationTest {
    @Test
    public void validateValidFundTest(){
        Validation validation = new Validation();
        Fund fund = new Fund(1, 1, 0.15);
        assertThat(validation.validateFund(fund), is(true));
        fund = new Fund(0, 0, 0.10);
        assertThat(validation.validateFund(fund), is(true));
    }

    @Test
    public void validateInvalidIncomeTest(){
        Validation validation = new Validation();
        Fund fund = new Fund(-1, 1, 0.15);
        assertThat(validation.validateFund(fund), is(false));
    }

    @Test
    public void validateInvalidExpenseTest(){
        Validation validation = new Validation();
        Fund fund = new Fund(1, -1, 0.15);
        assertThat(validation.validateFund(fund), is(false));
    }

    @Test
    public void validateInvalidTaxRate(){
        Validation validation = new Validation();
        Fund fund = new Fund(1, 1, 0.099);
        assertThat(validation.validateFund(fund), is(false));
        fund = new Fund(1, 1, 0.1501);
        assertThat(validation.validateFund(fund), is(false));
    }

    @Test
    public void validateProfitTest(){
        Validation validation = new Validation();
        Fund fund = new Fund(1, 2, 0.1);
        assertThat(validation.validateFund(fund), is(false));
    }

    @Test
    public void validateValidMemberTest(){
        Validation validation = new Validation();
        List<Member> members = new ArrayList<>();
        Member memberOne = new Member("Jack", 0.3, true);
        Member memberTwo = new Member("Jim Brown", 0.7, false);
        members.add(memberOne);
        members.add(memberTwo);
        assertThat(validation.validateMembers(members), is(true));
    }
    @Test
    public void validateEmptyMemberTest(){
        Validation validation = new Validation();
        List<Member> members = new ArrayList<>();
        assertThat(validation.validateMembers(members), is(false));
    }

    @Test
    public void validateInvalidProportionTest(){
        Validation validation = new Validation();
        List<Member> members = new ArrayList<>();
        Member memberOne = new Member("Jack", -0.1, true);
        Member memberTwo = new Member("Jim Brown", 0.7, false);
        members.add(memberOne);
        members.add(memberTwo);
        assertThat(validation.validateMembers(members), is(false));
    }

    @Test
    public void validateInvalidProportionTest2(){
        Validation validation = new Validation();
        List<Member> members = new ArrayList<>();
        Member memberOne = new Member("Jack", 30, true);
        Member memberTwo = new Member("Jim Brown", 101, false);
        members.add(memberOne);
        members.add(memberTwo);
        assertThat(validation.validateMembers(members), is(false));
    }

    @Test
    public void validateTotalProportionNotMatchTest(){
        Validation validation = new Validation();
        List<Member> members = new ArrayList<>();
        Member memberOne = new Member("Jack", 30, true);
        Member memberTwo = new Member("Jim Brown", 69, false);
        members.add(memberOne);
        members.add(memberTwo);
        assertThat(validation.validateMembers(members), is(false));
    }

    @Test
    public void validateAllMembersArePensionTest(){
        Validation validation = new Validation();
        List<Member> members = new ArrayList<>();
        Member memberOne = new Member("Jack", 0.3, true);
        Member memberTwo = new Member("Jim Brown", 0.7, true);
        members.add(memberOne);
        members.add(memberTwo);
        assertThat(validation.validateMembers(members), is(false));
    }

}
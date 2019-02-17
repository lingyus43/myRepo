package com.leroy.smsf.process;

import com.leroy.smsf.model.Member;
import org.junit.Test;
import org.omg.PortableInterceptor.INACTIVE;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class AllocationTest {

    @Test
    public void allocateProfitTest() {
        Member memberOne = new Member("Jack", 0.2, false);
        Member memberTwo = new Member("John", 0.3, false);
        Member memberThree = new Member("Jim", 0.5, false);
        List<Member> members = new LinkedList<>();
        members.add(memberOne);
        members.add(memberTwo);
        members.add(memberThree);
        int totalProfit = 100;
        Allocation allocation = new Allocation();
        Map<String, Integer> allocationMap = new HashMap<>();

        allocationMap = allocation.allocateProfit(totalProfit, members);

        assertThat(allocationMap.get("Jack"), is(20));
        assertThat(allocationMap.get("John"), is(30));
        assertThat(allocationMap.get("Jim"), is(50));

        totalProfit = 50;
        memberOne = new Member("Anna", 0.17, false);
        memberTwo = new Member("Andy", 0.28, false);
        memberThree = new Member("Amy", 0.55, false);
        members.clear();
        members.add(memberOne);
        members.add(memberTwo);
        members.add(memberThree);

        allocationMap = allocation.allocateProfit(totalProfit, members);
        assertThat(allocationMap.get("Anna"), is(9));
        assertThat(allocationMap.get("Andy"), is(14));
        assertThat(allocationMap.get("Amy"), is(28));

    }

    @Test
    public void allocateTaxPayable() {
        Member memberOne = new Member("Jack", 0.2, false);
        Member memberTwo = new Member("John", 0.3, false);
        Member memberThree = new Member("Jim", 0.5, false);
        List<Member> members = new LinkedList<>();
        members.add(memberOne);
        members.add(memberTwo);
        members.add(memberThree);
        int totalTaxPayable = 100;
        Allocation allocation = new Allocation();
        Map<String, Integer> allocationMap = new HashMap<>();

        allocationMap = allocation.allocateTaxPayable(members, totalTaxPayable);

        assertThat(allocationMap.get("Jack"), is(20));
        assertThat(allocationMap.get("John"), is(30));
        assertThat(allocationMap.get("Jim"), is(50));

        memberOne = new Member("Anna", 0.17, true);
        memberTwo = new Member("Andy", 0.28, false);
        memberThree = new Member("Amy", 0.55, false);
        members.clear();
        members.add(memberOne);
        members.add(memberTwo);
        members.add(memberThree);

        allocationMap = allocation.allocateTaxPayable(members, totalTaxPayable);
        assertThat(allocationMap.get("Anna"), is(0));
        assertThat(allocationMap.get("Andy"), is(34));
        assertThat(allocationMap.get("Amy"), is(66));
    }

    @Test
    public void allocateTaxPayableTest2() {
        Member memberOne = new Member("Jack", 0.202, false);
        Member memberTwo = new Member("John", 0.304, false);
        Member memberThree = new Member("Jim", 0.494, false);
        List<Member> members = new LinkedList<>();
        members.add(memberOne);
        members.add(memberTwo);
        members.add(memberThree);
        int totalTaxPayable = 100;
        Allocation allocation = new Allocation();
        Map<String, Integer> allocationMap = new HashMap<>();

        allocationMap = allocation.allocateTaxPayable(members, totalTaxPayable);

        assertThat(allocationMap.get("Jack"), is(20));
        assertThat(allocationMap.get("John"), is(30));
        assertThat(allocationMap.get("Jim"), is(50));

    }
}
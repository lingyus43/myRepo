package com.leroy.smsf.process;

import com.leroy.smsf.model.Fund;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class CalculationTest {
    @Test
    public void testCal(){
        double rate = 0.55;
        int profit = 10;
        System.out.println(rate * profit);

        System.out.println(555 == 5.55*100);
        System.out.println((int) 5.4);

        String a = "0.5";
        System.out.println(Double.parseDouble(a));
    }

    @Test
    public void profitCalculateTest(){
        Fund fund = new Fund(5,1, 0.15);
        Calculation calculation = new Calculation();
        int profit = calculation.calculateProfit(fund);
        assertThat(profit, is(4));
    }

    @Test
    public void calculateTaxPayableTest(){
        Fund fund = new Fund(100, 10, 0.1);
        Calculation calculation = new Calculation();
        int tax = calculation.calculateTaxPayable(fund);
        assertThat(tax, is(9));

        fund = new Fund(100, 10, 0.11);
        tax = calculation.calculateTaxPayable(fund);
        assertThat(tax, is(10));
    }
}
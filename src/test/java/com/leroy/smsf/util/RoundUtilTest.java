package com.leroy.smsf.util;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class RoundUtilTest {
    @Test
    public void testRoundUp(){
        double resultBeforeRound = 5.5;
        assertThat(RoundUtil.roundResult(resultBeforeRound), is(6));
    }

    @Test
    public void testRoundDown(){
        double resultBeforeRound = 5.49;
        assertThat(RoundUtil.roundResult(resultBeforeRound), is(5));
    }
}
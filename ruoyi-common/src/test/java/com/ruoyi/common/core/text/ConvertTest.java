package com.ruoyi.common.core.text;

import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class ConvertTest
{
    @Test
    public void testToStrWithNullAndDefault()
    {
        assertEquals("default", Convert.toStr(null, "default"));
    }

    @Test
    public void testToCharFromString()
    {
        assertEquals(Character.valueOf('a'), Convert.toChar("abc"));
    }

    @Test
    public void testToCharReturnsNullForNullValue()
    {
        assertNull(Convert.toChar(null));
    }

    @Test
    public void testToIntFromNumericString()
    {
        assertEquals(Integer.valueOf(123), Convert.toInt("123"));
    }

    @Test
    public void testToIntReturnsDefaultForInvalidNumber()
    {
        assertEquals(Integer.valueOf(7), Convert.toInt("abc", 7));
    }

    @Test
    public void testToBoolRecognizesTrueValues()
    {
        assertEquals(Boolean.TRUE, Convert.toBool("yes"));
        assertEquals(Boolean.TRUE, Convert.toBool("ok"));
        assertEquals(Boolean.TRUE, Convert.toBool("1"));
    }

    @Test
    public void testToBoolRecognizesFalseValues()
    {
        assertEquals(Boolean.FALSE, Convert.toBool("no"));
        assertEquals(Boolean.FALSE, Convert.toBool("0"));
    }

    @Test
    public void testToBoolReturnsDefaultForUnknownValue()
    {
        assertEquals(Boolean.FALSE, Convert.toBool("maybe", false));
    }

    @Test
    public void testToSBCAndToDBCRoundTrip()
    {
        String original = "ABC 123";
        String sbc = Convert.toSBC(original);
        String roundTrip = Convert.toDBC(sbc);
        assertEquals(original, roundTrip);
    }

    @Test
    public void testDigitUppercaseForPositiveNumbers()
    {
        assertEquals("壹元整", Convert.digitUppercase(1));
        assertEquals("壹元壹角贰分", Convert.digitUppercase(1.12));
    }

    @Test
    public void testDigitUppercaseForNegativeNumbers()
    {
        assertEquals("负壹元整", Convert.digitUppercase(-1));
    }

    @Test
    public void testToIntArrayParsesCommaSeparatedNumbers()
    {
        assertArrayEquals(new Integer[]{1, 2, 0, 4}, Convert.toIntArray(",", "1,2,abc,4"));
    }
}

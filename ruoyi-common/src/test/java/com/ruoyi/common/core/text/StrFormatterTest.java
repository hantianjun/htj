package com.ruoyi.common.core.text;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class StrFormatterTest
{
    @Test
    public void testFormatSimpleReplacement()
    {
        assertEquals("this is a for b", StrFormatter.format("this is {} for {}", "a", "b"));
    }

    @Test
    public void testFormatEscapedPlaceholder()
    {
        assertEquals("this is {} for a", StrFormatter.format("this is \\{} for {}", "a", "b"));
    }

    @Test
    public void testFormatEscapedBackslashAndPlaceholder()
    {
        assertEquals("this is \\a for b", StrFormatter.format("this is \\\\{} for {}", "a", "b"));
    }

    @Test
    public void testFormatWhenPatternIsNull()
    {
        assertNull(StrFormatter.format(null, "a"));
    }

    @Test
    public void testFormatWithNoArgsReturnsOriginal()
    {
        assertEquals("hello", StrFormatter.format("hello"));
    }
}

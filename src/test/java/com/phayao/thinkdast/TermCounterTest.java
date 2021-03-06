package com.phayao.thinkdast;

import org.jsoup.select.Elements;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class TermCounterTest {
    private TermCounter counter;

    @Before
    public void setUp() throws Exception {
        String url = "https://en.wikipedia.org/wiki/Java_(programming_language)";

        WikiFetcher wf = new WikiFetcher();
        Elements paragraphs = wf.readWikipedia(url);

        counter = new TermCounter(url.toString());
        counter.processElements(paragraphs);
    }

    /**
     * Test method for {@link TermCounter#size()}.
     */
    @Test
    public void testSize() {
        assertThat(counter.size(), is(4798));
    }
}
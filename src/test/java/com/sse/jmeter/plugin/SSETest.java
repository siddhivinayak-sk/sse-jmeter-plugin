package com.sse.jmeter.plugin;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class SSETest extends TestCase
{
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public SSETest( String testName ) {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite() {
        return new TestSuite( SSETest.class );
    }

    /**
     * Rigourous Test :-)
     */
    public void testApp() {
        assertTrue( true );
    }
}

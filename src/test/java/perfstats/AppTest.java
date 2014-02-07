package perfstats;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.atLeastOnce;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

/**
 * Unit tests for 'App'.
 *
 * @author Naeem Tahir
 */
public class AppTest {

    /** Default time pattern for tests. */
    public static final String DATE_TIME_PATTERN = "yyyyy.MM.dd hh:mm:ss";

    private SimpleDateFormat timeParser = new SimpleDateFormat(DATE_TIME_PATTERN);
    private List<PerfStat> perfStats = new ArrayList<PerfStat>();

    @Before
    public void setUp() throws ParseException {
        String tag = "tag";
        Date startTime = timeParser.parse("2013.09.11 02:23:54");
        Date endTime = timeParser.parse("2013.09.11 10:23:54");
        PerfStat stat = new PerfStat(tag, startTime, endTime);
        stat.setCount(480);
        stat.setMax(357);
        stat.setMean(9.8);
        stat.setMedian(7);
        stat.setMin(5);
        stat.setPercentile90(8);
        stat.setPercentile99(120.7);
        stat.setStdDev(21.6);

        perfStats.add(stat);
    }

    @Test
    public void testValidateArgs() {
        String[] invalidInput = {};
        String[] validInput = {"test.log"};

        assertFalse(App.validateArgs(invalidInput));
        assertTrue(App.validateArgs(validInput));
    }

    @Test
    public void testPrintStats() throws ParseException, IOException {

        String outputLine1 =  "Code Block,Start Time,End Time,Count,Avg,Med,Min,Max,Std Dev,90th %ile,99th %ile\n";
        String outputLine2 =  "tag,2013-09-11 02:23:54,2013-09-11 10:23:54,480,9.8,7.0,5.0,357.0,21.6,8.0,120.7\n";

        App classUnderTest = new App();

        // Test with non-empty list
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        classUnderTest.printStats(perfStats, new PrintStream(out));
        assertEquals(outputLine1 + outputLine2, out.toString());

        // Test with an empty list
        out = new ByteArrayOutputStream();
        classUnderTest.printStats(new ArrayList<PerfStat>(), new PrintStream(out));
        assertEquals(outputLine1, out.toString());
    }

    @Test
    public void testGo() throws IOException {
        App classUnderTest = spy(new App());
        PerfStatCalculator mockStatsCalculator = mock(PerfStatCalculator.class);

        InputStream inputStream = new ByteArrayInputStream(new byte[10]);
        PrintStream printStream = System.out;

        when(mockStatsCalculator.generateStats((InputStream) any())).thenReturn(perfStats);
        classUnderTest.go(mockStatsCalculator, inputStream, printStream);

        verify(mockStatsCalculator, atLeastOnce()).generateStats(inputStream);
        verify(classUnderTest).printStats(perfStats, printStream);
    }
}

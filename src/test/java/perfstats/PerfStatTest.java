package perfstats;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertEquals;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Before;
import org.junit.Test;

/**
 * Unit tests for 'PerfStat'.
 *
 * @author Naeem Tahir
 */
public class PerfStatTest {

    /** Default time pattern for tests. */
    public static final String DATE_TIME_PATTERN = "yyyyy.MM.dd hh:mm:ss";

    private SimpleDateFormat timeParser = new SimpleDateFormat(DATE_TIME_PATTERN);
    private PerfStat classUnderTest;

    @Before
    public void setUp() throws ParseException {
        String tag = "tag";
        Date startTime = timeParser.parse("2013.09.11 02:23:54");
        Date endTime = timeParser.parse("2013.09.11 10:23:54");

        classUnderTest = new PerfStat(tag, startTime, endTime);
        classUnderTest.setCount(480);
        classUnderTest.setMax(357);
        classUnderTest.setMean(9.8);
        classUnderTest.setMedian(7);
        classUnderTest.setMin(5);
        classUnderTest.setPercentile90(8);
        classUnderTest.setPercentile99(120.7);
        classUnderTest.setStdDev(21.6);
    }

    @Test
    public void testEquals() throws ParseException {
        PerfStat obj = new PerfStat(classUnderTest.getTag(), classUnderTest.getStartTime(), classUnderTest.getEndTime());
        obj.setCount(classUnderTest.getCount());
        obj.setMax(classUnderTest.getMax());
        obj.setMean(classUnderTest.getMean());
        obj.setMedian(classUnderTest.getMedian());
        obj.setMin(classUnderTest.getMin());
        obj.setPercentile90(classUnderTest.getPercentile90());
        obj.setPercentile99(classUnderTest.getPercentile99());
        obj.setStdDev(classUnderTest.getStdDev());

        assertFalse(classUnderTest.equals(null));
        assertFalse(classUnderTest.equals(new String()));
        assertTrue(classUnderTest.equals(obj));
        assertTrue(classUnderTest.equals(classUnderTest));
    }

    @Test
    public void testHashCode() throws ParseException {
        PerfStat obj = new PerfStat(classUnderTest.getTag(), classUnderTest.getStartTime(), classUnderTest.getEndTime());
        obj.setCount(classUnderTest.getCount());
        obj.setMax(classUnderTest.getMax());
        obj.setMean(classUnderTest.getMean());
        obj.setMedian(classUnderTest.getMedian());
        obj.setMin(classUnderTest.getMin());
        obj.setPercentile90(classUnderTest.getPercentile90());
        obj.setPercentile99(classUnderTest.getPercentile99());
        obj.setStdDev(classUnderTest.getStdDev());

        assertEquals(obj.hashCode(), classUnderTest.hashCode());
    }

    @Test
    public void testToString() throws ParseException {
        String output = classUnderTest.toString();

        String expected = "[tag=tag,startTime=Wed Sep 11 02:23:54 EDT 2013,endTime=Wed Sep 11 10:23:54 EDT 2013,count=480,min=5.0,max=357.0,mean=9.8,median=7.0,stdDev=21.6,percentile90=8.0,percentile99=120.7]";
        String actual = output.substring(output.indexOf("["));

        assertEquals(expected, actual);
    }
}

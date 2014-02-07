package perfstats;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.text.ParseException;

import org.junit.Before;
import org.junit.Test;

/**
 * Unit tests for 'Perf4jData'.
 *
 * @author Naeem Tahir
 */
public class Perf4jDataTest {

    private Perf4jData classUnderTest;

    @Before
    public void setUp() throws ParseException {
        String tag = "tag";
        long startTime = 1357769279886L;
        long endTime = 1357769280515L;
        double[] elapsedTimes = new double[] {1.2, 3.4, 5.6};

        classUnderTest = new Perf4jData(tag);
        classUnderTest.setStartTime(startTime);
        classUnderTest.setEndTime(endTime);
        for (double d : elapsedTimes) {
            classUnderTest.addElapsedTime(d);
        }
    }

    @Test
    public void testEquals() throws ParseException {
        Perf4jData obj = new Perf4jData(classUnderTest.getTag());

        obj.setStartTime(classUnderTest.getStartTime());
        obj.setEndTime(classUnderTest.getEndTime());
        for (double d : classUnderTest.getElapsedTimes()) {
            obj.addElapsedTime(d);
        }

        assertFalse(classUnderTest.equals(null));
        assertFalse(classUnderTest.equals(new String()));
        assertTrue(classUnderTest.equals(obj));
        assertTrue(classUnderTest.equals(classUnderTest));
    }

    @Test
    public void testHashCode() throws ParseException {
        Perf4jData obj = new Perf4jData(classUnderTest.getTag());

        obj.setStartTime(classUnderTest.getStartTime());
        obj.setEndTime(classUnderTest.getEndTime());
        for (double d : classUnderTest.getElapsedTimes()) {
            obj.addElapsedTime(d);
        }

        assertEquals(obj.hashCode(), classUnderTest.hashCode());
    }

    @Test
    public void testToString() throws ParseException {
        String output = classUnderTest.toString();

        String expected = "[tag=tag,startTime=1357769279886,endTime=1357769280515,elapsedTimes=[1.2, 3.4, 5.6]]";
        String actual = output.substring(output.indexOf("["));

        assertEquals(expected, actual);
    }
}

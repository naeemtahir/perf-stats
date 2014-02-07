package perfstats;

/**
 * Unit tests for 'PerfStatCalculator'.
 *
 * @author Naeem Tahir
 */
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class PerfStatCalculatorTest {

    @Test
    public void testGenerateStats() throws IOException {
        String input = "start[1357769276545] time[64] tag[JMS retrieveMessage]\n"
                     + "start[1357769277546] time[63] tag[JMS retrieveMessage]\n"
                     + "start[1357769278546] time[64] tag[JMS retrieveMessage]\n"
                     + "start[1357769279546] time[64] tag[JMS retrieveMessage]";

        InputStream inputStream = new ByteArrayInputStream(input.getBytes("UTF-8"));
        PerfStatCalculator classUnderTest = new PerfStatCalculatorImpl();

        List<PerfStat> perfStats = classUnderTest.generateStats(inputStream);
        String str = perfStats.get(0).toString();
        String actual = str.substring(str.indexOf('['));
        String expected = "[tag=JMS retrieveMessage,startTime=Wed Jan 09 17:07:56 EST 2013,endTime=Wed Jan 09 17:07:59 EST 2013,count=4,min=63.0,max=64.0,mean=63.75,median=64.0,stdDev=0.5,percentile90=64.0,percentile99=64.0]";

        assertEquals(expected, actual);
    }

}

package perfstats;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * Calculates performance statistics from Perf4j instrumented input stream.
 *
 * @author Naeem Tahir
 */
public interface PerfStatCalculator {
    /**
     *
     * @param perfLogStream
     *        Perf4j Instrumented input stream (typically a
     *        <code>FileInputStream</code>)
     * @return List containing performance metrics for each code block.
     * @throws IOException
     *        If encountered an issue with I/O while interacting
     *        with provided streams
     */
    List<PerfStat> generateStats(InputStream perfLogStream) throws IOException;
}

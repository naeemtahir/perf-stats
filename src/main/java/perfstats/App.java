package perfstats;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.List;

/**
 * Generate performance metrics (e.g., MIN, MAX, MEAN, MEDIAN, STDDEV,
 * PERCENTILES) from Perf4J instrumented logs.
 *
 * @author Naeem Tahir
 */
public class App {

    /**
     * Kicks of metrics calculation.
     *
     * @param statsCalculator
     *        A <code>PerfStatsCalculator</code> instance
     * @param in
     *        Perf4j Instrumented input stream (typically a
     *        <code>FileInputStream</code>)
     * @param out
     *        PrintStream for writing output
     * @throws IOException
     *         If encountered an issue with I/O while interacting
     *         with provided streams
     */
    public final void go(final PerfStatCalculator statsCalculator,
            final InputStream in, final PrintStream out)
            throws IOException {

        List<PerfStat> perfStats = statsCalculator.generateStats(in);
        printStats(perfStats, out);
    }

    /**
     * Prints stats using provided <code>PrintStream</code>.
     *
     * @param perfStats
     *        A <code>List</code> containing performance metrics.
     * @param out
     *        PrintStream for writing output
     */
    final void printStats(final List<PerfStat> perfStats, final PrintStream out) {
        out.println("Code Block,Start Time,End Time,Count,Avg,Med,Min,Max,"
                + "Std Dev,90th %ile,99th %ile");

        for (PerfStat perfStat : perfStats) {
            out.printf(
                    "%s,%TF %TT,%TF %TT,%d,%.1f,%.1f,%.1f,%.1f,%.1f,%.1f,%.1f%n",
                    perfStat.getTag(),
                    perfStat.getStartTime(),
                    perfStat.getStartTime(),
                    perfStat.getEndTime(),
                    perfStat.getEndTime(),
                    perfStat.getCount(),
                    perfStat.getMean(),
                    perfStat.getMedian(),
                    perfStat.getMin(),
                    perfStat.getMax(),
                    perfStat.getStdDev(),
                    perfStat.getPercentile90(),
                    perfStat.getPercentile99());
        }
    }

    /**
     * Validates command line arguments/prints application usage.
     *
     * @param args
     *        Command line arguments
     * @return
     *        A flag indicating if arguments are valid.
     */
    static boolean validateArgs(final String[] args) {
        if (args.length < 1) {
            System.out.println("Generate performance metrics (e.g., MIN, "
                    + "MAX, MEAN, MEDIAN, STDDEV, PERCENTILES) from Perf4J "
                    + "instrumented logs.\n");
            System.out.println("Usage: java perfstats.App "
                    + "<perf4j_instrumented_log>");
            return false;
        }

        return true;
    }

    /**
     * Application entry point.
     *
     * @param args
     *        Command line arguments
     */
    public static void main(final String[] args) {

        if (validateArgs(args)) {
            try {
                InputStream in = new FileInputStream(args[0]);
                PerfStatCalculator statsCalculator = new PerfStatCalculatorImpl();

                new App().go(statsCalculator, in, System.out);
            } catch (FileNotFoundException e) {
                System.err.printf("Couldn't open %s, please check file path.", args[0]);
            } catch (IOException e) {
                System.err.printf("Exception occured: %s", e.toString());
            }
        }
    }
}

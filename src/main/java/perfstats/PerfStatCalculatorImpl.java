package perfstats;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.math.stat.StatUtils;
import org.perf4j.StopWatch;
import org.perf4j.helpers.StopWatchParser;

/**
 * Default implementation of PerfStatCalculator.
 *
 * @author Naeem Tahir
 */
public class PerfStatCalculatorImpl implements PerfStatCalculator {

    /** Median. */
    public static final int FIFTYTH_PERCENTILE = 50;
    /** 90th Percentile. */
    public static final int NINETYTH_PERCENTILE = 90;
    /** 99th Percentile. */
    public static final int NINETYNINE_PERCENTILE = 99;

    /**
     * {@inheritDoc}
     */
    public final List<PerfStat> generateStats(final InputStream perfLogStream)
            throws IOException {
        Map<String, Perf4jData> perf4jDataMap = collectPerf4jData(perfLogStream);
        return calculateStats(perf4jDataMap);
    }

    /**
     * Helper method to collect raw data from input stream.
     *
     * @param perfLogStream
     *        Perf4j instrumented input stream.
     * @return A map containing raw data for each code block (i.e., tag).
     * @throws IOException
     *        If encountered an error while reading data from input stream.
     */
    final Map<String, Perf4jData> collectPerf4jData(
            final InputStream perfLogStream) throws IOException {

        BufferedReader streamReader = new BufferedReader(new InputStreamReader(
                perfLogStream, "UTF-8"));
        Map<String, Perf4jData> perf4jDataMap = new HashMap<String, Perf4jData>();

        StopWatchParser stopWatchParser = new StopWatchParser();
        String line;
        while ((line = streamReader.readLine()) != null) {
            StopWatch stopWatch = stopWatchParser.parseStopWatch(line);

            if (stopWatch != null) {
                String tag = stopWatch.getTag();
                double elapsedTime = stopWatch.getElapsedTime();
                long startTime = stopWatch.getStartTime();

                Perf4jData perf4jData = perf4jDataMap.get(tag);
                if (perf4jData == null) {
                    perf4jData = new Perf4jData(tag);
                    perf4jData.setStartTime(startTime);
                    perf4jDataMap.put(tag, perf4jData);
                }

                perf4jData.addElapsedTime(elapsedTime);
                perf4jData.setEndTime(startTime);
            }
        }

        streamReader.close();

        return perf4jDataMap;
    }

    /**
     * Computes performance metrics.
     *
     * @param perf4jDataMap
     *        A map containing raw data for each code block (i.e., tag).
     * @return List containing computed performance metrics.
     */
    final List<PerfStat> calculateStats(final Map<String, Perf4jData> perf4jDataMap) {
        List<PerfStat> finalStats = new ArrayList<PerfStat>();

        for (Entry<String, Perf4jData> entry : perf4jDataMap.entrySet()) {
            Perf4jData perf4jData = entry.getValue();

            String tag = perf4jData.getTag();
            int count = perf4jData.getCount();
            Date startTime = new Date(perf4jData.getStartTime());
            Date endTime = new Date(perf4jData.getEndTime());
            List<Double> elapsedTimesList = perf4jData.getElapsedTimes();
            double[] elapsedTimes = ArrayUtils.toPrimitive(elapsedTimesList
                    .toArray(new Double[elapsedTimesList.size()]));

            PerfStat perfStat = new PerfStat(tag, startTime, endTime);
            perfStat.setCount(count);
            perfStat.setMin(StatUtils.min(elapsedTimes));
            perfStat.setMax(StatUtils.max(elapsedTimes));
            perfStat.setMean(StatUtils.mean(elapsedTimes));
            perfStat.setStdDev(Math.sqrt(StatUtils.variance(elapsedTimes)));
            perfStat.setMedian(StatUtils.percentile(elapsedTimes,
                    FIFTYTH_PERCENTILE));
            perfStat.setPercentile90(StatUtils.percentile(elapsedTimes,
                    NINETYTH_PERCENTILE));
            perfStat.setPercentile99(StatUtils.percentile(elapsedTimes,
                    NINETYNINE_PERCENTILE));

            finalStats.add(perfStat);
        }

        return finalStats;
    }
}

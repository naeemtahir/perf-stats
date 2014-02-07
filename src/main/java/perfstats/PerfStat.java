package perfstats;

import java.util.Date;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

/**
 * Represents final statistics for a tag.
 *
 * @author Naeem Tahir
 */
public class PerfStat {

    /** Name/tag of a code block. */
    private String tag;

    /** Calculation start time. */
    private Date startTime;

    /** Calculation end time. */
    private Date endTime;

    /** Tag occurance count. */
    private int count;

    /** Minimum time it took to execute a given code block. */
    private double min;

    /** Maximum time it took to execute a given code block. */
    private double max;

    /** Mean time it took to execute a given code block. */
    private double mean;

    /** Median time it took to execute a given code block. */
    private double median;

    /** Standard deviation. */
    private double stdDev;

    /** 90th percentile. */
    private double percentile90;

    /** 99th percentile. */
    private double percentile99;

    /**
     *
     * @param aTag
     *        Name/tag of a code block
     * @param aStartTime
     *        Start time
     * @param aEndTime
     *        End time
     */
    public PerfStat(final String aTag, final Date aStartTime, final Date aEndTime) {
        this.tag = aTag;
        this.startTime = aStartTime;
        this.endTime = aEndTime;
    }

    /**
     *
     * @return tag
     */
    public final String getTag() {
        return tag;
    }

    /**
     *
     * @return startTime
     */
    public final Date getStartTime() {
        return startTime;
    }

    /**
     *
     * @return endTime
     */
    public final Date getEndTime() {
        return endTime;
    }

    /**
     *
     * @param aCount
     *        Tag occurance count
     */
    public final void setCount(final int aCount) {
        this.count = aCount;
    }

    /**
     *
     * @param aMin
     *        Minimum time it took to execute a given code block.
     */
    public final void setMin(final double aMin) {
        this.min = aMin;
    }

    /**
     *
     * @param aMax
     *        Maximum time it took to execute a given code block.
     */
    public final void setMax(final double aMax) {
        this.max = aMax;
    }

    /**
     *
     * @param aMean
     *        Average time it took to execute a given code block.
     */
    public final void setMean(final double aMean) {
        this.mean = aMean;
    }

    /**
     *
     * @param aStdDev
     *        Standard deviation.
     */
    public final void setStdDev(final double aStdDev) {
        this.stdDev = aStdDev;
    }

    /**
     *
     * @param aPercentile99
     *        99th percentile
     */
    public final void setPercentile99(final double aPercentile99) {
        this.percentile99 = aPercentile99;
    }

    /**
     *
     * @param aPercentile90
     *        90th percentile
     */
    public final void setPercentile90(final double aPercentile90) {
        this.percentile90 = aPercentile90;
    }

    /**
     *
     * @param aMedian
     *        Median
     */
    public final void setMedian(final double aMedian) {
        this.median = aMedian;
    }

    /**
     *
     * @return tag occurance count
     */
    public final int getCount() {
        return count;
    }

    /**
     *
     * @return min
     */
    public final double getMin() {
        return min;
    }

    /**
     *
     * @return max
     */
    public final double getMax() {
        return max;
    }

    /**
     *
     * @return mean
     */
    public final double getMean() {
        return mean;
    }

    /**
     *
     * @return stdDev
     */
    public final double getStdDev() {
        return stdDev;
    }

    /**
     *
     * @return percentile99
     */
    public final double getPercentile99() {
        return percentile99;
    }

    /**
    *
    * @return percentile90
    */
    public final double getPercentile90() {
        return percentile90;
    }

    /**
    *
    * @return median
    */
    public final double getMedian() {
        return median;
    }

    @Override
    public final boolean equals(final Object obj) {
        if (obj == null) {
            return false;
        }

        if (!(obj instanceof PerfStat)) {
            return false;
        }

        PerfStat rhs = (PerfStat) obj;
        return new EqualsBuilder()
                .append(tag, rhs.tag)
                .append(startTime, rhs.startTime)
                .append(endTime, rhs.endTime)
                .append(count, rhs.count)
                .append(min, rhs.min)
                .append(max, rhs.max)
                .append(mean, rhs.mean)
                .append(median, rhs.median)
                .append(stdDev, rhs.stdDev)
                .append(percentile90, rhs.percentile90)
                .append(percentile99, rhs.percentile99)
                .isEquals();
    }

    @Override
    public final int hashCode() {
        return new HashCodeBuilder(13, 29)
                .append(tag)
                .append(startTime)
                .append(endTime)
                .append(count)
                .append(min)
                .append(max)
                .append(mean)
                .append(median)
                .append(stdDev)
                .append(percentile90)
                .append(percentile99)
                .toHashCode();
    }

    @Override
    public final String toString() {
        return new ToStringBuilder(this)
                .append("tag", tag)
                .append("startTime", startTime)
                .append("endTime", endTime)
                .append("count", count)
                .append("min", min)
                .append("max", max)
                .append("mean", mean)
                .append("median", median)
                .append("stdDev", stdDev)
                .append("percentile90", percentile90)
                .append("percentile99", percentile99)
                .toString();
    }
}

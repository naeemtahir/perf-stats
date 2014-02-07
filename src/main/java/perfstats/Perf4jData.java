package perfstats;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * Accumulator for all elapsed times corresponding to a tag.
 *
 * @author Naeem Tahir
 */
public class Perf4jData {

    /** Tag name. */
    private String tag;

    /** Tag start time. */
    private long startTime;

    /** Tag end time. */
    private long endTime;

    /** List of all elapsed times for this tag. */
    private List<Double> elapsedTimes;

    /**
     * Default constructor.
     *
     * @param aTag
     *        Name/tag of a code block
     */
    public Perf4jData(final String aTag) {
        this.tag = aTag;
        elapsedTimes = new ArrayList<Double>();
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
    public final long getStartTime() {
        return startTime;
    }

    /**
     *
     * @param aStartTime
     *        Calculation start time
     */
    public final void setStartTime(final long aStartTime) {
        this.startTime = aStartTime;
    }

    /**
     *
     * @return endTime
     */
    public final long getEndTime() {
        return endTime;
    }

    /**
     *
     * @param aEndTime
     *        Calculation end time
     */
    public final void setEndTime(final long aEndTime) {
        this.endTime = aEndTime;
    }

    /**
     *
     * @return list of all elapsed times for this tag.
     */
    public final List<Double> getElapsedTimes() {
        return elapsedTimes;
    }

    /**
     *
     * @param aElapsedTime
     *        Add elapsed time to list of elapsed times for this tag.
     */
    public final void addElapsedTime(final double aElapsedTime) {
        elapsedTimes.add(aElapsedTime);
    }

    /**
     *
     * @return tag occurance count
     */
    public final int getCount() {
        return elapsedTimes.size();
    }

    @Override
    public final boolean equals(final Object obj) {
        if (obj == null) {
            return false;
        }

        if (!(obj instanceof Perf4jData)) {
            return false;
        }

        Perf4jData rhs = (Perf4jData) obj;
        return new EqualsBuilder()
                .append(tag, rhs.tag)
                .append(startTime, rhs.startTime)
                .append(endTime, rhs.endTime)
                .append(elapsedTimes, rhs.elapsedTimes)
                .isEquals();
    }

    @Override
    public final int hashCode() {
        return new HashCodeBuilder(13, 29)
                .append(tag)
                .append(startTime)
                .append(endTime)
                .append(elapsedTimes)
                .toHashCode();
    }

    @Override
    public final String toString() {
        return new ToStringBuilder(this)
                .append("tag", tag)
                .append("startTime", startTime)
                .append("endTime", endTime)
                .append("elapsedTimes", elapsedTimes)
                .toString();
    }
}

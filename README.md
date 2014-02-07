PerfStats
=========
Generate performance metrics (e.g., MIN, MAX, MEAN, MEDIAN, STDDEV, PERCENTILES) from Perf4J instrumented logs.

How to Build
------------
<pre><code>mvn clean install assembly:single</code></pre>

Creates a self-contained executable JAR (perf-stats-1.0-jar-with-dependencies.jar) under 'target'.

<i>Optionally Generate Code Quality Metrics:</i>
 
<pre><code>mvn site</code></pre> 

Quality reports location: 'target/site/index.html'.

How to Run
----------
<pre><code>
java -jar perf-stats-1.0-jar-with-dependencies.jar <perf4j_log>
</code></pre>
	
Example:
<pre><code>
java -jar perf-stats-1.0-jar-with-dependencies.jar sample_input.log > sample_output.csv 2>&1
</code></pre>

Sample Report
-------------

![Sample Output](https://raw2.github.com/naeemtahir/perf-stats/master/sample/sample_output.png)

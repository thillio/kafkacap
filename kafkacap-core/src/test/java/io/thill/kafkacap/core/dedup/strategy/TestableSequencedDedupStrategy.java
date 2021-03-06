/*
 * Licensed under the Apache License, Version 2.0
 * You may obtain a copy of the License at http://www.apache.org/licenses/LICENSE-2.0
 */
package io.thill.kafkacap.core.dedup.strategy;

import org.apache.kafka.clients.consumer.ConsumerRecord;

public class TestableSequencedDedupStrategy extends SequencedDedupStrategy<Long, String> {

  private Integer lastGapPartition;
  private Long lastGapFromSequence;
  private Long lastGapToSequence;

  public TestableSequencedDedupStrategy() {
    this(false, 5000, "");
  }

  public TestableSequencedDedupStrategy(boolean orderedCapture, long sequenceGapTimeoutMillis) {
    this(orderedCapture, sequenceGapTimeoutMillis, "");
  }

  public TestableSequencedDedupStrategy(boolean orderedCapture, long sequenceGapTimeoutMillis, String headerPrefix) {
    super(orderedCapture, sequenceGapTimeoutMillis, headerPrefix);
  }

  @Override
  protected long parseSequence(ConsumerRecord<Long, String> record) {
    return record.key();
  }

  @Override
  protected void onSequenceGap(int partition, long fromSequence, long toSequence) {
    lastGapPartition = partition;
    lastGapFromSequence = fromSequence;
    lastGapToSequence = toSequence;
  }

  public Integer getLastGapPartition() {
    return lastGapPartition;
  }

  public Long getLastGapFromSequence() {
    return lastGapFromSequence;
  }

  public Long getLastGapToSequence() {
    return lastGapToSequence;
  }

}
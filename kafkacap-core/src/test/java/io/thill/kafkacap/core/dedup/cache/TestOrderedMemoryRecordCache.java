/*
 * Licensed under the Apache License, Version 2.0
 * You may obtain a copy of the License at http://www.apache.org/licenses/LICENSE-2.0
 */
package io.thill.kafkacap.core.dedup.cache;

public class TestOrderedMemoryRecordCache extends AbstractOrderedRecordCacheTest {

  @Override
  protected RecordCacheFactory<String, String> createFactory() {
    return MemoryRecordCache.factory();
  }
}

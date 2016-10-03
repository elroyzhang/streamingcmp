/**
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.tencent.jstorm.daemon.worker;

import junit.framework.Assert;

import org.junit.Test;

public class WorkerStatusTest {
  @Test
  public void testWorkerStatus() {
    WorkerStatus status = null;
    Assert.assertEquals(5, WorkerStatus.values().length);
    status = WorkerStatus.disallowed;
    Assert.assertEquals(WorkerStatus.disallowed, status);
    status = WorkerStatus.notStarted;
    Assert.assertEquals(WorkerStatus.notStarted, status);
    status = WorkerStatus.timedOut;
    Assert.assertEquals(WorkerStatus.timedOut, status);
    status = WorkerStatus.valid;
    Assert.assertEquals(WorkerStatus.valid, status);
    status = WorkerStatus.restarting;
    Assert.assertEquals(WorkerStatus.restarting, status);
  }
}

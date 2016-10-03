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
package com.tencent.jstorm.daemon.executor.grouping;

import java.util.List;
import java.util.Random;

import com.google.common.collect.Lists;

import org.apache.storm.grouping.LoadMapping;

/**
 * @author <a href="mailto:caofangkun@gmail.com">caokun</a>
 * @author <a href="mailto:xunzhang555@gmail.com">zhangxun</a>
 * @author <a href="mailto:gulele2003@qq.com">gulele</a>
 * @author <a href="mailto:darezhong@qq.com">liuyuzhong</a>
 * @author <a href="mailto:fuangguang@126.com">wangfangguang</a>
 * @ModifiedBy zionwang
 * @ModifiedTime 11:19:29 AM Feb 23, 2016
 */
public class NoneGrouper implements IGrouper {
  private Random random;
  private List<Integer> targetTasks;
  private int numTasks;

  public NoneGrouper(List<Integer> targetTasks) {
    this.random = new Random();
    this.targetTasks = targetTasks;
    this.numTasks = targetTasks.size();
  }

  @Override
  public List<Integer> fn(Integer taskId, List<Object> values,
      LoadMapping load) {
    int rnd = random.nextInt();
    if (rnd != Integer.MIN_VALUE) {
      rnd = Math.abs(rnd);
    } else {
      rnd = Integer.MIN_VALUE;
    }
    int i = rnd % numTasks;
    return Lists.newArrayList(targetTasks.get(i));
  }

}

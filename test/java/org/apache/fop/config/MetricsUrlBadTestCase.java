/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

/* $Id$ */

package org.apache.fop.config;

import java.io.File;

// this font has a metrics-url that does not exist on filesystem
public class MetricsUrlBadTestCase extends BaseUserConfigTestCase {

    /**
     * @see junit.framework.TestCase#TestCase(String)
     */
    public MetricsUrlBadTestCase(String name) {
        super(name);
    }

    /**
     * @see org.apache.fop.config.BaseUserConfigTestCase#getUserConfigFile()
     */
    protected File getUserConfigFile() {
        return new File( getBaseConfigDir() + "/test_metricsurl_bad.xconf");
    }
    
    public String getName() {
        return "test_metricsurl_bad.xconf";
    }
}
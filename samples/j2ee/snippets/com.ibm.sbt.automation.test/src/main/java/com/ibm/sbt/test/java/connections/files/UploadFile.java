/*
 * © Copyright IBM Corp. 2012
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); 
 * you may not use this file except in compliance with the License. 
 * You may obtain a copy of the License at:
 * 
 * http://www.apache.org/licenses/LICENSE-2.0 
 * 
 * Unless required by applicable law or agreed to in writing, software 
 * distributed under the License is distributed on an "AS IS" BASIS, 
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or 
 * implied. See the License for the specific language governing 
 * permissions and limitations under the License.
 */
package com.ibm.sbt.test.java.connections.files;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.ibm.sbt.automation.core.test.BaseAuthJavaServiceTest;

/**
 * @author Vimal Dhupar
 * @date Nov 15, 2013
 */
public class UploadFile extends BaseAuthJavaServiceTest {

    @Test
    public void runTest() {
        boolean result = checkNoError("Social_Files_Upload_File");
        assertTrue(getNoErrorMsg(), result);
    }

}

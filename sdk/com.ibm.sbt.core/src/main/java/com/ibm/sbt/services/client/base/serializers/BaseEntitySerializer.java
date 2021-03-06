/*
 * © Copyright IBM Corp. 2013
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

package com.ibm.sbt.services.client.base.serializers;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.ibm.sbt.services.client.base.BaseEntity;

/**
 * @author Mario Duarte
 *
 */
public class BaseEntitySerializer<T extends BaseEntity> extends XmlSerializer {
	protected T entity;
	
	public BaseEntitySerializer(T entity) {
		super();
		if(entity == null) throw new NullPointerException("The entity may not be null");
		this.entity = entity;
	}
	
	public static class DateSerializer {
		
		private static SimpleDateFormat dateFormat = 
				new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");	

		public static String toString(Date date) {
			return toString(dateFormat, date);
		}
		
		public static String toString(DateFormat dateFormat, Date date) {
			if(date == null) return null;
			else return dateFormat.format(date);
		}
		
		public static Date valueOf(String date) {
			return valueOf(dateFormat, date);
		}

		public static Date valueOf(DateFormat dateFormat, String date) {
			try {
				return dateFormat.parse(date);
			} catch (ParseException e) {
				// FIXME log exception
				return null;
			}
		}
	}
}

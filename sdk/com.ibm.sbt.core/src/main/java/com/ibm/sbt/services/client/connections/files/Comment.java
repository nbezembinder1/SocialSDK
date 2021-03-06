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
package com.ibm.sbt.services.client.connections.files;

import static com.ibm.sbt.services.client.base.ConnectionsConstants.nameSpaceCtx;
import java.util.Date;
import org.w3c.dom.Node;
import com.ibm.commons.util.StringUtil;
import com.ibm.commons.xml.NamespaceContext;
import com.ibm.commons.xml.xpath.XPathExpression;
import com.ibm.sbt.services.client.base.AtomEntity;
import com.ibm.sbt.services.client.base.AtomXPath;
import com.ibm.sbt.services.client.base.BaseService;
import com.ibm.sbt.services.client.base.datahandlers.XmlDataHandler;
import com.ibm.sbt.services.client.connections.common.Person;
import com.ibm.sbt.services.client.connections.files.model.FileEntryXPath;

/**
 * Comment Entry Class - representing a Comment Entry of the File.
 * 
 * @author Vimal Dhupar
 */
public class Comment extends AtomEntity {
	private String	commentId;
	private String	comment;
	private Person	authorEntry;
	private Person	modifierEntry;
	
	public Comment() {
	}

	public Comment(String id) {
		this.commentId = id;
	}
	
	public Comment(FileService svc, XmlDataHandler dh) {
        super(svc, dh);
        if (dh!=null) {
        authorEntry = new Person(getService(), new XmlDataHandler((Node)this.getDataHandler().getData(), 
        		nameSpaceCtx, (XPathExpression)AtomXPath.author.getPath()));
        modifierEntry = new Person(getService(), new XmlDataHandler((Node)this.getDataHandler().getData(), 
        		nameSpaceCtx, (XPathExpression)AtomXPath.modifier.getPath()));
        }
    }

    /**
     * 
     * @param service
     * @param node
     * @param namespaceCtx
     * @param xpathExpression
     */
	public Comment(BaseService service, Node node, NamespaceContext namespaceCtx, 
			XPathExpression xpathExpression) {
		super(service, node, namespaceCtx, xpathExpression);
	}
	
	@Override
	public void setId(String id) {
	    //comment comes with uuid but parent entity wants urn qualified id
	    super.setId((id==null || id.startsWith("urn:lsid:ibm.com:td:"))? id : "urn:lsid:ibm.com:td:"+id);
	}
	public String getCommentId() {
		if (!StringUtil.isEmpty(commentId)) {
			return commentId;
		}
		if (getAsString(FileEntryXPath.Category) != null && !getAsString(FileEntryXPath.Category).equals("comment")) {
			return null;
		}
		return getAsString(FileEntryXPath.Uuid);
	}
	
	public String getComment() {
		if (!StringUtil.isEmpty(comment)) {
			return comment;
		}
		return getContent();
	}
	
	
	public String getTitle() {
		return this.getAsString(FileEntryXPath.Title);
	}
	
	public Date getCreated() {
		return this.getAsDate(FileEntryXPath.Created);
	}

	public Date getModified() {
		return this.getAsDate(FileEntryXPath.Modified);
	}

	public String getVersionLabel() {
		return this.getAsString(FileEntryXPath.VersionLabel);
	}

	public Date getUpdated() {
		return this.getAsDate(FileEntryXPath.Updated);
	}

	public Date getPublished() {
		return this.getAsDate(FileEntryXPath.Published);
	}

	public Person getModifier() {
		return modifierEntry;
	}

	public Person getAuthor() {
		return authorEntry;
	}

	public String getLanguage() {
		return this.getAsString(FileEntryXPath.Language);
	}
	
	public boolean getDeleteWithRecord() {
		return this.getAsBoolean(FileEntryXPath.DeleteWithRecord);
	}
	
	public Comment getCommentEntry() {
		setComment(getComment());
		setCommentId(getCommentId());
		return this;
	}
	
	private void setCommentId(String commentId) {
		this.commentId = commentId;
	}

	private void setComment(String comment) {
		this.comment = comment;
		setContent(comment);
	}
	
	
}

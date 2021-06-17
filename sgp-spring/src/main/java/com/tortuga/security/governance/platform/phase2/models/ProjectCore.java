package com.tortuga.security.governance.platform.phase2.models;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import com.tortuga.security.governance.platform.phase2.models.helper.DesignFile;
import com.tortuga.security.governance.platform.phase2.models.helper.RuleFileset;
import com.tortuga.security.governance.platform.phase2.models.helper.SecurityRuleSet;

@Document(collection = "projectCore")
public class ProjectCore {
	
		@Id
		private String _id;
	  	private String checksum;
	  	
	  	@Field("design")
	  	private List<DesignFile> designFiles;
	  	
	  	private String lastModified;
	    private String projectName;
	    
	    @Field("securityRules")
	    private List<SecurityRuleSet> securityRules;
	    private List<RuleFileset> ruleFileset;
	    
	    
	    
	    
	    
		public String get_id() {
			return _id;
		}
		public void set_id(String _id) {
			this._id = _id;
		}
		public String getChecksum() {
			return checksum;
		}
		public void setChecksum(String checksum) {
			this.checksum = checksum;
		}
		public String getProjectName() {
			return projectName;
		}
		public void setProjectName(String projectName) {
			this.projectName = projectName;
		}

		public String getLastModified() {
			return lastModified;
		}
		public void setLastModified(String lastModified) {
			this.lastModified = lastModified;
		}
		public List<RuleFileset> getRuleFileset() {
			return ruleFileset;
		}
		public void setRuleFileset(List<RuleFileset> ruleFileset) {
			this.ruleFileset = ruleFileset;
		}
		public List<DesignFile> getDesignFiles() {
			return designFiles;
		}
		public void setDesignFiles(List<DesignFile> designFiles) {
			this.designFiles = designFiles;
		}
		public List<SecurityRuleSet> getSecurityRules() {
			return securityRules;
		}
		public void setSecurityRules(List<SecurityRuleSet> securityRules) {
			this.securityRules = securityRules;
		}
	    
}

package com.lcpan.dao;

import java.util.List;

public interface AjaxDAO {
	public String getOnSiteMemberCount();
	public String getOnSiteMembers();
	public String scanNewRFID();
	public String pay();
	public List<String> getSalesRecordTotalPrice();
}

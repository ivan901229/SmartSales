package com.lcpan.dao;


import java.util.List;

import com.lcpan.bean.AnalysisBean;

public interface AnalysisDAO {
	public List<AnalysisBean> flowOfCustomer();
	public List<AnalysisBean> calProductSum();
}

package com.ldasset.common;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize.Inclusion;

import java.util.List;
import java.util.Map;

@JsonSerialize(
	 include = Inclusion.NON_NULL
)
public class RestfulResult {
	public static final String NO_DATA_FOUND = "找不到数据";
	

    private List<Map<String, Object>> objects;
    private Map<String, Object> summaryData;
    private Long totalCount = null;
    private boolean success = false;
    private String msg;
    private Map<String, Object> object;
    private Object data;
    private Object resultSet;

    public RestfulResult() {
        this.success = false;
        this.msg = "";
    }
    public List<Map<String, Object>> getObjects() {
        return this.objects;
    }

    public void setObjects(List<Map<String, Object>> objects) {
        this.objects = objects;
    }

    public Long getTotalCount() {
        return this.totalCount;
    }

    public void setTotalCount(long totalCount) {
        this.totalCount = Long.valueOf(totalCount);
    }

    public void setObject(Map<String, Object> object) {
        this.object = object;
    }

    public Map<String, Object> getObject() {
        return this.object;
    }

    public Object getData() {
        return this.data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public Map<String, Object> getSummaryData() {
        return this.summaryData;
    }

    public void setSummaryData(Map<String, Object> summaryData) {
        this.summaryData = summaryData;
    }

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public void setTotalCount(Long totalCount) {
		this.totalCount = totalCount;
	}

    public Object getResultSet() {
        return resultSet;
    }

    public void setResultSet(Object resultSet) {
        this.resultSet = resultSet;
    }
}

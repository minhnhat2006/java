package com.sharpinu.web.bean.admin;

public class OverviewStatisticBean {
	private String name;
	private Integer total;
	private Integer last7Days;
	private Integer last30Days;
	private Integer type;

	public OverviewStatisticBean() {
		
	}

	public OverviewStatisticBean(String name, Integer total, Integer last7Days, Integer last30Days, Integer type) {
		this.name = name;
		this.total = total;
		this.last7Days = last7Days;
		this.last30Days = last30Days;
		this.type = type;
	}


	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getTotal() {
		return total;
	}

	public void setTotal(Integer total) {
		this.total = total;
	}

	public Integer getLast7Days() {
		return last7Days;
	}

	public void setLast7Days(Integer last7Days) {
		this.last7Days = last7Days;
	}

	public Integer getLast30Days() {
		return last30Days;
	}

	public void setLast30Days(Integer last30Days) {
		this.last30Days = last30Days;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

}

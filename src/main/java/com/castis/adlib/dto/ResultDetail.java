package com.castis.adlib.dto;

import lombok.Data;

@Data
public class ResultDetail {

	int resultCode;

	String name;

	String description;

	public ResultDetail() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ResultDetail(String name, String description) {
		super();
		this.name = name;
		this.description = description;
	}

	public ResultDetail(int resultCode, String name, String description) {
		super();
		this.resultCode = resultCode;
		this.name = name;
		this.description = description;
	}


	public ResultDetail(int resultCode) {
		super();

		this.resultCode = resultCode;
	}

	public ResultDetail(int resultCode, String description) {
		this.resultCode = resultCode;
		this.description = description;
	}
}

package com.practice.model;

public enum IssuerName {
	Melli("101010"),
	QarzulHosnehMehr("101011"),
	Sepeh("101012"),
	Parsian("101013"),
	QarzulHosnaResalat("101014"),
	Sina("101015"),
	Karafarin("101016"),
	Saman("101017"),
	Maskan("101018"),
	Shahr("101019"),
	Ayandeh("101020"),
	Post("101021"),
	Daay("101022"),
	Saderat("101023"),
	Mellat("101024"),
	Tejarat("101025"),
	Refah("101026"),
	Hekmat("101027"),
	Tourism("101028"),
	Qavamin("101029"),
	Ansar("101030"),
	Pasargad("101031");
	
	private final String issuerCode;
	
	private IssuerName(String issuerCode){
		this.issuerCode = issuerCode;
	}
	
	public String getIssuerCode(){
		return this.issuerCode;
	}
}

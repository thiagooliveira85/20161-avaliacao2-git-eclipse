package br.com.avaliacao.util;

public enum TagsHTML {
	
	HTML("<HTML>"),
	_HTML("</HTML>"),
	HEAD("<HEAD>"),
	_HEAD("</HEAD>"),
	BODY("<BODY><CENTER>"),
	_BODY("</CENTER></BODY>"),
	H2("<H2>"),
	_H2("</H2>"),
	CENTER("<CENTER>"),
	_CENTER("</CENTER>"),
	BR("<BR>"),
	TABLE("<TABLE BORDER='1' cellpadding='10'>"),
	_TABLE("</TABLE>");
	
	private String tag;

	TagsHTML(String tag){
		this.setTag(tag);
	}

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}
	
	@Override
	public String toString() {
		return getTag();
	}

}

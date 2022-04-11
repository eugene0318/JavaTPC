package kr.inflearn;

public class ExcelVO {
	private String title;
	private String author;
	private String company;
	private String isbn;
	private String imgurl;
	
	public ExcelVO() {
		super();
	}

	public ExcelVO(String title, String author, String company, String isbn, String imgurl) {
		super();
		this.title = title;
		this.author = author;
		this.company = company;
		this.isbn = isbn;
		this.imgurl = imgurl;
	}

	@Override
	public String toString() {
		return "ExcelVO [title=" + title + ", author=" + author + ", company=" + company + ", isbn=" + isbn
				+ ", imgurl=" + imgurl + "]";
	}
	
	
}

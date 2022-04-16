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

	public ExcelVO(String title, String author, String company) {
		super();
		this.title = title;
		this.author = author;
		this.company = company;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public String getImgurl() {
		return imgurl;
	}

	public void setImgurl(String imgurl) {
		this.imgurl = imgurl;
	}

	
	
}

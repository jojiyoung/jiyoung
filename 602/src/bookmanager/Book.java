package bookmanager;
import java.io.Serializable;

public class Book implements Serializable {// 시리얼(직렬)<-->pararell(병렬)시킬 수 있는
											// 인터페이스가 있다는 의미.
	private String isbn;// 직렬 병렬은 어딘가에 전송시킬 때 필요하다. 직렬은 그냥 일렬로 줄 세울 수 있다는 의미.
	private String title;
	private String author;
	private String price;
	private String year;
	private String publisher;
	private String rate; // 5점만점 (인기도)

	public Book(String isbn, String title, String author, String price, String year,
			String publisher, String rate) {// 데이터클래스라 메인 없이 그냥 생성자
		this.isbn = isbn;
		this.title = title;
		this.author = author;
		this.price = price;
		this.year = year;
		this.publisher = publisher;
		this.rate = rate;
	}

	public Object[] getAll(){
		return new Object[]{isbn,title,author,price,year,publisher,rate};
		
		
	}
	
	
	public String getYear() {
		return year;
	}

	public void setYear(String a) {
		this.year = a;
	}

	public String getPublisher() {
		return publisher;
	}

	public void setPublisher(String a) {
		this.publisher = a;
	}

	public String getRate() {
		return this.rate;
	}

	public void setRate(String a) {
		this.rate = a;
	}

	public String getAuthor() {
		return author;
	}

	public String getIsbn() {
		return isbn;
	}

	public String getPrice() {
		return price;
	}

	public String getTitle() {
		return title;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String toString() {// 오브젝트의 메소드 toString이므로 오버라이드 가능
		return getIsbn() + "," + getTitle() + "," + getAuthor() + ","
				+ getPrice()+",";
	}

}

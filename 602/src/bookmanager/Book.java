package bookmanager;
import java.io.Serializable;

public class Book implements Serializable {// �ø���(����)<-->pararell(����)��ų �� �ִ�
											// �������̽��� �ִٴ� �ǹ�.
	private String isbn;// ���� ������ ��򰡿� ���۽�ų �� �ʿ��ϴ�. ������ �׳� �Ϸķ� �� ���� �� �ִٴ� �ǹ�.
	private String title;
	private String author;
	private String price;
	private String year;
	private String publisher;
	private String rate; // 5������ (�α⵵)

	public Book(String isbn, String title, String author, String price, String year,
			String publisher, String rate) {// ������Ŭ������ ���� ���� �׳� ������
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

	public String toString() {// ������Ʈ�� �޼ҵ� toString�̹Ƿ� �������̵� ����
		return getIsbn() + "," + getTitle() + "," + getAuthor() + ","
				+ getPrice()+",";
	}

}

package Objects;

public class Comments {
	long id, id_baiviet, id_taikhoan;
	String ngayDang, noidung, ngaySua;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getId_baiviet() {
		return id_baiviet;
	}

	public void setId_baiviet(long id_baiviet) {
		this.id_baiviet = id_baiviet;
	}

	public long getId_taikhoan() {
		return id_taikhoan;
	}

	public void setId_taikhoan(long id_taikhoan) {
		this.id_taikhoan = id_taikhoan;
	}

	public String getNgayDang() {
		return ngayDang;
	}

	public void setNgayDang(String ngayDang) {
		this.ngayDang = ngayDang;
	}

	public String getNgaySua() {
		return ngaySua;
	}

	public void setNgaySua(String ngaySua) {
		this.ngaySua = ngaySua;
	}

	public String getNoidung() {
		return noidung;
	}

	public void setNoidung(String noidung) {
		this.noidung = noidung;
	}

	public Comments() {

	}
	
	public Comments(long id, long id_baiviet, long id_taikhoan, String ngayDang, String noidung, String ngaySua) {
		super();
		this.id = id;
		this.id_baiviet = id_baiviet;
		this.id_taikhoan = id_taikhoan;
		this.ngayDang = ngayDang;
		this.noidung = noidung;
		this.ngaySua = ngaySua;
	}
}

package Objects;

public class Comments {
	long id, id_baiviet, id_taikhoan;
	String created_at, noidung, updated_at;

	public String getUpdated_at() {
		return updated_at;
	}

	public void setUpdated_at(String updated_at) {
		this.updated_at = updated_at;
	}

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

	public String getCreated_at() {
		return created_at;
	}

	public void setCreated_at(String created_at) {
		this.created_at = created_at;
	}

	public String getNoidung() {
		return noidung;
	}

	public void setNoidung(String noidung) {
		this.noidung = noidung;
	}

	public Comments(long id, long id_baiviet, long id_taikhoan, String created_at, String noidung, String updated_at) {
		super();
		this.id = id;
		this.id_baiviet = id_baiviet;
		this.id_taikhoan = id_taikhoan;
		this.created_at = created_at;
		this.noidung = noidung;
		this.updated_at = updated_at;
	}

	public Comments() {

	}
}

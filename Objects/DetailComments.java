package Objects;

public class DetailComments {
	long id, id_binhluan, id_taikhoan;
	String created_at, noidung;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getId_binhluan() {
		return id_binhluan;
	}

	public void setId_binhluan(long id_binhluan) {
		this.id_binhluan = id_binhluan;
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

	public DetailComments(long id, long id_binhluan, long id_taikhoan, String created_at, String noidung) {
		super();
		this.id = id;
		this.id_binhluan = id_binhluan;
		this.id_taikhoan = id_taikhoan;
		this.created_at = created_at;
		this.noidung = noidung;
	}

	public DetailComments() {

	}
}

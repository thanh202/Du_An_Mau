package thanhlvph23422.fpoly.mob2041_thanhlvph23422.model;

public class LoaiSach {
    private  int id;
    private String tenloai;

    public LoaiSach(int id, String tenloai) {
        this.id = id;
        this.tenloai = tenloai;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTenloai() {
        return tenloai;
    }

    public void setTenloai(String tenloai) {
        this.tenloai = tenloai;
    }
}

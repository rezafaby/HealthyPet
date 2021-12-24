package id.reza.healthypet;


public class Model {
    String id, nmpemilik, nmpeliharaan, telepon, jenis_kelamin, jenis_perawatan, umur, textumur, is_valid;

    public String getIs_valid() {
        return is_valid;
    }

    public void setIs_valid(String is_valid) {
        this.is_valid = is_valid;
    }

    public String getTextumur() {
        return textumur;
    }

    public void setTextumur(String textumur) {
        this.textumur = textumur;
    }

    public Model(String id, String nmpemilik, String nmpeliharaan, String telepon, String jenis_kelamin, String jenis_rawat, String umur, String textumur, String is_valid){
        this.id = id;
        this.nmpemilik = nmpemilik;
        this.nmpeliharaan = nmpeliharaan;
        this.telepon = telepon;
        this.jenis_kelamin = jenis_kelamin;
        this.jenis_perawatan = jenis_rawat;
        this.umur = umur;
        this.textumur = textumur;
        this.is_valid = is_valid;
    }
    public String getId() {
        return id;
    }

    public void setId(String id) { this.id = id; }

    public String getNmpemilik() {
        return nmpemilik;
    }

    public void setNmpemilik(String nmpemilik) { this.nmpemilik = nmpemilik; }

    public String getNmpeliharaan() {
        return nmpeliharaan;
    }

    public void setNmpeliharaan(String nmpeliharaan) {
        this.nmpeliharaan = nmpeliharaan;
    }

    public String getTelepon() {
        return telepon;
    }

    public void setTelepon(String telepon) {
        this.telepon = telepon;
    }

    public String getJenis_kelamin() {
        return jenis_kelamin;
    }

    public void setJenis_kelamin(String jenis_kelamin) {
        this.jenis_kelamin = jenis_kelamin;
    }

    public String getJenis_perawatan() { return jenis_perawatan; }

    public void setJenis_perawatan(String jenis_perawatan) { this.jenis_perawatan = jenis_perawatan; }

    public String getUmur() { return umur; }

    public void setUmur(String umur) {
        this.umur = umur;
    }
}

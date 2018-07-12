package woo.sopt22.meowbox.View.MeowBoxDetail;

public class DetailModel {
    private String text;
    private int imgUrl;

    public DetailModel(String text, int imgUrl) {
        this.text = text;
        this.imgUrl = imgUrl;
    }

    public void setImgUrl(int imgUrl) {
        this.imgUrl = imgUrl;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getImgUrl() {
        return imgUrl;
    }

    public String getText() {
        return text;
    }
}

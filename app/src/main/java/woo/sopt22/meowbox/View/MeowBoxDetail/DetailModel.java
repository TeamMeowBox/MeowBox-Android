package woo.sopt22.meowbox.View.MeowBoxDetail;

public class DetailModel {
    private String text;
    private String imgUrl;

    public DetailModel(String text, String imgUrl) {
        this.text = text;
        this.imgUrl = imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public String getText() {
        return text;
    }
}

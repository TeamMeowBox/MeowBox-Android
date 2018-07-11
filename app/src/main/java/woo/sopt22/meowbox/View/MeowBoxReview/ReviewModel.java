package woo.sopt22.meowbox.View.MeowBoxReview;

public class ReviewModel {

    private String imgUrl;
    private String hashText;
    private String hashId;


    public ReviewModel(String imgUrl, String hashText, String hashId) {
        this.imgUrl = imgUrl;
        this.hashText = hashText;
        this.hashId = hashId;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public void setHashText(String hashText) {
        this.hashText = hashText;
    }
    public void setHashId(String hashId) {
        this.hashId = hashId;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public String getHashText() {
        return hashText;
    }
    public String getHashId() {
        return hashId;
    }
}

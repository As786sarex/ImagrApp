package myfab.wildcardenter.com.imager;

public class ItemViewModel {
    private String profile_img_url;
    private String user_name;
    private String img_url;
    private int mlike;
    private int mViews;
    private int mDownloads;
    private String large_img_url;

    public ItemViewModel(String profile_img_url, String user_name, String img_url,
                         int mlike, int mViews, int mDownloads, String large_img_url) {
        this.profile_img_url = profile_img_url;
        this.user_name = user_name;
        this.img_url = img_url;
        this.mlike = mlike;
        this.mViews = mViews;
        this.mDownloads = mDownloads;
        this.large_img_url = large_img_url;
    }

    public String getProfile_img_url() {
        return profile_img_url;
    }

    public void setProfile_img_url(String profile_img_url) {
        this.profile_img_url = profile_img_url;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getImg_url() {
        return img_url;
    }

    public void setImg_url(String img_url) {
        this.img_url = img_url;
    }

    public int getMlike() {
        return mlike;
    }

    public void setMlike(int mlike) {
        this.mlike = mlike;
    }

    public int getmViews() {
        return mViews;
    }

    public void setmViews(int mViews) {
        this.mViews = mViews;
    }

    public int getmDownloads() {
        return mDownloads;
    }

    public void setmDownloads(int mDownloads) {
        this.mDownloads = mDownloads;
    }

    public String getLarge_img_url() {
        return large_img_url;
    }

    public void setLarge_img_url(String large_img_url) {
        this.large_img_url = large_img_url;
    }
}


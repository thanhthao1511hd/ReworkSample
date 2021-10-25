package com.example.reworksample.model.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Result {
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("photos")
    @Expose
    private List<Photo> photos = null;
    @SerializedName("space_meta")
    @Expose
    private SpaceMeta spaceMeta;
    @SerializedName("rating")
    @Expose
    private Double rating;
    @SerializedName("review")
    @Expose
    private Integer review;
    @SerializedName("space_service_type_meta")
    @Expose
    private SpaceServiceTypeMeta spaceServiceTypeMeta;
    @SerializedName("point")
    @Expose
    private Integer point;
    @SerializedName("promotion")
    @Expose
    private Object promotion;
    @SerializedName("slug")
    @Expose
    private String slug;
    @SerializedName("is_sponsored")
    @Expose
    private Boolean isSponsored;
    @SerializedName("checkin")
    @Expose
    private Integer checkin;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Photo> getPhotos() {
        return photos;
    }

    public void setPhotos(List<Photo> photos) {
        this.photos = photos;
    }

    public SpaceMeta getSpaceMeta() {
        return spaceMeta;
    }

    public void setSpaceMeta(SpaceMeta spaceMeta) {
        this.spaceMeta = spaceMeta;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    public Integer getReview() {
        return review;
    }

    public void setReview(Integer review) {
        this.review = review;
    }

    public SpaceServiceTypeMeta getSpaceServiceTypeMeta() {
        return spaceServiceTypeMeta;
    }

    public void setSpaceServiceTypeMeta(SpaceServiceTypeMeta spaceServiceTypeMeta) {
        this.spaceServiceTypeMeta = spaceServiceTypeMeta;
    }

    public Integer getPoint() {
        return point;
    }

    public void setPoint(Integer point) {
        this.point = point;
    }

    public Object getPromotion() {
        return promotion;
    }

    public void setPromotion(Object promotion) {
        this.promotion = promotion;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public Boolean getIsSponsored() {
        return isSponsored;
    }

    public void setIsSponsored(Boolean isSponsored) {
        this.isSponsored = isSponsored;
    }

    public Integer getCheckin() {
        return checkin;
    }

    public void setCheckin(Integer checkin) {
        this.checkin = checkin;
    }
}

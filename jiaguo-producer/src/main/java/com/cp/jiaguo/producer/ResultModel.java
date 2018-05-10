package com.cp.jiaguo.producer;

import java.util.List;

/**
 * POJO for fastjson
 */
public class ResultModel {
    public ResultModel() {
    }

    public Product product;
    public boolean isUserFav;

    public static class Product {
        public Product() {
        }

        public int productId;
        public int price;
        public String title;
        public String[] description;
        public AddressInfo addressInfo;
        public List<ProductMediaInfo> productMediaInfoList;
        public int favCount;
        public String starRatingDesc;
    }

    public static class AddressInfo{
        public String cityName;
        public String districtName;
        public String fullAddress;
        public double latitude;
        public double longitude;
    }

    public static class ProductMediaInfo{
        public String categoryName;
        public boolean isCover;
        public String mediaUrl;
    }
}

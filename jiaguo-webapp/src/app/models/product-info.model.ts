export class ProductInfo {
    product: Product;
    isUserFav: boolean;
}

class Product {
    productId: number;
    price: number;
    title: string;
    description: string[];
    addressInfo: AddressInfo;
    productMediaInfoList: ProductMediaInfo[];
    avCount: number;
    starRatingDesc: string;
}

class AddressInfo {
    cityName: string;
    districtName: string;
    fullAddress: string;
    latitude: number;
    longitude: number;
}

class ProductMediaInfo {
    categoryName: string;
    isCover: boolean;
    mediaUrl: string;
}
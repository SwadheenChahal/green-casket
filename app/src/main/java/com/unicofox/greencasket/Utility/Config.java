package com.unicofox.greencasket.Utility;

import com.unicofox.greencasket.Model.ModelMain;

import java.util.ArrayList;
import java.util.List;

public class Config {

    public static final int TYPE_PRODUCT = 29, TYPE_LOADING = 59;
    public static boolean SHOW_PROGRESS_BAR = true, isUserLoggedIn = false;
    public static String APP_PREF = "JTXX11";


    public static final String
            FOR_GET = "1",
            FOR_SET = "2",
    REMOVE_CART_ITEM="7648",
    PLUSH_CART_ITEM="46546",
    MINUS_CART_ITEM="565",
    PLACE_CART_ORDERS ="3453",
    PLACE_ORDER="6556",
    COUNT_CART_ITEMS="5685",
    FOR_PRODUCTS="54354",
    FOR_SEARCH="4584";



    public static String USER_EMAIL = "";
    public static List<ModelMain> PRODUCTS_LIST = new ArrayList<>();

    public static String API_KEY = "https://greencasket.in/greencasket/",
            API_SIGNUP = API_KEY + "api/signup.php",
            API_LOGIN = API_KEY + "api/login.php",
            API_PRODUCTS = API_KEY + "api/products.php",
            API_RATING= API_KEY + "api/rating.php",
            API_CART= API_KEY + "api/cart.php",
            API_ORDERS= API_KEY + "api/order.php";
}

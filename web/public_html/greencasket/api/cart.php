<?
require_once __DIR__ . '../../dbs/myphpcon.php';
require_once __DIR__ . '../../utility/globals.php';
 
 $for=$_POST['for'];
 $email=$_POST['email'];
 if(isset($_POST['product_id'])){
      $id = $_POST['product_id'];
 }
 if($for==$count_cart_items){
     $query_count_items="select count(*) as count from cart where user_email='$email'";
     $row_count_items = mysqli_fetch_assoc(mysqli_query($dbhandle,$query_count_items));
     echo $row_count_items['count'];
 } else if($for==$get){
     $query_get_cart_items="select products.product_title, 
     products.product_image,products.product_current_price,
     products.product_real_price,products.product_id, 
     cart.cart_items from products inner join cart on 
     cart.product_id = products.product_id where user_email='$email'";
     if($result_get_cart_items=mysqli_query($dbhandle,$query_get_cart_items)){
        $json_response = array();
        while ($row_get_cart_item = mysqli_fetch_array($result_get_cart_items, MYSQLI_ASSOC)) {
            $rows_get_cart_item['CartData'][] = $row_get_cart_item;
        } echo json_encode($rows_get_cart_item);
         
     } 
 } else if ($for==$set){
    if((mysqli_num_rows(mysqli_query($dbhandle,"select * from `cart` where product_id='$id' and user_email='$email'")))?FALSE:TRUE){
     if(mysqli_query($dbhandle,"insert into `cart`(`product_id`, `user_email`) values ('$id','$email')")) 
     echo "Product Added In Cart";
     else echo "An Error Occurred";  
    } else echo "Product Already In Cart";
 } else if ($for==$remove_cart_item){
     if(mysqli_query($dbhandle,"delete from `cart` where `product_id`='$id' and `user_email`='$email'"))
     echo "Product Removed From Cart";
     else echo "An Error Occurred";
 }  else if ($for==$plush_cart_item){
     if(mysqli_query($dbhandle,"update `cart` set `cart_items`=cart.cart_items+1 where product_id='$id' and user_email='$email'"))
     echo "Product Quantity Increased";
     else echo "An Error Occurred";
 }  else if ($for==$minus_cart_item){
     if(mysqli_query($dbhandle,"update `cart` set `cart_items`=cart.cart_items-1 where product_id='$id' and user_email='$email'"))
     echo "Product Quantity Decreased";
     else echo "An Error Occurred";
 }  else if ($for==$place_cart_orders){
     
     if(mysqli_query($dbhandle,"insert into orders (product_id,user_email,total_items) select product_id,user_email,cart_items from cart where user_email='$email'"))
     {
       mysqli_query($dbhandle,"DELETE FROM `cart` WHERE `user_email`='$email'");
       echo "Order Placed Success";
     } else echo "An Error Occurred";

 }
 
 ?>
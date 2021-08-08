<?
require_once __DIR__ . '../../dbs/myphpcon.php';
require_once __DIR__ . '../../utility/globals.php';
 
 $for=$_POST['for'];
 if(isset($_POST['email']))$email = $_POST['email'];
 if(isset($_POST['product_id']))$product_id=$_POST['product_id'];
 
  if($for==$get){
     $query_get_orders="select products.product_title, 
     products.product_image,products.product_current_price,    
     orders.order_id,orders.order_status from products inner join orders on 
     orders.product_id = products.product_id  where user_email='$email'";
    
      if($result_get_order_items=mysqli_query($dbhandle,$query_get_orders)){
        $json_response = array();
        while ($row_get_order_item = mysqli_fetch_array($result_get_order_items, MYSQLI_ASSOC)) {
            $rows_get_order_item['OrdersData'][] = $row_get_order_item;
        } echo json_encode($rows_get_order_item);
         
     } 
 }
 else if($for==$place_order){
     if(mysqli_query($dbhandle,"INSERT INTO `orders`(`product_id`, `user_email`) VALUES ('$product_id','$email')"))
     {
       echo "Order Placed Success";
     } else echo "An Error Occurred";
 }
 
 
 ?>
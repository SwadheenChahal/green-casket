<?
require_once __DIR__ . '../../dbs/myphpcon.php';
require_once __DIR__ . '../../utility/globals.php';
 
 $for=$_POST['for'];
 $email=$_POST['email'];
 $id = $_POST['product_id'];
 
 if($for==$get){
      $get_ratings="select * from `ratings` where user_email= '$email' and product_id='$id'";
      $res_get_ratings=mysqli_query($dbhandle,$get_ratings);
      if(mysqli_num_rows($res_get_ratings)>0){
           $row_ratings=mysqli_fetch_assoc($res_get_ratings);
            echo $row_ratings['rating'];
      }else echo "404";
 }
 
 else  if($for==$set){
        $new_rating=$_POST['rating'];
        $is_already_exist="select * from `ratings` where user_email='$email' and product_id='$id'";
        $res_is_already_exist=mysqli_query($dbhandle,$is_already_exist);
        if(mysqli_num_rows($res_is_already_exist)>0){
        $query_finishing="update products 
        set product_ratings = product_ratings - 
        (select rating from ratings WHERE product_id='$id' 
        and user_email='$email') + 
        $new_rating where product_id='$id'";
    
        if(mysqli_query($dbhandle,$query_finishing)){
          $query_update="update `ratings` SET `rating`='$new_rating' where 1";
          if(mysqli_query($dbhandle,$query_update)) 
          echo "Rating Updated Success !";
          else echo "Something went wrong !"; 
         }else echo "Some Error Occurred";

        }
        else
        {
            $query_update_products="UPDATE `products` 
            SET `product_ratings`=product_ratings+
            $new_rating,`product_raters`=product_raters+1 WHERE product_id=$id";
            
            if(mysqli_query($dbhandle,$query_update_products))
            {
             $query_insert = "INSERT INTO `ratings` 
            (`product_id`, `user_email`, `rating`) 
            VALUES ('$id','$email','$new_rating')";
            if(mysqli_query($dbhandle,$query_insert))
            echo "Rating Success !";
             else echo "Something went wrong !"; 
            }
            
            else echo "Some Error Occurred";

        }
 }
 
 

?>
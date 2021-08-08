<?php
include "includes/navbar.php";
if(isset($_SESSION['username']))
  
{
?>
<!---Main content started------>

<div class="row">

 <div class="card center-align">
      <?php
if(isset($_SESSION['message']))
{
echo $_SESSION['message'];
unset($_SESSION['message']);
}
?>   
</div>

<div class="col l4 m6 s12">
<ul class="collection with-header">
<li class="collection-header green">
<div class="center">
<br />
<img src="admin_imgs/user.svg" height="70px" width="70px" alt="" class="circle">
<h5 class="white-text"><?php echo $_SESSION['username'];?></h5>
<span class="email white-text">
<?php
$user_id=$_SESSION['userid'];
$sql="select pub_email from pubs where pub_id='$user_id'";
$res=mysqli_query($dbhandle,$sql);
$row=mysqli_fetch_assoc($res);
echo $row['pub_email'];
?>
<br />
<br />
<hr />
<br />

  <div class="row ">
      <div class="col s6 valign-wrapper">
          
          Total Orders: 

<?
$row_total_orders = mysqli_fetch_assoc(mysqli_query($dbhandle,"select count(*) as 
count from products inner join orders on 
orders.product_id = 
products.product_id 
where product_publisher_id='$user_id'"));

echo $row_total_orders['count'];
?>

      </div>
      
      
      <div class="col s6 valign-wrapper">
          
          Completed Orders: 
<?
$row_completed_orders = mysqli_fetch_assoc(mysqli_query($dbhandle,"select count(*) as 
count from products inner join orders on 
orders.product_id = 
products.product_id 
where product_publisher_id='$user_id' and orders.order_status='Delivered'"));

echo $row_completed_orders['count'];
?>
          
      </div>
      
      
      
        <div class="col s6 valign-wrapper">
          
            Pending Orders: 
<?
$row_pending_orders = mysqli_fetch_assoc(mysqli_query($dbhandle,"select count(*) as 
count from products inner join orders on 
orders.product_id = 
products.product_id 
where product_publisher_id='$user_id' and orders.order_status!='Delivered'"));

echo $row_pending_orders['count'];
?>
            
        </div>
        
        
      <div class="col s6 valign-wrapper">
          
          Earmings: 
          
      <? $query_get_earnings="select product_current_price 
      from products inner join orders on 
      orders.product_id = 
      products.product_id 
      where product_publisher_id='$user_id' 
      and orders.order_status='Delivered'";
      $earning_res=mysqli_query($dbhandle,$query_get_earnings);
      
      if(mysqli_num_rows($earning_res)>0)
      {
          $total_earnings=0;
          while($row_earn=mysqli_fetch_assoc($earning_res)) 
          {
              $total_earnings+=$row_earn['product_current_price'];
  
          }
          echo "₹".$total_earnings;
     } else echo "₹0";
    ?>
          

      </div>
    </div>

<br />


</span>
</div>
</li>
</ul>
</div>



<div class="col l4 m6 s12">
<ul class="collection with-header">
<li class="collection-header orange">
<p class="white-text">Your Products</p>    


<?php

$user_id=$_SESSION['userid'];
$url="http://leafycasket.com/greencasket/";

$res_product=mysqli_query($dbhandle,"select product_id,product_title,product_image,product_current_price from products where product_publisher_id='$user_id'");


if(mysqli_num_rows($res_product)>0)
{
    while($row_product=mysqli_fetch_assoc($res_product)) 
{
?>


<div class="card">
        <div class="card-image">
          <img src="<?php echo $url.$row_product['product_image'];?>">
         

        </div>
        <div class="card-content">
             <span class="card-title"><?php echo $row_product['product_title'];?></span>
        
          <p> 
             <div class="chip">
  PRODUCT ID: PDC<?php echo $row_product['product_id'];?>

   </div>
   
          </p>
        </div>
        <div class="card-action">
         ₹ <?php echo $row_product['product_current_price'];?> 
         
          &nbsp; &nbsp;
         <a href="delete_product.php?id=<?php echo $row_product['product_id'];?>">DELETE</a>
         
     
        </div>
      </div>

<?
}
} 

?>




</li>
</ul>
</div>



<div class="col l4 m6 s12">
<ul class="collection with-header">
<li class="collection-header red">
<p class="white-text">Customers Orders</p>    
 

<?php

$user_id=$_SESSION['userid'];
$url="http://leafycasket.com/greencasket/";

   $query_get_orders="select products.product_title, 
     products.product_image,products.product_current_price,products.product_id,
     users.user_name,users.user_email,users.user_number,users.user_address,
     orders.order_id,orders.order_status from products inner join orders on 
     orders.product_id = products.product_id  
     INNER JOIN users on users.user_email=orders.user_email where product_publisher_id='$user_id'";

$res_order=mysqli_query($dbhandle,$query_get_orders);

if(mysqli_num_rows($res_order)>0)
{
    while($row_order=mysqli_fetch_assoc($res_order)) 
{
?>

  <div class="card">
    <div class="card-image waves-effect waves-block waves-light">
      <img class="activator" src="<?php echo $url.$row_order['product_image'];?>">
    </div>
    <div class="card-content">
      <span class="card-title activator grey-text text-darken-4"><?php echo $row_order['product_title'];?><i class="material-icons right">more_vert</i></span>
      <div class="chip">
   PRODUCT ID: PDC<?php echo $row_order['product_id'];?>
   </div>
      
       <div class="chip">
   ORDER ID: OCD<?php echo $row_order['order_id'];?>
   </div>
   
   <div class="chip">
   STATUS: <?php echo $row_order['order_status'];?>
   </div>
   
    </div>
    <div class="card-reveal">
      <span class="card-title grey-text text-darken-4">₹<?php echo $row_order['product_current_price'];?><i class="material-icons right">close</i></span>
    <hr />
      <p>
          
          Buyer Name : <?php echo $row_order['user_name'];?>
          <br />
          Buyer Email : <?php echo $row_order['user_email'];?>
          <br />
          Buyer Number : <?php echo $row_order['user_number'];?>
          <br />
          Buyer Address : <?php echo $row_order['user_address'];?>
          <br />
          <br />
          <br />
          <br />
          
          Order Status
          <hr />
          <?
          $arr = ["Processing", "Shipped", "On the Way","Delivery Tomorrow","Delivery Today","Delivered","Cancelled"];
          $order_id=$row_order['order_id'];
          foreach ($arr as $item){
              
          if($item==$row_order['order_status']) 
          echo "<div class='chip red white-text'> $item</div>";
          else echo "<a href='order_status.php?id=$order_id&status=$item'> <div class='chip black-text'> $item</div></a>";
          
          echo "&nbsp;";
          }
          ?>
          
          
      </p>
    </div>
  </div>
         
<?
}
} 

?>   

</li>
</ul>
</div>

</div>


<?php
include "includes/footer.php";
}
else
{
  $_SESSION['message']="<div class='chip red white-text'>Login To Continue</div>";
  header("Location: login.php");
}
?>
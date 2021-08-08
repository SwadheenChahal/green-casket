<?php
include "includes/navbar.php";
if(isset($_SESSION['username']))
  
{
?>
<!---Main content started------>

<div class="row">
    
  
<div class="col l12 m12 s12">
<div class="card white-grey">
    
<?php
if (isset($_POST['submit'])) 
{

$product_title=$_POST['product_title'];
$product_brand=$_POST['product_brand'];
$product_real_price=$_POST['product_real_price'];
$product_current_price=$_POST['product_current_price'];
$product_des=$_POST['product_des'];
$img=$_FILES['img'];
$user_id=$_SESSION['userid'];
$imgName=$img['name'];
 
       $insert_product="INSERT INTO `products`(`product_title`, `product_brand`, `product_image`, `product_publisher_id`, `product_current_price`,    `product_real_price`, `product_description`) VALUES ('$product_title','$product_brand','images/$imgName','$user_id'
        ,'$product_current_price','$product_real_price','$product_des')";
        
           
          if (mysqli_query($dbhandle, $insert_product)){ 
             move_uploaded_file($img["tmp_name"],"greencasket/images/".$imgName);
             echo "<div class='chip green white-text'>Upload Success !</div>";
           }else echo "<div class='chip red white-text'>Some Error !</div>";
 
    
}  ?>
           
    
    
<ul class="collection with-header">
<li class="collection-header red">
<h5 class="white-text">Add Product</h5>
</li>
  
  <div class="container" style="margin-top: 50px;margin-bottom: 50px; text-align: center;">
	<form method="POST" enctype="multipart/form-data">


    <div class="file-field input-field">
      <div class="btn red">
        <span>Product Image</span>
        <input 
        type="file"
        accept="image/*"
        class="materialize-file validate"
        name="img" 
        required
        >
      </div>
      
      <div class="file-path-wrapper">
        <input class="file-path validate" type="text">
      </div>
    </div>
    <br>
    <br>
    
     <input placeholder="Product Title" name="product_title" type="text" required>
    
    
     <br>
    <br>
    
     <input placeholder="Product Brand" name="product_brand" type="text" required>
    
     <br>
    <br>
    
    <input placeholder="Product Real Price" name="product_real_price" type="number" required>
   
     <br>
    <br>
    
    
    <input placeholder="Product Current Pice" name="product_current_price" type="number" required>
    
    
     <br>
    <br>
    
    
    <div class="input-field">
        <textarea
            class="materialize-textarea validate"
            name="product_des"
            placeholder="Enter Your Product Description Here..."
            required></textarea>
            <label for="product_des" data-error="Please Enter Title"></label>
    </div>


  <br>

<br>

<input type="submit" name="submit" id="btn" class="btn red" value="Upload">


</form>

 
</div>
  
</ul>
</div>
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
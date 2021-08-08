<?php
include "includes/navbar.php";
?>


 <div class="center-align">
     <br />
     <br />
      <?php
if(isset($_SESSION['message']))
{
echo $_SESSION['message'];
unset($_SESSION['message']);
}
?>   

   
 </div>   

<div style="margin:50px;">
    



<form action="change_password.php" method="POST" style="height: 500px">
<input type="password" name="password" placeholder="Change Password">
<input type="password" name="con_password" placeholder="Confirm Password">
<div class="center">
    <br />
<input type="submit" name="update" value="Change Password" class="btn green">
</div>

</form>



</div>

<?php
include "includes/footer.php";
?>

<?php
if(isset($_POST['update']))
{
  $password=$_POST['password'];
  $con_password=$_POST['con_password'];

if(strlen($password)>4){
    if($con_password===$password){
      $user_id=$_SESSION['userid'];
    $sql="update pubs set pub_password='$password' where pub_id='$user_id'";
    $res=mysqli_query($dbhandle,$sql);
if($res)
{
  $_SESSION['message']="<div class='chip green white-text'>Password Successfully Changed.</div>";
header("Location: change_password.php");
  }
  else
  {
    $_SESSION['message']="<div class='chip red white-text'>Sorry, Something went wrong, Please Try Again.</div>";
    header("Location: change_password.php");
  }
}
else{
  $_SESSION['message']="<div class='chip red white-text'>Password donot Match.</div>";
  header("Location: change_password.php");
}
} else{
    
    $_SESSION['message']="<div class='chip red white-text'>Invalid Password</div>";
    header("Location: change_password.php");
    
}

}
?>
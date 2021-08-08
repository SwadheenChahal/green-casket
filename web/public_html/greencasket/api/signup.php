<?
require_once __DIR__ . '../../dbs/myphpcon.php';

$username=$_POST['username'];
$email=$_POST['email'];
$number=$_POST['number'];
$address=$_POST['address'];
$password=$_POST['password'];


$is_user_email_already_exist="select user_email from users where user_email='$email'";
$user_email_res=mysqli_query($dbhandle,$is_user_email_already_exist);
if(mysqli_num_rows($user_email_res)>0)
{
  echo "Email Already Exist !";
 
}
else
{
     $signup_user="insert into users(user_email,user_name,user_number,user_address,user_password) values('$email','$username','$number','$address','$password')";
     
     $signup_res=mysqli_query($dbhandle,$signup_user);
     
     if($signup_res){
        echo "Signup Success !";
     }
     else{
         echo "Some Error Occurred !";
     }
}



?>
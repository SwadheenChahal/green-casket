<?
require_once __DIR__ . '../../dbs/myphpcon.php';

$email=$_POST['email'];
$password=$_POST['password'];

$sql="select * from users where user_email='$email' AND user_password='$password'";
$res=mysqli_query($dbhandle,$sql);
if(mysqli_num_rows($res)>0) echo "Login Success !";
else echo "Sorry, Credentials Don't Match";


?>
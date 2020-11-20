<?php
if(isset($_GET['delete_id']))
 {
  // select image from db to delete
  $stmt_select = $DB_con->prepare('SELECT userPic FROM picture WHERE ID =:id');
  $stmt_select->execute(array(':id'=>$_GET['delete_id']));
  $imgRow=$stmt_select->fetch(PDO::FETCH_ASSOC);
  unlink("user_images/".$imgRow['userPic']);
  
  // it will delete an actual record from db
  $stmt_delete = $DB_con->prepare('DELETE FROM picture WHERE ID =:id');
  $stmt_delete->bindParam(':id',$_GET['delete_id']);
  $stmt_delete->execute(); 
  
  header("Location: index.php");
 }
 
?>
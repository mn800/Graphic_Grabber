<?php
// Set the variables for the database access:
  require_once('connectvars.php');
  
$dbc = new mysqli(DB_HOST, DB_USER, DB_PASSWORD, DB_NAME);

$query = "DROP TABLE developer_jobs";
 
if ($dbc->query($query)) {
 	echo "The query was successfully executed. Table dropped!<br />";
} else {
 	echo "The query could not be executed!" .$dbc->error();
} 
mysqli_close($dbc);
?>
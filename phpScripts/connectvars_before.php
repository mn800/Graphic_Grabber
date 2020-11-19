<?php
require_once('connectvars.php');
$dbc = mysql_connect(DB_HOST,DB_USER,DB_PASSWORD);

$query ="CREATE DATABASE IF NOT EXISTS" .DB_NAME;

// Eexcute the query:
if(mysqli_query($dbc,$query)){
	echo "<p>The query,$query, was successfully executed!</p>";
}else{
	echo "<p>The query could not be executed! Error:" .mysqli_error($dbs)."</p>";
}mysqli_close($dbc);

// The last line fo code 
mysqli_close($dbc);  // closes the MySQL link

	$dbc = mysqli_connect(DB_HOST, DB_USER, DB_PASSWORD, DB_NAME);
	
//Example of a CREATE TABLE query:
$query = "CREATE TABLE IF NOT EXISTS Movies (id INT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY, FirstName TEXT,LastName TEXT, EmailAddress TEXT, Comments TEXT)";

if (mysqli_query ($dbc, $query)) {
echo "The query was successfully executed!<br />";
 } else {
 echo "The query could not be executed!" . mysqli_error($dbc);
 }
 mysqli_close($dbc);
 ?>
 
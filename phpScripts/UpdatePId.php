<?php 
$myfile = fopen("PIDTracker.txt", "w");
$PId = $_POST['PId'];
fwrite($myfile,$PId);
fclose($myfile);
?>
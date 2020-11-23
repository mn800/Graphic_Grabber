<?php 
// Connects to file that contains the current highest PId
$myfile = fopen("PIDTracker.txt", "r");
printf("%s", fread($myfile,filesize("PIDTracker.txt")));
fclose($myfile);
?>